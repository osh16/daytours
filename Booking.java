public class Booking {
    private int ID;
    private String Trip;
    private String PaymentMethod;
    private String CustomerName;
    private int Amount;
    private String Booking_date;
    
    public int getID(){
        return ID;
    }
    
    
    public String getTrip(){
        return Trip;
    }
    
    public String getPaymentMethod(){
        
        return PaymentMethod;
    }
    
    public String getCustomerName(){
        
        return CustomerName;
    }

    public int getAmount(){
	
	return Amount;
    }

    public String getDate(){
	
	return Booking_date;
    }

    
    public Booking(int ID,String Trip,String PaymentMethod,String CustomerName,int Amount,String Booking_date){
        this.ID = ID;
        this.Trip = Trip;
        this.PaymentMethod = PaymentMethod;
        this.CustomerName = CustomerName;
        this.Amount = Amount;
        this.Booking_date = Booking_date;
    }
}
