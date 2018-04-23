package main.java.utility;

public class Timer {
	
	long start;
	long end;
	
	public Timer() {};
	
	public void start() {
		start = System.nanoTime();
	};
	
	public void end() {
		end = System.nanoTime();
	}
	
	public long nanos() {
		return (end - start);
	};
	
	public double seconds() {
		return (end - start) / 1000000000D;
	};

}
