import java.sql.*;

public class Connect {
    public static void connect(String query) throws Exception {
	Connection conn;

	try {
	    Class.forName("org.sqlite.JDBC");
	    conn = DriverManager.getConnection("jdbc:sqlite:daytours.db");
	} catch (Exception e) {
	    e.printStackTrace();
	}     
    }

    public static void main(String[] args) {
	try {
	    connect("select avg(stars) from ratings");
	} catch(Exception e) {
	    e.printStackTrace();
	}

    }
}
