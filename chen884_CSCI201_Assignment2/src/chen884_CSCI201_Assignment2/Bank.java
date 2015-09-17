package chen884_CSCI201_Assignment2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Bank {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int accountInput = -1;
		while(accountInput != 5){
			System.out.println("\nWelcome to the bank.\n     1) Existing Account Holder\n     2) Open a New Account\nWhat would you like to do? ");
			Scanner s = new Scanner(System.in);
			int mainInput = s.nextInt();
			if(mainInput == 1){
				FileWriter fw = null;
				PrintWriter pw = null;
				FileReader fr = null;
				BufferedReader br = null;
				boolean incorrectLogin = true;
				String username = null;
				String password = null;
				String line = null;
				CheckingAccount C;
				CheckingAccount C1;
				SavingsAccount S;
				SavingsAccount S1;
				boolean quit = true;
				boolean filenotfound = false;
				boolean quitstate = false;
				while(incorrectLogin){
					System.out.println("Username: ");
					username = s.next();
					if(quitstate){
						if(username.equals("q")) break;
						else{
							quitstate = false;
							quit = true;
						}
					}
					
					try {
						fr = new FileReader("./Users.txt");
						br = new BufferedReader(fr);
						line = br.readLine();
						while(line != null){
							String[] arr = line.split(" ");
							if(username.equals(arr[0])){
								System.out.println("Password: ");
								password = s.next();
								if(password.equals(arr[1])){
									System.out.println("Welcome to your accounts, " + username + ".");
									quit = false;
									break;
								}
							}
							line = br.readLine();
						}
						if(quit){
							incorrectLogin = true;
							System.out.println("I’m sorry, but that username and password does not match any at our bank. Please try again (or enter \'q\' to return to the main menu).");
							
							quitstate = true;
						}
						else incorrectLogin = false;
					}
					catch(FileNotFoundException e){
						System.out.println("I’m sorry, but that username and password does not match any at our bank. Please try again (or enter \'q\' to return to the main menu).");
						
						filenotfound = true;
						incorrectLogin = false;
						try {
							fw = new FileWriter("./Users.txt");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						pw = new PrintWriter(fw);
						
	
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(!quit){
					String [] arr = line.split(" ");
					C = new CheckingAccount(Double.parseDouble(arr[2]));
					C1 = C;
					if(C.getBalance() + Double.parseDouble(arr[3]) < 1000 ){
						S = new Basic(Double.parseDouble(arr[3]));
					}
					else if(C.getBalance() + Double.parseDouble(arr[3]) < 10000 ){
						S = new Premium(Double.parseDouble(arr[3]));
					}
					else{
						S = new Deluxe(Double.parseDouble(arr[3]));
					}
					S1 = S;
					while(accountInput!=5){
						System.out.println("\n     1) View Account Information\n     2) Make a Deposit\n     3) Make a Withdrawal\n     4) Determine Balance in x Years\n     5) Logout     \nWhat would you like to do? ");
						accountInput = s.nextInt();
						if(accountInput == 1){
							System.out.println("You have a Checking account with a balance of $ " + C.getBalance());
							System.out.println("You have a " + S.getAccountType() + " account with a balance of $ " + S.getBalance());
						}
						else if(accountInput == 2){
							System.out.println("Here are the accounts you have: \n     1) Checking\n     2) " + S.getAccountType() + "\nInto which account would you like to make a deposit?");
							int depIn = s.nextInt();
							if(depIn == 1){
								boolean Negative = true;
								double checkingsDeposit = 0;
								boolean tryagain = false;
								while(Negative){
									System.out.println("How much to deposit into your checking account? ");
									if(s.hasNextDouble()){
										checkingsDeposit = s.nextDouble();	
										tryagain = false;
									}
									else{
										System.out.println("\"" + s.next() + "\" is not a valid amount.");
										tryagain = true;
									}
									if(!tryagain){
										if(checkingsDeposit > 0) Negative = false;
										else System.out.println("You are not allowed to deposit a negative amount.");
								
									}
								}
								System.out.println("$" + checkingsDeposit + " has been deposited into your checking account");
								C1 = new CheckingAccount(C.getBalance() + checkingsDeposit);
							}
							else if(depIn == 2){
								boolean Negative = true;
								double savingsDeposit = 0;
								boolean tryagain = false;
								while(Negative){
									System.out.println("How much to deposit into your " + S.getAccountType() + " account? ");
									if(s.hasNextDouble()){
										savingsDeposit = s.nextDouble();	
										tryagain = false;
									}
									else{
										System.out.println("\"" + s.next() + "\" is not a valid amount.");
										tryagain = true;
									}
									if(!tryagain){
										if(savingsDeposit > 0) Negative = false;
										else System.out.println("You are not allowed to deposit a negative amount.");
								
									}
								}
								System.out.println("$" + savingsDeposit + " has been deposited into your " + S.getAccountType() + " account");
								S1 = new SavingsAccount(S.getBalance() + savingsDeposit);
								if(S1.getBalance() + C1.getBalance() < 1000){
									S1 = new Basic(S1.getBalance());
								}
								else if(S1.getBalance() + C1.getBalance() < 10000){
									S1 = new Premium(S1.getBalance());
								}
								else{
									S1 = new Deluxe(S1.getBalance());
								}
							}
						}
						else if(accountInput == 3){
							System.out.println("Here are the accounts you have: \n     1) Checking\n     2) " + S.getAccountType() + "\nFrom which account would you like to withdraw?");
							int withOut = s.nextInt();
							if(withOut == 1){
								boolean Negative = true;
								boolean TooMuch = true;
								double checkingsWithdrawal = 0;
								boolean tryagain = false;
								while(Negative||TooMuch){
									System.out.println("How much to withdraw? ");
									if(s.hasNextDouble()){
										checkingsWithdrawal= s.nextDouble();	
										tryagain = false;
									}
									else{
										System.out.println("\"" + s.next() + "\" is not a valid amount.");
										tryagain = true;
									}
									if(!tryagain){
										if(checkingsWithdrawal > C.getBalance()){
											Negative = false;
											TooMuch = true;
											System.out.println("You do not have $" + checkingsWithdrawal + " in your checking account.");
										}
										else if(checkingsWithdrawal > 0){
											Negative = false;
											TooMuch = false;
										}
										else{
											System.out.println("You are not allowed to withdrawal a negative amount.");
											Negative = true;
											TooMuch = false;
										}
								
									}
								}
								System.out.println("$" + checkingsWithdrawal + " withdrawn from your checking account");
								C1 = new CheckingAccount(C.getBalance() - checkingsWithdrawal);
							}
							else if(withOut == 2){
								boolean Negative = true;
								boolean TooMuch = true;
								double savingsWithdrawal = 0;
								boolean tryagain = false;
								while(Negative||TooMuch){
									System.out.println("How much to withdraw? ");
									if(s.hasNextDouble()){
										savingsWithdrawal= s.nextDouble();	
										tryagain = false;
									}
									else{
										System.out.println("\"" + s.next() + "\" is not a valid amount.");
										tryagain = true;
									}
									if(!tryagain){
										if(savingsWithdrawal > C.getBalance()){
											Negative = false;
											TooMuch = true;
											System.out.println("You do not have $" + savingsWithdrawal + " in your " + S.getAccountType() + " account.");
										}
										else if(savingsWithdrawal > 0){
											Negative = false;
											TooMuch = false;
										}
										else{
											System.out.println("You are not allowed to withdrawal a negative amount.");
											Negative = true;
											TooMuch = false;
										}
								
									}
								}
								System.out.println("$" + savingsWithdrawal + " withdrawn from your " + S.getAccountType() + " account.");
								S1 = new SavingsAccount(S.getBalance() - savingsWithdrawal);
								if(S1.getBalance() + C1.getBalance() < 1000){
									S1 = new Basic(S1.getBalance());
								}
								else if(S1.getBalance() + C1.getBalance() < 10000){
									S1 = new Premium(S1.getBalance());
								}
								else{
									S1 = new Deluxe(S1.getBalance());
								}
							}
						}
						else if(accountInput == 4){
							System.out.println("In how many years? ");
							int years = s.nextInt();
							System.out.println("Your " + S.getAccountType() + " account will have the following: ");
							System.out.println("Year          Amount          Interest          ");
							System.out.println("----          ------          --------");
							for(int i = 0; i < years; i ++){
								System.out.print(i + "             $");
								System.out.printf("%.2f", S1.getBalanceAfterNumYears(i));
								System.out.print("          $");
								System.out.printf("%.2f", S1.getBalanceAfterNumYears(i+1)-S1.getBalanceAfterNumYears(i));
								System.out.println();
							}
							System.out.print(years + "             $");
							System.out.printf("%.2f", S1.getBalanceAfterNumYears(years));
							System.out.println();
						}
						else if(accountInput == 5){
							System.out.println("Thank you for coming to the bank!");
						}
						if(accountInput == 2 || accountInput == 3){
							line = null;
							try {
								fr = new FileReader("./Users.txt");
								br = new BufferedReader(fr);
								String totalText = "";
								while((line = br.readLine()) != null){
									totalText += line + '\n';
								}
								String text = username + " " + password + " " + C.getBalance() + " " + S.getBalance();
								String text1 = username + " " + password + " " + C1.getBalance() + " " + S1.getBalance();
								totalText = totalText.replaceAll(text,text1);
								
								fw = new FileWriter("./Users.txt");
								pw = new PrintWriter(fw);
								pw.write(totalText);
							}
							catch(FileNotFoundException e){
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								fw.close();
								br.close();
								fr.close();
								pw.flush();
								pw.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
			else if(mainInput == 2){
				FileWriter fw = null;
				PrintWriter pw = null;
				FileReader fr = null;
				BufferedReader br = null;
				boolean nameTaken = true;
				String username = null;
				boolean quit = false;
				boolean filenotfound = false;
				boolean quitstate = false;
				while(nameTaken){
					System.out.println("Username: ");
					username = s.next();
					if(quitstate){
						if(username.equals("q")) break;
						else{
							quitstate = false;
							quit = false;
						}
					}
					
					try {
						fr = new FileReader("./Users.txt");
						br = new BufferedReader(fr);
						String line = br.readLine();
						while(line != null){
							String[] arr = line.split(" ");
							if(username.equals(arr[0])){
								System.out.println("I’m sorry, but the username \" " + username + "\" is already associated with an account. Please try again (or enter \'q\' to return to the main menu).");
								quit = true;
								break;
							}
							line = br.readLine();
						}
						if(quit){
							nameTaken = true;
							quitstate = true;
						}
						else nameTaken = false;
					}
					catch(FileNotFoundException e){
						filenotfound = true;
						nameTaken = false;
						try {
							fw = new FileWriter("./Users.txt");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						pw = new PrintWriter(fw);
						
	
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(!quit){
					if(!filenotfound){
						try {
							fw = new FileWriter("./Users.txt", true);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						pw = new PrintWriter(fw);
					}
					System.out.println("Great, that username is not in use!");
					System.out.println("Password: ");
					String password = s.next();
					pw.print(username + " " + password + " ");
					
					boolean Negative = true;
					double checkingsDeposit = 0;
					boolean tryagain = false;
					while(Negative){
						System.out.println("How much would you like to deposit in checking? ");
						if(s.hasNextDouble()){
							checkingsDeposit = s.nextDouble();	
							tryagain = false;
						}
						else{
							System.out.println("\"" + s.next() + "\" is not a valid amount.");
							tryagain = true;
						}
						if(!tryagain){
							if(checkingsDeposit > 0) Negative = false;
							else System.out.println("You are not allowed to deposit a negative amount.");
					
						}
					}
					CheckingAccount C = new CheckingAccount(checkingsDeposit);
					
					Negative = true;
					tryagain = false;
					double savingsDeposit = 0;
					while(Negative){
						System.out.println("How much would you like to deposit in savings? ");
						if(s.hasNextDouble()){
							savingsDeposit = s.nextDouble();
							tryagain = false;
						}
						else{
							System.out.println("\"" + s.next() + "\" is not a valid amount.");
							tryagain = true;
						}
						if(!tryagain){
							if(savingsDeposit > 0) Negative = false;
							else System.out.println("You are not allowed to deposit a negative amount.");
					
						}
					}
					
					pw.println(checkingsDeposit + " " + savingsDeposit + " ");
					if(savingsDeposit + C.getBalance() < 1000.00){
						Basic S = new Basic(savingsDeposit);
					}
					else if(savingsDeposit + C.getBalance() < 10000.00){
						Premium S = new Premium(savingsDeposit);
					}
					else{
						Deluxe S = new Deluxe(savingsDeposit);
					}
					
					try {
						fw.close();
						br.close();
						fr.close();
						pw.flush();
						pw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}
