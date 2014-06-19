/**
 * 
 */
package ca.sevenless.pixelcrops.display;

import java.awt.Graphics2D;

import ca.sevenless.pixelcrops.display.util.GraphicsHolder.GraphicNotFoundException;
import ca.sevenless.pixelcrops.util.BoxCoord;
import ca.sevenless.pixelcrops.util.Coord;

/**
 * @author Sevenless
 *
 */
public class DisplayWorldTiles {
	
	DisplayTile[][] tileSet;

	private DisplayTile[][] makeDisplayTiles(int x, int y){
		
		DisplayTile[][] displayTileSet = new DisplayTile[x][y];
		
		for (int j = 0; j < displayTileSet.length; j++)
			for (int i = 0; i < displayTileSet[0].length; i++){
				//TODO
			}
		
		return displayTileSet;
	}

	void updateTiles(String[][] tileImageNames){
		//TODO
	}
	
	private void drawTileSet(BoxCoord drawLocation, Graphics2D screenBuffer2d) throws GraphicNotFoundException{
		
		Coord coordTL = drawLocation.getTL();
		Coord coordBR = drawLocation.getBR();
		
		int tileWidth = (coordBR.getX() - coordTL.getX() ) / tileSet.length;
		int tileHeight = (coordBR.getY() - coordTL.getY()) / tileSet[0].length;
		
		for (int j = 0; j < tileSet.length; j++)
			for (int i = 0; i < tileSet[0].length; i++){
				Coord topLeft =  new Coord(coordTL.getX()+j*tileWidth, coordTL.getY()+i*tileHeight);
				Coord bottomRight = new Coord(topLeft).transform(tileWidth,tileHeight);
				tileSet[j][i].drawTile(topLeft, bottomRight, screenBuffer2d);
			}
		
	}
	
}
