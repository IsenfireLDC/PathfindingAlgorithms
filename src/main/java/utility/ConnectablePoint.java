package main.java.utility;

import java.awt.Color;

@SuppressWarnings("serial")
public class ConnectablePoint extends java.awt.Point {
	
	public boolean connected = false;
	public double distance = 0;
	public Color color = Color.RED;
	
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
