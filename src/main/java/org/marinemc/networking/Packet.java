package org.marinemc.networking;

import org.marinemc.io.ByteInput;
import org.marinemc.io.ByteOutput;
public abstract interface Packet {
	public int getID();
	
	public interface Clientbound extends Packet{
		/**
		 * Writes the packet to an ByteOutput
		 * @param out The output
		 */
		public void write(ByteOutput out);
	}

	public interface Serverbound extends Packet {
		/**
		 * Reads from an ByteInput
		 * @param in The input
		 */
		public void read(ByteInput in);
	}
	
	public interface Both extends Clientbound, Serverbound {}
}