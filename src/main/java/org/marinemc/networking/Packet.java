package org.marinemc.networking;

import org.marinemc.io.ByteInput;
import org.marinemc.io.ByteOutput;
public abstract interface Packet {
	public static int getID() {
		return 0;
	}
	
	public interface Clientbound extends Packet{
		/**
		 * Writes the packet to an ByteOutput
		 * @param out The output
		 */
		public void write(ByteOutput out);
		
		public int getOutboundID();
	}

	public interface Serverbound extends Packet {
		/**
		 * Reads from an ByteInput
		 * @param in The input
		 */
		public void read(ByteInput in);
		
		public int getInboundID();
	}
}