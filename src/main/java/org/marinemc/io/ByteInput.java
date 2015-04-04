package org.marinemc.io;

public interface ByteInput {
	
	/**
	 * Reads a boolean
	 * @return The boolean that have been read.
	 */
	public boolean readBoolean();
	
	/**
	 * Reads a byte 
	 * @return The byte that have been read.
	 */
	public byte readByte();
	
	/**
	 * Reads a short
	 * @return The short that have been read.
	 */
	public short readShort();
	
	/**
	 * Reads a integer
	 * @return The integer that have been read.
	 */
	public int readInt();
	
	/**
	 * Read a float
	 * @return The float that have been read.
	 */
	public float readFloat();
	
	/**
	 * Reads a long
	 * @return The long that have been read.
	 */
	public long readLong();
	
	/**
	 * Read a double
	 * @return The double that have been read.
	 */
	public double readDouble();
	
	/**
	 * Reads a varying integer
	 * @return The integer that have been read.
	 */
	public int readVarInt();
	
	/**
	 * Reads 'amount' of bytes in to an array
	 * @param amount The amount of bytes to read
	 * @return The read bytes
	 */
	public byte[] read(int amount);
	
}
