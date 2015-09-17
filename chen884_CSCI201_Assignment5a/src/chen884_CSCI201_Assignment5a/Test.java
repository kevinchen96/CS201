package chen884_CSCI201_Assignment5a;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;





public class Test {
	private DrawPanel paneldraw;
	private Graphics2D g;
	BufferedImage icon;
	File actualDir;
	public Test(){
		JFrame frame = new JFrame("Factory");
		frame.setSize(800, 600);
		JMenuBar jmb = new JMenuBar();
		JMenu Open = new JMenu("Open Folder");
		Object[] cols = new Object[1];
		ArrayList<String> hi = new ArrayList<String>();
		ArrayList<Integer> hi2= new ArrayList<Integer>();
		ArrayList<Integer> hi3 = new ArrayList<Integer>();
		
		ArrayList<Recipe> recipe = new ArrayList<Recipe>();
		hi2.add(999);
		hi2.add(999);
		hi2.add(999);
		hi3.add(0);
		hi3.add(0);
		hi3.add(0);
		hi3.add(0);
		hi3.add(0);
		hi3.add(0);
		Open.addMenuListener(new MenuListener(){

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
				ArrayList<ArrayList<Integer>> number = null;
				ArrayList<ArrayList<Integer>> tool = null;
				ArrayList<Integer> Location = null;
				ArrayList<Integer> time = null;
				ArrayList<Integer> hi4 = null;
				ArrayList<Integer> hi5 = null;
				int RecipeNumber;
				// TODO Auto-generated method stub
				File file = null;
				// TODO Auto-generated method stub
				JFileChooser c = new JFileChooser();
				c.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = c.showOpenDialog(frame.getContentPane());
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					File file1 = c.getSelectedFile();
					actualDir = new File(System.getProperty("user.dir") + "\\" + file1.getName() + "\\") ;
					actualDir.mkdir();
					File [] files = c.getSelectedFile().listFiles();
					//System.out.println(files.length);
					for(int i = 0; i < files.length; i++){
						String Dir = System.getProperty("user.dir") + "\\" + file1.getName() + "\\" + files[i].getName();
						File temp = new File(Dir);
						
						try {
							Files.copy(files[i].toPath(), temp.toPath());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(temp.getName().endsWith(".rcp")){
							BufferedReader x = null;
							try {
								x = new BufferedReader(new FileReader(temp));
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							String tempLine = null;
							try {
								tempLine = x.readLine();
								////System.out.println(tempLine);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							String[] stuff = tempLine.split("\\[");
							////System.out.println(stuff[1]);
							String[] stuff2 = stuff[1].split("\\]");
							////System.out.println(stuff2[0]);
							////System.out.println(Integer.parseInt(stuff2[1].substring(2,3)));
							for(int j = 0; j < Integer.parseInt(stuff2[1].split("x")[1].split(" ")[0]); j++){
								hi.add(stuff2[0] + " ... not built");
							}
							RecipeNumber = Integer.parseInt(stuff2[1].split("x")[1].split(" ")[0]);
							String templine2 = null;
							try {
								templine2 = x.readLine();
								//System.out.println(templine2);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							int times = Integer.parseInt(templine2.split("\\[")[1].split("\\]")[0].split(":")[1]);
							String name = templine2.split("\\[")[1].split("\\]")[0].split(":")[0];
							////System.out.println(name);
							hi4 = new ArrayList<Integer>();
							hi5 = new ArrayList<Integer>();
							while(name.equals("Wood")||name.equals("Metal")||name.equals("Plastic")){
								hi4.add(times);
								if(name.equals("Wood")){
									hi5.add(0);
								}
								else if(name.equals("Metal")){
									hi5.add(1);
								}
								else if(name.equals("Plastic")){
									hi5.add(2);
								}
								try {
									templine2 = x.readLine();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								if((templine2.split("\\[")[1].split("\\]")[0].split(":").length) == 1) break;
								else{
									times = Integer.parseInt(templine2.split("\\[")[1].split("\\]")[0].split(":")[1]);
									name = templine2.split("\\[")[1].split("\\]")[0].split(":")[0];
								//	//System.out.println(name);
								}
							}
							int current = 0;
							number = new ArrayList<ArrayList<Integer>>();
							tool = new ArrayList<ArrayList<Integer>>();
							Location = new ArrayList<Integer>();
							time = new ArrayList<Integer>();
							while(templine2!=null){
								
								//System.out.println(templine2);
								number.add(new ArrayList<Integer>());
								tool.add(new ArrayList<Integer>());
								String[] in = templine2.split("\\[");
								String[] in2 = in[1].split("\\]");
								String[] in4 = in2[0].split("Use ");
								//System.out.println(in4.length);
								//System.out.println(in4[1]);
								String[]in5 = in4[1].split(" at ");
								if(in5.length == 1){
									number.get(current).add(-1);
									//System.out.println(number.get(current).get(0));
									//System.out.println("This is the size: " + number.get(current).size());
									tool.get(current).add(-1);
				
									if(in5[0].split(" for ")[0].equals("Saw")){
										Location.add(0);
									}
									else if(in5[0].split(" for ")[0].equals("Press")){
										Location.add(1);
									}
									else if(in5[0].split(" for ")[0].equals("Workbench")){
										Location.add(2);
									}
									else if(in5[0].split(" for ")[0].equals("Painting Station")){
										Location.add(3);
									}
									else if(in5[0].split(" for ")[0].equals("Anvil")){
										Location.add(4);
									}
									else if(in5[0].split(" for ")[0].equals("Furnace")){
										Location.add(5);
									}
									time.add(Integer.parseInt(in5[0].split(" for ")[1].split("s")[0]));
									//System.out.println(number.get(current).size());
								}
								else{
									
									String[] in3 = in5[0].split(" and ");
									String[] in6 = in5[1].split(" for ");
									int time0 = Integer.parseInt(in6[1].split("s")[0]);
									//System.out.println(in3.length);
									for(int m = 0; m < in3.length; m++){
										number.get(current).add(Integer.parseInt(in3[m].split("x ")[0]));
										//System.out.println("This is the number of tools" + Integer.parseInt(in3[m].split("x ")[0]));
										if(in3[m].split("x ")[1].equals("Hammer")||in3[m].split("x ")[1].equals("Hammers")){
												tool.get(current).add(0);
										}
										else if(in3[m].split("x ")[1].equals("Screwdriver")||in3[m].split("x ")[1].equals("Screwdrivers")){
											//for(int b = 0; b < number.get(current).get(number.get(current).size()-1); b++){
												tool.get(current).add(1);
										//	}
										}
										else if(in3[m].split("x ")[1].equals("Plier")||in3[m].split("x ")[1].equals("Pliers")){
										//	for(int b = 0; b < number.get(current).get(number.get(current).size()-1); b++){
												tool.get(current).add(2);
											//}
										}
										else if(in3[m].split("x ")[1].equals("Scissor")||in3[m].split("x ")[1].equals("Scissors")){
											//for(int b = 0; b < number.get(current).get(number.get(current).size()-1); b++){
												tool.get(current).add(3);
											//}
										}
										else if(in3[m].split("x ")[1].equals("Paintbrush")||in3[m].split("x ")[1].equals("Paintbrushes")){
											//for(int b = 0; b < number.get(current).get(number.get(current).size()-1); b++){
												tool.get(current).add(4);
											//}
										}
									}
									if(in6[0].equals("Saw")){
										Location.add(0);
									}
									else if(in6[0].equals("Press")){
										Location.add(1);
									}
									else if(in6[0].equals("Workbench")){
										Location.add(2);
									}
									else if(in6[0].equals("Painting Station")){
										Location.add(3);
									}
									else if(in6[0].equals("Anvil")){
										Location.add(4);
									}
									else if(in6[0].equals("Furnace")){
										Location.add(5);
									}
									time.add(time0);
								}
								current++;
								try {
									templine2 = x.readLine();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							for(int something = 0; something < RecipeNumber; something++){
								Recipe recipes = new Recipe(number, tool, Location, time, hi4, hi5);
								recipe.add(recipes);
							}
							//recipe.get(recipe.size()-1).print();
						}
						else if(temp.getName().endsWith(".factory")){
							BufferedReader x = null;
							try {
								x = new BufferedReader(new FileReader(temp));
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							String templine2 = null;
							try {
								templine2 = x.readLine();
								//System.out.println(templine2);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							int times = Integer.parseInt(templine2.split("\\[")[1].split("\\]")[0].split(":")[1]);
							String name = templine2.split("\\[")[1].split("\\]")[0].split(":")[0];
							//System.out.println(name);
							while(name.equals("Workers")||name.equals("Hammers")||name.equals("Screwdrivers")||name.equals("Pliers")||name.equals("Scissors")||name.equals("Paintbrushes")){
								if(name.equals("Workers")){
									for(int k = 0; k < times; k++){
										hi3.set(0, hi3.get(0)+1);
									}
								}
								else if(name.equals("Hammers")){
									for(int k = 0; k < times; k++){
										hi3.set(1, hi3.get(1)+1);
									}
								}
								else if(name.equals("Screwdrivers")){
									for(int k = 0; k < times; k++){
										hi3.set(2, hi3.get(2)+1);
									}
								}
								else if(name.equals("Pliers")){
									for(int k = 0; k < times; k++){
										hi3.set(3, hi3.get(3)+1);
									}
								}
								else if(name.equals("Scissors")){
									for(int k = 0; k < times; k++){
										hi3.set(4, hi3.get(4)+1);
									}
								}
								else if(name.equals("Paintbrushes")){
									for(int k = 0; k < times; k++){
										hi3.set(5, hi3.get(5)+1);
									}
								}
								try {
									templine2 = x.readLine();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								if(templine2 == null) break;
								else{
									times = Integer.parseInt(templine2.split("\\[")[1].split("\\]")[0].split(":")[1]);
									name = templine2.split("\\[")[1].split("\\]")[0].split(":")[0];
									//System.out.println(name);
								}
							}
						}
					}
					Object[]rows = new Object[hi.size()];
					for(int i = 0; i < hi.size(); i++){
						rows[i] = hi.get(i);
					}
					cols[0] = "Taskboard";

					JList tables = new JList(rows);
					JScrollPane jsp = new JScrollPane(tables);
					jsp.setPreferredSize(new Dimension(184, 600));
					JPanel panel = new JPanel(new BorderLayout());
					JPanel panel1 = new JPanel(new BorderLayout());
					panel.add(new JLabel("Taskbar", JLabel.CENTER), BorderLayout.NORTH);
					panel.add(jsp, BorderLayout.CENTER);
					panel1.add(panel, BorderLayout.EAST);
					frame.add(panel1);
					paneldraw = new DrawPanel(hi2, hi3, frame, tables, rows, time);
					paneldraw.setPreferredSize(new Dimension(600,600));
					panel1.add(paneldraw, BorderLayout.CENTER);
					//paneldraw.repaint();
					//for(int i = 0; i < hi3.get(0); i++){
						frame.validate();
						Worker x = new Worker(paneldraw, recipe, 0);
						x.start();
						
						Worker x1 = new Worker(paneldraw, recipe, 1);
						x1.start();
					
					//}
					//paneldraw.run();
					
				}
			}
			
		});
		jmb.add(Open);
		frame.setJMenuBar(jmb);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public static void main(String args[]){
		Test t = new Test();
	}
	public void update(){
		
	}
}
