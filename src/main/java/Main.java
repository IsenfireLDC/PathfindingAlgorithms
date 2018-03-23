package main.java;

import main.java.prims.Path;
import main.java.prims.PrimsAlgoV1;
import main.java.prims.PrimsAlgoV2;

public class Main {
	
	public static void main(String[] args) {
		PrimsAlgoV1.main(args);
		PrimsAlgoV2 v2 = new PrimsAlgoV2(Path.makeFullMatrix(Path.points.length), Path.points);
		v2.makeHeap();
		v2.heap.printAllMin();
		v2.heap.printMaps();
	}
}
