import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class test extends JFrame {

	public test() {
		super("Tic-Tac-Toe");
		setSize(400,400);
		setLocation(200,200);
		JLabel l = new JLabel("Player One's Turn");
		add(l, BorderLayout.NORTH);
		JPanel J1 = new JPanel();
		JPanel J2 = new JPanel();
		J1.setLayout(new GridLayout(3, 3));
		
		
		JButton button1 = new JButton();
		JButton button2 = new JButton();
		JButton button3 = new JButton();
		JButton button4 = new JButton();
		JButton button5 = new JButton();
		JButton button6 = new JButton();
		JButton button7 = new JButton();
		JButton button8 = new JButton();
		JButton button9 = new JButton();
		class buttonAdapter implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!(l.getText().equals("Player One Won!")) && !(l.getText().equals("Player Two Won!"))){
					
				String s = "";
				if(l.getText().equals("Player One's Turn")){
					l.setText("Player Two's Turn");
					s = "X";
				}
				else{
					l.setText("Player One's Turn");
					s = "O";
				}
				((JButton) e.getSource()).setText(s);
				((JButton) e.getSource()).setEnabled(false);
				if(button1.getText().equals(button2.getText()) && button2.getText().equals(button3.getText())){
					if(button1.getText().equals("X")){
						l.setText("Player One won!");
					}
					else if(button1.getText().equals("O")){
						l.setText("Player Two won!");
					}
				}
				else if(button4.getText().equals(button5.getText()) && button5.getText().equals(button6.getText())){
					if(button5.getText().equals("X")){
						l.setText("Player One won!");
					}
					else if(button5.getText().equals("O")){
						l.setText("Player Two won!");
					}
				}
				else if(button7.getText().equals(button8.getText()) && button8.getText().equals(button9.getText())){
					if(button7.getText().equals("X")){
						l.setText("Player One won!");
					}
					else if(button7.getText().equals("O")){
						l.setText("Player Two won!");
					}
				}
				else if(button1.getText().equals(button4.getText()) && button4.getText().equals(button7.getText())){
					if(button1.getText().equals("X")){
						l.setText("Player One won!");
					}
					else if(button1.getText().equals("O")){
						l.setText("Player Two won!");
					}
				}
				else if(button2.getText().equals(button5.getText()) && button5.getText().equals(button8.getText())){
					if(button5.getText().equals("X")){
						l.setText("Player One won!");
					}
					else if(button5.getText().equals("O")){
						l.setText("Player Two won!");
					}
				}
				else if(button3.getText().equals(button6.getText()) && button6.getText().equals(button9.getText())){
					if(button3.getText().equals("X")){
						l.setText("Player One won!");
					}
					else if(button3.getText().equals("O")){
						l.setText("Player Two won!");
					}
				}
				else if(button1.getText().equals(button5.getText()) && button5.getText().equals(button9.getText())){
					if(button5.getText().equals("X")){
						l.setText("Player One won!");
					}
					else if(button5.getText().equals("O")){
						l.setText("Player Two won!");
					}
				}
				else if(button3.getText().equals(button5.getText()) && button5.getText().equals(button7.getText())){
					if(button5.getText().equals("X")){
						l.setText("Player One won!");
					}
					else if(button5.getText().equals("O")){
						l.setText("Player Two won!");
					}
				}
				}
		
			}
		}
		button1.addActionListener(new buttonAdapter());
		button2.addActionListener(new buttonAdapter());
		button3.addActionListener(new buttonAdapter());
		button4.addActionListener(new buttonAdapter());
		button5.addActionListener(new buttonAdapter());
		button6.addActionListener(new buttonAdapter());
		button7.addActionListener(new buttonAdapter());
		button8.addActionListener(new buttonAdapter());
		button9.addActionListener(new buttonAdapter());

		J1.add(button1);
		J1.add(button2);
		J1.add(button3);
		J1.add(button4);
		J1.add(button5);
		J1.add(button6);
		J1.add(button7);
		J1.add(button8);
		J1.add(button9);
		
		add(J1);
		JButton button10 = new JButton("Restart Game");
		button10.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				l.setText("Player One's Turn");
				button1.setEnabled(true);
				button2.setEnabled(true);
				button3.setEnabled(true);
				button4.setEnabled(true);
				button5.setEnabled(true);
				button6.setEnabled(true);
				button7.setEnabled(true);
				button8.setEnabled(true);
				button9.setEnabled(true);
				button1.setText("");
				button1.setText("");
				button2.setText("");
				button3.setText("");
				button4.setText("");
				button5.setText("");
				button6.setText("");
				button7.setText("");
				button8.setText("");
				button9.setText("");

			}
			
		});
		add(button10, BorderLayout.SOUTH);
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test t = new test();
	}

}
