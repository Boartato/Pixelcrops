/**
 * 
 */
package ca.sevenless.pixelcrops.gui.util;

import java.util.ArrayList;

import ca.sevenless.pixelcrops.gui.GameMouseListener;


/**
 * @author Sevenless
 *
 */
public class ButtonManager{

	private ArrayList<Button> buttonList;
	
	private double x1;
	private double y1;
	private double x2;
	private double y2;
	
	public ButtonManager(double x1, double y1, double x2, double y2){
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		buttonList = new ArrayList<Button>();
		
	}
	
	
	/**
	 * Adds a button to the list whose parent is preset to this UserInterface
	 * @param newButton
	 */
	public void addButton(Button newButton){
		buttonList.add(newButton);
	}
	
	
	/**
	 * Cycles through all of the buttons and passes them the mouse Coords to see if they were clicked
	 * 
	 * @param relMouseX Relative to this UserInterface MouseX coords
	 * @param relMouseY Relative to this UserInterface MouseY coords
	 */
	public void clickEvent(double mouseX, double mouseY){
		
		if(x1 <= mouseX && x2 >= mouseX && y1 <= mouseY && y2 >= mouseY)
		{
			double relMouseX = mouseX - x1;
			//System.out.println("X: " + relMouseX);
			double relMouseY = mouseY - y1;
			//System.out.println("Y: " + relMouseY);
		
			for (Button b : buttonList)
			{
				System.out.println(relMouseX + " " + relMouseY);
		
				b.clickCheck(relMouseX, relMouseY);;
			}
			
		}
			
			
	}
	
	
	
}
