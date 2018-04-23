package main.java.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import main.java.algorithms.DijkstrasAlgo;
import main.java.algorithms.PrimsAlgoV2;

public class Test {
	
	Map<Double, Double> distMap = new HashMap<Double, Double>();
	
	ArrayList<int[][]> adjMatrixes = new ArrayList<int[][]>();
	ArrayList<ConnectablePoint[]> pointArrays = new ArrayList<ConnectablePoint[]>();
	
	int testSize;
	int start;
	int end;
	int testCases = 0;
	
	double maxDist;
	boolean specified;
	
	Timer timer = new Timer();
	
	double runtimeP;
	double runtimeD;
	
	double operP;
	double operD;
	
	public Test(int testSize, double maxDist) {
		this.testSize = testSize;
		this.maxDist = maxDist;
		this.start = 0;
		this.end = testSize;
		this.specified = false;
	};
	
	public Test(int testSize, double maxDist, int start, int end) {
		this.testSize = testSize;
		this.maxDist = maxDist;
		this.start = start;
		this.end = end;
		this.specified = true;
	};
	
	public void genTests(int testCases) {
		timer.start();
		this.testCases = testCases;
		for (int i = 0; i < testCases; i++) {
			adjMatrixes.add(Path.randomAdjacencyMatrix(testSize));
			pointArrays.add(Path.randomPointArray(testSize, maxDist));
		}
		timer.end();
		System.out.println("Operation took " + timer.nanos() + " nanos. (" + timer.seconds() + " seconds)");
	};
	
	public void runTests() {
		timer.start();
		for (int i = 0; i < pointArrays.size(); i++) {
			if (!specified) {
				start = (int)Math.ceil(Math.random() * testSize) - 1;
				end = (int)Math.ceil(Math.random() * testSize) - 1;
			}
			System.out.println("Test " + (i+1));
			Path.printMatrix(adjMatrixes.get(i));
			double prims = runPrims(i);
			double dijkstras = runDijkstras(i);
			distMap.put(prims, dijkstras);
			System.out.println();
		}
		timer.end();
		System.out.println("Operation took " + timer.nanos() + " nanos. (" + timer.seconds() + " seconds)");
		System.out.println();
		
//		System.out.println(distMap);
		int[] compare = compareDists();
		System.out.println("Dijkstras Algorithm gave a better distance in " + compare[0] + "/" + testCases + " instances.");
		System.out.println("Both algorithms gave the same distance in " + compare[1] + "/" + testCases + " instances.");
		System.out.println("Prims Algorithm gave a better distance in " + compare[2] + "/" + testCases + " instances.");
		System.out.println();
		
		System.out.println("Average runtime of Prims Algorithm was " + runtimeP/testCases + " seconds.");
		System.out.println("Average runtime of Dijkstras Algorithm was " + runtimeD/testCases + " seconds.");
		System.out.println();
		
		System.out.println("Average number of operations for Prims Algorithm was " + operP/testCases + ".");
		System.out.println("Average number of operations for Dijkstras Algorithm was " + operD/testCases + ".");
	};
	
	public double runPrims(int caseNum) {
		timer.start();
		int[][] matrix = this.adjMatrixes.get(caseNum);
		ConnectablePoint[] points = new ConnectablePoint[testSize];
		points = this.pointArrays.get(caseNum);
		
		PrimsAlgoV2 v2 = new PrimsAlgoV2(matrix, points);
		System.out.println(points[0].connected);
		v2.run(start, end);
		timer.end();
		System.out.println("Points: " + v2.points[start] + " " + v2.points[end]);
		runtimeP += timer.seconds();
		operP += v2.operations;
		return v2.points[end].distance;
	};
	
	public double runDijkstras(int caseNum) {
		timer.start();
		int[][] matrix = this.adjMatrixes.get(caseNum);
		ConnectablePoint[] points = new ConnectablePoint[testSize];
		points = this.pointArrays.get(caseNum);
		
		DijkstrasAlgo d = new DijkstrasAlgo(matrix, points);
		System.out.println(points[0].connected);
		int[] start = new int[] {this.start};
		d.run(start, end);
		System.out.println("Point " + start[0] + " to point " + end);
		timer.end();
		System.out.println("Points: " + d.points[start[0]] + " " + d.points[end]);
		runtimeD += timer.seconds();
		operD += d.operations;
		return d.points[end].distance;
	};
	
	public int[] compareDists() {
		int[] result = new int[] {0, 0, 0};
		Set<Double> set = distMap.keySet();
		Iterator<Double> iter = set.iterator();
		while (iter.hasNext()) {
			double key = iter.next();
			if (key > distMap.get(key))
				result[0]++;
			else if (key == distMap.get(key))
				result[1]++;
			else if (key < distMap.get(key))
				result[2]++;
		}
		return result;
	};

}
