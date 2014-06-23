package ca.sevenless.pixelcrops.gui.util;

import ca.sevenless.pixelcrops.display.util.GraphicsHolder;
import ca.sevenless.pixelcrops.display.util.GraphicsHolder.GraphicNotFoundException;
import ca.sevenless.pixelcrops.display.util.GraphicsLoader;
import ca.sevenless.pixelcrops.util.BoxCoord;

public class GUIManager extends ButtonManager {
	
	GraphicsHolder g;
	public GUIManager(BoxCoord box)
	{
		super(box);
		g = new GraphicsHolder(); 
		
	}
	public void loadImage()
	{
		
	}

	
	public void clickEvent(double mouseX, double mouseY)
	{
		
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
		
			for (Button b : buttonList)
			{
				System.out.println(relMouseX + " " + relMouseY);
		
				b.clickCheck(relMouseX, relMouseY);
				//change image
				try {
					b.setGraphic(g.find(b.getButtonName() + "2"));
				} catch (GraphicNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}	
	}

}
