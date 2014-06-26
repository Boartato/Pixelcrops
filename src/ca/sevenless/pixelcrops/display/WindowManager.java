/**
 * 
 */
package ca.sevenless.pixelcrops.display;

import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ca.sevenless.pixelcrops.display.util.Drawable;
import ca.sevenless.pixelcrops.display.util.GraphicsHolder;
import ca.sevenless.pixelcrops.display.util.GraphicsLoader;
import ca.sevenless.pixelcrops.display.util.ThreadedCanvas;
import ca.sevenless.pixelcrops.display.util.GraphicsLoader.NoAcceptedImageFormatsException;
import ca.sevenless.pixelcrops.gui.GameKeyListener;
import ca.sevenless.pixelcrops.gui.GameMouseListener;
import ca.sevenless.pixelcrops.init.GameInitialization;
import ca.sevenless.pixelcrops.util.BoxCoord;
import ca.sevenless.pixelcrops.world.Tile;
import ca.sevenless.pixelcrops.world.farm.FarmInterface;



/**
 * Manages the initialization of the frame which holds the client graphical display and graphical user interface for the game
 * @author Sevenless
 *
 */
public class WindowManager {
	private Frame frame;
	private ThreadedCanvas canvas;
	
	private GameInitialization main;
	private Thread displayThread;
	
	protected GraphicsHolder graphicsHolder;
	protected DisplayFarm displayFarm;
	
	private boolean fullscreen = false;
	private int frameRate;
	

	/**
	 * Initializes a frame and attaches the created canvas to it
	 * @param mouseListener 
	 * @param keyListener 
	 * @param graphicResourceDirectory 
	 * @param imageFormats 
	 * @param _canvas
	 * @throws IOException 
	 */
	public WindowManager(	GameInitialization _main,
							GameKeyListener keyListener, 
							GameMouseListener mouseListener, 
							String graphicResourceDirectory, 
							Iterable<String> imageFormats, 
							BoxCoord farmLocation,
							FarmInterface farmInterface,
							boolean _fullscreen, 
							int _frameRate) throws IOException {
		main = _main;
		fullscreen = _fullscreen;
		frameRate = _frameRate;
		
		loadGraphicResources(graphicResourceDirectory, imageFormats);
		initDisplayObjects(farmLocation, farmInterface);
		initThreadedCanvas(displayFarm);
		initFrame();
		attachListeners(keyListener, mouseListener);
		
		displayThread.start();
		makeVisible();
	}
	
	
	/**
	 * Loads graphics from the given resource directory after creating a GraphicsLoader class which loads files 
	 * ending with the given acceptable imageFormats.
	 * 
	 * @param graphicResourceDirectory Name of the top level directory containing graphic resources
	 * @param imageFormats Only files ending in these formats are loaded, others are ignored
	 * @throws IOException Thrown if the loader encounters a system based input/output exception
	 */
	private void loadGraphicResources(String graphicResourceDirectory, Iterable<String> imageFormats) throws IOException{
		
		GraphicsLoader graphicsLoader = new GraphicsLoader(imageFormats);
		graphicsHolder = new GraphicsHolder();
		
		try {
			graphicsLoader.loadGraphics(graphicResourceDirectory, graphicsHolder);
		} catch (NoAcceptedImageFormatsException e) {
			System.out.println("Corrupted setup files, no acceptable image formats given in init");
			e.printStackTrace();//Should only happen in case of corrupted setup files
		}
		
		
	}
	
	private void initDisplayObjects(BoxCoord farmLocation, FarmInterface farmInterface){
		
		//TODO initialize Display objects from passed game logic information
		
		displayFarm = new DisplayFarm(farmLocation, farmInterface);
		
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
	 * @param displayFarm2 
	 */
	private void initThreadedCanvas(DisplayFarm displayFarm){
		List<Drawable> drawables = new ArrayList<Drawable>();
		drawables.add(displayFarm);
		
		canvas = new ThreadedCanvas(frameRate, this, drawables );
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
