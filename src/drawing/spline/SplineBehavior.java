package drawing.spline;

import net.DoodleEvent;
import drawing.DrawingArea;

public abstract class SplineBehavior {
	
	private DrawingArea drawingArea;

	public SplineBehavior(DrawingArea drawingArea) {
		this.drawingArea = drawingArea;
	}

	public DrawingArea getDrawingArea() {
		return drawingArea;
	}

	public void setDrawingArea(DrawingArea drawingArea) {
		this.drawingArea = drawingArea;
	}
	
	public abstract void draw(DoodleEvent next);
	
	public abstract void drawPoint(DoodleEvent point);
	
	public abstract void flush();
}
