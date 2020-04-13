import java.util.Date;

public class Rating {
    private int id;
    private String title;
    private String date;
    private int stars;
    private String feedback;


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

    public Rating(int id, String title, String date, int stars , String feedback) {
	this.id = id;
	this.title = title;
	this.date = date;
	this.stars = stars;
	this.feedback = feedback;
    }
}
