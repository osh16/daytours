import java.sql.ResultSet; 
import java.sql.PreparedStatement;

public class RatingController {
    Connect c = new Connect();
    
    public int getAvgRating(Tour tour) {
	Rating[] rating = getRating(tour);
	int avg = 0;
	for (int i = 0; i < rating.length; i++) {
	    avg += rating[i].getStars();
	}
	return avg/rating.length;
    }

    public Rating[] getRating(Tour tour) {
	Rating[] rating = null;
	ResultSet rs = null;
	int i = 0;

	try {
	    String query = "select * from ratings where id = " + String.valueOf(tour.getId());
	    c.connect();
	    rs = c.retrieve(query);

	    if (rs != null) {
		while (rs.next()) {
		    rating[i] = new Rating(
			rs.getInt(1),	    // id
			rs.getString(2),    // name
			rs.getString(3),    // date
			rs.getInt(4),	    // stars
			rs.getString(5),    // feedback
			rs.getInt(6),	    // tour_id
			rs.getInt(7)	    // passenger_id
		    );

		    i++;
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

	c.close(); // loka tengingu
	return rating;
    }
    
    // einkun fyrir tour, sem kemur fra einhverjum passenger
    public void addRating(Rating rating, Tour tour, Passenger passenger) {
	try {
	    c.connect();
	    PreparedStatement p = c.conn.prepareStatement("insert into ratings values(?,?,?,?,?)");
	    p.setInt(1, rating.getId());
	    p.setString(2, rating.getName());
	    p.setString(3, rating.getDate());
	    p.setInt(4, rating.getStars());
	    p.setString(5, rating.getFeedback());
	    p.setInt(6, tour.getId());
	    p.setInt(7, passenger.getId());
	    p.executeUpdate();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	c.close();
    }

    // test
    public static void main(String[] args) {
	/*RatingController rc = new RatingController();

	// get 
	Rating rating = rc.getRating(1);
	if (rating != null) {
	    System.out.println(rating.getId());
	    System.out.println(rating.getName());
	    System.out.println(rating.getDate());
	    System.out.println(rating.getStars());
	    System.out.println(rating.getFeedback());
	}

	// set
	Rating rating2 = new Rating(0,"test","2010-10-10",5,"test");
	System.out.println(rating2.getName());
	rc.addRating(rating2);

	// get 
	rating = rc.getRating(-1);
	if (rating != null) {
	    System.out.println(rating.getId());
	    System.out.println(rating.getName());
	    System.out.println(rating.getDate());
	    System.out.println(rating.getStars());
	    System.out.println(rating.getFeedback());
	}
	*/
    }
}
