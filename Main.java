import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    public static void main(String[] args){
	RatingController rc = new RatingController();
	TourController tc = new TourController();

	System.out.println("=========== get rating by id ==========");
	// skoda rating med id
	Rating rating = rc.getRatingById(2);
	System.out.println(rating.getId());
	System.out.println(rating.getTitle());
	System.out.println(rating.getFeedback());


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
