package org.marinemc.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public final class NetworkManager {
	public ServerSocket serverSocket;
	
	private List<Client> connectedClients;
	
	//TODO: Multiple Workers
	
	public NetworkManager(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		connectedClients = new CopyOnWriteArrayList<>();
		
		new NetworkWorker(this).start();
	}
	
	public int clients() {
		return connectedClients.size();
	}

	public void checkForTermenation() {
		for(final Client c : connectedClients) {
			if(c.isClosed())
				connectedClients.remove(c);
		}
	}
	
	protected void workAll() {
		for(final Client c : connectedClients) {
			c.process();
		}
	}
	
}
