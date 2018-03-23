package main.java.rendering;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RenderPath extends JPanel {
	
	private int scale = 15;
	
	JFrame jFrame = new JFrame();
	
	public RenderPath() {
		
	};
	
	public void renderTest() {
		jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		final LinesComponent comp = new LinesComponent();
		comp.addLine(1, 3, 14, 20, Color.RED);
		comp.addLine(2, 2, 15, 20, Color.RED);
		comp.addLine(3, 3, 16, 20, Color.RED);
	    comp.setPreferredSize(new Dimension(320, 200));
	    jFrame.getContentPane().add(comp, BorderLayout.CENTER);
	    jFrame.pack();
	    jFrame.setVisible(true);
	};
	
	public void render(Point[] points, int[] connections) {
		jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		final PointsComponent compP = new PointsComponent();
		for (int i = 0; i < points.length; i++) {
			int x1 = (int)points[i].getX() * scale;
			int y1 = (int)points[i].getY() * scale;
			compP.addPoint(x1, y1);
		}
		compP.setPreferredSize(new Dimension(320, 200));
		jFrame.getContentPane().add(compP, BorderLayout.CENTER);
		jFrame.pack();
		
		final LinesComponent compL = new LinesComponent();
		for (int i = 0; i < connections.length; i++) {
			int x1 = (int)points[i].getX() * scale;
			int y1 = (int)points[i].getY() * scale;
			int x2 = (int)points[connections[i]].getX() * scale;
			int y2 = (int)points[connections[i]].getY() * scale;
			compL.addLine(x1, y1, x2, y2);
		}
		compL.setPreferredSize(new Dimension(320, 200));
	    jFrame.getContentPane().add(compL, BorderLayout.CENTER);
	    jFrame.pack();
	    jFrame.setVisible(true);
	}

}
