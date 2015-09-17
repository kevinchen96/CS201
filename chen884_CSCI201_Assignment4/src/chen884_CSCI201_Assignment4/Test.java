package chen884_CSCI201_Assignment4;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Test extends JFrame {
	private int degrees[][];
	private String type[][];
	private static String color[];
	private static int ai[];
	private static double speed[];
	private static int locx[];
	private static int locy[];
	RoadPanel road;
	static Graphics g;
	public Test(){
		super("Roadway Simulator");
		setSize(800, 600);
		setMinimumSize(new Dimension(670,516));
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		JMenuBar jmb = new JMenuBar();
		JMenu openfile = new JMenu("Open File");
		jmb.add(openfile);
		this.setJMenuBar(jmb);
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		String [] columnNames = {"Car#", "X", "Y"};
		Object [][] data = {};
		JTable tables = new JTable(data, columnNames);
		JScrollPane jsp = new JScrollPane(tables);
		panel.add(jsp, BorderLayout.EAST);
		jsp.setPreferredSize(new Dimension(200, 800));
		add(panel);
		DrawPanel paneldraw = new DrawPanel();
		paneldraw.setPreferredSize(new Dimension(600,600));
		panel.add(paneldraw);
		setVisible(true);
		openfile.addMenuListener(new MenuListener(){

			@Override
			public void menuCanceled(MenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void menuDeselected(MenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void menuSelected(MenuEvent arg0) {
				// TODO Auto-generated method stub
				File file = null;
				// TODO Auto-generated method stub
				JFileChooser c = new JFileChooser();
				//FileNameExtensionFilter f = new FileNameExtensionFilter("Images", "jpg", "gif", "png");
				//c.setFileFilter(f);
				int returnVal = c.showOpenDialog(getContentPane());
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					file = c.getSelectedFile();
					String s = c.getSelectedFile().getName();
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder dBuilder = null;
					try {
						dBuilder = dbFactory.newDocumentBuilder();
					} catch (ParserConfigurationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Document doc = null;
					try {
						doc = dBuilder.parse(file);
					} catch (SAXException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					doc.getDocumentElement().normalize();
					type = new String[9][9];
					degrees = new int[9][9];
					NodeList nList = doc.getElementsByTagName("row");
					for(int i = 0; i < 9; i++){
						Node nNode = nList.item(i);
						for(int j = 0; j < 9; j++){
							if (nNode.getNodeType() == Node.ELEMENT_NODE) {
								Element eElement = (Element) nNode;
								Element eElement1 = ((Element) eElement.getElementsByTagName("tile").item(j));
								type[i][j] = eElement1.getAttribute("type");
								degrees[i][j] = Integer.parseInt(eElement1.getAttribute("degree"));
							}
						}
					}
					color = new String[5];
					ai = new int[5];
					speed = new double[5];
					locx = new int[5];
					char temp[] = new char[5];
					locy = new int[5];
					NodeList list = doc.getElementsByTagName("car");
					for(int i = 0; i < list.getLength(); i++){
						Node nNode = list.item(i);
						if (nNode.getNodeType() == Node.ELEMENT_NODE) {
							Element eElement = (Element) nNode;
							color[i] = eElement.getAttribute("color");
							ai[i] = Integer.parseInt(eElement.getAttribute("ai"));
							speed[i] = Double.parseDouble(eElement.getAttribute("speed"));
							Element eElement1 = ((Element) eElement.getElementsByTagName("location").item(0));
							locx[i] = Integer.parseInt(eElement1.getAttribute("x"));
							temp[i] = eElement1.getAttribute("y").charAt(0);
							locy[i] = temp[i] - 65;
						}
					}
					panel.remove(paneldraw);
					road = new RoadPanel(type, degrees, color, ai, speed, locx, locy, list.getLength());
					road.setPreferredSize(new Dimension(600,600));
					panel.add(road);
					for(int i = 0; i < list.getLength(); i++){
						CarThread car = new CarThread(road, color[i], ai[i], speed[i], locx[i], locy[i], i);
						car.start();
					}
					validate();
				}
				
			}
			
		});
		
		
	}
	public static void main(String[] args){
		Test t = new Test();
	}
}
