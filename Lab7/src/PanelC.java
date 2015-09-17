import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;


public class PanelC extends JPanel{
	
	public void paintComponent(Graphics g){
		int xPoly[] = {10, 20, 25, 25, 20, 10, 5, 5};
	    int yPoly[] = {10, 10, 20, 27, 37, 37, 27, 20};
	    
	    Polygon poly = new Polygon(xPoly, yPoly, xPoly.length);
	    AffineTransform at = new AffineTransform();
	    at.scale(4, 4);
	    at.translate(45, 0);
	    super.paintComponent(g);
	    Graphics2D g2D = (Graphics2D) g;
	    g2D.setPaint(Color.BLUE);
	    g2D.fill( at.createTransformedShape(poly));
	}
}
