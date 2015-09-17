import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;


public class MostTimesPlacedCommand extends SQLCommand {
	int place1;
	public MostTimesPlacedCommand(ReentrantLock queryLock, int place) {
		super(queryLock);
		this.place1 = place;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute() {
		ArrayList<String> Name = new ArrayList<String>();
		ArrayList<Integer> times = new ArrayList<Integer>();
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
		 String query1 = "SELECT horse_id, name FROM horse";
		 String place = "SELECT horse_id, place FROM race_result";
		 
		 Statement statement1;
		
		 ResultSet t1 = null;
		 try {
				
				statement1 = conn.createStatement();
				t1 = statement1.executeQuery(place);
		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 //System.out.println(Name.size() + " : size");
		 int raceCount = 0;
		 boolean found = false;
		 try {
			while(t1.next()){
				String Horse = null;
				if(Integer.parseInt(t1.getString("place")) == place1){
					//System.out.println("Found another place");
					Statement statement = null;
					statement = conn.createStatement();
					ResultSet t = statement.executeQuery(query1);
					while(t.next()){
						if(Integer.parseInt(t.getString("horse_id"))==Integer.parseInt(t1.getString("horse_id"))){
							//System.out.println("Found horse");
							Horse = t.getString("name");
							//System.out.println("Horse is : " + Horse);
						}
					}
					for(int i = 0; i < Name.size(); i++){
						//System.out.println(Name.size());
						if(Horse.equals(Name.get(i))){
							int j = times.get(i) + 1;
							times.set(i, j);
							found = true;
						}
					}
					if(found == false){
						Name.add(Horse);
						times.add(1);
						//System.out.println("Horse");
					}
					found = false;
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 int max = times.get(0);
		 int maxIndex = 0;
		 for(int i = 1; i < times.size(); i++){
			 if(max < times.get(i)){
				 max = times.get(i);
				 maxIndex = i;
			 }
		 }
		System.out.println(Name.get(maxIndex) + " finished number " + place1 + " the most times at " + max + " times");
		
		return true;
	}

}
