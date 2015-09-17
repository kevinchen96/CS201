

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

public class GameServer {
	
	//synchronized -> only 1 thread can access, vector is synchronized, ArrayList is not.
	//arralist better for single threaded programs
	//vectors better for multi-threaded programs
	private Vector<GameThread> ctVector = new Vector<GameThread>();
	
	public GameServer(int port){
		System.out.println("Starting server...");
		try {
			boolean start = false;
			int count = 0;
			//server socket lets us listen to a port
			ServerSocket ss = new ServerSocket(port);
			while(count < 2){ //while true allows for multiple (as many as possible) clients to connect
				Socket s = ss.accept();
				//create thread to deal with the socket
				count++;
				if(count == 2){
					System.out.print("Should STart");
					start = true;
				}
				GameThread ct = new GameThread(s, this, start);
				ctVector.add(ct); //add the chat thread to a list
				ct.start(); //starts the thread
			}
		} catch (IOException e) {
			System.out.println("ioe reading port: " + e.getMessage());
		}
	}
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		int port = 1234;
		new GameServer(port);
	}

	public void sendMessage(String msg, GameThread ct) {
		for(GameThread c : ctVector){
			if(ct != c){
				if(msg != null){
					c.send(msg);
				}
			}
		}
	}

	public void sendMessageAll(String string, GameThread ct) {
		// TODO Auto-generated method stub
		for(GameThread c : ctVector){
				if(string != null){
					c.send(string);
				}
		}
	}
	
}
