import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author thors
 */
public class Viðmót extends javax.swing.JFrame {

    
    
    public Viðmót() {
        initComponents();
        jTable1.setVisible(false);
        
    }
    
     private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        jTable1.setVisible(true);
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:\\C:\\Users\\thors\\Desktop\\Booking.db");
            String query = "select * from bookingInfo";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
           
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error");
        }
    }
    
     private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        try{
          Class.forName("org.sqlite.JDBC");
          Connection conn = DriverManager.getConnection("jdbc:sqlite:\\C:\\Users\\thors\\Desktop\\Booking.db");
          String query = "insert into bookingInfo (ID,Trip,paymentMethod,customerName) values (?,?,?,?)";
          PreparedStatement pst = conn.prepareStatement(query);
          pst.setString(1, jTextField1.getText());
          pst.setString(2, jTextField2.getText());
          pst.setString(3, jTextField3.getText());
          pst.setString(4, jTextField4.getText());
          pst.execute();
          pst.close();
          
          JOptionPane.showMessageDialog(null, "Vistað");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error");
        }
    }
    
    public static void main(String args[]) {
      java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Viðmót().setVisible(true);
            }
        });
    }
    
    Svo fullt af javaSwing breytum skilgreindar hérna
    
    }
    
    

    
    
