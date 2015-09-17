import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Test extends JFrame {
	PanelC p;
	ButtonC J;
	JButton J1;
	public Test(){
		super();
		setSize(500,500);
		p = new PanelC();
		J = new ButtonC("Don't click me");
		J.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				JOptionPane.showMessageDialog(Test.this, "WHY DID YOU CLICK ME AGAIN", "Message Dialog", JOptionPane.INFORMATION_MESSAGE, null);
			}
		});
		
		J1 = new JButton("Click Once");
		J1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				p.remove(J1);
				p.add(J, BorderLayout.CENTER);
				p.validate();
			}
			
		});
		p.add(J1, BorderLayout.CENTER);
		
		add(p);
		setVisible(true);
	}
	
	public static void main(String[] args){
		Test t = new Test();
	}
}
