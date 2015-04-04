package org.marinemc.networking;

public abstract class PacketInterceptor<T extends Packet.Serverbound> {
	final int packetID;
	
	public PacketInterceptor(int id) {
		packetID = id;
	}
	
	public abstract void intercept(T packet);
	
	public int getInterceptingPacketID() {
		return packetID;
	}
}
