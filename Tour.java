import java.util.Date;

public class Tour {
    private Integer id;
    private String name;
    private String date;// gaeti verid date type, skoda
    private double price;
    private String type;
    private String location;
    private int hasPickup;
    //skv design model
    //private Passenger[] passengers;
    //private Employee[] employees;

    public Integer getId() {
	return id;
    }

    public String getName() {
	return name;
    }

    public String getDate() {
	return date;
    }

    public double getPrice() {
	return price;
    }

    public String getType() {
	return type;
    }

    public String getLocation() {
	return location;
    }

    public int getHasPickup() {
        return hasPickup;
    }

    public Tour(Integer id, String name, String date, double price, String type, String location, int hasPickup) {
	this.id = id;
	this.name = name;
	this.date = date;
	this.price = price;
	this.type = type;
	this.location = location;
    this.hasPickup = hasPickup;
    }
}
