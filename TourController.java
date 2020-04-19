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
	String query = "select * from tours where name like \"" + name + "\"";
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

    public Tour[] searchTours(String search) {
    	ResultSet rs = null;
    	String query = "select * from tours where name like \"%" + search + "%\"";
    	int toursLength = getCount(query);
    	Tour[] tours = new Tour[toursLength];
    	int i = 0;

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
	    				rs.getString(6),
	    				rs.getInt(7)
	    			);
	    			i++;
	    		}
	    	}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }

    	return tours;
    }

    public Tour[] getPickupTours() {
    	ResultSet rs = null;
    	String query = "select * from tours where hasPickup = 1";
    	int toursLength = getCount(query);
    	Tour[] tours = new Tour[toursLength];
    	int i = 0;

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
	    				rs.getString(6),
	    				rs.getInt(7)
	    			);
	    			i++;
	    		}
	    	}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }

    	return tours;
    }

    public Tour[] getCheapTours(double max) {
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

	    	if (rs != null) {
	    		while (rs.next()) {
	    			tours[i] = new Tour(
	    				rs.getInt(1),
	    				rs.getString(2),
	    				rs.getString(3),
	    				rs.getDouble(4),
	    				rs.getString(5),
	    				rs.getString(6),
	    				rs.getInt(7)
	    			);
	    			i++;
	    		}
	    	}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }

    	return tours;
    }

    public Tour[] getAllTours() {
    	ResultSet rs = null;
    	String query = "select * from tours";
    	int toursLength = getCount(query);
    	Tour[] tours = new Tour[toursLength];
    	int i = 0;

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
	    				rs.getString(6),
	    				rs.getInt(7)
	    			);
	    			i++;
	    		}
	    	}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }

    	return tours;
    }


    public void printTour(Tour tour) {
    	if (tour != null) {
		String result = String.format("id: %d\nNafn á tour: %s\nTegund ferðar: %s\nDagsetning: %s\nVerð: %s\nStaðsetning: %s\n", tour.getId(), tour.getName(), tour.getType(), tour.getDate(), tour.getPrice(), tour.getLocation());
	    System.out.println(result);
    	}
    }
    public void printAllTours() {
	Tour[] tours = getAllTours();
	for (int i = 0; i < tours.length; i++) {
	    printTour(tours[i]);
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
					rs.getString(6),
					rs.getInt(7)
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
		    PreparedStatement p = c.conn.prepareStatement("insert into tours values(?,?,?,?,?,?,?)");
		    p.setInt(1, tour.getId());
		    p.setString(2, tour.getName());
		    p.setString(3, tour.getDate());
		    p.setDouble(4, tour.getPrice());
		    p.setString(5, tour.getType());
		    p.setString(6, tour.getLocation());
		    p.setInt(7, tour.getHasPickup());
		    p.executeUpdate();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		c.close();
    }
    
    public void deleteTourById(int id) {
    	try {
    		c.connect();
    		String query = "delete from tours where id = " + id;
    		PreparedStatement p = c.conn.prepareStatement(query);
    		p.executeUpdate();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    public void updateTour(Tour tour, String newName, String newTourDate, double newPrice, String newType, String newLocation) {
    	String query = "update tours set name = ?, set tour_date = ?, set price = ?, set tour_type = ?, set location = ? where id = " + tour.getId();
    	// String query = "update tours set name = ? where id = " + tour.getId();
    	try {
    		c.connect();
    		PreparedStatement p = c.conn.prepareStatement(query);
		if (newName.equals("")) {
		    p.setString(1, tour.getName());
		} else {
		    p.setString(1, newName);
		}

		if (newTourDate.equals("")) {
		    p.setString(2, tour.getDate());
		} else {
		    p.setString(2, newTourDate);
		}

		if (newPrice == -1) {
		    p.setDouble(3, tour.getPrice());
		} else {
		    p.setDouble(3, newPrice);
		}

		if (newType.equals("")) {
		    p.setString(4, tour.getType());
		} else {
		    p.setString(4, newType);
		}

		if (newLocation.equals("")) {
		    p.setString(5, tour.getLocation());
		} else {
		    p.setString(5, newLocation); 
		}

    		p.executeUpdate();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}
