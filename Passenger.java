
import java.util.Date;

public class Passenger {
    private int id;
    private String name;
    private String ssn;
    private int age;
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

    public Passenger(int id, String name, String ssn, int age) {
	this.id = id;
	this.name = name;
	this.ssn= ssn;
	this.age = age;
    }
}
