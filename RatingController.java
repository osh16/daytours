import java.sql.ResultSet; 
import java.sql.PreparedStatement;

public class RatingController {
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

    public Rating getRatingByName(String name) {
	String query = "select * from ratings where id = " + name;
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

    public static void printRating(Rating rating) {
    	if (rating != null) {
    		System.out.println(rating.getId());
    		System.out.println(rating.getTitle());
    		System.out.println(rating.getDate());
			System.out.println(rating.getStars());
    		System.out.println(rating.getFeedback());
    		System.out.println(rating.getTourId());
    		System.out.println(rating.getPassengerId());
    	}
    }

    // test
    public static void main(String[] args) {
	RatingController rc = new RatingController();
	TourController tc = new TourController();

	System.out.println("=========== get rating by id ==========");
	// skoda rating med id
	Rating rating = rc.getRatingById(2);
	// System.out.println(rating.getId());
	// System.out.println(rating.getTitle());
	// System.out.println(rating.getFeedback());
	printRating(rating);


	System.out.println("=========== get rating by tour ==========");
	// skoda ratings utfra tour
	Tour tour = tc.getTourById(2);
	rc.getRatingsByTour(tour);
	Rating[] ratings = rc.getRatingsByTour(tour);

	if (ratings != null) {
	    for (int i = 0; i < ratings.length; i++) {
		System.out.println(ratings[i].getId());
		System.out.println(ratings[i].getTitle());
		System.out.println(ratings[i].getFeedback());
	    }
	}

	// baeta vid rating
	System.out.println("=============baeta vid==================");
	rating = new Rating(rc.getLatestId()+1, "hehe", "2010-10-10", 5, "rosa gott", 2,6);
	rc.addRating(rating);	
	rating = rc.getRatingById(rc.getLatestId());
	System.out.println(rating.getId());
	System.out.println(rating.getTitle());
	System.out.println(rating.getFeedback());
    }
}
