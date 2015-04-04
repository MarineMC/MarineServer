package org.marinemc.networking;

public class NetworkWorker extends Thread {
	private final NetworkManager manager;
	
	/**
	 * Time to sleep for each update.
	 * To get tick rate do 1000/sleepTime
	 */
	public static final int sleepTime = 1000 / 125; // Tickrate = 125
	
	public NetworkWorker(NetworkManager manager) {
		super("NetworkWorker");
		this.manager = manager;
	}
	
	
	public void run() {
		long startTime;
		while(true) {
			startTime = System.currentTimeMillis();
			
			//TODO: Do half the clients and another worker does the other half depending on amount of players
			manager.workAll();
			
			try {
				NetworkWorker.sleep(sleepTime - (System.currentTimeMillis()-startTime));
			} catch (InterruptedException e) {}
		}
	}
	
}
