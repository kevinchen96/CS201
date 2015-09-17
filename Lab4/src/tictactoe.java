import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class tictactoe {
	
	public static void main(String [] args) {
		System.out.println("Please enter the name of the file: ");
		Scanner s = new Scanner(System.in);
		String input = "C:/Users/kevinchen96/CS201/Lab4/src/" + s.next();
		System.out.println(input);
		System.out.println("Works here1");
		FileWriter fw = null;

		System.out.println("Works here2");
		try {
			fw = new FileWriter("output.txt");

			System.out.println("Works here3");
		}
		catch (IOException ioe) {
			System.out.println("IOException: " + ioe.getMessage());
		}

		System.out.println("Works here4");
		PrintWriter pw = new PrintWriter(fw);
		try {

			System.out.println("Works here5");
			FileReader fr = new FileReader(input);

			System.out.println("Works here6");
			BufferedReader br = new BufferedReader(fr);

			String line = br.readLine();

			if (line != null) {
				System.out.println("Works here");
				boolean neither = true;
				boolean end = false;
				char grid[][] = new char[3][3];
				for(int i = 0; i < 3; i++){
					for(int j = 0; j<3; j++){
						grid[i][j] = '_';
					}
				}
				int row = Character.getNumericValue(line.charAt(0));
				int col = Character.getNumericValue(line.charAt(3));
				try{
					char test = grid[row][col];
				}
				catch(ArrayIndexOutOfBoundsException e){
					for(int i = 0; i < 3; i++){
						for(int j = 0; j < 3; j++){
							pw.print(grid[i][j] + " ");
						}
						pw.print('\n');
					}
					pw.println("Out of Bounds");
					neither = false;
					end = true;
				}
				
				int count = 0;
				
				if(count%2 == 0){
					grid[row][col] = 'x';
				}
				else{
					grid[row][col] = 'o';
				}
				count++;
				line = br.readLine();
				while (line != null && end == false) {
					row = Character.getNumericValue(line.charAt(0));
					col = Character.getNumericValue(line.charAt(3));
					try{
						char test = grid[row][col];
					}
					catch(ArrayIndexOutOfBoundsException e){
						for(int i = 0; i < 3; i++){
							for(int j = 0; j < 3; j++){
								pw.print(grid[i][j] + " ");
							}
							pw.print('\n');
						}
						pw.println("Out of Bounds");
						neither = false;
						break;
					}
					if(grid[row][col] != '_'){
						for(int i = 0; i < 3; i++){
							for(int j = 0; j < 3; j++){
								pw.print(grid[i][j] + " ");
							}
							pw.print('\n');
						}
						pw.println("Repeated Move");
						neither = false;
						break;
					}
					
					if(count%2 == 0){
						grid[row][col] = 'x';
					}
					else{
						grid[row][col] = 'o';
					}
					if((grid[0][0] == grid[1][1])&&( grid[1][1]== grid[2][2])&&( grid[0][0]!= '_') || (grid[0][2] == grid[1][1])&&( grid[1][1]== grid[2][0])&&( grid[0][2]!= '_') || (grid[0][0] == grid[0][1])&&( grid[0][1]== grid[0][2])&&( grid[0][0]!= '_') || (grid[1][0] == grid[1][1])&&( grid[1][1]== grid[1][2])&&( grid[1][0]!= '_') || (grid[2][0] == grid[2][1])&&( grid[2][1]== grid[2][2])&&( grid[2][0]!= '_') || (grid[0][0] == grid[1][0])&&( grid[1][0]== grid[2][0])&&( grid[0][0]!= '_') ||(grid[0][1] == grid[1][1])&&( grid[1][1]== grid[2][1]) &&( grid[0][1]!= '_') ||(grid[0][2] == grid[1][2])&&( grid[1][2]== grid[2][2])&&( grid[0][2]!= '_')){
						for(int i = 0; i < 3; i++){
							for(int j = 0; j < 3; j++){
								pw.print(grid[i][j] + " ");
							}
							pw.print('\n');
						}
						if(grid[row][col] == 'x') pw.println("Player one");
						else if(grid[row][col] == 'o') pw.println("Player two");
						neither = false;
						break;
					}
					count++;
					line = br.readLine();	
				}
				if(neither == true){
					for(int i = 0; i < 3; i++){
						for(int j = 0; j < 3; j++){
							pw.print(grid[i][j] + " ");
						}
						pw.print('\n');
					}
					pw.println("Neither");
				}
			}
			else{
				for(int i = 0; i < 3; i++){
					for(int j = 0; j < 3; j++){
						pw.print("_ ");
					}
					pw.print('\n');
				}
			}
			br.close();
			fr.close();
			pw.flush();
			pw.close();
			fw.close();
		} 
		catch (FileNotFoundException e) {
			for(int i = 0; i < 3; i++){
				for(int j = 0; j < 3; j++){
					pw.print("_ ");
				}
				pw.print('\n');
			}
			pw.println("Input DNE ");
		}
		catch (IOException ioe) {
			System.out.println("IOException: " + ioe.getMessage());
		}
		
	}
}


