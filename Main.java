import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.time.LocalDateTime;


public class Main {
    static BookingController bc = new BookingController();
    static PassengerController pc = new PassengerController();
    static RatingController rc = new RatingController();
    static TourController tc = new TourController();
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String getCurrentDate() {
	Date date = Calendar.getInstance().getTime();
	DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
	String strDate = dateFormat.format(date);
	return strDate;
    }

    public static boolean isNumeric(String str) { 
	  try {  
	    Double.parseDouble(str);  
	    return true;
	  } catch(NumberFormatException e){  
    return false;  
  }  
}

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
	System.out.println("5. Get tours with pickup");
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
	System.out.println("11.	Get all tours");
	System.out.println("12.	Get all ratings");
	System.out.println("13.	Get all bookings");
	System.out.println("14.	Get all passengers");
	System.out.println("q.	Go back");
	System.out.print("Sladu inn menu item: ");
    }

    public static void addBooking() throws IOException {
	System.out.println("Add booking");
	System.out.println("=============");

	pc.printAllPassengers();
	System.out.print("Sláðu inn id á passenger: ");
	int passengerId = Integer.parseInt(reader.readLine());	

	tc.printAllTours();
	System.out.print("Sláðu inn id á tour: ");
	int tourId = Integer.parseInt(reader.readLine());	

	System.out.print("Sláðu inn greiðsluaðferð: ");
	String payment = reader.readLine();
	
	bc.addBooking(new Booking(bc.getLatestId()+1, payment, getCurrentDate(), passengerId, tourId));
	
	reader.readLine();
    }
    public static void deleteBooking() throws IOException {
	System.out.println("Delete booking");
	System.out.println("=============");
	
	bc.printAllBookings();

	System.out.print("Sláðu inn ID á booking: ");
	int id = Integer.parseInt(reader.readLine());
	bc.deleteBooking(id);
	reader.readLine();

    }
    public static void updateBooking() throws IOException {
	System.out.println("Update booking");
	System.out.println("=============");

	bc.printAllBookings();
	System.out.print("Sláðu inn ID á booking: ");
	int id = Integer.parseInt(reader.readLine());

	Booking booking = bc.getBookingById(id);

	System.out.println("Ath. ef þú villt halda eins og er, ekki slá inn neitt");

	System.out.print("Sláðu inn greiðsluleið: ");
	String payment = reader.readLine();

	System.out.print("Sláðu inn dagsetningu (yyyy-mm-dd): ");
	String date = reader.readLine();

	System.out.print("Sláðu inn passenger id");
	int passengerId = Integer.parseInt(reader.readLine());

	System.out.print("Sláðu inn tour id");
	int tourId = Integer.parseInt(reader.readLine());

	bc.updateBooking(booking, payment, date, passengerId, tourId);
	reader.readLine();
    }
    public static void getBooking() throws IOException {
	System.out.println("Get booking");
	System.out.println("=============");

	System.out.print("Sláðu inn ID á booking: ");
	int id = Integer.parseInt(reader.readLine());
	Booking booking = bc.getBookingById(id);
	bc.printBooking(booking);
	if (booking == null) {
		System.out.println("Engin bókun til á þessu ID");
	}
	reader.readLine();
    }
    public static void addTour() throws IOException {
	System.out.println("Add tour");
	System.out.println("=============");

	System.out.print("Nafn: ");
	String name = reader.readLine();

	System.out.print("Dagsetning (yyyy-mm-dd): ");
	String date = reader.readLine();

	System.out.print("Verð: ");
	double price = Double.parseDouble(reader.readLine());

	System.out.print("Tegund ferðar: ");
	String type = reader.readLine();

	System.out.print("Staðsetning: ");
	String location = reader.readLine();

	System.out.print("Er hotel pickup? (0 eða 1): ");
	int hotelPickup = Integer.parseInt(reader.readLine());
	String pickup = reader.readLine();

	Tour tour = new Tour(tc.getLatestId()+1, name, date, price, type, location, hotelPickup);
	tc.addTour(tour);
	tc.printTour(tour);
	reader.readLine();
    }
    public static void deleteTour() throws IOException {
	System.out.println("Delete tour");
	System.out.println("=============");

	tc.printAllTours();

	System.out.print("Sláðu inn ID á tour: ");
	int id = Integer.parseInt(reader.readLine());
	tc.deleteTourById(id);
    }
    public static void updateTour() throws IOException {
	System.out.println("Update tour");
	System.out.println("=============");

	tc.printAllTours();

	System.out.print("Sláðu inn ID á tour");
	int id = Integer.parseInt(reader.readLine());
	Tour tour = tc.getTourById(id);
	
	System.out.println("Ath. ef þú villt halda eins og er, ekki slá inn neitt");

	System.out.print("Sláðu inn nafn: ");
	String name = reader.readLine();

	System.out.print("Sláðu inn dagsetningu (yyyy-mm-dd): ");
	String date = reader.readLine();	

	System.out.print("Sláðu inn verð (-1 til að halda eins og er): ");
	Double price = Double.parseDouble(reader.readLine());

	System.out.print("Sláðu inn nýja tegund af ferð: ");
	String type = reader.readLine();

	System.out.print("Sláðu inn nýja staðsetningu: ");
	String location = reader.readLine();

	tc.updateTour(tour, name, date, price, type, location);
	reader.readLine();

    }
    public static void getTour() throws IOException {
	System.out.println("Get tour");
	System.out.println("=============");
	System.out.print("Sláðu inn ID á tour: ");
	int id = Integer.parseInt(reader.readLine());
	Tour tour = tc.getTourById(id);
	tc.printTour(tour);
	reader.readLine();
    }
    public static void deleteRating() throws IOException {
	System.out.println("Delete rating");
	System.out.println("=============");
	rc.printAllRatings();
	System.out.print("Sláðu inn ID á rating: ");
	int id = Integer.parseInt(reader.readLine());
	rc.deleteRatingById(id);
	reader.readLine();
    }

    public static void updateRating() throws IOException {
	System.out.println("Update rating");
	System.out.println("=============");
	rc.printAllRatings();
	System.out.print("Sláðu inn ID á rating: ");
	int id = Integer.parseInt(reader.readLine());
	Rating rating = rc.getRatingById(id);
	System.out.print("Breyttu titil: ");
	String title = reader.readLine();
	System.out.print("Breyttu feedback: ");
	String feedback = reader.readLine();
	rc.updateRating(rating, title, feedback);
    }

    public static void getAllTours() throws IOException {
	System.out.println("Get all tours");
	System.out.println("=============");
	tc.printAllTours();
	reader.readLine();
    }

    public static void getAllRatings() throws IOException {
	System.out.println("Get all ratings");
	System.out.println("=============");
	rc.printAllRatings();
	reader.readLine();
    }

    public static void getAllBookings() throws IOException {
	System.out.println("Get all bookings");
	System.out.println("=============");
	bc.printAllBookings();
	reader.readLine();
    }

    public static void getAllPassengers() throws IOException {
	System.out.println("Get all passengers");
	System.out.println("=============");
	pc.printAllPassengers();
	reader.readLine();
    }

    public static void getCostEfficientTours() throws IOException  {
	System.out.println("Get cost efficient tours");
	System.out.println("=============");
	System.out.println("Sláðu inn hámarksverð");
	int price = Integer.parseInt(reader.readLine());

	Tour[] tours = tc.getCheapTours(price);

	for (int i = 0; i < tours.length; i++) {
	    tc.printTour(tours[i]);
	}
	reader.readLine();
    }

    public static void getRatingsByTour() throws IOException  {
	System.out.println("Get ratings by tour");
	System.out.println("=============");
	System.out.print("Sláðu inn id á tour: ");
	int id = Integer.parseInt(reader.readLine());
	Tour tour = tc.getTourById(id);
	System.out.println("Tour: " + tour.getName().toUpperCase());
	Rating[] ratings = rc.getRatingsByTour(tour);
	for (int i = 0; i < ratings.length; i++) {
	    rc.printRating(ratings[i]);
	}
	reader.readLine();

    }

    public static void addRating() throws IOException  {
	System.out.println("Add rating");
	System.out.println("=============");
	Passenger[] passengers = pc.getAllPassengers();
	for (int i = 0; i < passengers.length; i++) {
	    pc.printPassenger(passengers[i]);
	}

	System.out.print("Sláðu inn IDið þitt: ");
	int id = Integer.parseInt(reader.readLine());

	System.out.print("Leitaðu af túr til að bæta einkunn við: ");
	String query = reader.readLine();

	Tour[] tours = tc.searchTours(query);
	
	for (int i = 0; i < tours.length; i++) {
	    tc.printTour(tours[i]);
	}

	System.out.print("Sláðu inn ID á tour: ");
	int tour_id = Integer.parseInt(reader.readLine());

	System.out.print("Fyrirsögn: ");
	String title = reader.readLine();

	System.out.print("Feedback: ");
	String feedback = reader.readLine();

	System.out.print("Hversu margar stjörnur? (0-5)");
	int stars = Integer.parseInt(reader.readLine());

	if (stars > 5 || stars < 0) {
		while (stars > 5 || stars < 0) {
			System.out.println("Stjörnur verða að vera á bilinu 0 til 5");
			System.out.println("Hversu margar stjörnur? ");
			stars = Integer.parseInt(reader.readLine());
		}
	}

	rc.addRating(new Rating(rc.getLatestId()+1, title, getCurrentDate(), stars, feedback, tour_id, id));
	System.out.println("Túrinn sem þú bættir inn:");
	rc.printRating(rc.getRatingById(rc.getLatestId()));
	reader.readLine();
    }


    public static void getTourWithPickup() throws IOException  {
	System.out.println("Get tours with pickup");
	System.out.println("=============");
	Tour[] tours = tc.getPickupTours();
	for (int i = 0; i < tours.length; i++) {
	    tc.printTour(tours[i]);
	    System.out.println("=====");
	}
	reader.readLine();
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
		    addBooking(); 
		    break;
		case "2":// delete booking
		    deleteBooking();
		    break;
		case "3":// update booking
		    updateBooking();
		    break;
		case "4":// get booking
		    getBooking();
		    break;
		case "5":// add tour
		    addTour();
		    break;
		case "6":// delete tour
		    deleteTour();
		    break;
		case "7":// update tour
		    updateTour();
		    break;
		case "8":// get tour
		    getTour();
		    break;
		case "9":// delete rating 
		    deleteRating();
		    break;
		case "10":// update rating 
		    updateRating();
		    break;
		case "11":
		    getAllTours();
		    break;
		case "12":
		    getAllRatings();
		    break;
		case "13":
		    getAllBookings();
		    break;
		case "14":
		    getAllPassengers();
		    break;
		case "q":
		case "Q":
		    viktor = false;
		    break;
	    }
	}
    }


    public static void userMenu() throws IOException {
	boolean viktor = true;
	while (viktor) {
	    clearScreen();
	    printLogo();
	    printUserMenu();
	    String menu = reader.readLine();
	    clearScreen();
	    switch (menu) {
		case "1":// get all tours
		    getAllTours();
		    break;
		case "2":// cost efficient tours
		    getCostEfficientTours();
		    break;
		case "3":
		    getRatingsByTour();
		    break;
		case "4":// add rating
		    addRating();
		    break;
		case "5":// check if tour has pickup
		    getTourWithPickup();
		    break;
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
