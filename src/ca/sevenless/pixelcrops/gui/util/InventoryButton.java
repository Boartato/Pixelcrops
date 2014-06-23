package ca.sevenless.pixelcrops.gui.util;

import ca.sevenless.pixelcrops.gui.Holder;
import ca.sevenless.pixelcrops.util.BoxCoord;
import ca.sevenless.pixelcrops.world.inventory.Inventory;

import java.awt.image.BufferedImage;

/**
 * @param <T>
 *
 */


public class InventoryButton<T> extends Button {

    Inventory<T> inventory;
    
    boolean used;
    int x, y;
    T item;
    public InventoryButton(ButtonManager parent, BoxCoord _box, BufferedImage _graphic, Inventory<T> inventory, int x, int y)
    {
        super(parent, _box, _graphic);
        this.inventory = inventory;
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
    @SuppressWarnings("unchecked")
	public void clickCode() {
        if(used == true)
        {
        	item = inventory.removeItem(x, y);
        	this.returnItem(item);
           
        }
        else
        {
        	
        	inventory.addItem(item);
        
        }

    }
    public T returnItem( T item){
		return item;
    	
    }
}
