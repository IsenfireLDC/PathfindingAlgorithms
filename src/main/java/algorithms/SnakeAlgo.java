package main.java.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import main.java.utility.ConnectablePoint;
import main.java.utility.Util;

public class SnakeAlgo {
	
	public int[][] adjMatrix;
	public ConnectablePoint[] points;
	public Map<ConnectablePoint, Double> distances = new HashMap<ConnectablePoint, Double>();
	
	public SnakeAlgo(int[][] adjMatrix, ConnectablePoint[] points) {
		this.adjMatrix = adjMatrix;
		this.points = points;
	};
	
	/*
	 * Find start point
	 * Find all adjacent points
	 * Find distance to all adjacent points
	 * Starting from the smallest connection repeat steps 2 and 3
	 * Terminate when end point is found
	 */
	
	public int[][] run(int start, int end) {
		if (start == end) {
			System.out.println("Point " + end + " found with distance of 0");
		}
		int[][] result = new int[points.length][points.length];
		ArrayList<Integer> checked = new ArrayList<Integer>();
		double distance = 0D;
		int currentPoint = start;
		boolean finished = false;
		int count = 0;
		while (!finished) {
			System.out.println();
			System.out.println("Iteration " + ++count + ":");
			for (int i = 0; i < adjMatrix[currentPoint].length; i++) {
				if (adjMatrix[currentPoint][i] == 1) {
					distance = points[currentPoint].distance + points[currentPoint].distance(points[i]);
					System.out.println(distance);
					
					if (i == end) {
						System.out.println("Point " + end + " found with distance of " + distance);
						finished = true;
						break;
					}
					
					if (!checked.contains(i)) {
						if (distance < points[i].distance || !points[i].connected) {
							points[i].distance = distance;
							points[i].connected = true;
						}
						this.distances.put(points[i], distance);
					}
				}
			}
			checked.add(currentPoint);
			currentPoint = Util.search(Util.min(distances), points);
			distances.clear();
			
			if (count >= 500) {  //Just in case
				System.err.println("Terminating due to large number of iterations");
				finished = true;
			}
		}
		return result;
	}

}
