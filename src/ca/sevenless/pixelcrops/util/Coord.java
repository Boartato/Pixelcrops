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
	 * @param topLeft
	 */
	public Coord(Coord copiedCoord) {
		this.x = copiedCoord.getX();
		this.y = copiedCoord.getY();
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
	public void set (int newX, int newY){
		x = newX;
		y = newY;
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
