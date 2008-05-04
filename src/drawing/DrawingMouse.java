package drawing;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


import jpen.PKind;
import jpen.PLevel;
import jpen.PenManager;

import net.DoodleEvent;
import net.DoodleObserver;

/**
 * Adapter for {@link MouseListener} and {@link MouseMotionListener} that uses a
 * {@link DrawingArea} to perform drawing.
 * 
 */
public class DrawingMouse implements MouseListener, MouseMotionListener {

	private DrawingArea area;
	
	private PenManager pm;
	
	private DoodleObserver observer;

	public DrawingMouse(DrawingArea area, PenManager pm, DoodleObserver observer) {
		this.area = area;
		this.pm = pm;
		this.observer = observer;
	}

	private double getPressure()
	{
		if( pm.pen.getKindType() != PKind.Type.CURSOR ) {
			return pm.pen.getLevelValue(PLevel.Type.PRESSURE);
		} else {
			return 1.0;
		}
	}
		
	public void mouseDragged(MouseEvent event) {
		sendEvent(event, false);
	}

	private synchronized void sendEvent(MouseEvent event, boolean pressed) {
		DoodleEvent e = new DoodleEvent(event.getPoint(), pressed, getPressure());
		if (observer != null) {
			observer.sendDoodle(e);
		} else {
			area.performEvent(e);
		}
	}

	public void mousePressed(MouseEvent event) {
		sendEvent(event, true);
	}

	public void mouseClicked(MouseEvent event) {
	}

	public void mouseEntered(MouseEvent event) {
	}

	public void mouseExited(MouseEvent event) {
	}

	public void mouseReleased(MouseEvent event) {
		sendEvent(event, false);
	}

	public void mouseMoved(MouseEvent event) {
	}
}