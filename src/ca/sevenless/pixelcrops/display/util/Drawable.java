/**
 * 
 */
package ca.sevenless.pixelcrops.display.util;

import java.awt.Graphics2D;

/**
 * @author Sevenless
 *
 */
public interface Drawable {

	/**
	 * Method which draws the item on the passed Graphics2D object using the internal coordinates that the drawable object has encapsulated
	 * @param screenBuffer2D
	 */
	public void draw(Graphics2D screenBuffer2D);

	/**
	 * Determines whether or not the display element should be drawn on the screen
	 * @return
	 */
	public boolean isVisible();
	
}
