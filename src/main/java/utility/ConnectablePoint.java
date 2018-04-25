package main.java.utility;

import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class ConnectablePoint extends java.awt.Point {
	
	public boolean connected = false;
	public double distance = -1;
	public Color color = Color.RED;
	
//	public ArrayList<ConnectablePoint> connectedFrom = new ArrayList<ConnectablePoint>();
	
	public ConnectablePoint(int x, int y) {
		super(x, y);		
	};
	
	public void setConnected(boolean connected) {
		this.connected = connected;
	};
	
	public void mark() {
		color = Color.CYAN;
	};
	
	public void mark(Color color) {
		this.color = color;
	}

}
