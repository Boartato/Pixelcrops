package ca.sevenless.pixelcrops.gui;

import java.awt.image.BufferedImage;

import ca.sevenless.pixelcrops.gui.util.Button;
import ca.sevenless.pixelcrops.gui.util.ButtonManager;
import ca.sevenless.pixelcrops.util.BoxCoord;
import ca.sevenless.pixelcrops.world.farm.Farm;
import ca.sevenless.pixelcrops.world.inventory.Berry;
import ca.sevenless.pixelcrops.world.inventory.Inventory;

/**
 * Created by Anoop on 11/06/2014.
 * @param <T>
 */
public class PlotButton<T> extends Button {

    Farm farm;
    boolean used;
    int x, y;
    public PlotButton(ButtonManager parent, BoxCoord _box, BufferedImage _graphic, Farm farm, int x, int y)
    {
        super(parent, _box, _graphic);
        this.farm = farm;
        used = false;
        this.x = x;
        this.y = y;
    }
    /**
     * Changes used to true, to represent the farm having a plant
     */
    public void usedTrue()
    {
        used = true;
    }
    /**
     * Changes used to false, to represent the farm not having a plant
     */
    public void usedFalse()
    {
        used = false;
    }
    
    public void clickCode(){
    	if(used == true)
    	{
    		farm.waterField(x, y);
    		used = true; 
    	}
    	else
    	{
    		farm.sowSeed(x, y, new Berry(10, 10, 10) );
    		//add random number generator
    	}
    	
    }
}
