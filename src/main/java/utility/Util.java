package main.java.utility;

import java.util.ArrayList;
import java.util.Collection;
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
	}

}
