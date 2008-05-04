package drawing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class LayerManager {

	private ArrayList<Layer> layers;
	
	private int width;
	
	private int height;
	
	public LayerManager(int width, int height, int noOfLayers) {
		this.width = width;
		this.height = height;
		layers = new ArrayList<Layer>(noOfLayers);
		layers.add(new Layer(createBackgroundImage()));
		layers.add(new Layer(createNewImage()));
	}
	
	private BufferedImage createNewImage() {
		return new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	}
	
	private BufferedImage createBackgroundImage() {
		BufferedImage backgroundImg = createNewImage();
		Graphics2D g2d = backgroundImg.createGraphics();
		g2d.setColor(new Color(1f, 1f, 1f, 1f));		
		g2d.fillRect(0, 0, width, height);
		return backgroundImg;
	}
	
	public Layer getLayer(int index) {
		return layers.get(index);
	}
	
	public void drawComposite(Graphics2D g2d, Rectangle dirty)
	{
		for(Layer l : layers) {
			l.draw(g2d, dirty);
		}
	}
}
