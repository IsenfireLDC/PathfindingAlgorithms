package main.java.algorithms;

import java.util.ArrayList;

import main.java.utility.ConnectablePoint;
import main.java.utility.Util;

public class DijkstrasAlgo {
	
	public int[][] adjMatrix;
	public ConnectablePoint[] points;
	double max = -1D;
	
	public DijkstrasAlgo(int[][] adjMatrix, ConnectablePoint[] points) {
		this.adjMatrix = adjMatrix;
		this.points = points;
	};
	
	public int[][] run(int[] pointsIn, int end) { //TODO Make it renderable
		int[][] result = new int[0][0];
		ArrayList<Integer> recursive = new ArrayList<Integer>();
		double distance = 0D;
		for (int i = 0; i < pointsIn.length; i++) {
			int currentPoint = pointsIn[i];
			points[currentPoint].connected = true;
			for (int j = 0; j < adjMatrix[currentPoint].length; j++) {
				if (adjMatrix[currentPoint][j] == 1) {
					distance = points[currentPoint].distance + points[currentPoint].distance(points[j]);
//					System.out.println("Point " + j + " with distance of " + distance);
					
					if ((distance < points[j].distance || !points[j].connected) && ((max >= 0 && distance < max) || max < 0)) {
						//if distance is less than max or no max and distance is less than current distance or point is not connected
						points[j].distance = distance;
						points[j].connected = true;
						recursive.add(j);
					}
					
					if (j == end) {
						max = points[end].distance;
//						System.out.println("Point " + end + " found with distance " + points[end].distance);
					}
				}
			}
		}
		
		if (!recursive.isEmpty() && max != 0) {
			int[] a = new int[recursive.size()];
			a = Util.toIntArray(recursive);
			run(a, end);
		} else {
			System.out.println(points[end].distance);
		};
		
		return result;
	};
	
	public int[][] runExtended(int[] pointsIn, int end) {
		int[][] result = new int[0][0];
		ArrayList<Integer> recursive = new ArrayList<Integer>();
		double distance = 0D;
		for (int i = 0; i < pointsIn.length; i++) {
			int currentPoint = pointsIn[i];
			for (int j = 0; j < adjMatrix[currentPoint].length; j++) {
				if (adjMatrix[currentPoint][j] == 1) {
					distance = points[currentPoint].distance + points[currentPoint].distance(points[j]);
					System.out.println(distance);
					
					if (distance < points[j].distance || !points[j].connected) {
						//if distance is less than current distance or point is not connected
						points[j].distance = distance;
						points[j].connected = true;
						points[j].connectedFrom.add(points[currentPoint]);
						recursive.add(j);
					}
				}
			}
		}
		
		if (!recursive.isEmpty()) {
			int[] a = new int[recursive.size()];
			a = Util.toIntArray(recursive);
			runExtended(a, end);
		} else {
			System.out.println(points[end].distance);
		};
		
		return result;
	}

}
