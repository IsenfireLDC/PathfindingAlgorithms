package main.java.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Heap {
	
	ArrayList<Double> distances;
	Map<Double, Integer> distMap;
	Map<Double, ArrayList<ConnectablePoint[]>> map;
	
	public Heap() {
		this.distances = new ArrayList<Double>();
		this.distMap = new HashMap<Double, Integer>();
		this.map = new HashMap<Double, ArrayList<ConnectablePoint[]>>();
	};
	
	public double min() {
		boolean flag = false;
		//int index = 0;
		double prevDist = 0;
		for (int i = 0; i < distances.size(); i++) {
			if (distances.get(i) < prevDist || !flag) {
				prevDist = distances.get(i);
				//index = i;
				flag = true;
			}
		}
		return prevDist;
	};
	
	public double max() {
		boolean flag = false;
		//int index = 0;
		double prevDist = 0;
		for (int i = 0; i < distances.size(); i++) {
			if (distances.get(i) > prevDist || !flag) {
				prevDist = distances.get(i);
				//index = i;
				flag = true;
			}
		}
		return prevDist;
	};
	
	public void add(double dist, ConnectablePoint[] connection) {
		//System.out.println("Adding connection of distance " + dist + " between points " + connection[0] + " and " + connection[1]);
		if (distMap.containsKey(dist)) {
			//System.out.println("Adding another instance of " + dist + " to maps");
			int current = distMap.get(dist).intValue();
			distMap.replace(dist, current + 1);
			map.get(dist).add(connection);
		} else {
			//System.out.println("Adding new value " + dist + " to maps");
			distances.add(dist);
			distMap.put(dist, 1);
			ArrayList<ConnectablePoint[]> current = new ArrayList<ConnectablePoint[]>();
			current.add(connection);
			map.put(dist, current);
		}
	};
	
	public void remove(double dist, ConnectablePoint[] connection) {
		//System.out.println("Removing connection of distance " + dist + " between points " + connection[0] + " and " + connection[1]);
		if (distMap.containsKey(dist)) {
			int current = distMap.get(dist).intValue();
			if (current <= 1) {
				//System.out.println("Removing value " + dist + " from maps");
				distMap.remove(dist);
				map.remove(dist);
				distances.remove(dist);
			} else {
				//System.out.println("Removing one instance of " + dist + " from maps");
				map.get(dist).remove(connection);
				distMap.replace(dist, current - 1);
			}
		}
	};
	
	public ConnectablePoint[] pop(double dist) {
		ConnectablePoint[] connection = map.get(dist).get(map.get(dist).size() - 1);
		remove(dist, connection);
		return connection;
	};
	
	public void search(double dist) {
		
	};
	
	public void printAllMin() {
		int total = 0;
		for (int i = 0; i < distances.size(); i++) {
			double min = min();
			while (distMap.containsKey(min)) {
				ConnectablePoint[] current = map.get(min).get(map.get(min).size() - 1);
				System.out.println(distMap.get(min).intValue());
				System.out.println(current);
				System.out.println(min);
				total++;
				remove(min, current);
			}
		}
		if (distMap.isEmpty()) {
			System.out.println("Maps are empty");
		}
		System.out.println(total);
	};
	
	public void printMaps() {
		System.out.println();
		for (double current : distances) {
			System.out.println(current);
			System.out.println(distMap.get(current));
			for (ConnectablePoint[] point : map.get(current)) {
				System.out.println(point[0] + " " + point[1]);
			}
		}
	};
	
	public boolean isEmpty() {
		return map.isEmpty() && distMap.isEmpty() && distances.isEmpty();
	};
	
	public void clear() {
		map.clear();
		distMap.clear();
		distances.clear();
	};

}
