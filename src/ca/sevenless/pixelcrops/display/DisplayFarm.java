/**
 * 
 */
package ca.sevenless.pixelcrops.display;

import java.awt.Color;
import java.awt.Graphics2D;

import ca.sevenless.pixelcrops.util.Coord;
import ca.sevenless.pixelcrops.world.Tile;
import ca.sevenless.pixelcrops.world.farm.FarmInterface;
import ca.sevenless.pixelcrops.world.farm.Plant;

/**
 * @author Sevenless
 *
 */
public class DisplayFarm {
	
	private DisplayTile[][] tileSet;
	
	public DisplayFarm(Tile[][] tileSet){
		this.tileSet = makeDisplayTiles(tileSet);
	}
	
	private DisplayTile[][] makeDisplayTiles(Tile[][] tileSet){
		DisplayTile[][] displayTileSet = new DisplayTile[tileSet.length][tileSet[0].length];
		
		
		
		return displayTileSet;
	}

	void drawFarm(int x, int y, int sizeX, int sizeY, FarmInterface farm, Graphics2D screenBuffer2d){
		
		
		int fieldsX = farm.getX();
		int fieldsY = farm.getY();
		
		int fieldWidth = (x+sizeX) / fieldsX;
		int fieldHeight = (y+sizeY) / fieldsY;
		
		
		int fieldSize = fieldWidth;
		if (fieldWidth > fieldHeight)
			fieldSize = fieldHeight;
		
		drawTileSet(new Coord(x,y), new Coord(x+sizeX,y+sizeY), screenBuffer2d);
		
		for (int j = 0; j < fieldsX; j++)
			for (int i = 0; i < fieldsY; i++){
				Plant thisPlant = farm.getField(j, i);
				int thisX = x+j*fieldWidth;
				int thisY = y+i*fieldWidth;
				
				if (thisPlant != null)
					drawPlant(thisPlant, new Coord(thisX,thisY), 
							new Coord(thisX,thisY).transform(fieldSize,fieldSize), screenBuffer2d);
				
			}
		
		
		
	}
	
	/**
	 * Draws a bush with 3 berries on it of the colours selected on the given Graphics2D object. The drawn object 
	 * autoscales with the given dimensions.
	 * 
	 * @param berry colour of berries
	 * @param plant colour of plant
	 * @param x top left edge of plant
	 * @param y top left edge of plant
	 * @param size length of edges of the plant object
	 * @param hasBerry whether or not berries are visible on the plant
	 * @param screenBuffer2d the Graphics2D buffer that the plant will be drawn to
	 */
	private void drawPlant(Plant plant, Coord topLeft, Coord bottomRight, Graphics2D screenBuffer2d){
		
		int x = topLeft.getX();
		int y = topLeft.getY();
		int size = bottomRight.getX() - topLeft.getX();
		
		Color berry = plant.getBerryColor();
		
		//Draw the bush itself
		screenBuffer2d.setColor(plant.getLeafColor());
		screenBuffer2d.fillRect(x,y,size,size);
		
		//If the berries are present, draws them
		if (plant.isHarvestable()){
			drawColoredSquare(berry, x+size/6, y+size/5, size/6, screenBuffer2d);
			drawColoredSquare(berry, x+size-size/6*2, y+size/5, size/6, screenBuffer2d);
			drawColoredSquare(berry, x+size/2-(size/12), y+size-size/6*2, size/6, screenBuffer2d);
		}
	}
	
	private void drawTileSet(Coord coordTL, Coord coordBR, Graphics2D screenBuffer2d){
		
		int tileWidth = (coordBR.getX() - coordTL.getX() ) / tileSet.length;
		int tileHeight = (coordBR.getY() - coordTL.getY()) / tileSet[0].length;
		
		for (int j = 0; j < tileSet.length; j++)
			for (int i = 0; i < tileSet[0].length; i++){
				Coord topLeft =  new Coord(coordTL.getX()+j*tileWidth, coordTL.getY()+i*tileHeight);
				Coord bottomRight = new Coord(topLeft).transform(tileWidth,tileHeight);
				tileSet[j][i].drawTile(topLeft, bottomRight, screenBuffer2d);
			}
		
	}
	

	
	
	
	/**
	 * Draws a square of the provided colour/size at the given x/y coords on the provided 2D buffer.
	 * 
	 * @param color
	 * @param x
	 * @param y
	 * @param size
	 * @param screenBuffer2d
	 */
	private void drawColoredSquare(Color color, int x, int y, int size, Graphics2D screenBuffer2d){
		screenBuffer2d.setColor(color);
		screenBuffer2d.fillRect( x, y, size, size);
	}

}
