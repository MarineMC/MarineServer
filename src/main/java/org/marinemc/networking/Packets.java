package org.marinemc.networking;

import java.io.DataInput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

final class PacketTransformer {
	
	protected static Packet.Serverbound getPacket(DataInput input) {
		return null;
	}
}

public final class Packets {
	private static HashMap<Integer, List<PacketInterceptor<Packet.Serverbound>>> register;
	
	public static void registerHandler(int key, PacketInterceptor<Packet.Serverbound> handler) {
		register.putIfAbsent(key, new ArrayList<PacketInterceptor<Packet.Serverbound>>(3));
		register.get(key).add(handler);
	}
	
	public static void exe(int packetID, final Packet.Serverbound packet) {
		if(!register.containsKey(packetID))
			return;
		for(PacketInterceptor<Packet.Serverbound> interceptor : register.get(packetID))
			interceptor.intercept(packet);
	}
}