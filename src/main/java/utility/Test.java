package main.java.utility;

import java.io.IOException;
import java.util.ArrayList;

import main.java.algorithms.DijkstrasAlgo;
import main.java.algorithms.PrimsAlgoV2;

public class Test {
	
	ArrayList<Double> distP = new ArrayList<Double>();
	ArrayList<Double> distD = new ArrayList<Double>();
	
	ArrayList<int[][]> adjMatrixes = new ArrayList<int[][]>();
	ArrayList<ConnectablePoint[]> pointArrays = new ArrayList<ConnectablePoint[]>();
	
	int testSize;
	int start;
	int end;
	int testCases = 0;
	
	double maxDist;
	boolean specified;
	
	double runtimeP;
	double runtimeD;
	
	int operP;
	int operD;
	

	ArrayList<Double> runtimePr = new ArrayList<Double>();
	ArrayList<Double> runtimeDi = new ArrayList<Double>();
	
	ArrayList<Integer> operPr = new ArrayList<Integer>();
	ArrayList<Integer> operDi = new ArrayList<Integer>();
	
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
		Timer timer = new Timer();
		timer.start();
		this.testCases = testCases;
		adjMatrixes.clear();
		pointArrays.clear();
		for (int i = 0; i < testCases; i++) {
			adjMatrixes.add(Path.randomAdjacencyMatrix(testSize));
			pointArrays.add(Path.randomPointArray(testSize, maxDist));
		}
		timer.end();
		System.out.println("Operation took " + timer.nanos() + " nanos. (" + timer.seconds() + " seconds)");
	};
	
	public void runTests() {
		Timer timer = new Timer();
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
			distP.add(prims);
			distD.add(dijkstras);
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
		
		System.out.println("Average number of operations for Prims Algorithm was " + (1D * operP)/testCases + ".");
		System.out.println("Average number of operations for Dijkstras Algorithm was " + (1D * operD)/testCases + ".");
	};
	
	public void exportTests() throws IOException {
		Timer timer = new Timer();
		timer.start();
		long startTime = System.nanoTime();
		for (int i = 0; i < pointArrays.size(); i++) {
			if (!specified) {
				start = (int)Math.ceil(Math.random() * testSize) - 1;
				end = (int)Math.ceil(Math.random() * testSize) - 1;
			};
			
			System.out.println("Test " + (i+1));
			double prims = runPrims(i);
			distP.add(prims);
			
			double dijkstras = runDijkstras(i);
			distD.add(dijkstras);
			System.out.println();
		};
		
		String[] files = new String[] {"prims", "dijkstras"};
		ArrayList<ArrayList<Double>> dists = new ArrayList<ArrayList<Double>>();
		dists.add(distP);
		dists.add(distD);
		
		ArrayList<ArrayList<Double>> runtimes = new ArrayList<ArrayList<Double>>();
		runtimes.add(runtimePr);
		runtimes.add(runtimeDi);
		
		ArrayList<ArrayList<Integer>> operations = new ArrayList<ArrayList<Integer>>();
		operations.add(operPr);
		operations.add(operDi);
		
		int[] compare = compareDists();
		double[] runtime = new double[] {runtimeD, runtimeP};
		int[] operation = new int[] {operD, operP};
		
		Util.exportToCSV(files, dists, runtimes, operations, compare, runtime, operation, testCases);
		
		timer.end();
		long endTime = System.nanoTime();
		System.out.println("Operation took " + timer.nanos() + " nanos. (" + timer.seconds() + " seconds)");
		System.out.println("Operation took " + (endTime - startTime) / 1000000000D + " seconds.");
		System.out.println();
	};
	
	public double runPrims(int caseNum) {
		Timer timer = new Timer();
		timer.start();
		int[][] matrix = this.adjMatrixes.get(caseNum);
		ConnectablePoint[] points = this.pointArrays.get(caseNum).clone();
		
		PrimsAlgoV2 v2 = new PrimsAlgoV2(matrix, points);
		System.out.println(points[0].connected);
		v2.run(start, end);
		timer.end();
		System.out.println("Points: " + v2.points[start] + " " + v2.points[end]);
		runtimeP += timer.seconds();
		runtimePr.add(timer.seconds());
		operP += v2.operations;
		operPr.add(v2.operations);
		return v2.points[end].distance;
	};
	
	public double runDijkstras(int caseNum) {
		Timer timer = new Timer();
		timer.start();
		int[][] matrix = this.adjMatrixes.get(caseNum);
		ConnectablePoint[] points = this.pointArrays.get(caseNum).clone();
		
		DijkstrasAlgo d = new DijkstrasAlgo(matrix, points);
		System.out.println(points[0].connected);
		int[] start = new int[] {this.start};
		d.run(start, end);
		System.out.println("Point " + start[0] + " to point " + end);
		timer.end();
		System.out.println("Points: " + d.points[start[0]] + " " + d.points[end]);
		runtimeD += timer.seconds();
		runtimeDi.add(timer.seconds());
		operD += d.operations;
		operDi.add(d.operations);
		return d.points[end].distance;
	};
	
	public int[] compareDists() {
		int[] result = new int[] {0, 0, 0};
		int dumb = 0;
		for (int i = 0; i < distP.size(); i++) {
			if (distP.get(i) == -1 || distD.get(i) == -1)
				dumb++;
			if (distP.get(i) > distD.get(i))
				result[0]++;
			else if (distP.get(i).equals(distD.get(i)))
				result[1]++;
			else if (distP.get(i) < distD.get(i))
				result[2]++;
		}
		result[1] -= dumb;
		System.out.println("Things couldn't connect in " + dumb + "/" + testCases + " instances.");
		return result;
	};
	
	public void timerTest() throws InterruptedException {
		Timer t = new Timer();
		t.start();
		Thread.sleep(10000);
		t.end();
		System.out.println(t.seconds());
	};

}
