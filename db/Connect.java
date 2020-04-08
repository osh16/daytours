import java.sql.*;
/*

Connection connection = null;
try {
    // skipanir sem vid keyrum
    String stmt1 = "insert into moviestar values('Brad Pitt','Hollywood','M','18-12-1963')";
    String stmt2 = "select avg(length) as avg from movie";

    // bua til tengingu vid db
    connection = DriverManager.getConnection("jdbc:sqlite:Movie.db");

    // prepare statement
    PreparedStatement p = connection.prepareStatement(stmt1);

    // keyra stmt1 
    p.executeUpdate();

    // undirbua og keyra stmt2
    p = connection.prepareStatement(stmt2);
    ResultSet rs = p.executeQuery();

    // prenta draslid ut
    System.out.println("average length movies: " + rs.getString("avg"));
    
    // loka resultset
    rs.close();
*/
    /*
    public static void connect(String query) throws Exception {
	Connection conn = null;

	try {
	    Class.forName("org.sqlite.JDBC");
	    conn = DriverManager.getConnection("jdbc:sqlite:daytours.db");
	    PreparedStatement p = conn.prepareStatement(query);
	    ResultSet rs = p.executeQuery();
	    System.out.println(rs.getString("avg"));
	    rs.close();
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    try {
		if (conn != null) {
		    conn.close();
		}
	    } catch (Exception e2) {
		System.err.println(e2);
	    }
	}
    }
    */

public class Connect {
    public static Connection connect() throws Exception {
	Connection conn = null;

	try {
	    Class.forName("org.sqlite.JDBC");
	    conn = DriverManager.getConnection("jdbc:sqlite:daytours.db");
	} catch (Exception e) {
	    e.printStackTrace();
	} 
	return conn;
    }
    
    public static void insert(Connection conn, String query) {

    }
    
    public static String retrieve(Connection conn, String query) {
	String result = null;
	try {
	    PreparedStatement p = conn.prepareStatement(query);
	    ResultSet rs = p.executeQuery();
	    result = rs.getString(1);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return result;

    }

    public static void close(Connection conn) {
	try {
	    if (conn != null) {
		conn.close();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public static void main(String[] args) {
	try {
	    Connection conn = connect();
	    System.out.println(retrieve(conn, "select avg(stars) as avg from ratings"));
	    close(conn);
	} catch(Exception e) {
	    e.printStackTrace();
	}

    }
}
