package org.marinemc.networking;

import java.io.IOException;
import java.net.ServerSocket;

public final class NetworkManager {
	public ServerSocket serverSocket;
	
	public NetworkManager(int port) throws IOException {
		serverSocket = new ServerSocket(port);
	}
	
}
