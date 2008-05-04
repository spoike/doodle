package net.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import net.DoodleEvent;
import net.DoodleObserver;

public class ClientReceiver extends Thread {

	private DoodleObserver observer;
	private BufferedReader reader;
	
	public ClientReceiver(Socket clientSocket, DoodleObserver observer) throws IOException {
		reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		this.observer = observer;
	}

	@Override
	public void run() {
		try {
			while(true)
			{
				observer.sendDoodle(new DoodleEvent(reader.readLine()));
				yield();
			}
		} catch (IOException e) {
		} catch (NullPointerException e) {
		}
	}
	
}
