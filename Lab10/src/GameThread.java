

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameThread extends Thread{
	
	private Socket s;
	private GameServer cs;
	private PrintWriter pw;
	private boolean start;
	
	public GameThread(Socket s, GameServer cs, boolean start){
		this.s = s;
		this.cs  = cs;
		this.start = start;
		try {
			this.pw = new PrintWriter(s.getOutputStream()); // print writer used to send messages
		} catch (IOException e) {
			System.out.println("IOE in GameThread for pw: " + e.getMessage());
		} 
	}
	
	public void run() {
		//a client has connected to the server
		try {
			
			 //use buffered reader to read input from client
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			if(start == true){
				cs.sendMessageAll("Start", this);		
				start = false;
			}
			while(true){ // while true to be able to read multiple lines / send multiple messages
				String line = br.readLine(); // blocking line -> waits until we get something
				cs.sendMessage(line, this); // send message to other clients
			}
		} catch (IOException e) {
			System.out.println("ioe in GameThread for br: " + e.getMessage());
		}
		
	}

	public void send(String msg) {
		pw.println(msg);
		pw.flush(); //DON'T FORGET TO FLUSH
	}
	
}
