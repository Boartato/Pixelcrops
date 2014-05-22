package ca.sevenless.pixelcrops.gui.util;

import java.awt.Color;

import ca.sevenless.pixelcrops.gui.GameKeyListener;
import ca.sevenless.pixelcrops.gui.GameMouseListener;

public class TestButton extends Button {
	
	private GameMouseListener mouseListener;
	public TestButton(ButtonManager parent, double x1, double y1, double x2, double y2 ) 
	{
		super(parent, x1, y1, x2, y2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void clickCode() {
		System.out.println("Awesome");
	}
	public ButtonManager getParent()
	{
		return parent;
	}
	
	
	
	

}
