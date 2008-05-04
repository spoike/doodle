package net.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientSender extends Thread {

	private Client client;
	
	private BufferedWriter sender;

	public ClientSender(Socket clientSocket, int maxClients, Client client) throws IOException {
		this.client = client;
		this.sender = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
		sender.write(Integer.toString(maxClients));
		sender.newLine();
		sender.flush();
		yield();
	}

	@Override
	public void run() {
		String message;
		try {
			while ( true ) {
				message = client.getMessage();
				sender.write(message);
				sender.newLine();
				sender.flush();
				yield();
			}
		} catch (IOException e) {
			// client.disconnect();
		} catch (NullPointerException e) {
			// client.disconnect();
		}
	}

}
