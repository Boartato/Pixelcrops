/**
 * 
 */
package ca.sevenless.pixelcrops.display;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import ca.sevenless.pixelcrops.util.Coord;
import ca.sevenless.pixelcrops.world.farm.Farm;
import ca.sevenless.pixelcrops.world.farm.FarmInterface;
import ca.sevenless.pixelcrops.world.farm.Plant;
import ca.sevenless.pixelcrops.world.inventory.Berry;


/**
 * Handles the program specific painting of the canvas for the GameProject, as well as being the location for threaded run
 * code to repaint the screen.
 * 
 * @author Sevenless
 *
 */
public class ThreadedCanvas extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	
	//Buffer images are drawn to before posting the composit image to the screen for the user to see
	private BufferedImage buffer;
	//The desired framerate, rounding may make actual framerate differ slightly
	int frameRate;
	//For graceful cleanup of thread resources
	boolean notShutdown = true;
	
	public ThreadedCanvas(int initialFrameRate){
		frameRate = initialFrameRate;
	}
	
	
	/**
	 * Sets the size of the canvas to the desired dimensions, and then creates a new buffer object to match
	 */
	@Override
	public void setSize(int width, int height){
		super.setSize(width,height);
		buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	}
	
	/**
	 * Called via the repaint function that this ThreadedCanvas is running, handles all graphics in a logic
	 * independent manner.
	 */
	public void paint(Graphics g){
		Graphics2D screen = (Graphics2D) g;
        Graphics bufferG = buffer.getGraphics();
        Graphics2D screenBuffer2D = (Graphics2D) bufferG;
        refreshScreen(screenBuffer2D);
        
        Farm test = new Farm(3,3);
        test.sowSeed(0,0,new Berry(255,0,0));
        test.sowSeed(1,2,new Berry(100,100,100));
        
        while (!test.peekField(0,0).isHarvestable()){
        	test.waterField(0,0);
        	test.incrementAge();
        }
        
        while (!test.peekField(1,2).isHarvestable()){
        	test.waterField(1,2);
        	test.incrementAge();
        }
        
        drawFarm(0,0,100,100,test,screenBuffer2D);
        
        Sprite testSprite = null;
		try {
			testSprite = new Sprite("yellowcircle.png");
		} catch (SpriteImageMissingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		Coord testCoord = new Coord(100,100);
		
		testSprite.drawTL(screenBuffer2D,testCoord);
		testSprite.drawFB(screenBuffer2D,testCoord);
		
        screen.drawImage(buffer, 0, 0, this);
	}
	
	private void drawFarm(int x, int y, int sizeX, int sizeY, FarmInterface farm, Graphics2D buf2d){
		int fieldsX = farm.getX();
		int fieldsY = farm.getY();
		
		int fieldWidth = (x+sizeX) / fieldsX;
		int fieldHeight = (y+sizeY) / fieldsY;
		
		
		int fieldSize = fieldWidth;
		if (fieldWidth > fieldHeight)
			fieldSize = fieldHeight;
		
		for (int j = 0; j < fieldsX; j++)
			for (int i = 0; i < fieldsY; i++){
				Plant thisPlant = farm.peekField(j, i);
				int thisX = x+j*fieldWidth;
				int thisY = y+i*fieldWidth;
				
				if (thisPlant != null)
					drawPlant(thisPlant, thisX, thisY, fieldSize, buf2d);
				
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
	 * @param buf2d the Graphics2D buffer that the plant will be drawn to
	 */
	private void drawPlant(Plant plant, int x, int y, int size, Graphics2D buf2d){
		
		Color berry = plant.getBerryColor();
		
		//Draw the bush itself
		buf2d.setColor(plant.getLeafColor());
		buf2d.fillRect(x,y,size,size);
		
		//If the berries are present, draws them
		if (plant.isHarvestable()){
			drawColoredSquare(berry, x+size/6, y+size/5, size/6, buf2d);
			drawColoredSquare(berry, x+size-size/6*2, y+size/5, size/6, buf2d);
			drawColoredSquare(berry, x+size/2-(size/12), y+size-size/6*2, size/6, buf2d);
		}
	}
	
	/**
	 * Draws a square of the provided colour/size at the given x/y coords on the provided 2D buffer.
	 * 
	 * @param color
	 * @param x
	 * @param y
	 * @param size
	 * @param screenBuffer2D
	 */
	private void drawColoredSquare(Color color, int x, int y, int size, Graphics2D screenBuffer2D){
		screenBuffer2D.setColor(color);
		screenBuffer2D.fillRect( x, y, size, size);
	}
	
	/**
	 * Refreshes the buffer to a black background for drawing the next image on
	 * @param screenBuffer
	 */
	private void refreshScreen(Graphics2D screenBuffer2D){
		screenBuffer2D.setColor(Color.black);
        screenBuffer2D.fillRect(0, 0, getWidth(), getHeight());
	}
	
	@Override
	public void run() {
		
		while (notShutdown){
			this.repaint();
		}
		
		try {
			Thread.sleep(1000/frameRate);
		} catch (InterruptedException e) {
			//Expected during shutdown
		}
	}
	
	
	
	/**
	 * Called by the repaint function
	 */
	@Override
	public void update(Graphics g){
		paint(g);
	}

}
