import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.locks.ReentrantLock;


public class RaceCommand extends SQLCommand{

	public RaceCommand(ReentrantLock queryLock) {
		super(queryLock);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
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
				 String query1 = "SELECT horse_id FROM horse ORDER BY RAND() LIMIT 8";
				 String raceNumber = "SELECT race_number FROM race_result";
				 Statement statement;
				 Statement statement1;
				 ResultSet t = null;
				 ResultSet t1 = null;
				 try {
						statement = conn.createStatement();
						statement1 = conn.createStatement();
						t = statement.executeQuery(query1);
						t1 = statement1.executeQuery(raceNumber);
				
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 int raceCount = 0;
				 try {
					while(t1.next()){
						 raceCount = Integer.parseInt(t1.getString("race_number"));
					 }
					raceCount++;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 String query = "INSERT INTO race_result (race_number, horse_id, place) VALUES (?, ?, ?)";
				 PreparedStatement p = null;
				 try {
					p = conn.prepareStatement(query);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 int count = 0;
				 
				 try {
					while(t.next()){
						 count++;
						 p.setString(1, ""+raceCount);
						 p.setString(2,  t.getString("horse_id"));
						 p.setString(3, ""+count);
						 p.executeUpdate();
				//		 System.out.println(raceCount + " " + t.getString("horse_id" + " " + count));
					 }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		return true;
	}

}
