/**
 * 
 */
package ca.sevenless.pixelcrops.display;

import java.awt.Canvas;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Window;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import ca.sevenless.pixelcrops.gui.GameKeyListener;
import ca.sevenless.pixelcrops.gui.GameMouseListener;
import ca.sevenless.pixelcrops.init.GameInitialization;
import ca.sevenless.pixelcrops.util.ImageLoader;
import ca.sevenless.pixelcrops.world.Tile;



/**
 * Manages the initialization of the frame which holds the client graphical display and graphical user interface for the game
 * @author Sevenless
 *
 */
public class GraphicsManager {
	private Frame frame;
	private ThreadedCanvas canvas;
	
	private GameInitialization main;
	private Thread displayThread;
	
	protected DisplayFarm displayFarm;
	private BufferedImage testImage;
	
	private boolean fullscreen = false;
	private int frameRate;
	

	/**
	 * Initializes a frame and attaches the created canvas to it
	 * @param mouseListener 
	 * @param keyListener 
	 * @param _canvas
	 */
	public GraphicsManager(	GameInitialization _main,
							GameKeyListener keyListener, 
							GameMouseListener mouseListener, 
							boolean _fullscreen, 
							int _frameRate) {
		main = _main;
		fullscreen = _fullscreen;
		frameRate = _frameRate;
		
		loadGraphicResources();
		initDisplayObjects();
		initThreadedCanvas();
		initFrame();
		attachListeners(keyListener, mouseListener);
		
		displayThread.start();
		makeVisible();
	}
	
	private void loadGraphicResources(){
		try {
			testImage = ImageLoader.createImageIO("yellowcircle.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void initDisplayObjects(){
		Tile[][] tileSet = new Tile[2][2];
		tileSet[0][0] = new Tile(testImage, "testTile");
		tileSet[1][0] = new Tile(testImage, "testTile");
		tileSet[1][1] = new Tile(testImage, "testTile");
		tileSet[0][1] = new Tile(testImage, "testTile");
		
		displayFarm = new DisplayFarm(tileSet);
	}

	/**
	 * @param keyListener
	 * @param mouseListener
	 */
	private void attachListeners(GameKeyListener keyListener, GameMouseListener mouseListener) {

		canvas.addMouseListener(mouseListener);
		canvas.addMouseMotionListener(mouseListener);
		canvas.addMouseWheelListener(mouseListener);
		canvas.addKeyListener(keyListener);
		
		frame.addWindowListener(
			new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					windowClosingEvent();
				}
			});
		frame.addComponentListener(
			new ComponentAdapter(){
				@Override
				public void componentResized(ComponentEvent e){
					correctDisplayDimensions();
				}
				@Override
				public void componentMoved(ComponentEvent e){
					correctDisplayDimensions();
				}
			});
		
	}

	/**
	 * Creates ThreadedCanvas object and prepares itd for threading.
	 */
	private void initThreadedCanvas(){
		canvas = new ThreadedCanvas(frameRate, this);
		displayThread = new Thread(canvas);
	}
	
	/**
	 * Initializes the AWT frame and sets its close function to end the program
	 * 
	 * Note: Flickering when resizing is possibly due to a bug with the implementation of java on windows 7
	 */
	private void initFrame(){
		
		frame = new Frame("Game");
		frame.add(canvas);
		//Ignores OS level repaint calls, all repaint calls on this frame will come from inside this program
		frame.setIgnoreRepaint(true);
		
		if (fullscreen){
			frame.setUndecorated(true);
			frame.setExtendedState(Frame.MAXIMIZED_BOTH);
			GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			setDisplayDimensions(gd.getDisplayMode().getWidth(), gd.getDisplayMode().getHeight());
		}
		else
			setDisplayDimensions(500,500);
		
	}
	
	/**
	 * In cases where the user is manually resizing during windowed mode, this code is called to update the display
	 * within the frame so the canvas matches the frame size
	 */
	private void correctDisplayDimensions(){
		if (frame.getWidth() != canvas.getWidth() || frame.getHeight() != canvas.getHeight())
			canvas.setSize(frame.getWidth(), frame.getHeight());
	}
	
	/**
	 * Sets the frame and canvas dimensions to the desired width and height
	 * @param width
	 * @param height
	 */
	private void setDisplayDimensions(int width, int height){
		frame.setSize(width, height);
		canvas.setSize(width, height);
	}
	/**
	 * Called after all initialization is complete to ensure that setup has been completed before
	 * the screen becomes visible.
	 */
	private void makeVisible(){
		
		frame.setVisible(true);
		canvas.setVisible(true);
		canvas.requestFocus();
	}
	
	/**
	 * Handles any file saving/cleanup required before non-crash exiting of the program
	 */
	private void windowClosingEvent() {
		main.endProgram();
	}
	
	/**
	 * Cleans up display resources during client shutdown
	 */
	public void cleanupDisplayResources(){
		canvas.notShutdown = false;
		displayThread.interrupt();
		frame.dispose();
	}
	
	
	
}
