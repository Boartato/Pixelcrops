/**
 * 
 */
package ca.sevenless.pixelcrops.gui.util;

import ca.sevenless.pixelcrops.util.BoxCoord;
import ca.sevenless.pixelcrops.util.Coord;


/**
 * @author Sevenless
 *
 */
public abstract class HitBox {
	
	//Parent UserInterface of this button
	protected ButtonManager parent;
	
	//Hitbox coordinates with reference to the parent object
	private BoxCoord box; 
	
	
	/**
	 * Creates Hitbox using Box coord
	 * @param parent
	 * @param _box
	 */
	public HitBox (ButtonManager parent,  BoxCoord _box){
		this.parent = parent;
		
		box = _box; 
	}
	
	/**
	 * Determines if two buttons have the exact coordinates and parent instance
	 * 
	 * @param otherButton
	 * @return 
	 * Returns true if the two buttons have the same dimensions and parent instance
	 */
	public boolean equals(HitBox otherButton){
		BoxCoord myLocation = getLocation();
		BoxCoord otherLocation = otherButton.getLocation();
		
		if (parent != otherButton.getParent())
			return false;
		
		if (myLocation.equals(otherLocation))
				return false;
		
		return true;
	}
	
	
	/**
	 * Returns a location array for comparing two buttons
	 * @return
	 */
	public BoxCoord getLocation(){
		return box; 
	}
	
	/**
	 * Returns the parent for comparing two buttons
	 * @return
	 */
	public ButtonManager getParent(){
		return parent;
	}
	
	/**
	 * Getter for Top Left X coordinate of hitbox
	 * @return
	 */
	public double getX1()
	{
		return box.getTL().getX(); 
	}
	/**
	 * Getter for Bottom Right X coordinate of hitbox
	 * @return
	 */
	public double getX2()
	{
		
		return box.getBR().getX(); 
	}
	/**
	 * Getter for Top Left Y coordinate of hitbox
	 * @return
	 */
	public double getY1()
	{
		return box.getTL().getY(); 
	}
	/**
	 * Getter for Bottom Right Y coordinate of hitbox
	 * @return
	 */
	public double getY2()
	{
		return box.getBR().getY(); 
	}
	/**
	 * Returns true if the relative click location is within the button's relative bounds and runs the clickCode
	 * 
	 * @param relativeX Relative X location of the mouse with regards to the parent
	 * @param relativeY Relative Y location of the mouse with regards to the parent
	 * @return
	 * True if the click event happens within the button
	 */
	public boolean clickCheck(double relativeX, double relativeY){
		
		double x1 = this.getX1();
		double x2 = this.getX2();
		double y1 = this.getY1(); 
		double y2 = this.getY2();
		
		if (relativeX >= x1 && relativeX <= x2 && relativeY >= y1 && relativeY <= y2){
			clickCode();
			return true;
		}
		return false;
	}
	
	/**
	 * Code run when this particular implementation of button is clicked
	 */
	public abstract void clickCode();
	
	
}
