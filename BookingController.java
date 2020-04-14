/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdaytours;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class BookingController {
    Connect c = new Connect();
    
    
    public Booking getBookingById(int ID){
        Booking booking = null;
        ResultSet rs = null;
        
        try{
            String query = "select * from Booking where id = " + String.valueOf(ID);
            c.connect();
            rs = c.retrieve(query);
            
            if(rs !=null){
                while(rs.next()){
                    booking = new Booking(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getString(6)
                    );
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            
        }
                
        c.close();
        return booking;
    }
    
     public Booking getBookingByName(String name){
        Booking booking = null;
        ResultSet rs = null;

        try{
            String query = "select * from Booking where customername = " + String.valueOf(name);
            c.connect();
            rs = c.retrieve(query);

            if(rs != null){
                while(rs.next()){
                    booking = new Booking(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getString(6)
                    ); 
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        c.close();
        return booking;

    }
    
    public void setBookingRecords(Booking booking){
        try{
            c.connect();
            PreparedStatement p = c.conn.prepareStatement("insert into Booking values(?,?,?,?,?,?)");
            p.setInt(1, booking.getID());
            p.setString(2, booking.getTrip());
            p.setString(3, booking.getPaymentMethod());
            p.setString(4, booking.getCustomerName());
            p.setInt(5, booking.getAmount());
            p.setString(6,booking.getDate());
            p.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        c.close();
    }
    
    public static void main(String[] args){
        BookingController bc = new BookingController();
        
        Booking booking = bc.getBookingById(1);
        if(booking != null){
            System.out.println(booking.getID());
            System.out.println(booking.getTrip());
            System.out.println(booking.getPaymentMethod());
            System.out.println(booking.getCustomerName());
            System.out.println(booking.getAmount());
            System.out.println(booking.getDate());
        }
        
        Booking booking2 = new Booking(0,"test","test","test",1,"test");
        System.out.println(booking2.getTrip());
        bc.setBookingRecords(booking2);
        
        
        booking = bc.getBookingById(-1);
        if(booking != null){
            System.out.println(booking.getID());
            System.out.println(booking.getTrip());
            System.out.println(booking.getPaymentMethod());
            System.out.println(booking.getCustomerName());
            System.out.println(booking.getAmount());
            System.out.println(booking.getDate());
            
        }
 
        
    }
}

