import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.*;

public class Main {
    public static void clearScreen() {  
	System.out.print("\033[H\033[2J");  
	System.out.flush();  

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

	boolean viktor = true;

	while (viktor) {
	    clearScreen();
	    printMenu();
	    String menu = reader.readLine();
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
		case "4":
		    System.out.println("Sláðu inn id á tour");
		    id = Integer.parseInt(reader.readLine());
		    tour = tc.getTourById(id);
		    Rating[] ratings = rc.getRatingsByTour(tour);
		    System.out.println("=====");
		    for (int i = 0; i < ratings.length; i++) {
			rc.printRating(ratings[i]);
			System.out.println("=====");
		    }
		    break;
		case "5":
		    break;
		case "q":
		case "Q":
		    viktor = false;
		    break;
	    }
	}





























	/*RatingController rc = new RatingController();
	TourController tc = new TourController();

	System.out.println("=========== get rating by id ==========");
	// skoda rating med id
	Rating rating = rc.getRatingById(2);
	System.out.println(rating.getId());
	System.out.println(rating.getTitle());
	System.out.println(rating.getFeedback());


	System.out.println("=========== get rating by tour ==========");
	// skoda ratings utfra tour
	Tour tour = tc.getTourById(2);
	rc.getRatingsByTour(tour);
	Rating[] ratings = rc.getRatingsByTour(tour);

	if (ratings != null) {
	    for (int i = 0; i < ratings.length; i++) {
		System.out.println(ratings[i].getId());
		System.out.println(ratings[i].getTitle());
		System.out.println(ratings[i].getFeedback());
	    }
	}

	// baeta vid rating
	System.out.println("=============baeta vid==================");
	rating = new Rating(rc.getLatestId()+1, "hehe", "2010-10-10", 5, "rosa gott", 2,6);
	rc.addRating(rating);	
	rating = rc.getRatingById(rc.getLatestId());
	System.out.println(rating.getId());
	System.out.println(rating.getTitle());
	System.out.println(rating.getFeedback());*/
    }
}
