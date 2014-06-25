/**
 * 
 */
package ca.sevenless.pixelcrops.display;

import java.awt.Graphics;
import java.awt.Graphics2D;

import ca.sevenless.pixelcrops.display.WindowManager;
import ca.sevenless.pixelcrops.display.util.Sprite;
import ca.sevenless.pixelcrops.display.util.ThreadedCanvas;
import ca.sevenless.pixelcrops.display.util.Sprite.SpriteImageMissingException;
import ca.sevenless.pixelcrops.util.Coord;
import ca.sevenless.pixelcrops.world.farm.Farm;
import ca.sevenless.pixelcrops.world.inventory.Berry;

/**
 * @author Sevenless
 *
 */
public class ThreadedCanvasTest extends ThreadedCanvas{

	/**
	 * @param initialFrameRate
	 * @param graphicsManager
	 */
	public ThreadedCanvasTest(int initialFrameRate,
			WindowManager graphicsManager) {
		super(initialFrameRate, graphicsManager);
		// TODO Auto-generated constructor stub
	}
	
	public void paint(Graphics g){
		Graphics2D screen = (Graphics2D) g;
        Graphics bufferG = buffer.getGraphics();
        Graphics2D screenBuffer2D = (Graphics2D) bufferG;
        refreshScreen(screenBuffer2D);
        
        //Test Code
        
        Farm test = new Farm(2,2);
        test.sowSeed(0,0,new Berry(255,0,0));
        test.sowSeed(1,1,new Berry(100,100,100));
        
        while (!test.getField(0,0).isHarvestable()){
        	test.waterField(0,0);
        	test.incrementAge();
        }
        
        while (!test.getField(1,1).isHarvestable()){
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
		
		//End test code
		
        screen.drawImage(buffer, 0, 0, this);
	}
	
	
	public static void main(String[] args){
        
        
	}

}
