package main.java.utility;

import java.util.ArrayList;

public class Path {
	
	public static int[][] adjacencyMatrix = {
			{0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0}, //0 connected to 1, 2, 3
			{1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0}, //1 connected to 0, 2
			{1, 1, 0, 1, 1, 1, 0, 0, 1, 0, 1}, //2 connected to 0, 1, 3, 4, 5
			{1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0}, //3 connected to 0, 2, 4 
			{0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1}, //4 connected to 2, 3
			{0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1}, //5 connected to 3, 6
			{0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, //6 connected to 5
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //7 not connected
			{1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1},
			{1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0},
			{0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1}
	};
	
	public static int[][] makeFullMatrix(int size) {
		int[][] result = new int[size][size];
		for (int i = 0; i < size; i++) {
			int[] row = new int[size];
			for (int j = 0; j < size; j++) {
				if (i == j) {
					row[j] = 0;
				} else {
					row[j] = 1;
				}
			}
			result[i] = row;
		}
		return result;
	}
	
	public static ConnectablePoint pointA = new ConnectablePoint(1, 1);
	public static ConnectablePoint pointB = new ConnectablePoint(2, 3);
	public static ConnectablePoint pointC = new ConnectablePoint(3, 4);
	public static ConnectablePoint pointD = new ConnectablePoint(5, 5);
	public static ConnectablePoint pointE = new ConnectablePoint(6, 8);
	public static ConnectablePoint pointF = new ConnectablePoint(7, 10);
	public static ConnectablePoint pointG = new ConnectablePoint(5, 7);
	public static ConnectablePoint pointH = new ConnectablePoint(5, 10);
	public static ConnectablePoint pointI = new ConnectablePoint(7, 13);
	public static ConnectablePoint pointJ = new ConnectablePoint(9, 4);
	public static ConnectablePoint pointK = new ConnectablePoint(8, 5);
	
	public static ConnectablePoint[] points = {pointA, pointB, pointC, pointD, pointE, pointF, pointG, pointH, pointI, pointJ, pointK};
	
	public static ArrayList<ArrayList<Integer>> makeAdjacencyList(int[][] adjMatrix) {
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		for (int[] connect : adjMatrix) {
			ArrayList<Integer> connList = new ArrayList<Integer>();
			for (int conn : connect) {
				if (conn == 1) {
					connList.add(conn);
				}
			}
			list.add(connList);
		}
		return list;
	};
	
	public static void printMatrix(int[][] adjMatrixIn) {
		for (int[] arrIn : adjMatrixIn) {
			for (int intIn : arrIn) {
				System.out.print(intIn + " ");
			}
			System.out.println();
		}
	};
	
	public static ConnectablePoint[] randomPointArray(int length) {
		ConnectablePoint[] points = new ConnectablePoint[length];
		for (int i = 0; i < length; i++) {
			boolean exists = false;
			int pointX = (int)Math.round(Math.random() * 10);
			int pointY = (int)Math.round(Math.random() * 10);
			ConnectablePoint point = new ConnectablePoint(pointX, pointY);
			for (ConnectablePoint pointIn : points) {
				if (point.equals(pointIn)) {
					i--;
					exists = true;
					break;
				}
			}
			if (!exists) {
				points[i] = point;
			}
		}
		return points;
	};
	
	public static int[][] randomAdjacencyMatrix(int length) {
		int[][] adjList = new int[length][length];
		int limit = 0;
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < limit; j++) {
				int connection = (int)Math.round(Math.random());
				adjList[i][j] = connection;
				adjList[j][i] = connection;
			}
			limit++;
		}
		return adjList;
	};
	
	public static void dijkstrasTestCase() {
		Path.adjacencyMatrix = new int[][] {
			{0, 1, 1, 1, 0, 0, 0},
			{1, 0, 0, 0, 1, 0, 0},
			{1, 0, 0, 0, 0, 0, 1},
			{1, 0, 0, 0, 0, 1, 0},
			{0, 1, 0, 0, 0, 0, 1},
			{0, 0, 0, 1, 0, 0, 0},
			{0, 0, 1, 0, 1, 0, 0}
		};
		ConnectablePoint pointA = new ConnectablePoint(0, 0);
		ConnectablePoint pointB = new ConnectablePoint(2, 1);
		ConnectablePoint pointC = new ConnectablePoint(2, 2);
		ConnectablePoint pointD = new ConnectablePoint(1, 2);
		ConnectablePoint pointE = new ConnectablePoint(3, 1);
		ConnectablePoint pointF = new ConnectablePoint(2, 4);
		ConnectablePoint pointG = new ConnectablePoint(5, 1);
		Path.points = new ConnectablePoint[] {pointA, pointB, pointC, pointD, pointE, pointF, pointG};
	}

}
