import java.sql.*;

public class Connect {
    public static void connect(String query) {
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet r = null;

	try {
	    Class.forName("org.sqlite.JDBC");
	    conn = DriverManager.getConnection("jdbc:sqlite:daytours.db");
	    stmt = conn.createStatement();
	    r = stmt.executeQuery(query);
	    if (r.next()) System.out.println(r.getDouble(1));
	    
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    if (r != null) r.close();
	    if (stmt != null) stmt.close();
	    if (conn != null) conn.close();
	}
    }

    public static void main(String[] args) {
	connect("select avg(stars) from ratings");
    }
}
