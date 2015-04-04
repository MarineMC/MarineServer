package org.marinemc.io;

public class BufferedInput extends AbstractInput {
	int pos;
	
	byte[] buffer;
	
	
	@Override
	public byte readByte() {
		return buffer[pos++];
	}

	@Override
	public byte[] read(int amount) {
		byte[] data = new byte[amount];
		
		System.arraycopy(buffer, pos, data, 0, amount);
		
		pos += amount;
		
		return data;
	}
}
