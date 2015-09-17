import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class test {

	public static void main(String[] args) {
		Connection conn = null;
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/data", "root", "lightning");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println("Enter your first name: ");
		 Scanner s = new Scanner(System.in);
		 String first = s.next();
		 System.out.println("Enter your last name: ");
		 String last = s.next();
		 String query = "INSERT INTO user (firstName,lastName) VALUES (?,?)";
		 PreparedStatement p = null;
		 try {
			p = conn.prepareStatement(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			p.setString(1, first);
			p.setString(2, last);
			p.executeUpdate();
			p.setString(1, "cocoa");
			p.setString(2, "puffs");
			p.executeUpdate();
			p.setString(1, "hello");
			p.setString(2, "kitty");
			p.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 Statement statement;
		 String query2 = "SELECT firstName, lastName FROM user";
		 ResultSet t = null;
		 try {
			statement = conn.createStatement();
			t = statement.executeQuery(query2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		  try {
			while(t.next()){
				System.out.println(t.getString("firstName") + " " + t.getString("lastName"));
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
