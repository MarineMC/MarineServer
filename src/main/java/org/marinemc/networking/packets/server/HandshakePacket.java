package org.marinemc.networking.packets.server;

import org.marinemc.io.ByteInput;
import org.marinemc.networking.Packet;

public class HandshakePacket implements Packet.Serverbound {

	private int protocol;
	
	private String serverAdress;
	
	private int port;
	
	private int nextState;
	
	@Override
	public void read(ByteInput in) {
		protocol = in.readVarInt();
		serverAdress = in.readString();
		port = in.readUnsignedShort();
		nextState = in.readVarInt();
	}

	public int getProtocol() {
		return protocol;
	}

	public String getServerAdress() {
		return serverAdress;
	}

	public int getPort() {
		return port;
	}

	public int getNextState() {
		return nextState;
	}

	@Override
	public int getInboundID() {
		return 0;
	}

}
