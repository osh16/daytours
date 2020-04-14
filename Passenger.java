
import java.util.Date;

public class Passenger {
    private int id;
    private String name;
    private String ssn;
    private int age;
    private String specialNeeds;
    private int tour_id;
    //private Rating[] ratings;

    public int getId() {
	return id;
    }

    public String getName() {
	return name;
    }

    public String getSsn() {
	return ssn;
    }

    public int getAge() {
	return age;
    }

    public String getSpecialNeeds() {
        return specialNeeds;
    }

    public int getTour_id() {
        return tour_id;
    }

    public Passenger(int id, String name, String ssn, int age, String specialNeeds, int tour_id) {
	this.id = id;
	this.name = name;
	this.ssn= ssn;
	this.age = age;
	this.specialNeeds = specialNeeds;
	this.tour_id = tour_id;
    }
}
