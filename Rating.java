import java.util.Date;

public class Rating {
    private int id;
    private String title;
    private String date;
    private int stars;
    private String feedback;
    private int tourId;
    private int passengerId;

    public int getId() {
	return id;
    }

    public String getTitle() {
	return title;
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
	return tourId;
    }

    public int getPassengerId() {
	return passengerId;
    }

    public Rating(int id, String title, String date, int stars , String feedback, int tourId, int passengerId) {
	this.id = id;
	this.title = title;
	this.date = date;
	this.stars = stars;
	this.feedback = feedback;
	this.tourId = tourId;
	this.passengerId = passengerId;
    }
}
