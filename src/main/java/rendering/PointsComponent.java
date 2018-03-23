package main.java.rendering;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JComponent;

public class PointsComponent extends JComponent {
	
	private static class PointR {
	    final int x; 
	    final int y;
	    final int diameter;
	    final Color color;

	    public PointR(int x, int y, int diameter, Color color) {
	        this.x = x - (diameter / 2);
	        this.y = y - (diameter / 2);
	        this.diameter = diameter;
	        this.color = color;
	    }               
	}
	
	private final LinkedList<PointR> points = new LinkedList<PointR>();

	public void addPoint(int x, int y) {
	    points.add(new PointR(x, y, 5, Color.RED));        
	    repaint();
	}

	public void clearPoints() {
	    points.clear();
	    repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (PointR point : points) {
			g.setColor(point.color);
			g.fillOval(point.x, point.y, point.diameter, point.diameter);
		}
	}
}
