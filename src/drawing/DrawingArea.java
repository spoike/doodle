package drawing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


import jpen.PenManager;
import net.DoodleEvent;
import net.DoodleObserver;
import net.DoodlesBuffer;
import drawing.brush.BrushDraw;
import drawing.brush.DrawBehavior;
import drawing.spline.QuadraticSpline;
import drawing.spline.SplineBehavior;

public class DrawingArea extends JPanel {

	/**
	 * A generated serial number.
	 */
	private static final long serialVersionUID = -6909518366111719557L;
	
	private LayerManager layerManager;
	
	private DrawBehavior drawBehavior;

	private DrawingMouse drawingMouse;
	
	private DoodlesBuffer buffer;
	
	private SplineBehavior spline;
	
	private int width = 400;
	
	private int height = 400;
	
	public DrawingArea(DrawBehavior drawBehavior, int width, int height, int layerAmount, DoodleObserver observer) {
		setPreferredSize(new Dimension(width, height));
		this.width = width;
		this.height = height;
		setBorder(BorderFactory.createLineBorder(Color.blue, 2));
		setBackground(Color.white);

		this.drawBehavior = drawBehavior;
		drawingMouse = new DrawingMouse(this, new PenManager(this), observer);
		spline = new QuadraticSpline(this);

		addMouseListener(drawingMouse);
		addMouseMotionListener(drawingMouse);
		layerManager = new LayerManager(width, height, layerAmount);
		clippingRect = new Rectangle();
		resetClippingRect();
	}

	public DrawingArea(int width, int height, int layerAmount, DoodleObserver observer) {
		this(new BrushDraw(1, 10), width, height, layerAmount, observer);
	}
	
	public void performEvent(DoodleEvent e) {		
		if(e.isNewPath()) {
			spline.flush();
			spline.drawPoint(e);
		}
		else {
			spline.draw(e);
		}
		
		if (buffer != null) {
			buffer.put(e);
		}
	}

	/**
	 * Draws the doodle on the drawing area. Note that the doodle won't
	 * not be rendered until the drawing area is flushed.
	 * 
	 * @param e The doodle data specifying what to draw
	 */
	public void draw(DoodleEvent e) {
		drawBehavior.performDraw(e.getPoint(), 
				layerManager.getLayer(1).getGraphics(), 
				e.getPressure());
		checkClippingRectangle(drawBehavior.getBrushClip());
	}

	private void checkClippingRectangle(Rectangle brushClip) {
		if (brushClip.x < clippingRect.x) {
			clippingRect.x = brushClip.x;
		}
		if (brushClip.y < clippingRect.y) {
			clippingRect.y = brushClip.y;
		}
		int condition = calcCondition(brushClip.width, brushClip.x);
		if (clippingRect.width < condition) {
			clippingRect.width = condition;
		}
		condition = calcCondition(brushClip.height, brushClip.y);
		if (clippingRect.height < condition) {
			clippingRect.height = condition;
		}
	}
	
	private int calcCondition(int wh, int xy)
	{
		if(xy > -1) {
			return wh + xy;
		}
		else {
			// TODO: This is only a temporary fix to the edge bug
			// For larger strokes this will fail to produce the dirty
			// rectangle needed.
			return wh + Math.abs(xy) * 2;	
		}
	}
	
	private Rectangle clippingRect;
	
	private void resetClippingRect()
	{
		clippingRect.x = width;
		clippingRect.y = height;
		clippingRect.setSize(0, 0);			
	}
	
	/**
	 * Renders what has been drawn on the drawing area.
	 */
	public void flush() {
		repaint(clippingRect);
		resetClippingRect();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		layerManager.drawComposite((Graphics2D) g, g.getClipBounds());
	}	
	
	public void setDoodlesBuffer(DoodlesBuffer buffer) {
		this.buffer = buffer;
	}

}
