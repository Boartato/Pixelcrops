package ca.sevenless.pixelcrops.gui;

import ca.sevenless.pixelcrops.gui.util.Button;
import ca.sevenless.pixelcrops.gui.util.ButtonManager;
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
  
    /*
     * sets the used attribute to true, represents the inventory slot being used 
     */
    public void usedTrue()
    {
        used = true;
    }
    /*
     * sets used attribute to false, represents the inventory slot being empty
     */
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
    /*
     * returns item in slot 
     */
    public T returnItem( T item){
		return item;
    	
    }
}
