/**
 * 
 */
package ca.sevenless.pixelcrops.init;

import ca.sevenless.pixelcrops.display.GraphicsManager;
import ca.sevenless.pixelcrops.gui.GameKeyListener;
import ca.sevenless.pixelcrops.gui.GameMouseListener;
import ca.sevenless.pixelcrops.world.World;

/**
 * Initializes game client
 * 
 * @author Sevenless
 *
 */
public class GameInitialization {

	private GraphicsManager graphicsManager;
	private GameKeyListener keyListener;
	private GameMouseListener mouseListener;

	private World gameWorld;
	
	//Setup values for inventory dimensions
	private int invX = 4;
	private int invY = 4;
	//Setup values for farm dimensions
	private int farmX = 1;
	private int farmY = 1;
	
	/**
	 * Handles game setup by initializing graphics, data and socket managers
	 */
	public GameInitialization(){
		
		initListeners();
		initGameWorld();
		graphicsManager = new GraphicsManager(this, keyListener, mouseListener, false, 30);
	}

	private void initGameWorld(){
		
		gameWorld = new World(farmX, farmY, invX, invY);
		
	}
	
	private void initListeners(){
		keyListener = new GameKeyListener(this);
		mouseListener = new GameMouseListener(this, null);
	}
	
	/**
	 * Calls the appropriate code to cleanup client resources before program closure
	 */
	public void endProgram() {
	
		//Request logout from the server before allowing the program to close
		System.exit(0);
		
	}
	
	public static void main(String[] args){
		@SuppressWarnings("unused")
		GameInitialization main = new GameInitialization();
	}
	
}
