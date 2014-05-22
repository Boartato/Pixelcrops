/**
 * 
 */
package ca.sevenless.pixelcrops.display;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import ca.sevenless.pixelcrops.util.Coord;
import ca.sevenless.pixelcrops.util.ImageLoader;

/**
 * @author Sevenless
 *
 */
public class Sprite {

	BufferedImage graphic;
	
	//Dimensions of the graphic
	int width;
	int height;
	
	//Center of the bottom of the graphic relative to the upper left corner
	int baseX;
	int baseY;
	
	public Sprite(String imageName) throws SpriteImageMissingException{
		
		try {
			graphic = ImageLoader.createImageIO(imageName);
		} catch (IOException e) {
			throw new SpriteImageMissingException("Sprite failed to load");
		}
		
		width = graphic.getWidth();
		height = graphic.getHeight();
		
		baseX = width/2; 
		baseY = height;
		
	}
	/**
	 * Draws the sprite at the given location, matching up the top left corner of the image
	 * with the coords given
	 * 
	 * @param screenBuffer2D Buffer image the sprite is being drawn to
	 * @param coord Coordinate object used to locate this draw call
	 */
	public void drawTL(Graphics2D screenBuffer2D, Coord coord){
        screenBuffer2D.drawImage(graphic, 
        		coord.getX(),coord.getY(),
        		width,height, null);
	}
	
	/**
	 * Draws the sprite at the given location, matching the center bottom of the image
	 * to the coords given
	 * @param screenBuffer2D Buffer image the sprite is being drawn to
	 * @param coord Coordinate object used to locate this draw call
	 */
	public void drawFB(Graphics2D screenBuffer2D, Coord coord){
		screenBuffer2D.drawImage(graphic, 
				coord.getX()+baseX, coord.getY()+baseY,
				width,height, null);
	}
	
}