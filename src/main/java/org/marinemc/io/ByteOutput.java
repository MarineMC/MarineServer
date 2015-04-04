package org.marinemc.io;

public interface ByteOutput {
	public void writeByte(byte b);
	public void writeBoolean(boolean b);
	public void writeShort(short s);
	public void writeInt(int i);
	public void writeLong(long l);
	public void writeVarInt(int i);
	public void write(byte... bytes);
	public void writeString(String s);
}
