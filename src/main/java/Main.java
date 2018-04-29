package main.java;

import java.io.IOException;

import main.java.algorithms.DijkstrasAlgo;
import main.java.algorithms.PrimsAlgoV2;
import main.java.rendering.RenderPath;
import main.java.utility.ConnectablePoint;
import main.java.utility.Path;
import main.java.utility.Test;

public class Main {
	
	public static void main(String[] args) {
		Test test = new Test(20, 30);
		test.genTests(100);
//		test.runTests();
		try {
			test.exportTests();
		} catch (IOException e) {e.printStackTrace();};
//		for (int i = 0; i < 20; i++) {
//			System.out.println("Test " + (i + 1));
//			testPrims();
//		}
	};
	
	public static void testPrims() {
		int size = 5;
		int[][] matrix = Path.randomAdjacencyMatrix(size)/*Path.makeFullMatrix(size)*/;
		ConnectablePoint[] points = Path.randomPointArray(size, 30);
		PrimsAlgoV2 v2 = new PrimsAlgoV2(matrix, points);
		Path.printMatrix(matrix);
		
//		RenderPath r = new RenderPath();
		//r.grid(50);
		/*r.render(points, */v2.run((int)Math.ceil(Math.random() * size) - 1, (int)Math.ceil(Math.random() * size) - 1)/*)*/;		
	};
	
	public static void testDijkstras() {
		int size = Path.points.length/*20*/;
//		Path.dijkstrasTestCase();
		int[][] matrix = /*Path.adjacencyMatrix*/Path.randomAdjacencyMatrix(size);
		ConnectablePoint[] points = /*Path.points*/Path.randomPointArray(size);
		
		Path.printMatrix(matrix);
		
		DijkstrasAlgo d = new DijkstrasAlgo(matrix, points);
		int end = (int)Math.ceil(Math.random()*size) - 1;
		int[] start = new int[] {0};
		d.run(start, end);
		System.out.println("Point " + start[0] + " to point " + end);
	}
}
