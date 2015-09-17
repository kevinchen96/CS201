import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;


public class test {
	public static void main(String args[]){
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
		 String delete = "DELETE FROM horse";
		 String delete1 = "DELETE FROM race_result";
		 Statement statement, statement1;
		 try {
				statement = conn.createStatement();
				statement1 = conn.createStatement();
				statement.executeUpdate(delete);
				statement1.executeUpdate(delete1);
		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		ReentrantLock lock = new ReentrantLock();
		for(int i = 0; i < 8; i++){
			AddHorseCommand x = new AddHorseCommand(lock);
			Thread z = new Thread(x);
			z.start();
		}
		for(int i = 0; i < 10 ; i++){
			RaceCommand race = new RaceCommand(lock);
			Thread th = new Thread(race);
			th.start();
		}
		for(int i = 0; i < 8; i++){
			MostTimesPlacedCommand race = new MostTimesPlacedCommand(lock, i+1);
			Thread t = new Thread(race);
			t.start();
		}
	}
}
