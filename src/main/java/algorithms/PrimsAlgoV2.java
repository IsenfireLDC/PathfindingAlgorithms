package main.java.algorithms;

import java.awt.Color;
import java.util.ArrayList;

import main.java.utility.ConnectablePoint;
import main.java.utility.Heap;

public class PrimsAlgoV2 {
	
	public Heap fullHeap;
	public Heap currentHeap;
	public int[][] adjMatrix;
	public ConnectablePoint[] points;
	
	public int operations = 0;
	
	public PrimsAlgoV2(int[][] adjMatrix, ConnectablePoint[] points) {
		this.adjMatrix = adjMatrix;
		this.points = points;
		this.fullHeap = new Heap();
		this.currentHeap = new Heap();
	};
	
	public void makeHeap() {
		System.out.println("Running for " + points.length + " points.");
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < adjMatrix[i].length; j++) {
				if (adjMatrix[i][j] == 1) {
					double distance = points[i].distance(points[j]);
					fullHeap.add(distance, new ConnectablePoint[] {points[i], points[j]});
				}
			}
		}
		System.out.println("==================================================== fullHeap created =====================================================");
	};
	
	public int[] search(ConnectablePoint[] points) { //finds the indexes of the given points
		int[] found = new int[points.length];
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < this.points.length; j++) {
				if (this.points[j].equals(points[i])) {
					found[i] = j;
				}
			}
		}
		return found;
	};
	
	public void findConnections(int[] indexes) { //adds all connections for given points to currentHeap
		/*System.out.print("Finding connections for point(s) ");
		for (int index : indexes) {
			System.out.print(index + " ");
		};
		System.out.println();*/
		for (int index : indexes) {
			double distance = -1D;
			ConnectablePoint[] connection = new ConnectablePoint[0];
			for (int i = 0; i < adjMatrix[index].length; i++) {
				if (adjMatrix[index][i] == 1) {
					distance = points[index].distance(points[i]);
					connection = new ConnectablePoint[] {points[index], points[i]};
					//System.out.println(distance);
					this.currentHeap.add(distance, connection);
				}
			}
		}
	};
	
	public int[][] run() {
		makeHeap();
		ArrayList<int[]> resultList = new ArrayList<int[]>();
		double dist = fullHeap.min();
		ConnectablePoint[] connection = fullHeap.pop(dist);
		int[] current = search(connection);
		findConnections(current);
		while (!currentHeap.isEmpty()) {
			//System.out.println("Rough heap size is " + currentHeap.distances.size());
			//set values
			dist = currentHeap.min();
			connection = currentHeap.pop(dist);
			current = search(connection);
			if (!(points[current[0]].connected && points[current[1]].connected)) {
				//Connect the points
				resultList.add(new int[] {current[0], current[1]});
				
				if (points[current[0]].connected && !points[current[1]].connected) {
					current = search(new ConnectablePoint[] {connection[1]});
				} else if (points[current[1]].connected && !points[current[0]].connected) {
					current = search(new ConnectablePoint[] {connection[0]});
				};
				
				for (int index : current) {
					points[index].setConnected(true);
				};
				
				findConnections(current);
			}
			
		}
		int[][] result = new int[resultList.size()][2];
		result = resultList.toArray(result);
		return result;
	};
	
	public int[][] run(int start) {
		points[start].mark();
		points[start].connected = true;
		ArrayList<int[]> resultList = new ArrayList<int[]>();
		ConnectablePoint[] connection = new ConnectablePoint[] {points[start]};
		
		//find first connection
		int[] current = search(connection);
		findConnections(current);
		while (!currentHeap.isEmpty()) {
			//System.out.println("Rough heap size is " + currentHeap.distances.size());
			//set values
			double dist = currentHeap.min();
			connection = currentHeap.pop(dist);
			operations++;
			current = search(connection);
			if (!(points[current[0]].connected && points[current[1]].connected)) {
				//Connect the points
				operations++;
				resultList.add(new int[] {current[0], current[1]});
				
				double distance = points[current[0]].distance(points[current[1]]);
				if (points[current[0]].connected && !points[current[1]].connected) {
					points[current[1]].distance = points[current[0]].distance + distance;
					current = search(new ConnectablePoint[] {connection[1]});
					
				} else if (points[current[1]].connected && !points[current[0]].connected) {
					points[current[0]].distance = points[current[1]].distance + distance;
					current = search(new ConnectablePoint[] {connection[0]});
				};
				
				for (int index : current) {
					points[index].setConnected(true);
//					points[currentA[--indexA]].connectedFrom.add(points[index]);
				};
				
				//find new possible connections
				operations++;
				findConnections(current);
			}
			
		}
		
		int[][] result = new int[resultList.size()][2];
		result = resultList.toArray(result);
		return result;
	};
	
	public int[][] run(int start, int end) {
		int[][] path = run(start);
		points[end].mark(Color.GREEN);
		System.out.println("Distance from point " + start + " to point " + end + ": " + points[end].distance);
		return path;
	};

}
