package main.java.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Util {
	
	public Util() {};
	
	public static <T> T min(Map<T, Double> distances) {
		boolean first = true;
		double prevDist = 0;
		Set<T> set = distances.keySet();
		Collection<Double> distC = distances.values();
		Double[] arr = new Double[distC.size()];
		arr = distC.toArray(arr);
		for (int i = 0; i < distC.size(); i++) {
			double dist = arr[i];
			if (dist < prevDist || first) {
				//System.out.println(dist + " is less than " + prevDist);
				prevDist = dist;
				first = false;
			}
		}
		//return prevDist;
		T itemR = null;
		for (T item : set) {
			if (distances.get(item) == prevDist) {
				itemR = item;
			}
		}
		return itemR;
	};
	
	public static <T> int search(T point, T[] allPoints) {
		int found = -1;
			for (int j = 0; j < allPoints.length; j++) {
				if (allPoints[j].equals(point)) {
					found = j;
				}
			}
		return found;
	};
	
	public static int[] toIntArray(ArrayList<Integer> arrList) {
		int[] result = new int[arrList.size()];
		for (int i = 0; i < arrList.size(); i++) {
			result[i] = arrList.get(i);
		}
		return result;
	};
	
	public static <T, E> void printMapExpanded(Map<T, E> map) {
		System.out.println("Printing map: " + map);
		Set<T> set = map.keySet();
		Collection<E> collection = map.values();
		Iterator<E> iterV = collection.iterator();
		Iterator<T> iterK = set.iterator();
		while (iterK.hasNext()) {
			T key = iterK.next();
			E value = (E) iterV.next();
			System.out.println(key + " " + value);
		}
	};
	
	public static void exportToCSV(String[] files, ArrayList<ArrayList<Double>> dists, ArrayList<ArrayList<Double>> runtimes, ArrayList<ArrayList<Integer>> operations) throws IOException {
		String sep = System.getProperty("line.separator");
		for (int i = 0; i < files.length; i++) {
			File file = new File(files[i] + ".csv");
			Writer writer = new BufferedWriter(new FileWriter(file));
			
			writer.write(files[i] + ",Distance,Time,Operations" + sep);
			
			for (int j = 0; j < dists.get(i).size(); j++) {
				String dist = dists.get(i).get(j).toString();
				if (dists.get(i).get(j).equals(-1D)) {
					dist = "BROKE";
				};
				
				writer.write((j+1) + "," + dist + "," + runtimes.get(i).get(j) + "," + operations.get(i).get(j) + sep);
			};
			
			int k = dists.get(i).size() + 1;
			writer.write("Averages:,=AVERAGE(B2:B" + k + "),=AVERAGE(C2:C" + k + "),=AVERAGE(D2:D" + k + ")" + sep);
			writer.write("Path Not Found:,\"=COUNTIF(B2:B101, \"=BROKE\")\"");
			
			writer.flush();
			writer.close();
		}
	};
	
	public static void exportToCSV(String[] files, ArrayList<ArrayList<Double>> dists, ArrayList<ArrayList<Double>> runtimes, ArrayList<ArrayList<Integer>> operations, int[] compare, double[] runtime, int[] operation, int testCases) throws IOException {
		Util.exportToCSV(files, dists, runtimes, operations);
		String sep = System.getProperty("line.separator");
		File file = new File("overview.csv");
		Writer writer = new BufferedWriter(new FileWriter(file));
		
		writer.write(",Better Distance" + sep);
		writer.write("Dijkstras," + compare[0] + "/" + testCases + sep);
		writer.write("Equal," + compare[1] + "/" + testCases + sep);
		writer.write("Prims," + compare[2] + "/" + testCases + sep);
		writer.write("No Path Found," + (testCases - (compare[0] + compare[1] + compare[2]))+ "/" + testCases + sep);
		writer.write("," + sep);
		
		writer.write(",Average Runtime" + sep);
		writer.write("Dijkstras," + runtime[0]/testCases + sep);
		writer.write("Prims," + runtime[1]/testCases + sep);
		writer.write("," + sep);
		
		writer.write(",Average Operations" + sep);
		writer.write("Dijkstras," + (1D * operation[0])/testCases + sep);
		writer.write("Prims," + (1D * operation[1])/testCases + sep);
		
		writer.flush();
		writer.close();
	};

}
