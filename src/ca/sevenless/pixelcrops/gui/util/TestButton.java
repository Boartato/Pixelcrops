package ca.sevenless.pixelcrops.gui.util;

import java.awt.Color;

import ca.sevenless.pixelcrops.gui.GameKeyListener;
import ca.sevenless.pixelcrops.gui.GameMouseListener;
import ca.sevenless.pixelcrops.util.BoxCoord;
import ca.sevenless.pixelcrops.util.Coord;

public class TestButton extends HitBox {
	
	private GameMouseListener mouseListener;
	public TestButton(ButtonManager parent, int x1, int y1, int x2, int y2 ) 
	{
		super(parent, new BoxCoord(new Coord(x1, y1), new Coord(x2, y2)));
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
