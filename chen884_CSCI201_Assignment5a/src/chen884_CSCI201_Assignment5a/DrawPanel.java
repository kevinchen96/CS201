package chen884_CSCI201_Assignment5a;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

public class DrawPanel extends JPanel{
	private ArrayList<Integer> numMaterials;
	private ArrayList<Integer> numTools;
	private ArrayList<Integer> totalNumTools;
	private ArrayList<Integer> time;
	private int countRecipe;
	private ArrayList<ArrayList<Boolean>> workstations;
	private JFrame frame;
	private Worker x;
	private Graphics g;

	private JList Tables;
	Object[] strings;
	public DrawPanel(ArrayList<Integer> hi2, ArrayList<Integer> hi3, JFrame frame, JList Table, Object[] rows, ArrayList<Integer> time){
		super();
		numMaterials = hi2;
		numTools = hi3;
	
		workstations = new ArrayList<ArrayList<Boolean>>();
		for(int i = 0; i < 6; i++){
			workstations.add(new ArrayList<Boolean>());
		}
		workstations.get(0).add(true);
		workstations.get(0).add(true);
		workstations.get(0).add(true);
		
		workstations.get(1).add(true);
		
		workstations.get(2).add(true);
		workstations.get(2).add(true);
		workstations.get(2).add(true);
		
		workstations.get(3).add(true);
		workstations.get(3).add(true);
		workstations.get(3).add(true);
		workstations.get(3).add(true);
		
		workstations.get(4).add(true);
		workstations.get(4).add(true);

		workstations.get(5).add(true);
		workstations.get(5).add(true);
		totalNumTools = new ArrayList<Integer>();
		for(int i = 0; i < numTools.size(); i++){
			totalNumTools.add(numTools.get(i));
		}
		frame = frame;
		Tables = Table;
		strings = rows;
		this.time = time;
		countRecipe = 0;
	}
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        	BufferedImage wood = null;
        	BufferedImage metal = null;
        	BufferedImage plastic = null;
        	BufferedImage hammer = null;
        	BufferedImage screwdriver = null;
        	BufferedImage plyer = null;
        	BufferedImage scissor = null;
        	BufferedImage paintbrush = null;
        	BufferedImage anvil = null;
        	BufferedImage furnace = null;
        	BufferedImage paintstation = null;
        	BufferedImage press = null;
        	BufferedImage workbench = null;
        	BufferedImage tablesaw = null;
        	
        	
			try {
				wood = ImageIO.read(new File("resources/wood.png"));
				metal = ImageIO.read(new File("resources/metal.png"));
				plastic = ImageIO.read(new File("resources/plastic.png"));
				hammer = ImageIO.read(new File("resources/hammer.png"));
				screwdriver = ImageIO.read(new File("resources/screwdriver.png"));
				plyer = ImageIO.read(new File("resources/pliers.png"));
				scissor = ImageIO.read(new File("resources/scissors.png"));
				paintbrush = ImageIO.read(new File("resources/paintbrush.png"));
				anvil = ImageIO.read(new File("resources/anvil.png"));
				furnace = ImageIO.read(new File("resources/furnace.png"));
				paintstation = ImageIO.read(new File("resources/paintingstation.png"));
				press = ImageIO.read(new File("resources/press.png"));
				workbench = ImageIO.read(new File("resources/workbench.png"));
				tablesaw = ImageIO.read(new File("resources/tablesaw.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            g.drawImage(wood, 150, 25, null);
            g.drawString("" + numMaterials.get(0), 170, 55);
            g.drawString("Wood", 150, 25);
            g.drawImage(metal, 275, 25, null);
            g.drawString("" + numMaterials.get(1), 295, 55);
            g.drawString("Metal", 275, 25);
            g.drawImage(plastic, 400, 25, null);
            g.drawString("" + numMaterials.get(2), 420, 55);
            g.drawString("Plastic", 400, 25);
            
            g.drawImage(hammer, 10, 80, null);
            g.drawString(numTools.get(1) + "/" + totalNumTools.get(1), 30, 110);
            g.drawString("Hammer", 10, 80);
            g.drawImage(screwdriver, 10, 180, null);
            g.drawString(numTools.get(2) + "/" + totalNumTools.get(2), 30, 210);
            g.drawString("ScrewDriver", 10, 180);
            g.drawImage(plyer, 10, 280, null);
            g.drawString(numTools.get(3) + "/" + totalNumTools.get(3), 30, 310);
            g.drawString("Pliers", 10, 280);
            g.drawImage(scissor, 10, 380, null);
            g.drawString(numTools.get(4) + "/" + totalNumTools.get(4), 30, 410);
            g.drawString("Scissors", 10, 380);
            g.drawImage(paintbrush, 10, 480, null);
            g.drawString(numTools.get(5) + "/" + totalNumTools.get(5), 30, 510);
            g.drawString("Paintbrush", 10, 480);
            
            g.setColor(Color.GREEN);
            g.drawImage(anvil, 175, 140, null);
            g.drawString("Open", 175, 140);
            g.drawImage(anvil, 250, 140, null);
            g.drawString("Open", 250, 140);
            g.drawImage(workbench, 325, 140, null);
            g.drawString("Open", 325, 140);
            g.drawImage(workbench, 400, 140, null);
            g.drawString("Open", 400, 140);
            g.drawImage(workbench, 475, 140, null);
            g.drawString("Open", 475, 140);
            g.drawImage(furnace, 175, 280, null);
            g.drawString("Open", 175, 280);
            g.drawImage(furnace, 250, 280, null);
            g.drawString("Open", 250, 280);
            g.drawImage(tablesaw, 325, 280, null);
            g.drawString("Open", 325, 280);
            g.drawImage(tablesaw, 400, 280, null);
            g.drawString("Open", 400, 280);
            g.drawImage(tablesaw, 475, 280, null);
            g.drawString("Open", 475, 280);
            g.drawImage(paintstation, 175, 420, null);
            g.drawString("Open", 175, 420);
            g.drawImage(paintstation, 250, 420, null);
            g.drawString("Open", 250, 420);
            g.drawImage(paintstation, 325, 420, null);
            g.drawString("Open", 325, 420);
            g.drawImage(paintstation, 400, 420, null);
            g.drawString("Open", 400, 420);
            g.drawImage(press, 475, 420, null);
            g.drawString("Open", 475, 420);
            
            g.setColor(Color.BLACK);
            g.drawString("Anvils", 225, 200);
            g.drawString("Work Benches", 420, 200);
            g.drawString("Furnaces", 200, 340);
            g.drawString("Table Saws", 425, 340);
            g.drawString("Painting Stations", 200, 485);
            g.drawString("Press", 475, 485);
        }
	public void update(double x, double y) {
	// TODO Auto-generated method stub
		
		Graphics g = this.getGraphics();
		BufferedImage icon = null;
		try {
			icon = ImageIO.read(new File("resources/worker.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(icon, (int) x, (int) y, null);
		}
	public void updateMaterials(int change, int index) {
		// TODO Auto-generated method stub
		numMaterials.set(index, numMaterials.get(index)-change);
		repaint();
	}
	public void updateTools(int change, int index){
		numTools.set(index, numTools.get(index) - change);
		repaint();
	}
	public int getTools(int index){
		return numTools.get(index);
	}
	public void updateWorkStation(Boolean bool, int index, int index1, int x, int y, int TIME){
		Graphics g = this.getGraphics();
		workstations.get(index).set(index1, bool);
		if(bool == false){
			g.setColor(Color.RED);
			g.drawString(TIME+"s", x, y);
		}
		else{
			g.setColor(Color.GREEN);
			g.drawString("Open", x, y);
		}
	}
	public ArrayList<ArrayList<Boolean>> getWorkStation(){
		return workstations;
	}
	public void inProgress(int i) {
		// TODO Auto-generated method stub
		strings[i] = ((String) strings[i]).split(" ... ")[0] + " ... " + "In Progress";
		Tables.setListData(strings);
	}
	public void Complete(int i) {
		// TODO Auto-generated method stub
		strings[i] = ((String) strings[i]).split(" ... ")[0] + " ... " + "Complete";
		System.out.println(strings[i] + " " + i);
		Tables.setListData(strings);
	}
	public void updateCount() {
		// TODO Auto-generated method stub
		countRecipe++;
	}
	public int getCount(){
		return countRecipe;
	}
}
       

