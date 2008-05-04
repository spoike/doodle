package net;

import java.awt.Point;

public class DoodleEvent {
	
	private Point point;
	
	private boolean newPath;
	
	private double pressure;

	public DoodleEvent(Point point, boolean newPath, double pressure) {
		this.point = point;
		this.newPath = newPath;
		this.pressure = pressure;
	}
	
	public DoodleEvent(Point point, boolean newPath)
	{
		this(point, newPath, 1.0);
	}
	
	public DoodleEvent(Point point)
	{
		this(point, false, 1.0);
	}
	
	/**
	 * Builds a doodle event from a toString representation 
	 * of another.
	 * 
	 * @param doodleString The toString representation of a 
	 * {@link DoodleEvent}.
	 */
	public DoodleEvent(String doodleString)
	{
		// TODO: Format check
		String[] doodleData = doodleString.split(";");
		point = new Point(Integer.parseInt(doodleData[0]), Integer.parseInt(doodleData[1]));
		newPath = doodleData[2].equals("1");
		pressure = Double.parseDouble(doodleData[3]);
	}
	
	/**
	 * @return The two dimensional point for the doodle event
	 */
	public Point getPoint()
	{
		return point;
	}
	
	/**
	 * @return True if the event the start of a new path
	 */
	public boolean isNewPath()
	{
		return newPath;
	}
	
	/**
	 * @return The pressure of the doodle at the given point. 
	 * Is a decimal in the interval 0..1
	 */
	public double getPressure()
	{
		return pressure;
	}
	
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer(10);
		str.append(point.x);
		str.append(';');
		str.append(point.y);
		str.append(';');
		if(newPath == true) {
			str.append('1');
		}
		else
		{
			str.append('0');
		}
		str.append(';');
		str.append(pressure);
		return str.toString();
	}
	
}
