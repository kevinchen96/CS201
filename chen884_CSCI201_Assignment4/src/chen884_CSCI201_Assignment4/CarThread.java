package chen884_CSCI201_Assignment4;

import java.awt.Color;
import java.awt.Graphics;

public class CarThread extends Thread {
	RoadPanel r;
	String color;
	int ai, locx, locy, number, count;
	double speed;
	public CarThread(RoadPanel r, String color, int ai, double speed, int locx, int locy, int number){
		this.r = r;
		this.color = color;
		this.ai = ai;
		this.speed = speed;
		this.locx = locx; 
		this.locy = locy;
		this.number = number;
		count = 1;
		
	}
	public void run(){
		try{
			this.sleep((long) (1/(speed*6)*1000));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		while(true){
			if(count%6==0){
				count = 0;
				r.updateLoc(number);
			}
			r.update(number);
			long wait = (long) (1/(speed*6)*1000);
			try{
				this.sleep(wait);
				count++;
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
}
