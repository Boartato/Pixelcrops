/**
 * 
 */
package ca.sevenless.pixelcrops.display;

import java.awt.Color;
import java.awt.Graphics2D;

import ca.sevenless.pixelcrops.util.BoxCoord;
import ca.sevenless.pixelcrops.util.Coord;
import ca.sevenless.pixelcrops.world.Tile;
import ca.sevenless.pixelcrops.world.farm.FarmInterface;
import ca.sevenless.pixelcrops.world.farm.Plant;

/**
 * @author Sevenless
 *
 */
public class DisplayFarm {
	
	FarmInterface farm;
	BoxCoord drawingDimensions;
	
	/**
	 * Creates a new Display farm.
	 * @param drawingDimensions Area on the screen the farm should be drawn
	 * @param farmInterface The interface for communicating with the logic to find out what to display
	 */
	public DisplayFarm(BoxCoord drawingDimensions, FarmInterface farmInterface){
		this.drawingDimensions = drawingDimensions;
		this.farm= farmInterface;
	}
	
	/**
	 * Resizes the drawing dimensions to fit the BoxCoord passed as a parameter
	 * @param newDimensions New dimensions of the farm drawing area
	 */
	public void resize(BoxCoord newDimensions){
		this.drawingDimensions = newDimensions;
	}
	
	/*
	 * Draws a farm onto the given Graphics2D object using the drawingDimensions field to determine
	 * where things should be drawn.
	 */
	void draw(Graphics2D screenBuffer2d){
		
		int x = drawingDimensions.getTL().getX();
		int y = drawingDimensions.getTL().getY();
		int sizeX = drawingDimensions.getBR().getX() - x;
		int sizeY = drawingDimensions.getBR().getY() - y;
		
		int fieldsX = farm.getX();
		int fieldsY = farm.getY();
		
		int fieldWidth = (x+sizeX) / fieldsX;
		int fieldHeight = (y+sizeY) / fieldsY;
		
		
		int fieldSize = fieldWidth;
		if (fieldWidth > fieldHeight)
			fieldSize = fieldHeight;
			
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
