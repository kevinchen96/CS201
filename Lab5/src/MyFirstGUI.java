

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EtchedBorder;

public class MyFirstGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public MyFirstGUI() throws IOException {
		super();
		setSize(500, 500);
		setLocation(400, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//creating tabs
		JTabbedPane tabbedPane = new JTabbedPane();
		JPanel firstPanel = new JPanel(new BorderLayout());
		JPanel secondPanel = new JPanel(new BorderLayout());
		//adding a panel of buttons
		JPanel jp = new JPanel();
		jp.setPreferredSize(new Dimension(470, 38));
		jp.setBorder(BorderFactory.createLineBorder(Color.black));
		JButton button1 = new JButton("Button 1");
		jp.add(button1);
		JButton button2 = new JButton("Button 2");
		//setting color of button2
		button2.setBackground(Color.RED);
		button2.setContentAreaFilled(false);
        button2.setOpaque(true);
		jp.add(button2);
		//add(jp, BorderLayout.NORTH);
		firstPanel.add(jp, BorderLayout.NORTH);
		//adding JLabel in the south border
		JPanel southPanel = new JPanel(new BorderLayout());
		JLabel southLabel = new JLabel("This is the south quadrant");
		JLabel westLabel = new JLabel("West");
		JLabel eastLabel = new JLabel("East");
		southLabel.setBorder(BorderFactory.createRaisedBevelBorder());
		southPanel.add(southLabel, BorderLayout.CENTER);
		southPanel.add(westLabel, BorderLayout.WEST);
		southPanel.add(eastLabel, BorderLayout.EAST);
		southPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		//add(southPanel, BorderLayout.SOUTH);
		firstPanel.add(southPanel, BorderLayout.SOUTH);
		//adding tabs in JTabbedPane
		tabbedPane.add("First", firstPanel);
		tabbedPane.add("panda", secondPanel);
		add(tabbedPane);
		setVisible(true);
		//second tab
		
		/*BufferedImage picture = ImageIO.read(new File("panda.jpg"));
		JLabel picLabel = new JLabel(new ImageIcon(picture));
		*/
		ImageIcon imageIcon = new ImageIcon("panda.jpg"); 
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(450, 450,  java.awt.Image.SCALE_SMOOTH);   
		imageIcon = new ImageIcon(newimg);  
		JLabel picLabel = new JLabel(imageIcon);
		secondPanel.add(picLabel);
	}
	
	
	public static void main(String[] args) throws IOException {
		MyFirstGUI mfgui = new MyFirstGUI();
	}
}