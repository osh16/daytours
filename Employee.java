import java.util.Date;

public class Employee {
    private int id;
    private String name;
    private String email;
    private int phoneNumber;
    private boolean guide;
    private boolean tourPlanner;

    public int getId() {
	return id;
    }

    public String getName() {
	return name;
    }

    public String getEmail() {
	return email;
    }

    public int getPhoneNumber() {
	return phoneNumber;
    }

    public boolean getGuide() {
	return guide;
    }
    
    public boolean getTourPlanner() {
	return tourPlanner;
    }

    public Employee(int id, String name, String email, int phoneNumber, boolean guide, boolean tourPlanner) {
	this.id = id;
	this.name = name;
	this.email = email;
	this.phoneNumber = phoneNumber;
	this.guide = guide;
	this.tourPlanner = tourPlanner;
    }
}
