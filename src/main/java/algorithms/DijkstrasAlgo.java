package main.java.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import main.java.utility.ConnectablePoint;
import main.java.utility.Util;

public class DijkstrasAlgo {
	
	public int[][] adjMatrix;
	public ConnectablePoint[] points;
	double max = -1D;
	public Map<ConnectablePoint, Double> distances = new HashMap<ConnectablePoint, Double>();
	
	public DijkstrasAlgo(int[][] adjMatrix, ConnectablePoint[] points) {
		this.adjMatrix = adjMatrix;
		this.points = points;
	};
	
	/*
	 * Find start point
	 * Find all adjacent points
	 * Find distance to all adjacent points
	 * Going from the smallest to the largest connection repeat steps 2 and 3 for every connection
	 * Find smallest connection and repeat steps 2 to 4, only going back to previously searched points if smaller distance is found
	 * Terminate after finishing level of end point
	 */
	
	/*
	 * Function:
	 * Array of point indexes as input
	 * Marks points with distances
	 * Array of points marked as output
	 * 
	 * Wrapper:
	 * Calls function with start point
	 * Makes input list from output
	 * If endpoint marked:
	 * max distance is assigned
	 * 	any points above max distance are culled from input list
	 * If input list is empty:
	 * 	break loop
	 * 	print result
	 * 	output render
	 * Calls function with input list
	 * Loop to line 2
	 */
	
	public int[][] run(int[] pointsIn, int end) {
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
						System.out.println("Point " + end + " found with distance " + points[end].distance);
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
