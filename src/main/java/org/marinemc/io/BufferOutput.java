package org.marinemc.io;

public class BufferOutput extends AbstractOutput implements Byteable {

	byte[] data;
	
	int size;
	
	public BufferOutput() {
		this(64);
	}
	
	public BufferOutput(int preSize) {
		this.data = new byte[preSize];
		size = 0;
	}
	
	private void ensure(int size) {
		if(data.length-this.size >= size)
			return;
		
		byte[] r = new byte[this.size+size];
		
		System.arraycopy(data, 0, r, 0, size);
		
		data = r;
	}
	
	@Override
	public void writeByte(byte b) {
		ensure(4);
		data[++size] = b;
	}

	@Override
	public void write(byte... bytes) {
		ensure(bytes.length + 2);
		System.arraycopy(bytes, 0, data, size, bytes.length);
	}
	
	public void writeStart(byte... bytes) {
		byte[] r = new byte[bytes.length + size];
		
		System.arraycopy(bytes, 0, r, 0, bytes.length);
		System.arraycopy(data, 0, r, bytes.length, size);
		
		data = r;
	}

	@Override
	public byte[] getBytes() {
		byte[] r = new byte[size];
		System.arraycopy(data, 0, r, 0, size);
		return r;
	}

}
