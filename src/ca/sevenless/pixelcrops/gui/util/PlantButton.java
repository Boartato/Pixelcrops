package ca.sevenless.pixelcrops.gui.util;

import java.awt.image.BufferedImage;

import ca.sevenless.pixelcrops.util.BoxCoord;
import ca.sevenless.pixelcrops.world.inventory.Inventory;

/**
 * Created by Anoop on 11/06/2014.
 * @param <T>
 */
public class PlantButton<T> extends Button {

    Inventory<T> inventory;
    boolean used;
    int x, y;
    public PlantButton(ButtonManager parent, BoxCoord _box, BufferedImage _graphic, Inventory<T> inventory, int x, int y)
    {
        super(parent, _box, _graphic);
        this.inventory = inventory;
        used = false;
        this.x = x;
        this.y = y;

    }
}
