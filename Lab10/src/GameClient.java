

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class GameClient extends Thread{

	private PrintWriter pw;
	private BufferedReader br;
	private String attack;
	private static JLabel health1;
	private int health11;
	private int health22;

	private static JLabel health2;
	private static JFrame frame;
	private static JButton a;
	private static JButton m;
	private static long start;
	public GameClient(String hostName, int port, Scanner scan) {
		
		health11 = 10;
		health22 = 10;

		frame = new JFrame("FF7");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel top = new JPanel(new BorderLayout());
		JPanel center = new JPanel(new BorderLayout());
		health1 = new JLabel("10/10", JLabel.CENTER);
		health2 = new JLabel("Waiting for other player", JLabel.CENTER);
		a = new JButton("Sword");
		m = new JButton("Magic");
		a.setEnabled(false);
		m.setEnabled(false);
		//try to connect to server
		try {
			Socket s = new Socket(hostName, port);
			this.pw = new PrintWriter(s.getOutputStream());
			this.br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			
			this.start(); //start the thread (run method)
			
		} catch (Exception e) {
			System.out.println("Error trying to connect to server " + e.getMessage());
		}  
		a.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				long end = System.nanoTime();
				if((end-start)/1000000000 >=3){
					start = System.nanoTime();
					pw.println("Sword");
					pw.flush();
					health22 -= 2;
					health2.setText(health22+"/10");		
				}
				if(health22 <= 0){
					health2.setText("0/10");
					JOptionPane.showMessageDialog(frame, 
							"You Won!", 
							"Message Dialog", 
							JOptionPane.PLAIN_MESSAGE);
					a.setEnabled(false);
					m.setEnabled(false);
				}
			}
			
		});
		m.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
		
				long end = System.nanoTime();
				if((end-start)/1000000000 >=3){
					start = System.nanoTime();
					Random x = new Random();
					int r = x.nextInt(5) + 1;
					pw.println("Magic"+r);
					pw.flush();
					health22-=r;
					health2.setText(health22+"/10");
				}

				if(health22 <= 0){
					health2.setText("0/10");
					JOptionPane.showMessageDialog(frame, 
							"You Won!", 
							"Message Dialog", 
							JOptionPane.PLAIN_MESSAGE);
					a.setEnabled(false);
					m.setEnabled(false);
				}
			}
			
		});
		top.add(health1, BorderLayout.NORTH);
		top.add(health2, BorderLayout.CENTER);
		frame.getContentPane().add(top, BorderLayout.NORTH);
		center.add(a, BorderLayout.NORTH);
		center.add(m, BorderLayout.CENTER);
		frame.getContentPane().add(center, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void run(){
		while(true){
			try {
				while(true){
					String line = br.readLine();
					System.out.println(line);
					if(line.equals("Sword")){
						health11 -=2;
						health1.setText(health11+"/10");
					}
					else if(line.substring(0, 5).equals("Magic")){
						int r = Integer.parseInt(line.substring(5));
						health11 -= r;
						health1.setText(health11+"/10");	
					}
					else if(line.equals("Start")){
						health2.setText("10/10");
						start = System.nanoTime();
						long end = System.nanoTime();
						while((end-start)/1000000000 <=3){
							end = System.nanoTime();
						}
						a.setEnabled(true);
						m.setEnabled(true);
					}
					if(health11 <= 0){
						health1.setText("0/10");
						JOptionPane.showMessageDialog(frame, 
								"You Lost!", 
								"Message Dialog", 
								JOptionPane.PLAIN_MESSAGE);
						a.setEnabled(false);
						m.setEnabled(false);
					}
				}
			} catch (IOException e) {
				System.out.println("Trouble reading line in Chat Client: " + e.getMessage());
			}
		}
	}

	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		
		//get hostName and port
		String hostName = "localhost";
		int port = 1234;
		
		new GameClient(hostName, port, scan);
	}
	
}
