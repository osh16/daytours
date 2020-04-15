import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookingController {
    Connect c = new Connect();
    TourController tc = new TourController();
    PassengerController pc = new PassengerController();

    public int getLatestId() {
    	String query = "select * from booking where id = (select max(id) from booking)";
    	Booking booking = getBooking(query);
    	return booking.getId();
    }

    public int getCount(String query) {
	ResultSet rs = null;
	int count = 0;
	try {
	    c.connect();
	    rs = c.retrieve(query);
	    if (rs != null) {
		while (rs.next()) {
		    count++; 
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return count;
    }

    public Booking getBookingById(int id){
        String query = "select * from booking where id = " + id;
        return getBooking(query);
    }
    
    public Booking getBookingByName(String name){
        String query ="select * from booking where name = " + name;
        return getBooking(query);
    }
    
     public Booking getBooking(String query) {
        Booking booking = null;
        ResultSet rs = null;
            try {
                c.connect();
                rs = c.retrieve(query);

                if (rs != null) {
                    while (rs.next()) {
                        booking = new Booking(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getInt(5)
  
                        );
                    }
                }
            } catch (Exception e) {
                    e.printStackTrace();
        }

        c.close(); // loka tengingu
        return booking;
    }

    public Booking[] getAllBookings() {
        ResultSet rs = null;
	String query = "select * from booking";
	int count = getCount(query);
	Booking[] bookings = new Booking[count];
	int i = 0;

	try {
	    c.connect();
	    rs = c.retrieve(query);

	    if (rs != null) {
		while (rs.next()) {
		    bookings[i] = new Booking(
			rs.getInt(1),
			rs.getString(2),
			rs.getString(3),
                       
			rs.getInt(4),
			rs.getInt(5)
                        
		    );
		    i++;
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

        c.close(); // loka tengingu
        return bookings;
    }
    
    public void addBooking(Booking booking){
        try{
            c.connect();
            PreparedStatement p = c.conn.prepareStatement("insert into booking values(?,?,?,?,?)");
            p.setInt(1, booking.getId());
	    p.setString(2, booking.getPayment());
            p.setString(3, booking.getDate()); 
            p.setInt(4, booking.getPassengerId());
            p.setInt(5, booking.getTourId());
            p.executeUpdate();
        } catch(Exception e){
	    System.out.println("Ekki tókst að bæta við bókun");
            e.printStackTrace();
        }
        c.close();
        System.out.println("Bókun hefur verið bætt við.");
    }

    public void updateBooking(Booking booking, String newPayment, String newDate, int newPassenger, int newTour){
	String query = "update booking set payment = ?, set booking_date = ?, set passenger_id = ?, set tour_id = ?, where id = " + booking.getId();
        try{
            c.connect();
            PreparedStatement pst = c.conn.prepareStatement(query);

	    // ef thad eru engar breytingar, holdum vid thvi eins
	    if (newPayment == null) {
		pst.setString(1, booking.getPayment());
	    }  else {
		pst.setString(1, newPayment);
	    }

	    if (newDate == null) {
		pst.setString(2, booking.getDate());
	    } else {
		pst.setString(2, newDate);
	    }

	    if (newPassenger == 0) {
		pst.setInt(3, booking.getPassengerId());
	    } else {
		pst.setInt(3, newPassenger);
	    }

	    if (newTour == 0) {
		pst.setInt(4, booking.getTourId());
	    } else {
		pst.setInt(4, newTour);
	    }

            pst.executeUpdate();
        }catch(Exception e){
	    System.out.println("Ekki tókst að uppfæra bókun");
            e.printStackTrace();
        }
    }
    
    public void deleteBooking (Booking booking) {
	deleteBooking(booking.getId());
    }

    public void deleteBooking(int id){
        try{
            c.connect();
            String query ="delete from booking where id = "+id;
            PreparedStatement pst = c.conn.prepareStatement(query);
            pst.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("Bókun " + id + " hefur verið eytt.");
    }
    
    public void printAllBookings() {
	Booking[] bookings = getAllBookings();
	for (int i = 0; i < bookings.length; i++) {
	    printBooking(bookings[i]);
	}
    }
    
    public void printBooking(Booking booking) {
        if (booking != null){
	    String passengerName = pc.getPassengerById(booking.getPassengerId()).getName();
	    String tourName = tc.getTourById(booking.getTourId()).getName();
	    String result = String.format("Bókunarnr %d\nNafn: %s\nTour: %s\nDagsetning: %s\nGreiðsluleið: %s\n", booking.getId(), passengerName, tourName, booking.getDate(), booking.getPayment());
	    System.out.println(result);
        }
    }
    
    public static void main(String [] args){
        BookingController bc = new BookingController();
        
        Booking booking = new Booking(bc.getLatestId()+1,"kreditkort","2020-04-01",5,2);

        bc.deleteBooking(10);
        
        // bc.addBooking(booking);
        bc.printAllBookings();
        
    }
}
