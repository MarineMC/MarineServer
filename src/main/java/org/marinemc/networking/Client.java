package org.marinemc.networking;

import java.io.IOException;
import java.net.Socket;

public class Client {
	private static long nextID = Long.MIN_VALUE;
	
	private final long clientID;
	
	private final Socket connection;
	
	private boolean closed;
	
	public Client(final Socket connection) {
		clientID = nextID++;
		this.connection = connection;
		closed = false;
	}
	
	public long getID() {
		return clientID;
	}
	
	private int getRemaining() {
		try {
			return connection.getInputStream().available();
		} catch (IOException e) {
			closed = true;
			return 0;
		}
	}
	
	public boolean isClosed() {
		return closed;
	}
	
	public Packet.Serverbound[] process() {
		if(!connection.isClosed() || !connection.isConnected() || !connection.isInputShutdown() || !connection.isOutputShutdown())
			closed = true;
		
		if(closed) return null;
		
		int remaining = getRemaining();
		
		
		
		return null;
	}
	
}
