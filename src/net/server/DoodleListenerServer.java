package net.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


import net.DoodleObserver;
import net.DoodlesBuffer;

public class DoodleListenerServer extends Thread {

	private ServerSocket serverSocket;

	private DoodlesBuffer doodlesBuffer;
	
	private DoodleObserver doodleObserver;

	private int maxClients;

	public DoodleListenerServer(int port, DoodlesBuffer doodlesBuffer, int maxClients, DoodleObserver doodleObserver)
			throws IOException {
		serverSocket = new ServerSocket(port);
		this.doodlesBuffer = doodlesBuffer;
		this.doodleObserver = doodleObserver;
		this.maxClients = maxClients;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Socket clientSocket = serverSocket.accept();
				new Client(clientSocket, doodlesBuffer, maxClients, doodleObserver);
				printConnected(clientSocket.getInetAddress());
				yield();
			} catch (IOException e) {
				System.err.println("Could not connect client.");
			}
		}

	}

	private void printConnected(InetAddress clientAddy) {
		System.out.printf("Client has connected from: %s", clientAddy.toString());
		System.out.println();
	}

}
