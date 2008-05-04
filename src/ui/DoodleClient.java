package ui;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import net.DoodleEvent;
import net.DoodleObserver;
import drawing.DrawingArea;
import drawing.DrawingPane;


public class DoodleClient extends Thread implements DoodleObserver {
	
	private BufferedReader reader;
	private BufferedWriter writer;

	public DoodleClient(String hostString, int port) throws UnknownHostException, IOException {
		Socket socket = new Socket(InetAddress.getByName(hostString), port);
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	}
	
	@Override
	public void run() {
		String message;
		DrawingArea drawingArea;
		try {
			// TODO: Client needs to fetch the dimensions and layer amount from the server
			int clientAmount = Integer.parseInt(reader.readLine());
			drawingArea = new DrawingPane("DoodleClient", 400, 400, clientAmount, this).getDrawingArea();
			while (true) {
				message = reader.readLine();
				drawingArea.performEvent(new DoodleEvent(message));
				yield();			
			}
		} catch (IOException e) {
		} catch (NullPointerException e) {
		}
	}

	public synchronized void sendDoodle(DoodleEvent ev) {
		try {
			writer.write(ev.toString());
			writer.newLine();
			writer.flush();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
}
