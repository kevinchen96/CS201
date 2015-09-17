package chen884_CSCI201_Assignment4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class RoadPanel extends JPanel{
	private int currLocx[];
	private int currLocy[];
	private int prevLocx[];
	private int prevLocy[];
	private int direction[];
	private int tried[][];
	private int extremeX[];
	private String[][] type;
	private String[] color;
	private int[][] degree;
	private int[] ai, locx, locy;
	private double[] speed;
	private boolean[] state, moved, canMove, mostEast, mostWest;
	private Polygon I, L, T, P;
	int startX;
	int startY;
	int numCar;
	private ArrayList<ArrayList<Integer>> allLocx, setD;
	private ArrayList<ArrayList<Integer>> allLocy; 
	public RoadPanel(String[][] t, int [][] d, String[] color, int[] ai, double[] speed, int[] locx, int[] locy, int numCar){
		super();
		type = t;
		degree = d;
		this.color = color;
		this.ai = ai;
		this.speed = speed;
		this.locx = locx;
		this.locy = locy;
		this.numCar = numCar;
		allLocx = new ArrayList<ArrayList<Integer>>();
		allLocy = new ArrayList<ArrayList<Integer>>();
		setD = new ArrayList<ArrayList<Integer>>();
		state = new boolean[numCar];
		moved = new boolean[numCar];
		canMove = new boolean[numCar];
		mostEast = new boolean[numCar];
		mostWest = new boolean[numCar];
		
		direction = new int[numCar];
		currLocx = new int[numCar];
		currLocy = new int[numCar];
		prevLocx = new int[numCar];
		prevLocy = new int[numCar];
		tried = new int[numCar][5];
		extremeX = new int[2];

		for(int i = 0; i < numCar; i++){
			allLocx.add(new ArrayList<Integer>());
			allLocy.add(new ArrayList<Integer>());
			setD.add(new ArrayList<Integer>());
		}
		for(int i = 0; i < numCar; i++){
			state[i] = true;
			moved[i] = false;
			canMove[i] = true;
			mostEast[i] = false;
			mostWest[i] = false;
			currLocx[i] = locx[i];
			currLocy[i] = locy[i];
			
			prevLocx[i] = locx[i];
			prevLocy[i] = locy[i];
			if(ai[i] == 1){
				direction[i] = 2;
			}
			if(ai[i] == 2){
				for(int j = 0; j < 5; j++){
					tried[i][j] = 0;
				}
			}
			if(ai[i] == 3){
				direction[i] = 1;
			}
			if(ai[i] == 4){
				Random r = new Random();
				boolean taken = false;
				while(setD.get(i).size()!=4){
					int dir = r.nextInt(4);
					for(int j = 0; j < setD.get(i).size(); j++){
						if(dir == setD.get(i).get(j)){
							taken = true;
							break;
						}
					}
					if(!taken){
						setD.get(i).add(dir);
					}
					taken = false;
				}
			}
		}
		boolean stuff = false;

		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				if(!(type[j][i].equals("blank"))){
					stuff = true;

					break;
				}
			}
			if(stuff){
				extremeX[0] = i;
				break;
			}
		}
		stuff = false;

		for(int i = 8; i >=0; i--){
			for(int j = 0; j < 9; j++){
				if(!(type[j][i].equals("blank"))){
					stuff = true;
					break;
				}
			}
			if(stuff){
				extremeX[1] = i;
				break;
			}
		}
		
		
	
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		startX = getWidth()/2-225;
		startY = getHeight()/2-225;
		int backgroundx[] = {startX, startX+450, startX+450, startX};
		int backgroundy[] = {startY, startY, startY+450, startY+450};
		Polygon B = new Polygon(backgroundx, backgroundy, backgroundx.length);
		g.setColor((Color.GREEN).darker());
		g.drawPolygon(B);
		g.fillPolygon(B);
		
		g.setColor(Color.BLACK);
		for(int i = 0; i < 10; i ++){
        	int coordx[] = {getWidth()/2-225 + 50*i, getWidth()/2-225 + 50*i};
            int coordy[] = {getHeight()/2+225, getHeight()/2-225};
            Polygon temp = new Polygon(coordx, coordy, coordx.length);
            g.drawPolygon(temp);
            if(i!=9){
            	g.drawString(""+(i+1), getWidth()/2-225+50*i+25, getHeight()/2-250);
            }
        }
        for(int i = 0; i < 10; i ++){
        	int coordx[] = {getWidth()/2-225, getWidth()/2+225};
            int coordy[] = {getHeight()/2-225 + 50*i, getHeight()/2-225 + 50*i};
            Polygon temp = new Polygon(coordx, coordy, coordx.length);
            g.drawPolygon(temp);
            if(i!=9){
            	char c = (char) (65+i);
            	g.drawString(""+c, getWidth()/2-275+25, getHeight()/2-225+50*i+25);
            }
        }
        
		int coordxI[] = {startX+15, startX+35, startX+35, startX+15};
		int coordyI[] = {startY+0, startY+0, startY+50, startY+50};
		I = new Polygon(coordxI, coordyI, coordxI.length);
		int coordxL[] = {startX+15, startX+35, startX+35, startX+50, startX+50, startX+15};
		int coordyL[] = {startY+0, startY+0, startY+15, startY+15, startY+35, startY+35};
		L = new Polygon(coordxL, coordyL, coordxL.length);
		int coordxT[] = {startX+0, startX+50, startX+50, startX+35, startX+35, startX+15, startX+15, startX+0};
		int coordyT[] = {startY+15, startY+15, startY+35, startY+35, startY+50, startY+50, startY+35, startY+35};
		T = new Polygon(coordxT, coordyT, coordxT.length);
		int coordxP[] = {startX+15, startX+35, startX+35, startX+50, startX+50, startX+35, startX+35, startX+15, startX+15, startX+0, startX+0, startX+15};
		int coordyP[] = {startY+0, startY+0, startY+15, startY+15, startY+35, startY+35, startY+50, startY+50, startY+35, startY+35, startY+15, startY+15};
		P = new Polygon(coordxP, coordyP, coordxP.length);
		Graphics2D g2d = (Graphics2D) g;
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9 ;j++){
				
				if(!(type[i][j].equals("Blank"))){
					
					AffineTransform at = new AffineTransform();
					at.translate(j*50, i*50);
					at.rotate(Math.toRadians(Math.abs(360-degree[i][j])), startX+25, startY+25);
					
					if(type[i][j].equals("i")){
						g2d.draw(at.createTransformedShape(I));
						g2d.fill(at.createTransformedShape(I));
					}
					else if(type[i][j].equals("l")){
						g2d.draw(at.createTransformedShape(L));
						g2d.fill(at.createTransformedShape(L));
					}
					else if(type[i][j].equals("t")){
						g2d.draw(at.createTransformedShape(T));
						g2d.fill(at.createTransformedShape(T));
					}
					else if(type[i][j].equals("+")){
						g2d.draw(at.createTransformedShape(P));
						g2d.fill(at.createTransformedShape(P));
					}
				}
			}
		}
	
		/*for(int i = 0; i < 5; i++){
			Color c = null;
			try {
				c = (Color) Color.class.getField(color[i]).get(null);
			} catch (IllegalArgumentException | IllegalAccessException
					| NoSuchFieldException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			g.setColor(c);
			g.drawOval((locx[i]-1)*50+startX+10, locy[i]*50+startY+10, 30, 30);
			g.fillOval((locx[i]-1)*50+startX+10, locy[i]*50+startY+10, 30, 30);
			g.setColor(Color.BLACK);
			g.drawString((""+(i+1)),(locx[i]-1)*50+startX+21 , locy[i]*50+startY+30 );
		}*/
	}

	public void update(int number) {
		// TODO Auto-generated method stub
		Graphics g = this.getGraphics();
		if(state[number] == false){
			state[number] = true;
			Color c = null;
			try {
				c = (Color) Color.class.getField(color[number]).get(null);
			} catch (IllegalArgumentException | IllegalAccessException
					| NoSuchFieldException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			g.setColor(c);
			g.fillOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
			g.setColor(Color.BLACK);
			g.drawString((""+(number+1)),(currLocx[number]-1)*50+startX+21 , currLocy[number]*50+startY+30 );

		}
		else if(state[number] == true){
			state[number] = false;
			int tempx[] = {(currLocx[number]-1)*50+startX, (currLocx[number])*50+startX, (currLocx[number])*50+startX, (currLocx[number]-1)*50+startX};
			int tempy[] = {currLocy[number]*50+startY, currLocy[number]*50+startY, currLocy[number]*50+startY +50, currLocy[number]*50+startY + 50};
			Polygon p = new Polygon(tempx, tempy, tempx.length);
	
			g.setColor(Color.GREEN.darker());
			g.fillPolygon(p);
			g.setColor(Color.BLACK);
			g.drawPolygon(p);
			
			Graphics2D g2d = (Graphics2D) g;
			AffineTransform at = new AffineTransform();
			at.translate((currLocx[number]-1)*50, (currLocy[number])*50);
			at.rotate(Math.toRadians(Math.abs(360-degree[currLocy[number]][currLocx[number]-1])), startX+25, startY+25);
		
			if(type[currLocy[number]][currLocx[number]-1].equals("i")){
				g2d.draw(at.createTransformedShape(I));
				g2d.fill(at.createTransformedShape(I));
			}
			else if(type[currLocy[number]][currLocx[number]-1].equals("l")){
				g2d.draw(at.createTransformedShape(L));
				g2d.fill(at.createTransformedShape(L));
			}
			else if(type[currLocy[number]][currLocx[number]-1].equals("t")){
				g2d.draw(at.createTransformedShape(T));
				g2d.fill(at.createTransformedShape(T));
			}
			else if(type[currLocy[number]][currLocx[number]-1].equals("+")){
				g2d.draw(at.createTransformedShape(P));
				g2d.fill(at.createTransformedShape(P));
			}
				}
		
	}

	public void updateLoc(int number) {
		// TODO Auto-generated method stub
		Graphics g = this.getGraphics();
		g.setColor(Color.WHITE);
		//////System.out.println(currLocy[number] + " " + (currLocx[number]-1) + " " + ai[number] + " " + prevLocy[number] + " " + (prevLocx[number]-1) );
		String types = type[currLocy[number]][currLocx[number]-1];
		int degrees = degree[currLocy[number]][currLocx[number]-1];
		g.drawString(types, (locx[number]-1)*50+startX+50 , locy[number]*50+startY+50);
		if(ai[number] == 1){
			//g.drawString("zzzzzzzzzzzzzzzzzzz", (locx[number]-1)*50+startX+21 , locy[number]*50+startY+30);
			//////System.out.println("HI");
		//	////System.out.println(currLocy[number] + " " + (currLocx[number]-1) + " " + ai[number]);

			while(moved[number] == false){
				//////System.out.println(currLocy[number] + " " + (currLocx[number]-1) + " " + ai[number] + types + degrees);

				//////System.out.println("INTERSECTION");
				//moved[number] =true;
				//////System.out.println(direction[number]);
				if(direction[number] == 0){
					////System.out.println("Trying to move right");
					direction[number] = 1;
					if( (types.equals("l") && (degrees ==270 ||degrees == 0))||(types.equals("t") && (degrees==0 || degrees == 90))||(types.equals("i") && (degrees==90 || degrees == 270)) ||(types.equals("+"))){
						if(!(prevLocx[number] == currLocx[number]+1)){
							if(types.equals("i")){
								prevLocx[number] = currLocx[number];
							}
							else{
								prevLocy[number] = currLocy[number];
							}
							
							int tempx[] = {(currLocx[number]-1)*50+startX, (currLocx[number])*50+startX, (currLocx[number])*50+startX, (currLocx[number]-1)*50+startX};
							int tempy[] = {currLocy[number]*50+startY, currLocy[number]*50+startY, currLocy[number]*50+startY +50, currLocy[number]*50+startY + 50};
							Polygon p = new Polygon(tempx, tempy, tempx.length);
							
							g.setColor(Color.GREEN.darker());
							g.fillPolygon(p);
							g.setColor(Color.BLACK);
							g.drawPolygon(p);
							 
							Graphics2D g2d = (Graphics2D) g;
							AffineTransform at = new AffineTransform();
							at.translate((prevLocx[number]-1)*50, (prevLocy[number])*50);
							at.rotate(Math.toRadians(Math.abs(360-degree[prevLocy[number]][prevLocx[number]-1])), startX+25, startY+25);
						
							if(type[prevLocy[number]][prevLocx[number]-1].equals("i")){
								g2d.draw(at.createTransformedShape(I));
								g2d.fill(at.createTransformedShape(I));
							}
							else if(type[prevLocy[number]][prevLocx[number]-1].equals("l")){
								g2d.draw(at.createTransformedShape(L));
								g2d.fill(at.createTransformedShape(L));
							}
							else if(type[prevLocy[number]][prevLocx[number]-1].equals("t")){
								g2d.draw(at.createTransformedShape(T));
								g2d.fill(at.createTransformedShape(T));
							}
							else if(type[prevLocy[number]][prevLocx[number]-1].equals("+")){
								g2d.draw(at.createTransformedShape(P));
								g2d.fill(at.createTransformedShape(P));
							}
							Color c = null;
							try {
								c = (Color) Color.class.getField(color[number]).get(null);
							} catch (IllegalArgumentException | IllegalAccessException
									| NoSuchFieldException | SecurityException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							g.setColor(c);
							currLocx[number]= currLocx[number]+1;
							g.drawOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
							g.fillOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
							moved[number] = true;
					
						}
					}
				}
				else if(direction[number] == 1){
				//	////System.out.println("HI1");
					////System.out.println("Trying to move down");
					direction[number] = 2;
					////System.out.println(currLocy[number] + " " + (currLocx[number]-1) + " " + ai[number] + " " + prevLocy[number] + " " + (prevLocx[number]-1) );
					if( (types.equals("l") && (degrees == 180|| degrees == 270))||(types.equals("t") && (degrees==0 || degrees == 270))||(types.equals("i") && (degrees==0 || degrees == 180)) ||(types.equals("+"))){
						if(!(prevLocy[number] == currLocy[number]+1)){
							if(types.equals("i")){
								prevLocy[number] = currLocy[number];
							}
							else{
								prevLocx[number] = currLocx[number];
							}
						
							int tempx[] = {(currLocx[number]-1)*50+startX, (currLocx[number])*50+startX, (currLocx[number])*50+startX, (currLocx[number]-1)*50+startX};
							int tempy[] = {currLocy[number]*50+startY, currLocy[number]*50+startY, currLocy[number]*50+startY +50, currLocy[number]*50+startY + 50};
							Polygon p = new Polygon(tempx, tempy, tempx.length);
						
							g.setColor(Color.GREEN.darker());
							g.fillPolygon(p);
							g.setColor(Color.BLACK);
							g.drawPolygon(p);
							
							Graphics2D g2d = (Graphics2D) g;
							AffineTransform at = new AffineTransform();
							at.translate((prevLocx[number]-1)*50, (prevLocy[number])*50);
							at.rotate(Math.toRadians(Math.abs(360-degree[prevLocy[number]][prevLocx[number]-1])), startX+25, startY+25);
						
							if(type[prevLocy[number]][prevLocx[number]-1].equals("i")){
								g2d.draw(at.createTransformedShape(I));
								g2d.fill(at.createTransformedShape(I));
							}
							else if(type[prevLocy[number]][prevLocx[number]-1].equals("l")){
								g2d.draw(at.createTransformedShape(L));
								g2d.fill(at.createTransformedShape(L));
							}
							else if(type[prevLocy[number]][prevLocx[number]-1].equals("t")){
								g2d.draw(at.createTransformedShape(T));
								g2d.fill(at.createTransformedShape(T));
							}
							else if(type[prevLocy[number]][prevLocx[number]-1].equals("+")){
								g2d.draw(at.createTransformedShape(P));
								g2d.fill(at.createTransformedShape(P));
							}
							Color c = null;
							try {
								c = (Color) Color.class.getField(color[number]).get(null);
							} catch (IllegalArgumentException | IllegalAccessException
									| NoSuchFieldException | SecurityException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							g.setColor(c);
							currLocy[number]=currLocy[number]+1;
							g.drawOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
							g.fillOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
							moved[number] = true;
						}
					}
				}
				else if(direction[number] == 2){
				//	g.drawString("Hadf;akdsjflkajfI", (locx[number]-1)*50+startX+21 , locy[number]*50+startY+30);
				//	////System.out.println("HI2");
					////System.out.println("Trying to move left");
					direction[number] = 3;
					
					if( (types.equals("l") && (degrees ==90||degrees == 180))||(types.equals("t") && (degrees==180 || degrees == 270))||(types.equals("i") && (degrees==90 || degrees == 270)) ||(types.equals("+"))){
						if(!(prevLocx[number] == currLocx[number]-1)){
							////System.out.println("Moving Left");
							if(types.equals("i")){
								prevLocx[number] = currLocx[number];
							}
							else{
								prevLocy[number] = currLocy[number];
							}
						
							int tempx[] = {(currLocx[number]-1)*50+startX, (currLocx[number])*50+startX, (currLocx[number])*50+startX, (currLocx[number]-1)*50+startX};
							int tempy[] = {currLocy[number]*50+startY, currLocy[number]*50+startY, currLocy[number]*50+startY +50, currLocy[number]*50+startY + 50};
							Polygon p = new Polygon(tempx, tempy, tempx.length);
					
							g.setColor(Color.GREEN.darker());
							g.fillPolygon(p);
							g.setColor(Color.BLACK);
							g.drawPolygon(p);
							
							Graphics2D g2d = (Graphics2D) g;
							AffineTransform at = new AffineTransform();
							at.translate((prevLocx[number]-1)*50, (prevLocy[number])*50);
							at.rotate(Math.toRadians(Math.abs(360-degree[prevLocy[number]][prevLocx[number]-1])), startX+25, startY+25);
						
							if(type[prevLocy[number]][prevLocx[number]-1].equals("i")){
								g2d.draw(at.createTransformedShape(I));
								g2d.fill(at.createTransformedShape(I));
							}
							else if(type[prevLocy[number]][prevLocx[number]-1].equals("l")){
								g2d.draw(at.createTransformedShape(L));
								g2d.fill(at.createTransformedShape(L));
							}
							else if(type[prevLocy[number]][prevLocx[number]-1].equals("t")){
								g2d.draw(at.createTransformedShape(T));
								g2d.fill(at.createTransformedShape(T));
							}
							else if(type[prevLocy[number]][prevLocx[number]-1].equals("+")){
								g2d.draw(at.createTransformedShape(P));
								g2d.fill(at.createTransformedShape(P));
							}
							
							Color c = null;
							try {
								c = (Color) Color.class.getField(color[number]).get(null);
							} catch (IllegalArgumentException | IllegalAccessException
									| NoSuchFieldException | SecurityException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							g.setColor(c);
							currLocx[number]=currLocx[number]-1;
							g.drawOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
							g.fillOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
							moved[number] = true;
						}
					}
				}
				else if(direction[number] == 3){
				//	////System.out.println("HI3");
				//	////System.out.println(types + " " + degrees);
					////System.out.println("Trying to move up");
					direction[number] = 0;
					if( (types.equals("l") && (degrees ==0||degrees == 90))||(types.equals("t") && (degrees==90 || degrees == 180))||(types.equals("i") && (degrees==0 || degrees == 180)) ||(types.equals("+"))){
						if(!(prevLocy[number] == currLocy[number]-1)){
							////System.out.println("Going up");
							////System.out.println(currLocy[number]);
							if(types.equals("i")){
								prevLocy[number] = currLocy[number];
							}
							else{
								prevLocx[number] = currLocx[number];
							}

							////System.out.println(prevLocy[number]);
						
							int tempx[] = {(currLocx[number]-1)*50+startX, (currLocx[number])*50+startX, (currLocx[number])*50+startX, (currLocx[number]-1)*50+startX};
							int tempy[] = {currLocy[number]*50+startY, currLocy[number]*50+startY, currLocy[number]*50+startY +50, currLocy[number]*50+startY + 50};
							Polygon p = new Polygon(tempx, tempy, tempx.length);
							
							g.setColor(Color.GREEN.darker());
							g.fillPolygon(p);
							g.setColor(Color.BLACK);
							g.drawPolygon(p);
							
							Graphics2D g2d = (Graphics2D) g;
							AffineTransform at = new AffineTransform();
							at.translate((prevLocx[number]-1)*50, (prevLocy[number])*50);
							at.rotate(Math.toRadians(Math.abs(360-degree[prevLocy[number]][prevLocx[number]-1])), startX+25, startY+25);
						
							if(type[prevLocy[number]][prevLocx[number]-1].equals("i")){
								g2d.draw(at.createTransformedShape(I));
								g2d.fill(at.createTransformedShape(I));
							}
							else if(type[prevLocy[number]][prevLocx[number]-1].equals("l")){
								g2d.draw(at.createTransformedShape(L));
								g2d.fill(at.createTransformedShape(L));
							}
							else if(type[prevLocy[number]][prevLocx[number]-1].equals("t")){
								g2d.draw(at.createTransformedShape(T));
								g2d.fill(at.createTransformedShape(T));
							}
							else if(type[prevLocy[number]][prevLocx[number]-1].equals("+")){
								g2d.draw(at.createTransformedShape(P));
								g2d.fill(at.createTransformedShape(P));
							}
							
							Color c = null;
							try {
								c = (Color) Color.class.getField(color[number]).get(null);
							} catch (IllegalArgumentException | IllegalAccessException
									| NoSuchFieldException | SecurityException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							g.setColor(c);
							currLocy[number]=currLocy[number]-1;

							////System.out.println(currLocy[number]);
							g.drawOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
							g.fillOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
							moved[number] = true;
						}
					}
					
				}
			}
			moved[number] = false;
		}
		else if(ai[number] == 2){
			int size = 2;
			Random r = new Random();
			while(moved[number] == false){
				//System.out.println("Tried this many directions: "+tried[number][4]);
				if(tried[number][4]==4){
					allLocx.get(number).clear();
					size = allLocx.get(number).size();
					allLocy.get(number).clear();
					tried[number][4] = 0;
					for(int i = 0; i < 5; i++){
						tried[number][i] = 0;
					}
				}
				int randomInt = r.nextInt(4);
				if(randomInt == 0){
					
					//System.out.println(types + degrees);
					if( (types.equals("l") && (degrees ==0||degrees == 90))||(types.equals("t") && (degrees==90 || degrees == 180|| degrees == 270))||(types.equals("i") && (degrees==0 || degrees == 180)) ||(types.equals("+"))){
						for(int i = 0; i < allLocx.get(number).size(); i++){
							if( ( (currLocx[number]-1 == allLocx.get(number).get(i)) && (currLocy[number]-1 == allLocy.get(number).get(i)) ) ){
								
								canMove[number] = false;
								
							}
						}
						if(canMove[number] == true){
							allLocx.get(number).add(currLocx[number]-1);
							allLocy.get(number).add(currLocy[number]);
							
							
							int tempx[] = {(currLocx[number]-1)*50+startX, (currLocx[number])*50+startX, (currLocx[number])*50+startX, (currLocx[number]-1)*50+startX};
							int tempy[] = {currLocy[number]*50+startY, currLocy[number]*50+startY, currLocy[number]*50+startY +50, currLocy[number]*50+startY + 50};
							Polygon p = new Polygon(tempx, tempy, tempx.length);
							
							g.setColor(Color.GREEN.darker());
							g.fillPolygon(p);
							g.setColor(Color.BLACK);
							g.drawPolygon(p);
							 
							Graphics2D g2d = (Graphics2D) g;
							AffineTransform at = new AffineTransform();
							at.translate((currLocx[number]-1)*50, (currLocy[number])*50);
							at.rotate(Math.toRadians(Math.abs(360-degree[currLocy[number]][currLocx[number]-1])), startX+25, startY+25);
						
							if(type[currLocy[number]][currLocx[number]-1].equals("i")){
								g2d.draw(at.createTransformedShape(I));
								g2d.fill(at.createTransformedShape(I));
							}
							else if(type[currLocy[number]][currLocx[number]-1].equals("l")){
								g2d.draw(at.createTransformedShape(L));
								g2d.fill(at.createTransformedShape(L));
							}
							else if(type[currLocy[number]][currLocx[number]-1].equals("t")){
								g2d.draw(at.createTransformedShape(T));
								g2d.fill(at.createTransformedShape(T));
							}
							else if(type[currLocy[number]][currLocx[number]-1].equals("+")){
								g2d.draw(at.createTransformedShape(P));
								g2d.fill(at.createTransformedShape(P));
							}
							Color c = null;
							try {
								c = (Color) Color.class.getField(color[number]).get(null);
							} catch (IllegalArgumentException | IllegalAccessException
									| NoSuchFieldException | SecurityException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							g.setColor(c);
							currLocy[number]--;
							g.drawOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
							g.fillOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
							moved[number] = true;
							for(int i = 0; i < allLocx.get(number).size(); i++){
								//System.out.println(allLocy.get(number).get(i) + " " + allLocx.get(number).get(i) );
							}
							//System.out.println(randomInt);
							//System.out.println();
						}
						canMove[number] = true;
					}
					if(moved[number] == false){
						if(tried[number][0]==0){
							tried[number][0] ++;
						}
					}
				}
				else if(randomInt == 1){
					
					//System.out.println(types + degrees);
					if( (types.equals("l") && (degrees ==270 ||degrees == 0))||(types.equals("t") && (degrees==0 || degrees == 90|| degrees == 180))||(types.equals("i") && (degrees==90 || degrees == 270)) ||(types.equals("+"))){
						for(int i = 0; i < allLocx.get(number).size(); i++){
							if( ( (currLocx[number] == allLocx.get(number).get(i)) && (currLocy[number] == allLocy.get(number).get(i)) ) ){
							
								canMove[number] = false;
														}
						}
						if(canMove[number] == true){
							allLocx.get(number).add(currLocx[number]-1);
							allLocy.get(number).add(currLocy[number]);
							
							
							int tempx[] = {(currLocx[number]-1)*50+startX, (currLocx[number])*50+startX, (currLocx[number])*50+startX, (currLocx[number]-1)*50+startX};
							int tempy[] = {currLocy[number]*50+startY, currLocy[number]*50+startY, currLocy[number]*50+startY +50, currLocy[number]*50+startY + 50};
							Polygon p = new Polygon(tempx, tempy, tempx.length);
							
							g.setColor(Color.GREEN.darker());
							g.fillPolygon(p);
							g.setColor(Color.BLACK);
							g.drawPolygon(p);
							 
							Graphics2D g2d = (Graphics2D) g;
							AffineTransform at = new AffineTransform();
							at.translate((currLocx[number]-1)*50, (currLocy[number])*50);
							at.rotate(Math.toRadians(Math.abs(360-degree[currLocy[number]][currLocx[number]-1])), startX+25, startY+25);
						
							if(type[currLocy[number]][currLocx[number]-1].equals("i")){
								g2d.draw(at.createTransformedShape(I));
								g2d.fill(at.createTransformedShape(I));
							}
							else if(type[currLocy[number]][currLocx[number]-1].equals("l")){
								g2d.draw(at.createTransformedShape(L));
								g2d.fill(at.createTransformedShape(L));
							}
							else if(type[currLocy[number]][currLocx[number]-1].equals("t")){
								g2d.draw(at.createTransformedShape(T));
								g2d.fill(at.createTransformedShape(T));
							}
							else if(type[currLocy[number]][currLocx[number]-1].equals("+")){
								g2d.draw(at.createTransformedShape(P));
								g2d.fill(at.createTransformedShape(P));
							}
							Color c = null;
							try {
								c = (Color) Color.class.getField(color[number]).get(null);
							} catch (IllegalArgumentException | IllegalAccessException
									| NoSuchFieldException | SecurityException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							g.setColor(c);
							currLocx[number]++;
							g.drawOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
							g.fillOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
							moved[number] = true;
							for(int i = 0; i < allLocx.get(number).size(); i++){
								//System.out.println(allLocy.get(number).get(i) + " " + allLocx.get(number).get(i) );
								
							}
							//System.out.println(randomInt);
							//System.out.println();
						}
						canMove[number] = true;
					}
					if(moved[number] == false){
						if(tried[number][1]==0){
							tried[number][1] ++;
						}
					}
				}
				else if(randomInt == 2){
				
					//System.out.println(types + degrees);
					if( (types.equals("l") && (degrees == 180|| degrees == 270))||(types.equals("t") && (degrees==0 || degrees == 270|| degrees == 90))||(types.equals("i") && (degrees==0 || degrees == 180)) ||(types.equals("+"))){
						for(int i = 0; i < allLocx.get(number).size(); i++){
							if( ( (currLocx[number]-1 == allLocx.get(number).get(i)) && (currLocy[number]+1 == allLocy.get(number).get(i)) ) ){
						
								canMove[number] = false;
							
							}
						}
						if(canMove[number] == true){
							allLocx.get(number).add(currLocx[number]-1);
							allLocy.get(number).add(currLocy[number]);
							
							
							int tempx[] = {(currLocx[number]-1)*50+startX, (currLocx[number])*50+startX, (currLocx[number])*50+startX, (currLocx[number]-1)*50+startX};
							int tempy[] = {currLocy[number]*50+startY, currLocy[number]*50+startY, currLocy[number]*50+startY +50, currLocy[number]*50+startY + 50};
							Polygon p = new Polygon(tempx, tempy, tempx.length);
							
							g.setColor(Color.GREEN.darker());
							g.fillPolygon(p);
							g.setColor(Color.BLACK);
							g.drawPolygon(p);
							 
							Graphics2D g2d = (Graphics2D) g;
							AffineTransform at = new AffineTransform();
							at.translate((currLocx[number]-1)*50, (currLocy[number])*50);
							at.rotate(Math.toRadians(Math.abs(360-degree[currLocy[number]][currLocx[number]-1])), startX+25, startY+25);
						
							if(type[currLocy[number]][currLocx[number]-1].equals("i")){
								g2d.draw(at.createTransformedShape(I));
								g2d.fill(at.createTransformedShape(I));
							}
							else if(type[currLocy[number]][currLocx[number]-1].equals("l")){
								g2d.draw(at.createTransformedShape(L));
								g2d.fill(at.createTransformedShape(L));
							}
							else if(type[currLocy[number]][currLocx[number]-1].equals("t")){
								g2d.draw(at.createTransformedShape(T));
								g2d.fill(at.createTransformedShape(T));
							}
							else if(type[currLocy[number]][currLocx[number]-1].equals("+")){
								g2d.draw(at.createTransformedShape(P));
								g2d.fill(at.createTransformedShape(P));
							}
							Color c = null;
							try {
								c = (Color) Color.class.getField(color[number]).get(null);
							} catch (IllegalArgumentException | IllegalAccessException
									| NoSuchFieldException | SecurityException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							g.setColor(c);
							currLocy[number]++;
							g.drawOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
							g.fillOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
							moved[number] = true;
							for(int i = 0; i < allLocx.get(number).size(); i++){
								//System.out.println(allLocy.get(number).get(i) + " " + allLocx.get(number).get(i) );
							}
							//System.out.println(randomInt);
							//System.out.println();
						}
						canMove[number] = true;
					
					}
					if(moved[number] == false){
						if(tried[number][2]==0){
							tried[number][2] ++;
						}
					}
				}
				else if(randomInt == 3){
				
					//System.out.println(types + degrees + randomInt);
					if( (types.equals("l") && (degrees ==90||degrees == 180))||(types.equals("t") && (degrees==180 || degrees == 270|| degrees == 0))||(types.equals("i") && (degrees==90 || degrees == 270) ) ||(types.equals("+"))){
						//System.out.println(types + degrees + randomInt);
						for(int i = 0; i < allLocx.get(number).size(); i++){
							if( ( (currLocx[number]-2 == allLocx.get(number).get(i)) && (currLocy[number] == allLocy.get(number).get(i)) ) ){
								
								canMove[number] = false;
							
							}
						}
						if(canMove[number] == true){
							allLocx.get(number).add(currLocx[number]-1);
							allLocy.get(number).add(currLocy[number]);
							
							
							int tempx[] = {(currLocx[number]-1)*50+startX, (currLocx[number])*50+startX, (currLocx[number])*50+startX, (currLocx[number]-1)*50+startX};
							int tempy[] = {currLocy[number]*50+startY, currLocy[number]*50+startY, currLocy[number]*50+startY +50, currLocy[number]*50+startY + 50};
							Polygon p = new Polygon(tempx, tempy, tempx.length);
							
							g.setColor(Color.GREEN.darker());
							g.fillPolygon(p);
							g.setColor(Color.BLACK);
							g.drawPolygon(p);
							 
							Graphics2D g2d = (Graphics2D) g;
							AffineTransform at = new AffineTransform();
							at.translate((currLocx[number]-1)*50, (currLocy[number])*50);
							at.rotate(Math.toRadians(Math.abs(360-degree[currLocy[number]][currLocx[number]-1])), startX+25, startY+25);
						
							if(type[currLocy[number]][currLocx[number]-1].equals("i")){
								g2d.draw(at.createTransformedShape(I));
								g2d.fill(at.createTransformedShape(I));
							}
							else if(type[currLocy[number]][currLocx[number]-1].equals("l")){
								g2d.draw(at.createTransformedShape(L));
								g2d.fill(at.createTransformedShape(L));
							}
							else if(type[currLocy[number]][currLocx[number]-1].equals("t")){
								g2d.draw(at.createTransformedShape(T));
								g2d.fill(at.createTransformedShape(T));
							}
							else if(type[currLocy[number]][currLocx[number]-1].equals("+")){
								g2d.draw(at.createTransformedShape(P));
								g2d.fill(at.createTransformedShape(P));
							}
							Color c = null;
							try {
								c = (Color) Color.class.getField(color[number]).get(null);
							} catch (IllegalArgumentException | IllegalAccessException
									| NoSuchFieldException | SecurityException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							g.setColor(c);
							currLocx[number]--;
							g.drawOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
							g.fillOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
							moved[number] = true;
							for(int i = 0; i < allLocx.get(number).size(); i++){
								//System.out.println(allLocy.get(number).get(i) + " " + allLocx.get(number).get(i) );
								
							}
							//System.out.println(randomInt);
							//System.out.println();
						}
						canMove[number] = true;
					}
					if(moved[number] == false){
						if(tried[number][3]==0){
							tried[number][3] ++;
						}
					}
				}
				tried[number][4] = tried[number][0]+tried[number][1]+tried[number][2]+tried[number][3];
			}
			moved[number] = false;
			for(int i = 0; i < 5; i++){
				tried[number][i] = 0;
			}
		}
		else if(ai[number] == 3){
				
				if(currLocx[number]-1 == extremeX[1]){
					mostEast[number] = true;
					direction[number] = 3;
				}
				else if(currLocx[number]-1 == extremeX[0]){
					mostWest[number] = true;
					direction[number] = 1;
				}
				if(direction[number] == 1){
					System.out.println("Moving in the right attempting Right");
					if( (types.equals("l") && (degrees ==270 ||degrees == 0))||(types.equals("t") && (degrees==0 || degrees == 90|| degrees == 180))||(types.equals("i") && (degrees==90 || degrees == 270)) ||(types.equals("+"))){
						System.out.println("Moving Right");
						int tempx[] = {(currLocx[number]-1)*50+startX, (currLocx[number])*50+startX, (currLocx[number])*50+startX, (currLocx[number]-1)*50+startX};
						int tempy[] = {currLocy[number]*50+startY, currLocy[number]*50+startY, currLocy[number]*50+startY +50, currLocy[number]*50+startY + 50};
						Polygon p = new Polygon(tempx, tempy, tempx.length);
						
						g.setColor(Color.GREEN.darker());
						g.fillPolygon(p);
						g.setColor(Color.BLACK);
						g.drawPolygon(p);
						 
						Graphics2D g2d = (Graphics2D) g;
						AffineTransform at = new AffineTransform();
						at.translate((currLocx[number]-1)*50, (currLocy[number])*50);
						at.rotate(Math.toRadians(Math.abs(360-degree[currLocy[number]][currLocx[number]-1])), startX+25, startY+25);
					
						if(type[currLocy[number]][currLocx[number]-1].equals("i")){
							g2d.draw(at.createTransformedShape(I));
							g2d.fill(at.createTransformedShape(I));
						}
						else if(type[currLocy[number]][currLocx[number]-1].equals("l")){
							g2d.draw(at.createTransformedShape(L));
							g2d.fill(at.createTransformedShape(L));
						}
						else if(type[currLocy[number]][currLocx[number]-1].equals("t")){
							g2d.draw(at.createTransformedShape(T));
							g2d.fill(at.createTransformedShape(T));
						}
						else if(type[currLocy[number]][currLocx[number]-1].equals("+")){
							g2d.draw(at.createTransformedShape(P));
							g2d.fill(at.createTransformedShape(P));
						}
						Color c = null;
						try {
							c = (Color) Color.class.getField(color[number]).get(null);
						} catch (IllegalArgumentException | IllegalAccessException
								| NoSuchFieldException | SecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						g.setColor(c);
						currLocx[number]++;
						g.drawOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
						g.fillOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
						moved[number] = true;
					}
					else{
						while(moved[number] == false){
							Random r = new Random();
							int rand = r.nextInt(3);
							if(rand == 1){
								rand = 3;
							}
							System.out.println(rand);
							if(rand == 0){
								System.out.println("Moving in the right attempting UP");
								if( (types.equals("l") && (degrees ==0||degrees == 90))||(types.equals("t") && (degrees==90 || degrees == 180|| degrees == 270))||(types.equals("i") && (degrees==0 || degrees == 180)) ||(types.equals("+"))){
									int tempx[] = {(currLocx[number]-1)*50+startX, (currLocx[number])*50+startX, (currLocx[number])*50+startX, (currLocx[number]-1)*50+startX};
									int tempy[] = {currLocy[number]*50+startY, currLocy[number]*50+startY, currLocy[number]*50+startY +50, currLocy[number]*50+startY + 50};
									Polygon p = new Polygon(tempx, tempy, tempx.length);
									System.out.println("Moving UP");
									g.setColor(Color.GREEN.darker());
									g.fillPolygon(p);
									g.setColor(Color.BLACK);
									g.drawPolygon(p);
									 
									Graphics2D g2d = (Graphics2D) g;
									AffineTransform at = new AffineTransform();
									at.translate((currLocx[number]-1)*50, (currLocy[number])*50);
									at.rotate(Math.toRadians(Math.abs(360-degree[currLocy[number]][currLocx[number]-1])), startX+25, startY+25);
								
									if(type[currLocy[number]][currLocx[number]-1].equals("i")){
										g2d.draw(at.createTransformedShape(I));
										g2d.fill(at.createTransformedShape(I));
									}
									else if(type[currLocy[number]][currLocx[number]-1].equals("l")){
										g2d.draw(at.createTransformedShape(L));
										g2d.fill(at.createTransformedShape(L));
									}
									else if(type[currLocy[number]][currLocx[number]-1].equals("t")){
										g2d.draw(at.createTransformedShape(T));
										g2d.fill(at.createTransformedShape(T));
									}
									else if(type[currLocy[number]][currLocx[number]-1].equals("+")){
										g2d.draw(at.createTransformedShape(P));
										g2d.fill(at.createTransformedShape(P));
									}
									Color c = null;
									try {
										c = (Color) Color.class.getField(color[number]).get(null);
									} catch (IllegalArgumentException | IllegalAccessException
											| NoSuchFieldException | SecurityException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									g.setColor(c);
									currLocy[number]--;
									g.drawOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
									g.fillOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
									moved[number] = true;
								}
							}
							else if(rand == 2){
								System.out.println("Moving in the right attempting down");
								if( (types.equals("l") && (degrees == 180|| degrees == 270))||(types.equals("t") && (degrees==0 || degrees == 270|| degrees == 90))||(types.equals("i") && (degrees==0 || degrees == 180)) ||(types.equals("+"))){
									int tempx[] = {(currLocx[number]-1)*50+startX, (currLocx[number])*50+startX, (currLocx[number])*50+startX, (currLocx[number]-1)*50+startX};
									int tempy[] = {currLocy[number]*50+startY, currLocy[number]*50+startY, currLocy[number]*50+startY +50, currLocy[number]*50+startY + 50};
									Polygon p = new Polygon(tempx, tempy, tempx.length);
									System.out.println("Moving down");
									g.setColor(Color.GREEN.darker());
									g.fillPolygon(p);
									g.setColor(Color.BLACK);
									g.drawPolygon(p);
									 
									Graphics2D g2d = (Graphics2D) g;
									AffineTransform at = new AffineTransform();
									at.translate((currLocx[number]-1)*50, (currLocy[number])*50);
									at.rotate(Math.toRadians(Math.abs(360-degree[currLocy[number]][currLocx[number]-1])), startX+25, startY+25);
								
									if(type[currLocy[number]][currLocx[number]-1].equals("i")){
										g2d.draw(at.createTransformedShape(I));
										g2d.fill(at.createTransformedShape(I));
									}
									else if(type[currLocy[number]][currLocx[number]-1].equals("l")){
										g2d.draw(at.createTransformedShape(L));
										g2d.fill(at.createTransformedShape(L));
									}
									else if(type[currLocy[number]][currLocx[number]-1].equals("t")){
										g2d.draw(at.createTransformedShape(T));
										g2d.fill(at.createTransformedShape(T));
									}
									else if(type[currLocy[number]][currLocx[number]-1].equals("+")){
										g2d.draw(at.createTransformedShape(P));
										g2d.fill(at.createTransformedShape(P));
									}
									Color c = null;
									try {
										c = (Color) Color.class.getField(color[number]).get(null);
									} catch (IllegalArgumentException | IllegalAccessException
											| NoSuchFieldException | SecurityException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									g.setColor(c);
									currLocy[number]++;
									g.drawOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
									g.fillOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
									moved[number] = true;
								}
							}
							else if(rand == 3){
								System.out.println("Moving in the right attempting Left");
								if( (types.equals("l") && (degrees ==90||degrees == 180))||(types.equals("t") && (degrees==180 || degrees == 270|| degrees == 0))||(types.equals("i") && (degrees==90 || degrees == 270) ) ||(types.equals("+"))){
									int tempx[] = {(currLocx[number]-1)*50+startX, (currLocx[number])*50+startX, (currLocx[number])*50+startX, (currLocx[number]-1)*50+startX};
									int tempy[] = {currLocy[number]*50+startY, currLocy[number]*50+startY, currLocy[number]*50+startY +50, currLocy[number]*50+startY + 50};
									Polygon p = new Polygon(tempx, tempy, tempx.length);
									System.out.println("Moving Left");
									g.setColor(Color.GREEN.darker());
									g.fillPolygon(p);
									g.setColor(Color.BLACK);
									g.drawPolygon(p);
									 
									Graphics2D g2d = (Graphics2D) g;
									AffineTransform at = new AffineTransform();
									at.translate((currLocx[number]-1)*50, (currLocy[number])*50);
									at.rotate(Math.toRadians(Math.abs(360-degree[currLocy[number]][currLocx[number]-1])), startX+25, startY+25);
								
									if(type[currLocy[number]][currLocx[number]-1].equals("i")){
										g2d.draw(at.createTransformedShape(I));
										g2d.fill(at.createTransformedShape(I));
									}
									else if(type[currLocy[number]][currLocx[number]-1].equals("l")){
										g2d.draw(at.createTransformedShape(L));
										g2d.fill(at.createTransformedShape(L));
									}
									else if(type[currLocy[number]][currLocx[number]-1].equals("t")){
										g2d.draw(at.createTransformedShape(T));
										g2d.fill(at.createTransformedShape(T));
									}
									else if(type[currLocy[number]][currLocx[number]-1].equals("+")){
										g2d.draw(at.createTransformedShape(P));
										g2d.fill(at.createTransformedShape(P));
									}
									Color c = null;
									try {
										c = (Color) Color.class.getField(color[number]).get(null);
									} catch (IllegalArgumentException | IllegalAccessException
											| NoSuchFieldException | SecurityException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									g.setColor(c);
									currLocx[number]--;
									g.drawOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
									g.fillOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
									moved[number] = true;
								}
							}
						}
						moved[number] = false;
					}
				}
				else if(direction[number] == 3){
					System.out.println("Moving in the Left attempting Left");
					if( (types.equals("l") && (degrees ==90||degrees == 180))||(types.equals("t") && (degrees==180 || degrees == 270|| degrees == 0))||(types.equals("i") && (degrees==90 || degrees == 270) ) ||(types.equals("+"))){
						int tempx[] = {(currLocx[number]-1)*50+startX, (currLocx[number])*50+startX, (currLocx[number])*50+startX, (currLocx[number]-1)*50+startX};
						int tempy[] = {currLocy[number]*50+startY, currLocy[number]*50+startY, currLocy[number]*50+startY +50, currLocy[number]*50+startY + 50};
						Polygon p = new Polygon(tempx, tempy, tempx.length);
						System.out.println("Moving LEft");
						g.setColor(Color.GREEN.darker());
						g.fillPolygon(p);
						g.setColor(Color.BLACK);
						g.drawPolygon(p);
						 
						Graphics2D g2d = (Graphics2D) g;
						AffineTransform at = new AffineTransform();
						at.translate((currLocx[number]-1)*50, (currLocy[number])*50);
						at.rotate(Math.toRadians(Math.abs(360-degree[currLocy[number]][currLocx[number]-1])), startX+25, startY+25);
					
						if(type[currLocy[number]][currLocx[number]-1].equals("i")){
							g2d.draw(at.createTransformedShape(I));
							g2d.fill(at.createTransformedShape(I));
						}
						else if(type[currLocy[number]][currLocx[number]-1].equals("l")){
							g2d.draw(at.createTransformedShape(L));
							g2d.fill(at.createTransformedShape(L));
						}
						else if(type[currLocy[number]][currLocx[number]-1].equals("t")){
							g2d.draw(at.createTransformedShape(T));
							g2d.fill(at.createTransformedShape(T));
						}
						else if(type[currLocy[number]][currLocx[number]-1].equals("+")){
							g2d.draw(at.createTransformedShape(P));
							g2d.fill(at.createTransformedShape(P));
						}
						Color c = null;
						try {
							c = (Color) Color.class.getField(color[number]).get(null);
						} catch (IllegalArgumentException | IllegalAccessException
								| NoSuchFieldException | SecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						g.setColor(c);
						currLocx[number]--;
						g.drawOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
						g.fillOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
						moved[number] = true;
					}
					
					else{
						Random r = new Random();
						while(moved[number] == false){
							
							int rand = r.nextInt(3);
							if(rand == 3){
								rand = 1;
							}
							System.out.println(rand);
							if(rand == 0){
								System.out.println("Moving in the left attempting up");
								if( (types.equals("l") && (degrees ==0||degrees == 90))||(types.equals("t") && (degrees==90 || degrees == 180|| degrees == 270))||(types.equals("i") && (degrees==0 || degrees == 180)) ||(types.equals("+"))){
									int tempx[] = {(currLocx[number]-1)*50+startX, (currLocx[number])*50+startX, (currLocx[number])*50+startX, (currLocx[number]-1)*50+startX};
									int tempy[] = {currLocy[number]*50+startY, currLocy[number]*50+startY, currLocy[number]*50+startY +50, currLocy[number]*50+startY + 50};
									Polygon p = new Polygon(tempx, tempy, tempx.length);
									System.out.println("Moving up");
									g.setColor(Color.GREEN.darker());
									g.fillPolygon(p);
									g.setColor(Color.BLACK);
									g.drawPolygon(p);
									 
									Graphics2D g2d = (Graphics2D) g;
									AffineTransform at = new AffineTransform();
									at.translate((currLocx[number]-1)*50, (currLocy[number])*50);
									at.rotate(Math.toRadians(Math.abs(360-degree[currLocy[number]][currLocx[number]-1])), startX+25, startY+25);
								
									if(type[currLocy[number]][currLocx[number]-1].equals("i")){
										g2d.draw(at.createTransformedShape(I));
										g2d.fill(at.createTransformedShape(I));
									}
									else if(type[currLocy[number]][currLocx[number]-1].equals("l")){
										g2d.draw(at.createTransformedShape(L));
										g2d.fill(at.createTransformedShape(L));
									}
									else if(type[currLocy[number]][currLocx[number]-1].equals("t")){
										g2d.draw(at.createTransformedShape(T));
										g2d.fill(at.createTransformedShape(T));
									}
									else if(type[currLocy[number]][currLocx[number]-1].equals("+")){
										g2d.draw(at.createTransformedShape(P));
										g2d.fill(at.createTransformedShape(P));
									}
									Color c = null;
									try {
										c = (Color) Color.class.getField(color[number]).get(null);
									} catch (IllegalArgumentException | IllegalAccessException
											| NoSuchFieldException | SecurityException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									g.setColor(c);
									currLocy[number]--;
									g.drawOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
									g.fillOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
									moved[number] = true;
								}
							}
							if(rand == 1){
								System.out.println("Moving in the left attempting right");
								if( (types.equals("l") && (degrees ==270 ||degrees == 0))||(types.equals("t") && (degrees==0 || degrees == 90|| degrees == 180))||(types.equals("i") && (degrees==90 || degrees == 270)) ||(types.equals("+"))){
									int tempx[] = {(currLocx[number]-1)*50+startX, (currLocx[number])*50+startX, (currLocx[number])*50+startX, (currLocx[number]-1)*50+startX};
									int tempy[] = {currLocy[number]*50+startY, currLocy[number]*50+startY, currLocy[number]*50+startY +50, currLocy[number]*50+startY + 50};
									Polygon p = new Polygon(tempx, tempy, tempx.length);
									System.out.println("Moving right");
									g.setColor(Color.GREEN.darker());
									g.fillPolygon(p);
									g.setColor(Color.BLACK);
									g.drawPolygon(p);
									 
									Graphics2D g2d = (Graphics2D) g;
									AffineTransform at = new AffineTransform();
									at.translate((currLocx[number]-1)*50, (currLocy[number])*50);
									at.rotate(Math.toRadians(Math.abs(360-degree[currLocy[number]][currLocx[number]-1])), startX+25, startY+25);
								
									if(type[currLocy[number]][currLocx[number]-1].equals("i")){
										g2d.draw(at.createTransformedShape(I));
										g2d.fill(at.createTransformedShape(I));
									}
									else if(type[currLocy[number]][currLocx[number]-1].equals("l")){
										g2d.draw(at.createTransformedShape(L));
										g2d.fill(at.createTransformedShape(L));
									}
									else if(type[currLocy[number]][currLocx[number]-1].equals("t")){
										g2d.draw(at.createTransformedShape(T));
										g2d.fill(at.createTransformedShape(T));
									}
									else if(type[currLocy[number]][currLocx[number]-1].equals("+")){
										g2d.draw(at.createTransformedShape(P));
										g2d.fill(at.createTransformedShape(P));
									}
									Color c = null;
									try {
										c = (Color) Color.class.getField(color[number]).get(null);
									} catch (IllegalArgumentException | IllegalAccessException
											| NoSuchFieldException | SecurityException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									g.setColor(c);
									currLocx[number]++;
									g.drawOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
									g.fillOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
									moved[number] = true;
								}
							}
							else if(rand == 2){System.out.println("Moving in the left attempting down");
								if( (types.equals("l") && (degrees == 180|| degrees == 270))||(types.equals("t") && (degrees==0 || degrees == 270|| degrees == 90))||(types.equals("i") && (degrees==0 || degrees == 180)) ||(types.equals("+"))){
									int tempx[] = {(currLocx[number]-1)*50+startX, (currLocx[number])*50+startX, (currLocx[number])*50+startX, (currLocx[number]-1)*50+startX};
									int tempy[] = {currLocy[number]*50+startY, currLocy[number]*50+startY, currLocy[number]*50+startY +50, currLocy[number]*50+startY + 50};
									Polygon p = new Polygon(tempx, tempy, tempx.length);
									System.out.println("Moving down");
									g.setColor(Color.GREEN.darker());
									g.fillPolygon(p);
									g.setColor(Color.BLACK);
									g.drawPolygon(p);
									 
									Graphics2D g2d = (Graphics2D) g;
									AffineTransform at = new AffineTransform();
									at.translate((currLocx[number]-1)*50, (currLocy[number])*50);
									at.rotate(Math.toRadians(Math.abs(360-degree[currLocy[number]][currLocx[number]-1])), startX+25, startY+25);
								
									if(type[currLocy[number]][currLocx[number]-1].equals("i")){
										g2d.draw(at.createTransformedShape(I));
										g2d.fill(at.createTransformedShape(I));
									}
									else if(type[currLocy[number]][currLocx[number]-1].equals("l")){
										g2d.draw(at.createTransformedShape(L));
										g2d.fill(at.createTransformedShape(L));
									}
									else if(type[currLocy[number]][currLocx[number]-1].equals("t")){
										g2d.draw(at.createTransformedShape(T));
										g2d.fill(at.createTransformedShape(T));
									}
									else if(type[currLocy[number]][currLocx[number]-1].equals("+")){
										g2d.draw(at.createTransformedShape(P));
										g2d.fill(at.createTransformedShape(P));
									}
									Color c = null;
									try {
										c = (Color) Color.class.getField(color[number]).get(null);
									} catch (IllegalArgumentException | IllegalAccessException
											| NoSuchFieldException | SecurityException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									g.setColor(c);
									currLocy[number]++;
									g.drawOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
									g.fillOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
									moved[number] = true;
								}
							}
						}	
					}
					moved[number] = false;
				}
				
			}
			else if(ai[number] == 4){
				int count = 0;
				while(moved[number] = false){
					if(setD.get(number).get(count)==0){
						if( (types.equals("l") && (degrees ==0||degrees == 90))||(types.equals("t") && (degrees==90 || degrees == 180|| degrees == 270))||(types.equals("i") && (degrees==0 || degrees == 180)) ||(types.equals("+"))){
							int tempx[] = {(currLocx[number]-1)*50+startX, (currLocx[number])*50+startX, (currLocx[number])*50+startX, (currLocx[number]-1)*50+startX};
							int tempy[] = {currLocy[number]*50+startY, currLocy[number]*50+startY, currLocy[number]*50+startY +50, currLocy[number]*50+startY + 50};
							Polygon p = new Polygon(tempx, tempy, tempx.length);
							System.out.println("Moving up");
							g.setColor(Color.GREEN.darker());
							g.fillPolygon(p);
							g.setColor(Color.BLACK);
							g.drawPolygon(p);
							 
							Graphics2D g2d = (Graphics2D) g;
							AffineTransform at = new AffineTransform();
							at.translate((currLocx[number]-1)*50, (currLocy[number])*50);
							at.rotate(Math.toRadians(Math.abs(360-degree[currLocy[number]][currLocx[number]-1])), startX+25, startY+25);
						
							if(type[currLocy[number]][currLocx[number]-1].equals("i")){
								g2d.draw(at.createTransformedShape(I));
								g2d.fill(at.createTransformedShape(I));
							}
							else if(type[currLocy[number]][currLocx[number]-1].equals("l")){
								g2d.draw(at.createTransformedShape(L));
								g2d.fill(at.createTransformedShape(L));
							}
							else if(type[currLocy[number]][currLocx[number]-1].equals("t")){
								g2d.draw(at.createTransformedShape(T));
								g2d.fill(at.createTransformedShape(T));
							}
							else if(type[currLocy[number]][currLocx[number]-1].equals("+")){
								g2d.draw(at.createTransformedShape(P));
								g2d.fill(at.createTransformedShape(P));
							}
							Color c = null;
							try {
								c = (Color) Color.class.getField(color[number]).get(null);
							} catch (IllegalArgumentException | IllegalAccessException
									| NoSuchFieldException | SecurityException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							g.setColor(c);
							currLocy[number]--;
							g.drawOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
							g.fillOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
							moved[number] = true;
							setD.get(number).add(0);
							setD.get(number).remove(count);
							count = 0;
						}
					}
					else if(setD.get(number).get(count)==1){
						if( (types.equals("l") && (degrees ==270 ||degrees == 0))||(types.equals("t") && (degrees==0 || degrees == 90|| degrees == 180))||(types.equals("i") && (degrees==90 || degrees == 270)) ||(types.equals("+"))){
							int tempx[] = {(currLocx[number]-1)*50+startX, (currLocx[number])*50+startX, (currLocx[number])*50+startX, (currLocx[number]-1)*50+startX};
							int tempy[] = {currLocy[number]*50+startY, currLocy[number]*50+startY, currLocy[number]*50+startY +50, currLocy[number]*50+startY + 50};
							Polygon p = new Polygon(tempx, tempy, tempx.length);
							System.out.println("Moving right");
							g.setColor(Color.GREEN.darker());
							g.fillPolygon(p);
							g.setColor(Color.BLACK);
							g.drawPolygon(p);
							 
							Graphics2D g2d = (Graphics2D) g;
							AffineTransform at = new AffineTransform();
							at.translate((currLocx[number]-1)*50, (currLocy[number])*50);
							at.rotate(Math.toRadians(Math.abs(360-degree[currLocy[number]][currLocx[number]-1])), startX+25, startY+25);
						
							if(type[currLocy[number]][currLocx[number]-1].equals("i")){
								g2d.draw(at.createTransformedShape(I));
								g2d.fill(at.createTransformedShape(I));
							}
							else if(type[currLocy[number]][currLocx[number]-1].equals("l")){
								g2d.draw(at.createTransformedShape(L));
								g2d.fill(at.createTransformedShape(L));
							}
							else if(type[currLocy[number]][currLocx[number]-1].equals("t")){
								g2d.draw(at.createTransformedShape(T));
								g2d.fill(at.createTransformedShape(T));
							}
							else if(type[currLocy[number]][currLocx[number]-1].equals("+")){
								g2d.draw(at.createTransformedShape(P));
								g2d.fill(at.createTransformedShape(P));
							}
							Color c = null;
							try {
								c = (Color) Color.class.getField(color[number]).get(null);
							} catch (IllegalArgumentException | IllegalAccessException
									| NoSuchFieldException | SecurityException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							g.setColor(c);
							currLocx[number]++;
							g.drawOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
							g.fillOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
							moved[number] = true;
							setD.get(number).add(1);
							setD.get(number).remove(count);
							count = 0;
						}
					}
					else if(setD.get(number).get(count)==2){
						if( (types.equals("l") && (degrees == 180|| degrees == 270))||(types.equals("t") && (degrees==0 || degrees == 270|| degrees == 90))||(types.equals("i") && (degrees==0 || degrees == 180)) ||(types.equals("+"))){
							int tempx[] = {(currLocx[number]-1)*50+startX, (currLocx[number])*50+startX, (currLocx[number])*50+startX, (currLocx[number]-1)*50+startX};
							int tempy[] = {currLocy[number]*50+startY, currLocy[number]*50+startY, currLocy[number]*50+startY +50, currLocy[number]*50+startY + 50};
							Polygon p = new Polygon(tempx, tempy, tempx.length);
							System.out.println("Moving down");
							g.setColor(Color.GREEN.darker());
							g.fillPolygon(p);
							g.setColor(Color.BLACK);
							g.drawPolygon(p);
							 
							Graphics2D g2d = (Graphics2D) g;
							AffineTransform at = new AffineTransform();
							at.translate((currLocx[number]-1)*50, (currLocy[number])*50);
							at.rotate(Math.toRadians(Math.abs(360-degree[currLocy[number]][currLocx[number]-1])), startX+25, startY+25);
						
							if(type[currLocy[number]][currLocx[number]-1].equals("i")){
								g2d.draw(at.createTransformedShape(I));
								g2d.fill(at.createTransformedShape(I));
							}
							else if(type[currLocy[number]][currLocx[number]-1].equals("l")){
								g2d.draw(at.createTransformedShape(L));
								g2d.fill(at.createTransformedShape(L));
							}
							else if(type[currLocy[number]][currLocx[number]-1].equals("t")){
								g2d.draw(at.createTransformedShape(T));
								g2d.fill(at.createTransformedShape(T));
							}
							else if(type[currLocy[number]][currLocx[number]-1].equals("+")){
								g2d.draw(at.createTransformedShape(P));
								g2d.fill(at.createTransformedShape(P));
							}
							Color c = null;
							try {
								c = (Color) Color.class.getField(color[number]).get(null);
							} catch (IllegalArgumentException | IllegalAccessException
									| NoSuchFieldException | SecurityException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							g.setColor(c);
							currLocy[number]++;
							g.drawOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
							g.fillOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
							moved[number] = true;
							setD.get(number).add(2);
							setD.get(number).remove(count);
							count = 0;
						}
					}
					else if(setD.get(number).get(count)==3){
						if( (types.equals("l") && (degrees ==90||degrees == 180))||(types.equals("t") && (degrees==180 || degrees == 270|| degrees == 0))||(types.equals("i") && (degrees==90 || degrees == 270) ) ||(types.equals("+"))){
							int tempx[] = {(currLocx[number]-1)*50+startX, (currLocx[number])*50+startX, (currLocx[number])*50+startX, (currLocx[number]-1)*50+startX};
							int tempy[] = {currLocy[number]*50+startY, currLocy[number]*50+startY, currLocy[number]*50+startY +50, currLocy[number]*50+startY + 50};
							Polygon p = new Polygon(tempx, tempy, tempx.length);
							System.out.println("Moving LEft");
							g.setColor(Color.GREEN.darker());
							g.fillPolygon(p);
							g.setColor(Color.BLACK);
							g.drawPolygon(p);
							 
							Graphics2D g2d = (Graphics2D) g;
							AffineTransform at = new AffineTransform();
							at.translate((currLocx[number]-1)*50, (currLocy[number])*50);
							at.rotate(Math.toRadians(Math.abs(360-degree[currLocy[number]][currLocx[number]-1])), startX+25, startY+25);
						
							if(type[currLocy[number]][currLocx[number]-1].equals("i")){
								g2d.draw(at.createTransformedShape(I));
								g2d.fill(at.createTransformedShape(I));
							}
							else if(type[currLocy[number]][currLocx[number]-1].equals("l")){
								g2d.draw(at.createTransformedShape(L));
								g2d.fill(at.createTransformedShape(L));
							}
							else if(type[currLocy[number]][currLocx[number]-1].equals("t")){
								g2d.draw(at.createTransformedShape(T));
								g2d.fill(at.createTransformedShape(T));
							}
							else if(type[currLocy[number]][currLocx[number]-1].equals("+")){
								g2d.draw(at.createTransformedShape(P));
								g2d.fill(at.createTransformedShape(P));
							}
							Color c = null;
							try {
								c = (Color) Color.class.getField(color[number]).get(null);
							} catch (IllegalArgumentException | IllegalAccessException
									| NoSuchFieldException | SecurityException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							g.setColor(c);
							currLocx[number]--;
							g.drawOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
							g.fillOval((currLocx[number]-1)*50+startX+10, currLocy[number]*50+startY+10, 30, 30);
							moved[number] = true;
							setD.get(number).add(3);
							setD.get(number).remove(count);
							count = 0;
						}
					}
					count++;
				}
				moved[number] = false;
			}
		}
	}

