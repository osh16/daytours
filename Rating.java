import java.util.Date;

public class Rating {
    private int id;
    private String name;
    private String date;
    private int stars;
    private String feedback;
    //private int tour_id;
    //private int passenger_id;

    public int getId() {
	return id;
    }

    public String getName() {
	return name;
    }

    public String getDate() {
	return date;
    }

    public int getStars() {
	return stars;
    }

    public String getFeedback() {
	return feedback;
    }

    public int getTourId() {
	return tour_id;
    }

    public int getPassengerId() {
	return passenger_id;
    }
    

    public Rating(int id, String name, String date, int stars, String feedback, /*int tour_id, int passenger_id*/) {
	this.id = id;
	this.name = name;
	this.date = date;
	this.stars = stars;
	this.feedback = feedback;
	/*this.tour_id = tour_id;
	this.passenger_id = passenger_id;*/
    }
}
