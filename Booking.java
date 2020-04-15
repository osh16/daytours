public class Booking {
    private int id;
    private String paymentMethod;
    private String bookingDate;
    private int passengerId;
    private int tourId;
    
    public int getId(){
        return id;
    }

    public String getPayment(){
        return paymentMethod;
    }
    
    public String getDate(){
	return bookingDate;
    }
    
    public int getPassengerId() {
	return passengerId;
    }

    public int getTourId() {
	return tourId;
    }

    public Booking(int id,String paymentMethod, String bookingDate, int passengerId, int tourId) {
        this.id = id;
        this.paymentMethod = paymentMethod;
        this.bookingDate = bookingDate;
	this.passengerId = passengerId;
	this.tourId = tourId;
    }
}
