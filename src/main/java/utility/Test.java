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
		this.testCases = testCases;
		for (int i = 0; i < testCases; i++) {
			if (!specified) {
				start = (int)Math.ceil(Math.random() * testSize) - 1;
				end = (int)Math.ceil(Math.random() * testSize) - 1;
			}
			
			adjMatrixes.add(Path.randomAdjacencyMatrix(testSize));
			pointArrays.add(Path.randomPointArray(testSize, maxDist));
		}
	};
	
	public void runTests() {
		for (int i = 0; i < pointArrays.size(); i++) {
			System.out.println("Test " + i);
			Path.printMatrix(adjMatrixes.get(i));
			double prims = runPrims(i);
			double dijkstras = runDijkstras(i);
			distMap.put(prims, dijkstras);
			System.out.println();
		}
		System.out.println(distMap);
		int[] compare = compareDists();
		System.out.println("Dijkstras Algorithm was better in " + compare[0] + "/" + testCases + " instances.");
		System.out.println("Dijkstras Algorithm gave the same distance in " + compare[1] + "/" + testCases + " instances.");
	};
	
	public double runPrims(int caseNum) {
		int[][] matrix = this.adjMatrixes.get(caseNum);
		ConnectablePoint[] points = this.pointArrays.get(caseNum);
		PrimsAlgoV2 v2 = new PrimsAlgoV2(matrix, points);
		v2.run(start, end);
		return v2.points[end].distance;
	};
	
	public double runDijkstras(int caseNum) {
		int[][] matrix = this.adjMatrixes.get(caseNum);
		ConnectablePoint[] points = this.pointArrays.get(caseNum);
		
		DijkstrasAlgo d = new DijkstrasAlgo(matrix, points);
		int[] start = new int[] {this.start};
		d.run(start, end);
		System.out.println("Point " + start[0] + " to point " + end);
		return d.points[end].distance;
	};
	
	public int[] compareDists() {
		int[] result = new int[] {0, 0};
		Set<Double> set = distMap.keySet();
		Iterator<Double> iter = set.iterator();
		while (iter.hasNext()) {
			double key = iter.next();
			if (key > distMap.get(key))
				result[0]++;
			else if (key == distMap.get(key))
				result[1]++;
		}
		return result;
	}

}
