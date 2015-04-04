package org.marinemc.networking;


public class PacketThread extends Thread {
	
	final PacketFactory factory;
	
	public PacketThread(PacketFactory factory) {
		super("PacketManager");
		this.factory = factory;
	}
	
	
	
	public void run() {
		long startTime;
		while(true) {
			startTime = System.currentTimeMillis();
			
			for(Packet.Serverbound packet : factory)
				Packets.exe(packet.getInboundID(), packet);
			
			
			try {
				NetworkWorker.sleep(NetworkWorker.sleepTime - (System.currentTimeMillis()-startTime));
			} catch (InterruptedException e) {}
		}
	}
}
