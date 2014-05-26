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
	
	//Location of display objects used for displaying Pixelcrops objects like farms and etc
	GraphicsManager graphicsManager;
	//For graceful cleanup of thread resources
	boolean notShutdown = true;
	
	public ThreadedCanvas(int initialFrameRate, GraphicsManager graphicsManager){
		frameRate = initialFrameRate;
		this.graphicsManager = graphicsManager;
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
        
        Farm test = new Farm(2,2);
        test.sowSeed(0,0,new Berry(255,0,0));
        test.sowSeed(1,1,new Berry(100,100,100));
        
        while (!test.peekField(0,0).isHarvestable()){
        	test.waterField(0,0);
        	test.incrementAge();
        }
        
        while (!test.peekField(1,1).isHarvestable()){
        	test.waterField(1,1);
        	test.incrementAge();
        }
        
        graphicsManager.displayFarm.drawFarm(0,0,100,100,test,screenBuffer2D);
        
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
