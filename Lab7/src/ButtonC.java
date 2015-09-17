import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JButton;


public class ButtonC extends JButton {
	public ButtonC(String x){
		super(x);
	}
	public void paintComponent(Graphics g){
		int xPoly[] = {0, 125};
	    int yPoly[] = {0, 25};
	    int xPoly1[] = {125, 0};
	    int yPoly1[] = {0, 25};
	    
	    Polygon poly = new Polygon(xPoly, yPoly, xPoly.length);
	    Polygon poly1 = new Polygon(xPoly1, yPoly1, xPoly1.length);
	    super.paintComponent(g);
	    g.setColor(Color.RED);
	    g.drawPolygon(poly);
	    g.drawPolygon(poly1);
	    
	}
}
