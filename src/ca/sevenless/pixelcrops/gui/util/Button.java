package ca.sevenless.pixelcrops.gui.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import ca.sevenless.pixelcrops.util.BoxCoord;
import ca.sevenless.pixelcrops.util.Coord;

public class Button extends HitBox {
	BufferedImage graphic;
	String buttonName;
	//Dimensions of the graphic
	int width;
	int height;
	
	public Button(ButtonManager parent, BoxCoord _box, BufferedImage _graphic) {
		super(parent, _box);
		graphic = _graphic; 
		width = graphic.getWidth();
		height = graphic.getHeight();
		// TODO Auto-generated constructor stub
	}
	
	
	public void drawTL(Graphics2D screenBuffer2D, Coord coord){
        screenBuffer2D.drawImage(graphic, 
        		coord.getX(),coord.getY(),
        		width,height, null);
	}
	


	@Override
	public void clickCode() {
		// TODO Auto-generated method stub
		
	}


	public BufferedImage getGraphic() {
		return graphic;
	}


	public void setGraphic(BufferedImage graphic) {
		this.graphic = graphic;
	}


	public String getButtonName() {
		return buttonName;
	}


	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}
}
