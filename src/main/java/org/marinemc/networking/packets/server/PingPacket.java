package org.marinemc.networking.packets.server;

import org.marinemc.io.ByteInput;
import org.marinemc.io.ByteOutput;
import org.marinemc.networking.Packet.*;

public class PingPacket implements Serverbound, Clientbound{
	
	long time;

	@Override
	public void write(ByteOutput out) {
	}

	@Override
	public int getOutboundID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void read(ByteInput in) {
		time = in.readLong();
	}

	@Override
	public int getInboundID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
