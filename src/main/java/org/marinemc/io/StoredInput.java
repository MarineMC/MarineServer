package org.marinemc.io;

public class StoredInput extends AbstractInput {
	int pos;
	
	byte[] buffer;
	
	public void back() {
		--pos;
	}
	
	public int bytesRemaining() {
		return buffer.length - pos;
	}
	
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
