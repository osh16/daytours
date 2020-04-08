import java.sql.ResultSet; 
import java.sql.PreparedStatement;

public class RatingController {
    Connect c = new Connect();

    // tekur inn int id eins og er
    // aetti ad taka inn Tour tour 
    public Rating getRating(int id) {
	Rating rating = null;
	ResultSet rs = null;

	try {
	    String query = "select * from ratings where id = " + String.valueOf(id);
	    c.connect();
	    rs = c.retrieve(query);

	    if (rs != null) {
		while (rs.next()) {
		    rating = new Rating(
			rs.getInt(1),
			rs.getString(2),
			rs.getString(3),
			rs.getInt(4),
			rs.getString(5)
		    );
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

	c.close(); // loka tengingu
	return rating;
    }
    
    public void addRating(Rating rating) {
	try {
	    c.connect();
	    PreparedStatement p = c.conn.prepareStatement("insert into ratings values(?,?,?,?,?)");
	    //p.setInt(1, rating.getId());
	    p.setString(2, rating.getName());
	    p.setString(3, rating.getDate());
	    p.setInt(4, rating.getStars());
	    p.setString(5, rating.getFeedback());
	    p.executeUpdate();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	c.close();
    }

    // test
    public static void main(String[] args) {
	RatingController rc = new RatingController();

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
    }
}
