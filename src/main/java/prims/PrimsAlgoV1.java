package main.java.prims;

import java.awt.Point;
import java.util.ArrayList;

import main.java.rendering.RenderPath;

public class PrimsAlgoV1 {
	
	public PrimsAlgoV1() {
		
	};
	
	public static void main(String[] args) {
		//renderPrims(Path.points, Path.adjacencyMatrix);
		renderPrims(Path.points, Path.makeFullMatrix(Path.points.length));
	};
	
	public static void renderPrims(Point[] points, int[][] adjMatrix) {
		System.out.println("Running Prims Algorithm for matrix:");
		Path.printMatrix(adjMatrix);
		int[] connections = primsAlgo(Path.points, adjMatrix);
		
		RenderPath p = new RenderPath();
		p.render(Path.points, connections);
	}
	
	public static int[] primsAlgo(Point[] points, int[][] adjMatrix) {
		int[] result = new int[points.length];
		for (int i = 0; i < points.length; i++) {
			System.out.println("Checking point " + i);
			ArrayList<Integer> adjacents = getAdjacents(adjMatrix[i]);
			
			double prevDist = -1;
			Point connection = null;
			int connectionI = -1;
			boolean flag = false;
			for (int j = 0; j < adjacents.size(); j++) {
				
				int currentPoint = adjacents.get(j);
				double dist = points[i].distance(points[currentPoint]);
				//System.out.println("Adjacent to " + adjacents.get(j) + ": " + dist);
				if (dist < prevDist || !flag) {
					prevDist = dist;
					connection = points[currentPoint];
					connectionI = currentPoint;
					flag = true;
				}
			}
			if (prevDist == -1) {
				System.out.println("Not connected to any vertices.");
				connectionI = i;
			} else {
				System.out.println("Connected " + points[i] + "(" + i + ")" + " to " + connection + "(" + connectionI + ")" + " with distance of " + prevDist);
			}
			result[i] = connectionI;
		}
		return result;
	};
	
	public static ArrayList<Integer> getAdjacents(int[] adjacents) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < adjacents.length; i++) {
			if (adjacents[i] == 1) {
				result.add(i);
			}
		}
		return result;
	}
	
}
