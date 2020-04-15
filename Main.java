import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.*;

public class Main {
    static BookingController bc = new BookingController();
    static RatingController rc = new RatingController();
    static TourController tc = new TourController();

    public static void clearScreen() {  
	System.out.print("\033[H\033[2J");  
	System.out.flush();  
    }  

    public static void printLogo() {
	try {
	    BufferedReader reader = new BufferedReader(new FileReader("logo.txt"));
	    String line;
	    while ((line = reader.readLine()) != null) {
		System.out.println(line);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public static void printUserMenu() {
	System.out.println("1. Get all tours");
	System.out.println("2. Get cost efficient tours");
	System.out.println("3. Get ratings by tour");
	System.out.println("4. Add rating");
	System.out.println("5. Check if tour has pickup");
	System.out.println("q. Go back");
	System.out.print("Sladu inn menu item: ");
    }

    public static void printStaffMenu() {
	System.out.println("1.	Add booking");
	System.out.println("2.	Delete booking");
	System.out.println("3.	Update booking");
	System.out.println("4.	Get booking");
	System.out.println("5.	Add tour");
	System.out.println("6.	Delete tour");
	System.out.println("7.	Update tour");
	System.out.println("8.	Get tour");
	System.out.println("9.	Delete rating");
	System.out.println("10.	Update rating");
	System.out.println("q.	Go back");
	System.out.print("Sladu inn menu item: ");
    }

    public static void staffMenu() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	boolean viktor = true;
	while (viktor) {
	    clearScreen();
	    printLogo();
	    printStaffMenu();
	    String menu = reader.readLine();
	    clearScreen();
	    switch (menu) {
		case "1":// add booking
		    
		    break;
		case "2":// delete booking
		    System.out.print("Sláðu inn ID á booking: ");
		    int id = Integer.parseInt(reader.readLine());
		    bc.deleteBookingById(id);
		    break;
		case "3":// update booking
		    break;
		case "4":// get booking
		    System.out.print("Sláðu inn ID á booking: ");
		    id = Integer.parseInt(reader.readLine());
		    Booking booking = bc.getBookingRecordsById(id);
		    bc.printBooking(booking);
		    reader.readLine();
		    break;
		case "5":// add tour
		    System.out.print("Nafn: ");
		    String name = reader.readLine();
		    System.out.print("Dagsetning: ");
		    String date = reader.readLine();
		    System.out.print("Verð: ");
		    double price = Double.parseDouble(reader.readLine());
		    System.out.print("Tegund ferðar: ");
		    String type = reader.readLine();
		    System.out.print("Staðsetning: ");
		    String location = reader.readLine();
		    System.out.print("Er hotel pickup? (0 eða 1): ");
		    int hotelPickup = Integer.parseInt(reader.readLine());
		    Tour tour = new Tour(tc.getLatestId(), name, date, price, type, location, hotelPickup);
		    tc.addTour(tour);
		    tc.printTour(tour);  
		    reader.readLine();
		    break;
		case "6":// delete tour
		    System.out.print("Sláðu inn ID á tour: ");
		    id = Integer.parseInt(reader.readLine());
		    tc.deleteTourById(id);
		    break;
		case "7":// update tour
		    break;
		case "8":// get tour
		    System.out.print("Sláðu inn ID á tour: ");
		    id = Integer.parseInt(reader.readLine());
		    tour = tc.getTourById(id);
		    tc.printTour(tour);
		    reader.readLine();
		    break;
		case "9":// delete rating 
		    System.out.print("Sláðu inn ID á rating: ");
		    id = Integer.parseInt(reader.readLine());
		    rc.deleteRatingById(id);
		    reader.readLine();
		    break;
		case "10":// update rating 
		    break;
		case "q":
		case "Q":
		    viktor = false;
		    break;

	    }
	}
    }


    public static void userMenu() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	boolean viktor = true;
	while (viktor) {
	    clearScreen();
	    printLogo();
	    printUserMenu();
	    String menu = reader.readLine();
	    clearScreen();
	    switch (menu) {
		case "1":// get all tours
		    Tour[] tours = tc.getAllTours();
		    for (int i = 0; i < tours.length; i++) {
			tc.printTour(tours[i]);
		    }
		    reader.readLine();
		    break;
		case "2":// cost efficient tours
		    System.out.println("Sláðu inn hámarksverð");
		    int price = Integer.parseInt(reader.readLine());
		    tours = tc.getCheapTours(price);
		    System.out.println("=====");
		    for (int i = 0; i < tours.length; i++) {
			tc.printTour(tours[i]);
			System.out.println("=====");
		    }
		    reader.readLine();
		    break;
		case "3":// ratings by tour
		    System.out.print("Sláðu inn id á tour: ");
		    int id = Integer.parseInt(reader.readLine());
		    Tour tour = tc.getTourById(id);
		    System.out.println("Tour: " + tour.getName().toUpperCase());
		    Rating[] ratings = rc.getRatingsByTour(tour);
		    for (int i = 0; i < ratings.length; i++) {
			rc.printRating(ratings[i]);
		    }
		    reader.readLine();
		case "4":// add rating
		    System.out.print("Fyrirsögn: ");
		    String title = reader.readLine();
		    System.out.print("Feedback: ");
		    String feedback = reader.readLine();
		    //Rating rating = new Rating();

		    reader.readLine();
		case "5":// check if tour has pickup
		case "q":
		case "Q":
		    viktor = false;
		    break;
		
	    }
	}
    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	boolean viktor = true;

	while (viktor) {
	    clearScreen();
	    printLogo();
	    System.out.println("1. Passenger menu");
	    System.out.println("2. Staff menu");
	    System.out.println("q. Exit");
	    System.out.print("Sladu inn menu item: ");
	    String menu = reader.readLine();
	    clearScreen();
	    switch (menu) {
		case "1":
		    userMenu();
		    break;
		case "2":
		    staffMenu();
		    break;
		case "q":
		case "Q":
		    viktor = false;
		    break;
	    }
	}
    }
}
