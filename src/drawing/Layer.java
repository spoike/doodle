package drawing;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Layer {

	private BufferedImage image;

	public Layer(BufferedImage image) {
		this.image = image;
	}
		
	public void draw(Graphics2D g2d, Rectangle r)
	{
		g2d.drawImage(image, 
				r.x, r.y, 
				r.width + r.x, r.height + r.y, 
				r.x, r.y,
				r.width + r.x, r.height + r.y,
				null);
	}
	
	public Graphics2D getGraphics() {
		return image.createGraphics();
	}
}
