package net.server;

import java.io.IOException;
import java.net.Socket;


import net.DoodleObserver;
import net.DoodlesBuffer;

public class Client {

	private DoodlesBuffer doodlesBuffer;
	
	private ClientSender clientSender;
	
	private ClientReceiver clientReceiver;
	
	private int queueNumber;
	
	public Client(Socket clientSocket, DoodlesBuffer doodlesBuffer, int maxClients, DoodleObserver observer) throws IOException {
		this.doodlesBuffer = doodlesBuffer;
		queueNumber = 0;
		clientSender = new ClientSender(clientSocket, maxClients, this);
		clientReceiver = new ClientReceiver(clientSocket, observer);
		System.out.println("A client has started!");
		clientSender.start();
		clientReceiver.start();
	}

	public String getMessage() {
		return doodlesBuffer.get(queueNumber++).toString();
	}
	
	

}
