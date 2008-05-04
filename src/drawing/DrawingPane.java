package drawing;
import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;

import net.DoodleObserver;


public class DrawingPane extends JFrame {

	/**
	 * A generated number UID.
	 */
	private static final long serialVersionUID = 3240751569348403506L;
	
	private DrawingArea drawingArea;
	
	public DrawingPane(String string, int height, int width, int clientAmount, DoodleObserver observer) {
		super(string);
		
		Container content = getContentPane();

		drawingArea = new DrawingArea(width, height, clientAmount, observer);
		content.add(drawingArea, BorderLayout.CENTER);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		pack();
		setVisible(true);
	}

	public DrawingArea getDrawingArea() {
		return drawingArea;
	}

}
