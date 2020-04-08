import java.sql.*;

public class Connect {
    public static Connection conn = null;
    
    public static void connect() throws Exception {
	try {
	    Class.forName("org.sqlite.JDBC");
	    conn = DriverManager.getConnection("jdbc:sqlite:daytours.db");
	} catch (Exception e) {
	    e.printStackTrace();
	} 
    }
    
    public static void insert(String query) {

    }
    
    public static ResultSet retrieve(String query) {
	ResultSet result = null;
	try {
	    PreparedStatement p = conn.prepareStatement(query);
	    //ResultSet rs = p.executeQuery();
	    result = p.executeQuery();
	    //result = rs.getString(1);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return result;

    }

    public static void close() {
	
	try {
	    if (conn != null) {
		conn.close();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    //synidaemi
    public static void main(String[] args) {
	/*try {
	    connect();
	    String results = retrieve("select avg(stars) as avg from ratings");
	    System.out.println(results);
	    close();
	} catch(Exception e) {
	    e.printStackTrace();
	}
	*/

    }
}
