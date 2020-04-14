import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.*;

public class Main {
    public static void clearScreen() {  
	System.out.print("\033[H\033[2J");  
	System.out.flush();  
    }  

    public static void userMenu() {

    }

    public static void staffMenu() {

    }

    public static void adminMenu() {

    }

    public static void printMenu() {
	    System.out.println("Demo fyrir daytours kerfið");
	    System.out.println("=================");
	    System.out.println("1.  Get tour by id ");
	    System.out.println("2.  Get tour by name ");
	    System.out.println("3.  Get cheap tours ");
	    System.out.println("4.  Get rating by tour ");
	    System.out.println("5.  Get rating by ID ");
	    System.out.println("6.  Get rating by name	");
	    System.out.println("7.  Get booking ");
	    System.out.println("8.  Add tour ");
	    System.out.println("9.  Add rating ");
	    System.out.println("10. Add booking");
	    System.out.println("q. exit");
	    System.out.println("=================");
	    System.out.println("Sladu inn menu item");
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	BookingController bc = new BookingController();
	RatingController rc = new RatingController();
	TourController tc = new TourController();
	Tour tour;
	Rating rating;

	boolean viktor = true;

	while (viktor) {
	    clearScreen();
	    printMenu();
	    String menu = reader.readLine();
	    clearScreen();
	    switch (menu) {
		case "1":
		    System.out.println("Sláðu inn ID");
		    int id = Integer.parseInt(reader.readLine());
		    tour = tc.getTourById(id);
		    tc.printTour(tour); 
		    reader.readLine();
		    break;
		case "2":
		    System.out.println("Sláðu inn nafn á túr");
		    String name = reader.readLine();
		    System.out.println(name);
		    tour = tc.getTourByName(name);
		    tc.printTour(tour);
		    reader.readLine();
		    break;
		case "3":
		    System.out.println("Sláðu inn hámarksverð");
		    int price = Integer.parseInt(reader.readLine());
		    Tour[] tours = tc.getCheapTours(price);
		    System.out.println("=====");
		    for (int i = 0; i < tours.length; i++) {
			tc.printTour(tours[i]);
			System.out.println("=====");
		    }
		    reader.readLine();
		    break;
	    /*System.out.println("5.  Get rating by ID ");
	    System.out.println("6.  Get rating by name	");
	    System.out.println("7.  Get booking ");
	    System.out.println("8.  Add tour ");
	    System.out.println("9.  Add rating ");
	    System.out.println("10. Add booking");*/
		case "4":
		    System.out.println("Sláðu inn id á tour");
		    id = Integer.parseInt(reader.readLine());
		    tour = tc.getTourById(id);

		    Rating[] ratings = rc.getRatingsByTour(tour);
		    System.out.println("Nafn a túr: " + tour.getName().toUpperCase());
		    System.out.println("=====");
		    for (int i = 0; i < ratings.length; i++) {
			rc.printRating(ratings[i]);
			System.out.println("=====");
		    }
		    reader.readLine();
		    break;
		case "5":
		    System.out.println("Sláðu inn ID");
		    id = Integer.parseInt(reader.readLine());
		    rating = rc.getRatingById(id);
		    rc.printRating(rating);
		    reader.readLine();
		    break;
		case "6":
		case "q":
		case "Q":
		    viktor = false;
		    break;
	    }
	}
    }
}
