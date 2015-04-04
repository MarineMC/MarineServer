package org.marinemc.networking;

import java.util.Iterator;

import org.marinemc.networking.Packet.Serverbound;

public class PacketFactory implements Iterable<Serverbound>, Iterator<Serverbound>{
	
	public static final int STANDARD_BUFFER_SIZE = 64;
	
	public static final int MINIMUM_SPACE = 16;
	
	private volatile int pos;
	private volatile int size;
	
	private volatile Serverbound[] buffer;
	
	private volatile boolean lock;
	
	
	public PacketFactory() {
		buffer = new Serverbound[STANDARD_BUFFER_SIZE];
		pos = 0;
		size = 0;
		lock = false;
	}
	
	public int extraBufferSpace() {
		return buffer.length-size;
	}
	
	private void resize(int size) {
		Serverbound[] result = new Serverbound[Math.max(size, STANDARD_BUFFER_SIZE)];
		
		System.arraycopy(buffer, pos, result, 0, this.size-pos);
		
		buffer = result;
	}
	
	public void trim() {
		if(lock) return;
		if(extraBufferSpace() > MINIMUM_SPACE && buffer.length == STANDARD_BUFFER_SIZE)
			return;
		lock = true;
		resize(size + MINIMUM_SPACE + 8);
		lock = false;
	}
	
	public void add(Serverbound packet) {
		// Wait for the lock
		while(lock) {try {Thread.sleep(0, 3);} catch (InterruptedException e) {}}
		
		if(extraBufferSpace() > MINIMUM_SPACE-8)
			trim();
		
		buffer[++size] = packet;
		
	}

	@Override
	public boolean hasNext() {
		return pos < size;
	}

	@Override
	public Serverbound next() {
		return buffer[pos++];
	}

	@Override
	public Iterator<Serverbound> iterator() {
		return this;
	}
	
	
}
