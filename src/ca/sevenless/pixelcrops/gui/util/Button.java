/**
 * 
 */
package ca.sevenless.pixelcrops.gui.util;


/**
 * @author Sevenless
 *
 */
public abstract class Button {
	
	//Parent UserInterface of this button
	protected ButtonManager parent;
	
	//Hitbox coordinates with reference to the parent object
	private double x1;
	private double y1;
	private double x2;
	private double y2;
	
	/**
	 * Creates a new Button with the given parameters
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public Button (ButtonManager parent, double x1, double y1, double x2, double y2){
		this.parent = parent;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	/**
	 * Determines if two buttons have the exact coordinates and parent instance
	 * 
	 * @param otherButton
	 * @return 
	 * Returns true if the two buttons have the same dimensions and parent instance
	 */
	public boolean equals(Button otherButton){
		double[] myLocation = getLocation();
		double[] otherLocation = otherButton.getLocation();
		
		if (parent != otherButton.getParent())
			return false;
		
		for (int i = 0; i < otherLocation.length; i++)
			if (otherLocation[i] != myLocation[i])
				return false;
		
		return true;
	}
	
	
	/**
	 * Returns a location array for comparing two buttons
	 * @return
	 */
	public double[] getLocation(){
		double[] buttonLocation = new double[] {x1,y1,x2,y2};
		return buttonLocation;
	}
	
	/**
	 * Returns the parent for comparing two buttons
	 * @return
	 */
	public ButtonManager getParent(){
		return parent;
	}
	
	/**
	 * Returns true if the relative click location is within the button's relative bounds and runs the clickCode
	 * 
	 * @param relativeX Relative X location of the mouse with regards to the parnt
	 * @param relativeY Relative Y location of the mouse with regards to the parent
	 * @return
	 * True if the click event happens within the button
	 */
	public boolean clickCheck(double relativeX, double relativeY){
		
		
		
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
