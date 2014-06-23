/**
 * 
 */
package ca.sevenless.pixelcrops.display;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


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
	protected BufferedImage buffer;
	//The desired framerate, rounding may make actual framerate differ slightly
	int frameRate;
	
	//Location of display objects used for displaying Pixelcrops objects like farms and etc
	protected WindowManager graphicsManager;
	//For graceful cleanup of thread resources
	boolean notShutdown = true;
	//Drawing object
	private DisplayFarm displayFarm;
	
	//TODO
	/*
	 * Threaded canvas needs dependancy inversion to function in an engine. Collection of drawable
	 * elements with draw interface to feed into the pain function?
	 */
	
	public ThreadedCanvas(int initialFrameRate, WindowManager graphicsManager, DisplayFarm displayFarm){
		frameRate = initialFrameRate;
		this.graphicsManager = graphicsManager;
		this.displayFarm = displayFarm;
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
        
        //TODO drawing code goes here
        displayFarm.draw(screenBuffer2D);
		
        screen.drawImage(buffer, 0, 0, this);
	}
	
	
	/**
	 * Refreshes the buffer to a black background for drawing the next image on
	 * @param screenBuffer
	 */
	protected void refreshScreen(Graphics2D screenBuffer2D){
		screenBuffer2D.setColor(Color.black);
        screenBuffer2D.fillRect(0, 0, getWidth(), getHeight());
	}
	
	@Override
	public void run() {
		
		while (notShutdown){
			this.repaint();
			
			try {
				Thread.sleep(1000/frameRate);
			} catch (InterruptedException e) {
				//Expected during shutdown
				System.out.println("Canvas redraw thread shutting down");
			}
			
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
