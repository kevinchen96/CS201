import java.util.InputMismatchException;
import java.util.Scanner;


public class Maze {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Welcome to the Maze Game!");
		System.out.println("Are you an admin?");
		Scanner s = new Scanner(System.in);
		String admin = s.next();
		System.out.println("How many rows are in the grid?");
		int rows = Check(s);
		System.out.println("How many columns are in the grid?");
		int cols = Check(s);
		System.out.println("Enter " + rows*cols +" letters separated by spaces");
		String content = Check2(s, rows*cols);
		result(rows, cols, content, admin.equals("A"));
		
	}

	private static String Check2(Scanner s, int size) {
		// TODO Auto-generated method stub
		boolean check = true;
		String input = "";
		input = s.nextLine();
		while(check){
			input = s.nextLine();
			input = input.replaceAll(" ","");
			if(input.length() == size){
				check = false;
			}
			else {
				System.out.println(input.length() + " characters: " + input);
				System.out.println("Error: That is not 27 letters. Try again!");
				//String temp = s.nextLine();
			}
			

		}
		return input;
	}

	private static int Check(Scanner s) {
		// TODO Auto-generated method stub
		boolean check = true;
		int input = 0;
		while(check){
			try{
				input = s.nextInt();
				check = false;
			}
			catch(InputMismatchException exception){
				System.out.println("Error: that is not a number. Please try again");
				check = true;
				String temp = s.nextLine();
			}
		}
		return input;
		
	}

	private static void result(int rows, int cols, String content, boolean admin) {
		// TODO Auto-generated method stub
		int curRow = 0;
		int curCol = 0;
		if(admin) System.out.println("Here is the grid, just for you admin!");
		int x = 0;
		if(admin){
			while(x < rows){
				System.out.println(content.substring(x*cols, (x+1)*cols));
				x++;
			}
		}
		if(admin) System.out.println("Here is the step by step, just for you admin!");
		if(admin) System.out.println("R:" + curRow + " C:" + curCol + " L:" + content.substring(0, 1));
		int state = 1, prevstate = 1;
		String result = content.substring(0, 1);
		if(rows>1){
			while(curCol + 1 < cols){
				if(curRow == 0){
					state = 1;
					prevstate = 1;
				}
				else if(curRow+1 < rows & prevstate == 1){ 
					state = 1;
				}
				else if(curRow < rows & prevstate == -1){
					state = -1;
				}
				else if(curRow + 1 == rows){
					state = -1;
					prevstate = -1;
				}
			
				curRow += state;
				curCol ++;
				if(admin) System.out.println("R:" + curRow + " C:" + curCol + " L:" + content.substring(curRow*cols+curCol, curRow*cols+curCol+1));
				result = result + content.substring(curRow*cols+curCol, curRow*cols+curCol+1);
			}
		}
		System.out.println("Result: " + result);
		System.out.println("Thanks for using this program!");
	}

}
