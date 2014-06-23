/**
 * 
 */
package ca.sevenless.pixelcrops.gui.util;

import java.util.ArrayList;

import ca.sevenless.pixelcrops.gui.GameMouseListener;
import ca.sevenless.pixelcrops.util.BoxCoord;


/**
 * @author anooptoor, Sevenless
 *
 */
public class ButtonManager{

	public ArrayList<Button> buttonList;
	
	BoxCoord box;
	
	public ButtonManager(BoxCoord _box){
		box = _box;
		buttonList = new ArrayList<Button>();
		
	}
	
	
	/**
	 * Adds a button to the list whose parent is preset to this UserInterface
	 * @param newButton
	 */
	public void addButton(Button b)
	{
		buttonList.add(b);
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
	 * Cycles through all of the buttons and passes them the mouse Coords to see if they were clicked
	 * 
	 * @param relMouseX Relative to this UserInterface MouseX coords
	 * @param relMouseY Relative to this UserInterface MouseY coords
	 */
	public void clickEvent(double mouseX, double mouseY){
		
		double x1 = this.getX1();
		double x2 = this.getX2();
		double y1 = this.getY1(); 
		double y2 = this.getY2();
		
		
		
		
		if(x1 <= mouseX && x2 >= mouseX && y1 <= mouseY && y2 >= mouseY)
		{
			double relMouseX = mouseX - x1;
			//System.out.println("X: " + relMouseX);
			double relMouseY = mouseY - y1;
			//System.out.println("Y: " + relMouseY);
		
			for (HitBox b : buttonList)
			{
				System.out.println(relMouseX + " " + relMouseY);
		
				b.clickCheck(relMouseX, relMouseY);
			}
			
		}
			
			
	}
	
	
	
}
