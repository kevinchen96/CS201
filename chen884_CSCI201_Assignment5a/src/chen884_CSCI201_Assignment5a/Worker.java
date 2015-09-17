package chen884_CSCI201_Assignment5a;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Worker extends Thread {
	private BufferedImage icon;
	private double xP;
	private double yP;
	private int direction;
	private int state;
	private DrawPanel d;
	private int delay;
	private boolean instructionComplete;
	//private static int current;
	private int currentI;
	private ArrayList<Recipe> r;
	public Worker(DrawPanel d, ArrayList<Recipe> r, int delay){
		try {
			icon = ImageIO.read(new File("resources/worker.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		xP = 0;
		yP = 0;
		
		this.d = d;
		this.r = r;
		direction = 2;
		instructionComplete = false;
		state = 0;
		//d.addToList(this);
	}
	public void moveToMaterials(int current){
		while(yP>=80){
			yP -= .25;
			d.update(xP, yP); //0, -.25);
		}
		for(int j = 0; j < r.get(current).getNumMaterials().size(); j++){
			if(r.get(current).getMaterials().get(j) == 2){
				if(xP >= 455){
					while(xP >= 455){
						xP -= .25;
						d.update(xP, yP); //-.25, 0);
					}
				}
				else{
					while(xP < 455){
						xP += .25;
						d.update(xP, yP); //.25, 0);
					}
				}
				if(yP >= 25){
					while(yP >= 25){
						yP -= .25;
						d.update(xP, yP); //0, -.25);
					}
				}
				
				d.updateMaterials(r.get(current).getNumMaterials().get(j), 2);
				if(yP < 80){
					while(yP < 80){
						yP += .25;
						d.update(xP, yP); //0, .25);
					}
				}
			}
			else if(r.get(current).getMaterials().get(j) == 1){
				if(xP >= 330){
					while(xP >= 330){
						xP -= .25;
						d.update(xP, yP); //-.25, 0);
					}
				}
				else{
					while(xP < 330){
						xP += .25;
						d.update(xP, yP); //.25, 0);
					}
				}
				if(yP >= 25){
					while(yP >= 25){
						yP -= .25;
						d.update(xP, yP); //0, -.25);
					}
				}
				
				d.updateMaterials(r.get(current).getNumMaterials().get(j), 1);
				if(yP < 80){
					while(yP < 80){
						yP += .25;
						d.update(xP, yP); //0, .25);
					}
				}
			}
			else if(r.get(current).getMaterials().get(j) == 0){
				if(xP >= 205){
					while(xP >= 205){
						xP -= .25;
						d.update(xP, yP); //-.25, 0);
					}
				}
				else{
					while(xP < 205){
						xP += .25;
						d.update(xP, yP); //.25, 0);
					}
				}
				if(yP >= 25){
					while(yP >= 25){
						yP -= .25;
						d.update(xP, yP); //0, -.25);
					}
				}
				
				d.updateMaterials(r.get(current).getNumMaterials().get(j), 0);
				if(yP < 80){
					while(yP < 80){
						yP += .25;
						d.update(xP, yP); //0, .25);
					}
				}
			}
		}
		if(xP >= 80){
			while(xP >= 80){
				xP -= .25;
				d.update(xP, yP); //-.25, 0);
			}
		}
	}
	
	public void moveToWall(int current, boolean finished){
		if(yP>= 355){
			while(yP >= 355){
				yP-= .25;
				d.update(xP, yP); //0,-.25);
			}
		}
		else if(yP >= 180){
			if(yP < 215){
				while(yP < 215){
					yP += .25;
					d.update(xP, yP); //0, .25);
				}
			}
			else{
				while(yP >= 215){
					yP -= .25;
					d.update(xP, yP); //0, -.25);
				}
			}
		}
		else{
			if(yP < 80){
				while(yP < 80){
					yP += .25;
					d.update(xP, yP); //0, .25);
				}
			}
			else{
				while(yP >= 80){
					yP -= .25;
					d.update(xP, yP); //0, -.25);
				}
			}
		}
		while(xP <= 540){
			xP += .25;
			d.update(xP, yP); //.25, 0);
		}
		if(finished){
			d.Complete(current);
			if(d.getCount() < r.size()){
				d.inProgress(d.getCount());
			}
		}
		else if(!finished){
			d.inProgress(d.getCount());
			currentI = d.getCount();
			d.updateCount();
		}
	}
	public void run(){
		/*long temp = System.nanoTime();
		long temp2 = System.nanoTime();
		while(temp2 - temp < delay){
			temp2 = System.nanoTime();
		}*/
		while(xP < 80){
			xP+= .25;
			d.update(xP, yP); //.25, 0);
		}
		while(yP<80){
		//	moveToWall();
			yP += .25;
			d.update(xP, yP); //0, .25);
			
		}
		moveToWall(currentI, false);
		while(d.getCount() <= r.size()){
			System.out.println(currentI);
			

			////System.out.println(xP + " " + yP);
			moveToMaterials(currentI);
			////System.out.println(xP + " " + yP);
			
			for(int i = 0; i < r.get(currentI).getNumber().size(); i++){
				moveToTool(i, currentI);
				////System.out.println(xP + " " + yP);
				
				moveToArea(i, currentI);
				////System.out.println(xP + " " + yP);
				
				returnToTool(i, currentI);
				////System.out.println(xP + " " + yP);
				
			}
			moveToWall(currentI, true);
		//	moveToWallCompletion();
			currentI = d.getCount();	
			d.updateCount();
		}
	}
	private void returnToTool(int x, int current) {
		for(int i = 0; i < r.get(current).getTool().get(x).size(); i++){
			//System.out.println(r.get(current).getTool().get(x).get(i));
			if(r.get(current).getTool().get(x).get(i) == 0){
				if(yP < 80 ){
					while(yP < 80){
						yP += .25;
						d.update(xP, yP); //0, .25);
					}
				}
				else{
					while(yP >= 80){
						yP -= .25;
						d.update(xP, yP); //0, -.25);
					}
				}
				d.updateTools(-1* r.get(current).getNumber().get(x).get(i), 1);
			}
			else if(r.get(current).getTool().get(x).get(i) == 1){
				if(yP < 180 ){
					while(yP < 180){
						yP += .25;
						d.update(xP, yP); //0, .25);
					}
				}
				else{
					while(yP >= 180){
						yP -= .25;
						d.update(xP, yP); //0, -.25);
					}
				}
				d.updateTools(-1* r.get(current).getNumber().get(x).get(i), 2);
			}
			else if(r.get(current).getTool().get(x).get(i) == 2){
				if(yP < 280 ){
					while(yP < 280){
						yP += .25;
						d.update(xP, yP); //0, .25);
					}
				}
				else{
					while(yP >= 280){
						yP -= .25;
						d.update(xP, yP); //0, -.25);
					}
				}
				d.updateTools(-1* r.get(current).getNumber().get(x).get(i), 3);
			}
			else if(r.get(current).getTool().get(x).get(i) == 3){
				if(yP < 380 ){
					while(yP < 380){
						yP += .25;
						d.update(xP, yP); //0, .25);
					}
				}
				else{
					while(yP >= 380){
						yP -= .25;
						d.update(xP, yP); //0, -.25);
					}
				}
				d.updateTools(-1* r.get(current).getNumber().get(x).get(i), 4);
			}
			else if(r.get(current).getTool().get(x).get(i) == 4){
				if(yP < 480 ){
					while(yP < 480){
						yP += .25;
						d.update(xP, yP); //0, .25);
					}
				}
				else{
					while(yP >= 480){
						yP -= .25;
						d.update(xP, yP); //0, -.25);
					}
				}
				d.updateTools(-1* r.get(current).getNumber().get(x).get(i), 5);
			}
		}
		// TODO Auto-generated method stub
		
	}
	public void moveToArea(int x, int current) {
		// TODO Auto-generated method stub
		if(r.get(current).getLocation().get(x) == 0){
			if(yP < 215){
				while(yP < 215){
					yP += .25;
					d.update(xP, yP); //0, .25);
				}
			}
			else{
				while(yP >= 215){
					yP -= .25;
					d.update(xP, yP); //0, -.25);
				}
			}
			for(int i = 0; i < d.getWorkStation().get(0).size(); i++){
				if(d.getWorkStation().get(0).get(i) == true){
					while(xP < 325 + 75*i){
						xP += .25;
						d.update(xP, yP); //.25, 0);
					}
					while(yP < 280){
						yP += .25;
						d.update(xP, yP); //0, .25);
					}
					d.updateWorkStation(false, 0, i, 325 + 75*i, 280, r.get(current).getTime().get(x));
					long start = System.nanoTime();
					long now = System.nanoTime();
					while((now-start)/1000000000 != r.get(current).getTime().get(x)){
						now = System.nanoTime();
					}
					d.updateWorkStation(true, 0, i, 325 + 75*i, 280, r.get(current).getTime().get(x));
					while(yP >= 215){
						yP -= .25;
						d.update(xP, yP); //0, -.25);
						d.repaint();
					}
					break;
				}
			}
		}
		else if(r.get(current).getLocation().get(x) == 1){
			if(yP < 355){
				while(yP < 355){
					yP += .25;
					d.update(xP, yP); //0, .25);
				}
			}
			else{
				while(yP >= 355){
					yP -= .25;
					d.update(xP, yP); //0, -.25);
				}
			}
			if(d.getWorkStation().get(1).get(0) == true){
				while(xP < 475){
					xP += .25;
					d.update(xP, yP); //.25, 0);
				}
				while(yP < 420){
					yP += .25;
					d.update(xP, yP); //0, .25);
				}
				d.updateWorkStation(false, 1, 0, 475, 420, r.get(current).getTime().get(x));
				long start = System.nanoTime();
				long now = System.nanoTime();
				while((now-start)/1000000000 != r.get(current).getTime().get(x)){
					now = System.nanoTime();
				}
				d.updateWorkStation(true, 1, 0, 475, 420, r.get(current).getTime().get(x));
				while(yP >= 355){
					yP -= .25;
					d.update(xP, yP); //0, -.25);
					d.repaint();
				}
				
			}
		}
		else if(r.get(current).getLocation().get(x) == 2){
			if(yP < 80){
				while(yP < 80){
					yP += .25;
					d.update(xP, yP); //0, .25);
				}
			}
			else{
				while(yP >= 80){
					yP -= .25;
					d.update(xP, yP); //0, -.25);
				}
			}
			for(int i = 0; i < d.getWorkStation().get(2).size(); i++){
				if(d.getWorkStation().get(2).get(i) == true){
					while(xP < 325 + 75*i){
						xP += .25;
						d.update(xP, yP); //.25, 0);
					}
					while(yP < 140){
						yP += .25;
						d.update(xP, yP); //0, .25);
					}
					d.updateWorkStation(false, 2, i, 325 + 75*i, 140, r.get(current).getTime().get(x));
					long start = System.nanoTime();
					long now = System.nanoTime();
					while((now-start)/1000000000 != r.get(current).getTime().get(x)){
						now = System.nanoTime();
					}
					d.updateWorkStation(true, 2, i, 325 + 75*i, 140, r.get(current).getTime().get(x));
					while(yP >= 80){
						yP -= .25;
						d.update(xP, yP); //0, -.25);
						d.repaint();
					}
					break;
				}
			}
		}
		else if(r.get(current).getLocation().get(x) == 3){
			if(yP < 355){
				while(yP < 355){
					yP += .25;
					d.update(xP, yP); //0, .25);
				}
			}
			else{
				while(yP >= 355){
					yP -= .25;
					d.update(xP, yP); //0, -.25);
				}
			}
			for(int i = 0; i < d.getWorkStation().get(3).size(); i++){
				if(d.getWorkStation().get(3).get(i) == true){
					while(xP < 175 + 75*i){
						xP += .25;
						d.update(xP, yP); //.25, 0);
					}
					while(yP < 420){
						yP += .25;
						d.update(xP, yP); //0, .25);
					}
					d.updateWorkStation(false, 3, i, 175 + 75*i, 420, r.get(current).getTime().get(x));
					long start = System.nanoTime();
					long now = System.nanoTime();
					while((now-start)/1000000000 != r.get(current).getTime().get(x)){
						now = System.nanoTime();
					}
					d.updateWorkStation(true, 3, i, 175 + 75*i, 420, r.get(current).getTime().get(x));
					while(yP >= 355){
						yP -= .25;
						d.update(xP, yP); //0, -.25);
						d.repaint();
					}
					break;
				}
			}
			
		}
		else if(r.get(current).getLocation().get(x) == 4){
			if(yP < 80){
				while(yP < 80){
					yP += .25;
					d.update(xP, yP); //0, .25);
				}
			}
			else{
				while(yP >= 80){
					yP -= .25;
					d.update(xP, yP); //0, -.25);
				}
			}
			for(int i = 0; i < d.getWorkStation().get(4).size(); i++){
				if(d.getWorkStation().get(4).get(i) == true){
					while(xP < 175 + 75*i){
						xP += .25;
						d.update(xP, yP); //.25, 0);
					}
					while(yP < 140){
						yP += .25;
						d.update(xP, yP); //0, .25);
					}
					d.updateWorkStation(false, 4, i, 175 + 75*i, 140, r.get(current).getTime().get(x));
					long start = System.nanoTime();
					long now = System.nanoTime();
					while((now-start)/1000000000 != r.get(current).getTime().get(x)){
						now = System.nanoTime();
					}
					d.updateWorkStation(true, 4, i, 175 + 75*i, 140, r.get(current).getTime().get(x));
					while(yP >= 80){
						yP -= .25;
						d.update(xP, yP); //0, -.25);
						d.repaint();
					}
					break;
				}
			}
		}
		else if(r.get(current).getLocation().get(x) == 5){
			if(yP < 215){
				while(yP < 215){
					yP += .25;
					d.update(xP, yP); //0, .25);
				}
			}
			else{
				while(yP >= 215){
					yP -= .25;
					d.update(xP, yP); //0, -.25);
				}
			}
			for(int i = 0; i < d.getWorkStation().get(5).size(); i++){
				if(d.getWorkStation().get(5).get(i) == true){
					while(xP < 175 + 75*i){
						xP += .25;
						d.update(xP, yP); //.25, 0);
					}
					while(yP < 280){
						yP += .25;
						d.update(xP, yP); //0, .25);
					}
					d.updateWorkStation(false, 5, i, 175 + 75*i, 280, r.get(current).getTime().get(x));
					long start = System.nanoTime();
					long now = System.nanoTime();
					while((now-start)/1000000000 != r.get(current).getTime().get(x)){
						now = System.nanoTime();
					}
					d.updateWorkStation(true, 5, i, 175 + 75*i, 280, r.get(current).getTime().get(x));
					while(yP >= 215){
						yP -= .25;
						d.update(xP, yP); //0, -.25);
						d.repaint();
					}
					break;
				}
			}
		}
		if(xP >= 80){
			while(xP >= 80){
				xP -= .25;
				d.update(xP, yP); //-.25, 0);
			}
		}
	}
	public void moveToTool(int x, int current) {
		// TODO Auto-generated method stub
		for(int i = 0; i < r.get(current).getTool().get(x).size(); i++){
			if(r.get(current).getTool().get(x).get(i) == 0){
				if(yP < 80 ){
					while(yP < 80){
						yP += .25;
						d.update(xP, yP); //0, .25);
					}
				}
				else{
					while(yP >= 80){
						yP -= .25;
						d.update(xP, yP); //0, -.25);
					}
				}
				d.updateTools(r.get(current).getNumber().get(x).get(i), 1);
			}
			else if(r.get(current).getTool().get(x).get(i) == 1){
				if(yP < 180 ){
					while(yP < 180){
						yP += .25;
						d.update(xP, yP); //0, .25);
					}
				}
				else{
					while(yP >= 180){
						yP -= .25;
						d.update(xP, yP); //0, -.25);
					}
				}
				d.updateTools(r.get(current).getNumber().get(x).get(i), 2);
			}
			else if(r.get(current).getTool().get(x).get(i) == 2){
				if(yP < 280 ){
					while(yP < 280){
						yP += .25;
						d.update(xP, yP); //0, .25);
					}
				}
				else{
					while(yP >= 280){
						yP -= .25;
						d.update(xP, yP); //0, -.25);
					}
				}
				d.updateTools(r.get(current).getNumber().get(x).get(i), 3);
			}
			else if(r.get(current).getTool().get(x).get(i) == 3){
				if(yP < 380 ){
					while(yP < 380){
						yP += .25;
						d.update(xP, yP); //0, .25);
					}
				}
				else{
					while(yP >= 380){
						yP -= .25;
						d.update(xP, yP); //0, -.25);
					}
				}
				d.updateTools(r.get(current).getNumber().get(x).get(i), 4);
			}
			else if(r.get(current).getTool().get(x).get(i) == 4){
				if(yP < 480 ){
					while(yP < 480){
						yP += .25;
						d.update(xP, yP); //0, .25);
					}
				}
				else{
					while(yP >= 480){
						yP -= .25;
						d.update(xP, yP); //0, -.25);
					}
				}
				d.updateTools(r.get(current).getNumber().get(x).get(i), 5);
			}
		}
	}

}
	