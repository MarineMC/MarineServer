package org.marinemc.io;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.Arrays;

public class StreamedInput implements ByteInput {
	final WeakReference<InputStream> stream;
	
	//TODO: Some little buffer
	
	public StreamedInput(final InputStream stream) {
		this.stream = new WeakReference<>(stream);
	}
	
	@Override
	public boolean readBoolean() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public byte readByte() {
		try {
			return (byte) stream.get().read();
		} catch (IOException e) {
			return 0;
		}
	}

	@Override
	public float readFloat() {
		return Float.intBitsToFloat(readInt());
	}

	@Override
	public double readDouble() {
		return Double.longBitsToDouble(readLong());
	}
	
	@Override
	public short readShort() {
		byte[] x = readNotNull(2);
		return (short) (x[0] << 8 | x[1] & 0xff); 
	}

	@Override
	public int readInt() {
		byte[] x = readNotNull(2);
		return 	(x[0] & 0xff) << 24 | (x[1] & 0xff) << 16
			|	(x[2] & 0xff) << 8 | x[3] & 0xff;
	}

	@Override
	public long readLong() {
		byte[] x = readNotNull(2);
		return 	  (long)(x[0] & 0xff) << 56
				| (long)(x[1] & 0xff) << 48
				| (long)(x[2] & 0xff) << 40
				| (long)(x[3] & 0xff) << 32
				| (long)(x[4] & 0xff) << 24
				| (long)(x[5] & 0xff) << 16
				| (long)(x[6] & 0xff) << 8
				|		 x[7] & 0xff;
	}

	//TODO: Use some kind of buffer
	@Override
	public int readVarInt() {
		int out = 0;
		int bytes = 0;
		byte in;
		while (true) {
			in = readByte();
			out |= (in & 0x7F) << bytes++ * 7;
			if ((in & 0x80) != 0x80)
				break;
		}
		return out;
	}

	@Override
	public byte[] read(int amount) {
		if(stream.get() == null)
			return null;
		
		byte[] bytes = new byte[amount];
		
		try {
			stream.get().read(bytes);
		} catch (IOException e) {
			return null;
		}
		
		return bytes;
	}
	
	private byte[] readNotNull(int amount) {
		byte[] bytes = read(amount);
		
		if(bytes == null) {
			bytes = new byte[amount];
			Arrays.fill(bytes, (byte)0);
			return bytes;
		}
		
		return bytes;
	}
	
}
