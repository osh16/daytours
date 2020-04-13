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

    public static ResultSet retrieve(String query) {
	ResultSet result = null;
	try {
	    PreparedStatement p = conn.prepareStatement(query);
	    result = p.executeQuery();
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
}
