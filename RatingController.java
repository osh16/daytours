import java.sql.ResultSet; 

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
	    c.close(); // loka tengingu
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return rating;
    }
    
    public void insertRating() {

    }

    public void setRating(int id) {
	//má ekki vera ákveðinn langur t.d
	if(rating.length()>50) {
		//kannski try catch?
	}
	return rating;
    }
    
    // notum getStar() fra rating object

    /*public double getStar(double star) {
	if(star < 5.0 || star > 0.0 ) {
	    return star;
	} else {
	    return (Double) null;
	}
    }*/

    // test
    public static void main(String[] args) {
	RatingController rc = new RatingController();
	Rating rating = rc.getRating(1);
	if (rating != null) {
	    System.out.println(rating.getId());
	    System.out.println(rating.getName());
	    System.out.println(rating.getDate());
	    System.out.println(rating.getStars());
	    System.out.println(rating.getFeedback());
	}
    }
}
