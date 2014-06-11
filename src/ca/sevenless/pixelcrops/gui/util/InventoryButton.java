package ca.sevenless.pixelcrops.gui.util;

import ca.sevenless.pixelcrops.util.BoxCoord;
import ca.sevenless.pixelcrops.world.inventory.Inventory;

import java.awt.image.BufferedImage;

/**
 *
 */


public class InventoryButton<T> extends Button {

    Inventory<T> inventory;
    boolean used;
    int x, y;
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
    public void clickCode() {
        if(!used)
        {

            inventory.addItem(inventory.peekItem(20,20));//only works if Byref
            inventory.removeItem(20,20);
        }
        else
        {
            inventory.addItem(inventory.removeItem(20,20)); //only works if byref
        }

    }
}
