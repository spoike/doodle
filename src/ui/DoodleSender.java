package ui;
import java.io.IOException;

import net.DoodleEvent;
import net.DoodleObserver;
import net.DoodlesBuffer;
import net.server.DoodleListenerServer;
import drawing.DrawingArea;
import drawing.DrawingPane;

public class DoodleSender implements DoodleObserver {

	DrawingArea drawingArea;
	
	public DoodleSender(int port, int width, int height, int clientAmount) throws IOException
	{
		drawingArea = new DrawingPane("DoodleSender", width, height, clientAmount, null).getDrawingArea();
		DoodlesBuffer buffer = new DoodlesBuffer();
		drawingArea.setDoodlesBuffer(buffer);

		DoodleListenerServer server = new DoodleListenerServer(port, buffer, clientAmount, this);
		server.start();
	}

	public void sendDoodle(DoodleEvent e) {
		drawingArea.performEvent(e);
	}

}
