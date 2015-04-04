package org.marinemc.io;

import java.nio.charset.StandardCharsets;

public abstract class AbstractOutput implements ByteOutput {

	@Override
	public void writeBoolean(final boolean v) {
		if (v)
			writeByte((byte) 0x01);
		else
			writeByte((byte) 0x00);
	}

	@Override
	public void writeShort(final short v) {
		writeByte((byte) (0xff & v >> 8));
		writeByte((byte) (0xff & v));
	}

	@Override
	public void writeInt(final int v) {
		writeByte((byte) (0xff & v >> 24));
		writeByte((byte) (0xff & v >> 16));
		writeByte((byte) (0xff & v >> 8));
		writeByte((byte) (0xff & v));
	}

	@Override
	public void writeLong(final long v) {
		writeByte((byte) (0xff & v >> 56));
		writeByte((byte) (0xff & v >> 48));
		writeByte((byte) (0xff & v >> 40));
		writeByte((byte) (0xff & v >> 32));
		writeByte((byte) (0xff & v >> 24));
		writeByte((byte) (0xff & v >> 16));
		writeByte((byte) (0xff & v >> 8));
		writeByte((byte) (0xff & v));
	}

	@Override
	public void writeVarInt(int v) {
		byte part;
		while (true) {
			part = (byte) (v & 0x7F);
			v >>>= 7;
			if (v != 0)
				part |= 0x80;
			writeByte(part);
			if (v == 0)
				break;
		}
	}
	
	@Override
	public void writeString(final String s) {
		write(s.getBytes(StandardCharsets.UTF_8));
	}	
}
