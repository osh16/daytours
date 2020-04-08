import java.util.Date;

public class Rating {
    private int id;
    private String name;
    private Date date;
    private int stars;
    private String feedback;

    public int getId() {
	return id;
    }

    public String getName() {
	return name;
    }

    public Date getDate() {
	return date;
    }

    public int getStars() {
	return stars;
    }

    public String getFeedback() {
	return feedback;
    }

    public Rating(int id, String name, Date date, int stars, String feedback) {
	this.id = id;
	this.name = name;
	this.date = date;
	this.stars = stars;
	this.feedback = feedback;
    }
}
