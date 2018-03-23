package main.java.prims;

import java.awt.Point;
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
	
	public static Point pointA = new Point(1, 1);
	public static Point pointB = new Point(2, 3);
	public static Point pointC = new Point(3, 4);
	public static Point pointD = new Point(5, 5);
	public static Point pointE = new Point(6, 8);
	public static Point pointF = new Point(7, 10);
	public static Point pointG = new Point(5, 7);
	public static Point pointH = new Point(5, 10);
	public static Point pointI = new Point(7, 13);
	public static Point pointJ = new Point(9, 4);
	public static Point pointK = new Point(8, 5);
	
	public static Point[] points = {pointA, pointB, pointC, pointD, pointE, pointF, pointG, pointH, pointI, pointJ, pointK};
	
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
	}

}
