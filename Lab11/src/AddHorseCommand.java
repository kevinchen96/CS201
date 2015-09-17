import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.locks.ReentrantLock;


public class AddHorseCommand extends SQLCommand implements Runnable{

	public AddHorseCommand(ReentrantLock queryLock) {
		super(queryLock);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
		Connection conn = null;
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/lab11", "root", "lightning");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 String query1 = "SELECT word FROM words ORDER BY RAND() LIMIT 2";
		 
		 Statement statement;
		 ResultSet t = null;
		 try {
				statement = conn.createStatement();
				t = statement.executeQuery(query1);
		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 String horseName = null;
		 try {
			t.next();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 try {
			horseName = t.getString("word");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 try {
			t.next();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 try {
			horseName = horseName + " " + t.getString("word");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 String query = "INSERT INTO horse (name) VALUES (?)";
		 PreparedStatement p = null;
		 try {
			p = conn.prepareStatement(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			p.setString(1, horseName);
			p.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 return true;
	}

}
