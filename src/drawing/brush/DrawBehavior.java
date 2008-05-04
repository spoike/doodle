package drawing.brush;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;


public abstract class DrawBehavior {

	private Rectangle dirtyRectangle;
	
	protected int minBrushSize;
	
	protected int maxBrushSize;
		
	public DrawBehavior(int minBrushSize, int maxBrushSize) {
		this.minBrushSize = minBrushSize;
		this.maxBrushSize = maxBrushSize;
	}
	
	public abstract void performDraw(Point point, Graphics2D g2d, double pressure);

	public Rectangle getBrushClip() {
		return dirtyRectangle;
	}
	
	protected void setBrushClip(Rectangle dirtyRectangle) {
		this.dirtyRectangle = dirtyRectangle;
	}
	
}
