import java.sql.ResultSet; 
import java.util.Date;
//import java.util.SimpleDateFormatter;

public class RatingController {
    Connect c = new Connect();
    //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public Rating getRating(int id) {
	Rating rating = null;
	ResultSet rs = null;
		    /*rating = new Rating(
			rs.getInt(1),
			rs.getString(2),
			rs.getDate(3),
			rs.getInt(4),
			rs.getString(5)
		    );*/

	try {
	    String query = "select * from ratings where id = " + String.valueOf(id);
	    c.connect(); // tengjast db
	    rs = c.retrieve(query); //senda query
	    if (rs != null) {
		while (rs.next()) {
		    System.out.println(rs.getInt(1));
		    System.out.println(rs.getString(2));
		    //System.out.println(rs.getDate(3));
		    System.out.println(rs.getInt(4));
		    System.out.println(rs.getString(5));
		}
	    }
	    c.close(); // loka tengingu
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return rating;
    }
    
    public String setRating(String rating) {
	//má ekki vera ákveðinn langur t.d
	if(rating.length()>50) {
		//kannski try catch?
	}
	return rating;
    }
    
    public double getStar(double star) {
	if(star < 5.0 || star > 0.0 ) {
	    return star;
	} else {
	    return (Double) null;
	}
    }

    // test
    public static void main(String[] args) {
	RatingController rc = new RatingController();
	rc.getRating(1);
    }
}
