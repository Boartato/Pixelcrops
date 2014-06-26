/**
 * 
 */
package ca.sevenless.pixelcrops.world.farm;

import java.io.Serializable;

import ca.sevenless.pixelcrops.world.inventory.Berry;

/**
 * @author Sevenless
 *
 */
public class Farm implements Serializable, FarmInterface{

	private static final long serialVersionUID = 1L;
	
	//2d array of plantable locations and their contained plants if they exist
	private Plant[][] field;
	
	/**
	 * Creates an empty farm of size x*y
	 * @param x
	 * @param y
	 */
	public Farm(int x, int y){
		field = new Plant[x][y];
	}
	
	
	/**
	 * Generates a new plant at the given field location from the given berry object
	 * @param x
	 * @param y
	 * @param seed
	 */
	public void sowSeed(int x, int y, Berry seed){
	
		if (field[x][y] == null)
			field[x][y] = new Plant(seed);
		
	}
	
	/**
	 * Returns the plant present in field x,y if it exists, null otherwise
	 * @param x
	 * @param y
	 * @return
	 */
	public Plant getField(int x, int y){
		if (field[x][y] != null)
			return field[x][y];
		return null;
	}

	/**
	 * Calls the water function from plant if a plant is present on the field x,y
	 * @param x
	 * @param y
	 */
	public void waterField(int x, int y){
		if (field[x][y] != null)
			field[x][y].water();
	}
	
	/**
	 * Runs through the possible field slots and increments the age of the plant if there is a plant in that field
	 */
	public void incrementAge(){
		for (int j = 0; j < field.length; j++)
			for (int i = 0; i < field[j].length; i++)
				if (field[j][i] != null)
					field[j][i].incrementAge();
	}


	/* (non-Javadoc)
	 * @see ca.sevenless.pixelcrops.world.farm.FarmInterface#getX()
	 */
	@Override
	public int getX() {
		return field.length;
	}


	/* (non-Javadoc)
	 * @see ca.sevenless.pixelcrops.world.farm.FarmInterface#getY()
	 */
	@Override
	public int getY() {
		return field[0].length;
	}


	
	
	
}
