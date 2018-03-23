package main.java.prims;

import java.awt.Point;

public class PrimsAlgoV2 {
	
	public Heap heap;
	public int[][] adjMatrix;
	public Point[] points;
	
	public PrimsAlgoV2(int[][] adjMatrix, Point[] points) {
		this.adjMatrix = adjMatrix;
		this.points = points;
		this.heap = new Heap();
	};
	
	public void makeHeap() {
		System.out.println(points.length);
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < adjMatrix[i].length; j++) {
				if (adjMatrix[i][j] == 1) {
					double distance = points[i].distance(points[j]);
					heap.add(distance, new Point[] {points[i], points[j]});
				}
			}
		}
	};

}
