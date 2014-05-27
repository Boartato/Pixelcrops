/**
 * 
 */
package ca.sevenless.pixelcrops.world;

import java.awt.image.BufferedImage;

/**
 * @author Sevenless
 *
 */
public class Tile {

	private BufferedImage tileImage;//TODO convert image code into display specific part of the project
	private String tileName;
	
	public Tile(BufferedImage tileImage, String tileName){
	
		this.tileImage = tileImage;
		this.tileName = tileName;
		
	}

	/**
	 * @return the tileImage
	 */
	public BufferedImage getTileImage() {
		return tileImage;
	}

	/**
	 * @return the tileName
	 */
	public String getTileName() {
		return tileName;
	}
	
	
	
	
}
