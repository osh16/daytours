import java.sql.ResultSet; 
import java.sql.PreparedStatement;

public class RatingController {
    static TourController tc = new TourController();
    static RatingController rc = new RatingController();
    Connect c = new Connect();


    public int getLatestId() {
	String query = "select * from ratings where id = (select max(id) from ratings)";
	Rating rating = getRating(query);
	return rating.getId();
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

    public Rating[] getRatingsByTour(Tour tour) {
	ResultSet rs = null;
	String query = "select * from ratings where tour_id = " + tour.getId();
	int count = getCount(query);
	int i = 0;
	Rating[] ratings = new Rating[count];
	try {
	    c.connect();
	    rs = c.retrieve(query);

	    if (rs != null) {
		while (rs.next()) {
		    ratings[i] = new Rating(
			rs.getInt(1),
			rs.getString(2),
			rs.getString(3),
			rs.getInt(4),
			rs.getString(5),
			rs.getInt(6),
			rs.getInt(7)
		    );
		    i++;
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return ratings;
    }

    public Rating getRatingById(int id) {
	String query = "select * from ratings where id = " + String.valueOf(id);
	return getRating(query);
    }

    public Rating getRating(String query) {
	Rating rating = null;
	ResultSet rs = null;

	try {
	    c.connect();
	    rs = c.retrieve(query);

	    if (rs != null) {
		while (rs.next()) {
		    rating = new Rating(
			rs.getInt(1),
			rs.getString(2),
			rs.getString(3),
			rs.getInt(4),
			rs.getString(5),
			rs.getInt(6),
			rs.getInt(7)
		    );
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

	c.close(); // loka tengingu
	return rating;
    }

    public Passenger getPassengerByRating(Rating rating) {
	Passenger passenger = null;
	ResultSet rs = null;
	String query = "select * from passenger where id = " + rating.getPassengerId();

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
    
    public void addRating(Rating rating) {
	try {
	    c.connect();
	    PreparedStatement p = c.conn.prepareStatement("insert into ratings values(?,?,?,?,?,?,?)");
	    //p.setInt(1, rating.getId());
	    p.setInt(1, rating.getId());
	    p.setString(2, rating.getTitle());
	    p.setString(3, rating.getDate());
	    p.setInt(4, rating.getStars());
	    p.setString(5, rating.getFeedback());
	    p.setInt(6, rating.getTourId());
	    p.setInt(7, rating.getPassengerId());
	    p.executeUpdate();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	c.close();
    }
    
    public void updateRating(Rating rating) {

    }

    public void deleteRatingById(int id) {
    	try {
    		c.connect();
    		String query = "delete from ratings where id = " + id;
    		PreparedStatement p = c.conn.prepareStatement(query);
    		p.executeUpdate();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    public static void printRating(Rating rating) {
	if (rating != null) {
	    String name = rc.getPassengerByRating(rating).getName();
	    String result = String.format("Nafn: %s\nTitill: %s\n%s\n", name, rating.getTitle(), rating.getFeedback());
	    for (int i = 0; i < rating.getStars(); i++) {
		result += "★"; 
	    }
	    result += "\n";
	    System.out.println(result);
	}
    }
}
