/**
 * 
 */
package ca.sevenless.pixelcrops.util;

/**
 * Wrapper for Cartesian coordinates which allows them to be passed together and modified
 * as a pair.
 * 
 * @author Sevenless
 */
public class Coord {

	private int x;
	private int y;
	
	/**
	 * Creates a new coordinate with the x and y values provided
	 * @param x
	 * @param y
	 */
	public Coord (int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Creates a new Coord that is a copy of the provided Coord object
	 * 
	 * @param topLeft
	 */
	public Coord(Coord copiedCoord) {
		this.x = copiedCoord.getX();
		this.y = copiedCoord.getY();
	}
	
	/**
	 * Creates a copy of the coord provided and transforms it with the provided deltaX and deltaY values
	 * @param copiedCoord
	 * @param deltaX
	 * @param deltaY
	 */
	public Coord(Coord copiedCoord, int deltaX, int deltaY) {
		this.x = copiedCoord.getX() + deltaX;
		this.y = copiedCoord.getY() + deltaY;
	}

	/**
	 * Modifies a coordinate by adding the differences provided
	 * @param deltaX
	 * @param deltaY
	 */
	public Coord transform (int deltaX, int deltaY){
		x += deltaX;
		y += deltaY;
		return this;
	}
	
	/**
	 * Modifies a coordinate by setting the x and y values to those provided
	 * @param newX
	 * @param newY
	 */
	public Coord set (int newX, int newY){
		x = newX;
		y = newY;
		return this;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	
	
	
}
