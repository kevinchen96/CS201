package chen884_CSCI201_Assignment4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JPanel;

public class DrawPanel extends JPanel{
	public DrawPanel(){
		super();
		this.setBackground(Color.WHITE);
		
	}
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i = 0; i < 10; i ++){
        	int coordx[] = {getWidth()/2-225 + 50*i, getWidth()/2-225 + 50*i};
            int coordy[] = {getHeight()/2+225, getHeight()/2-225};
            Polygon temp = new Polygon(coordx, coordy, coordx.length);
            g.drawPolygon(temp);
        }
        for(int i = 0; i < 10; i ++){
        	int coordx[] = {getWidth()/2-225, getWidth()/2+225};
            int coordy[] = {getHeight()/2-225 + 50*i, getHeight()/2-225 + 50*i};
            Polygon temp = new Polygon(coordx, coordy, coordx.length);
            g.drawPolygon(temp);
        }
    }
}
