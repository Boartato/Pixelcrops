/**
 * 
 */
package ca.sevenless.pixelcrops.display;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import ca.sevenless.pixelcrops.display.util.GraphicsHolder;
import ca.sevenless.pixelcrops.display.util.GraphicsHolder.GraphicNotFoundException;
import ca.sevenless.pixelcrops.util.Coord;
import ca.sevenless.pixelcrops.world.Tile;

/**
 * @author Sevenless
 *
 */
public class DisplayTile {

	private Tile parentTile;
	private String tileImageName;
	private BufferedImage tileImage;
	private GraphicsHolder graphicsHolder;
	
	public DisplayTile(Tile parentTile, GraphicsHolder graphicsHolder){
		this.parentTile = parentTile;
		this.graphicsHolder = graphicsHolder;
	}
	
	/**
	 * Draws a tile between the two coordinates given and autosizes the image onto the provided Graphics2D object
	 * 
	 * @param tileSet2 Tile being draw
	 * @param topLeft Coordinates of the top left corner of the drawn tile
	 * @param bottomRight Coordinates of the bottom right corner of the drawn tile
	 * @param screenBuffer2d Graphics2D object the tiles are being drawn to
	 * @throws GraphicNotFoundException 
	 */
	public void drawTile(Coord topLeft, Coord bottomRight, Graphics2D screenBuffer2d) throws GraphicNotFoundException{
		
		if (parentTile.getTileName() != tileImageName){
			tileImageName = parentTile.getTileName();
			tileImage = graphicsHolder.find(tileImageName);
		}
		
		screenBuffer2d.drawImage(tileImage, 
        		topLeft.getX(),topLeft.getY(),
        		bottomRight.getX()-topLeft.getX(),bottomRight.getY() - topLeft.getY(), null);
	}

}
