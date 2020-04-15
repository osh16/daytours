import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class PassengerController {
    Connect c = new Connect();

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

    public Passenger getPassengerById(int id) {
	String query = "select * from passenger where id = " + id;
	return getPassenger(query);
    }

    public Passenger getPassenger(String query) {
	ResultSet rs = null;
	Passenger passenger = null;	
	try {
	    c.connect();
	    rs = c.retrieve(query);
	    if (rs != null) {
		while (rs.next()) {
		    passenger = new Passenger(
			rs.getInt(1),
			rs.getString(2),
			rs.getString(3),
			rs.getInt(4),
			rs.getString(5),
			rs.getInt(6)
		    );
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	c.close();
	return passenger;
    }

    public Passenger[] getAllPassengers() {
	ResultSet rs = null;
	String query = "select * from passenger";
	int count = getCount(query);
	Passenger[] passengers = new Passenger[count];

	try {
	    int i = 0;
	    c.connect();
	    rs = c.retrieve(query);

	    if (rs != null) {
		while (rs.next()) {
		    passengers[i] = new Passenger(
			rs.getInt(1),
			rs.getString(2),
			rs.getString(3),
			rs.getInt(4),
			rs.getString(5),
			rs.getInt(6)
		    );
		    i++;
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return passengers;
    }

    public void printAllPassengers() {
	Passenger[] passengers = getAllPassengers();
	for (int i = 0; i < passengers.length; i++) {
	    printPassenger(passengers[i]);
	}
    }

    public void printPassenger(Passenger passenger) {
	if (passenger != null) {
	    String result = String.format("id: %d\nNafn: %s\nKennitala: %s\n", passenger.getId(), passenger.getName(), passenger.getSsn());
	    System.out.println(result);
	}
    }

}
