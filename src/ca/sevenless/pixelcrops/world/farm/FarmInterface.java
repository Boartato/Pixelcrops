/**
 * 
 */
package ca.sevenless.pixelcrops.world.farm;

import ca.sevenless.pixelcrops.world.inventory.Berry;

/**
 * @author Sevenless
 *
 */
public interface FarmInterface {

	/**
	 * Generates a new plant at the given field location from the given berry object
	 * @param x
	 * @param y
	 * @param seed
	 */
	public void sowSeed(int x, int y, Berry seed);
	
	/**
	 * Returns the plant present in field x,y if it exists, null otherwise
	 * @param x
	 * @param y
	 * @return
	 */
	public Plant getField(int x, int y);

	/**
	 * Calls the water function from plant if a plant is present on the field x,y
	 * @param x
	 * @param y
	 */
	public void waterField(int x, int y);
	
	public int getX();
	
	public int getY();
	
	
	/**
	 * Runs through the possible field slots and increments the age of the plant if there is a plant in that field
	 */
	public void incrementAge();
}
