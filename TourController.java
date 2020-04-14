import java.sql.ResultSet; 
import java.sql.PreparedStatement;

public class TourController {
    Connect c = new Connect();

    public int getLatestId() {
    	String query = "select * from tours where id = (select max(id) from tours)";
    	Tour tour = getTour(query);
    	return tour.getId();
    }

    public Tour getTourById(int id) {
		String query = "select * from tours where id = " + String.valueOf(id); 
		return getTour(query);
    }

    public Tour getTourByName(String name) {
		String query = "select * from tours where name = " + name;
		return getTour(query);
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

    public Tour getCheapTours(double max) {
    	ResultSet rs = null;
    	String query = "select * from tours where price <= " + max;
    	// String count = "select count(*) from tours where price <= " + max;
    	// int toursLength = Integer.parseInt(count);
    	int toursLength = getCount(query);
    	Tour[] tours = new Tour[toursLength];
    	int i = 0;

    	try {
		    c.connect();
		    rs = c.retrieve(query);

	    try {
	    	c.connect();
	    	rs = c.retrieve(query);

	    	if (rs != null) {
	    		while (rs.next()) {
	    			tours[i] = new Tour(
	    				rs.getInt(1),
	    				rs.getString(2),
	    				rs.getString(3),
	    				rs.getDouble(4),
	    				rs.getString(5),
	    				rs.getString(6)
	    			);
	    			i++;
	    		}
	    	}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }

    	return tours;
    }

    public static void print(Tour tour) {
    	if (tour != null) {
    		System.out.println(tour.getId());
			System.out.println(tour.getName());
			System.out.println(tour.getDate());
			System.out.println(tour.getPrice());
			System.out.println(tour.getType());
			System.out.println(tour.getLocation());
    	}
    }

    public Tour getTour(String query) {
		Tour tour = null;
		ResultSet rs = null;

		try {
		    c.connect();
		    rs = c.retrieve(query);

		    if (rs != null) {
				while (rs.next()) {
				    tour = new Tour(
					rs.getInt(1),
					rs.getString(2),
					rs.getString(3),
					rs.getDouble(4),
					rs.getString(5),
					rs.getString(6)
				    );
				}
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}

		c.close(); // loka tengingu
		return tour;
    }
    
    public void addTour(Tour tour) {
		try {
		    c.connect();
		    PreparedStatement p = c.conn.prepareStatement("insert into tours values(?,?,?,?,?,?)");
		    p.setInt(1, tour.getId());
		    p.setString(2, tour.getName());
		    p.setString(3, tour.getDate());
		    p.setDouble(4, tour.getPrice());
		    p.setString(5, tour.getType());
		    p.setString(5, tour.getLocation());
		    p.executeUpdate();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		c.close();
    }

    // test
    public static void main(String[] args) {
    	TourController tc = new TourController();

    	Tour tour = tc.getTourById(2);
    	print(tour);

    	Tour[] tours = tc.getCheapTours(30000);
    	for (int i = 0; i < tours.length; i++) {
    		print(tours[i]);
    	}

   //  	tour = tc.getTourById(1);
   //  	if (tour != null) {
   //  		System.out.println(tour.getId());
			// System.out.println(tour.getName());
			// System.out.println(tour.getDate());
			// System.out.println(tour.getPrice());
			// System.out.println(tour.getType());
			// System.out.println(tour.getLocation());
   //  	}

   //  	Tour newTour = new Tour(6,"allahu akhbar","2001-09-11",1.0,"hryðjuverkaferð","NY");
   //  	tc.addTour(newTour);
   //  	tour = tc.getTourById(6);
   //  	if (tour != null) {
   //  		System.out.println(tour.getId());
			// System.out.println(tour.getName());
			// System.out.println(tour.getDate());
			// System.out.println(tour.getPrice());
			// System.out.println(tour.getType());
			// System.out.println(tour.getLocation());
   //  	}

    	int maxId = tc.getLatestId();
    	System.out.println("ID = " + maxId);
    }
}
