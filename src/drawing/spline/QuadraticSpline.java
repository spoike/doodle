package drawing.spline;

import java.awt.Point;

import net.DoodleEvent;

import drawing.DrawingArea;

/**
 * <p>Implements the Quadratic Spline path behavior. It will calculate 
 * and draw the interpolated points based on quadratic splines 
 * (paths in these splines have three defined points).</p>
 * 
 * <p>Quadratic splines are calculated like this: <br />
 * B(t) = (1-t)²*P0 + 2t*(1-t)*P1 + t²*P2<br />
 * where t is a real number in [0,1]</p>
 * 
 */
public class QuadraticSpline extends SplineBehavior {

	public QuadraticSpline(DrawingArea drawingArea) {
		super(drawingArea);
	}

	private Point p0;

	private Point p1;
	
	private double pressure0 = 0.0;
	
	private double pressure1 = 0.0;
	
	@Override
	public void draw(DoodleEvent next) {
		Point nextPoint = next.getPoint();
		double nextPressure = next.getPressure();
		if (p0 != null && p1 != null) {
			int step = (int) (1.5*(p0.distance(p1) + p1.distance(nextPoint)));
			for (int i = 0; i < step; i++)
			{
				double t = i/(double)step; 
				DoodleEvent d = new DoodleEvent(
						quadBezier(t, p0, p1, nextPoint), 
						false, 
						calcPressure(t, pressure0, pressure1, nextPressure)
						);
				getDrawingArea().draw(d);
			}
			getDrawingArea().flush();
			p1 = null;
		}
		p0 = p1;
		pressure0 = pressure1;
		p1 = nextPoint;
		pressure1 = nextPressure;
	}

	private Point quadBezier(double t, Point p0, Point p1, Point p2) {
		double u = (1-t);
		int x = (int) (u*u*p0.getX() + 2*t*u*p1.getX() + t*t*p2.getX());
		int y = (int) (u*u*p0.getY() + 2*t*u*p1.getY() + t*t*p2.getY());
		return new Point(x, y);
	}
	
	private double calcPressure(double t, double p0, double p1, double p2)
	{
		double u = (1-t);
		return (u*u*p0 + 2*t*u*p1 + t*t*p2);
	}
	
	@Override
	public void flush() {
		p0 = null;
		p1 = null;
	}

	@Override
	public void drawPoint(DoodleEvent point) {
		p0 = p1 = point.getPoint();
		pressure0 = pressure1 = point.getPressure();
		getDrawingArea().draw(point);
	}

}
