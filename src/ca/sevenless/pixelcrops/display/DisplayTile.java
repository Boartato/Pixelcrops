/**
 * 
 */
package ca.sevenless.pixelcrops.display;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import ca.sevenless.pixelcrops.util.Coord;
import ca.sevenless.pixelcrops.world.Tile;

/**
 * @author Sevenless
 *
 */
public class DisplayTile {

	private BufferedImage tileImage;
	
	public DisplayTile(Tile parentTile){
		
	}
	
	/**
	 * Draws a tile between the two coordinates given and autosizes the image onto the provided Graphics2D object
	 * 
	 * @param tileSet2 Tile being draw
	 * @param topLeft Coordinates of the top left corner of the drawn tile
	 * @param bottomRight Coordinates of the bottom right corner of the drawn tile
	 * @param screenBuffer2d Graphics2D object the tiles are being drawn to
	 */
	public void drawTile(Coord topLeft, Coord bottomRight, Graphics2D screenBuffer2d){
				
		screenBuffer2d.drawImage(tileImage, 
        		topLeft.getX(),topLeft.getY(),
        		bottomRight.getX()-topLeft.getX(),bottomRight.getY() - topLeft.getY(), null);
	}

}
