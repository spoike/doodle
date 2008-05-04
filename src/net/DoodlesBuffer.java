package net;

import java.util.ArrayList;

public class DoodlesBuffer {
	ArrayList<DoodleEvent> doodles;
	
	public DoodlesBuffer() {
		doodles = new ArrayList<DoodleEvent>();
	}

	public synchronized DoodleEvent get(int queueNumber) {
		while ( doodles.size()-1 < queueNumber ) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		return doodles.get(queueNumber);
	}
	
	public synchronized void put(DoodleEvent e) {
		doodles.add(e);
		notifyAll();
	}

}
