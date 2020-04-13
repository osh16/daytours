public class Booking {
    private int ID;
    private String Trip;
    private String PaymentMethod;
    private String CustomerName;
    
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
    
    public Booking(int ID,String Trip,String PaymentMethod,String CustomerName){
        this.ID = ID;
        this.Trip = Trip;
        this.PaymentMethod = PaymentMethod;
        this.CustomerName = CustomerName;
    }
}
