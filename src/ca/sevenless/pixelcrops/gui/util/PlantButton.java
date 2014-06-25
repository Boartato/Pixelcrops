package ca.sevenless.pixelcrops.gui.util;

import java.awt.image.BufferedImage;

import ca.sevenless.pixelcrops.util.BoxCoord;
import ca.sevenless.pixelcrops.world.farm.Farm;
import ca.sevenless.pixelcrops.world.inventory.Inventory;

/**
 * Created by Anoop on 11/06/2014.
 * @param <T>
 */
public class PlantButton<T> extends Button {

    Farm farm;
    boolean used;
    int x, y;
    public PlantButton(ButtonManager parent, BoxCoord _box, BufferedImage _graphic, Farm farm, int x, int y)
    {
        super(parent, _box, _graphic);
        this.farm = farm;
        used = false;
        this.x = x;
        this.y = y;
       

    }
    public void usedTrue()
    {
        used = true;
    }
   
    public void usedFalse()
    {
        used = false;
    }
    
    public void clickCode(){
    	if(used == true)
    	{
    		farm.waterField(x, y);
    	}
    	else
    	{
    		farm.sowSeed(x, y, farm.getSeed());
    	}
    	
    }
}
