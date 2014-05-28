/**
 * 
 */
package ca.sevenless.pixelcrops.world;

import java.io.Serializable;

import ca.sevenless.pixelcrops.world.farm.Farm;
import ca.sevenless.pixelcrops.world.farm.FarmInterface;
import ca.sevenless.pixelcrops.world.inventory.Berry;
import ca.sevenless.pixelcrops.world.inventory.Inventory;
import ca.sevenless.pixelcrops.world.inventory.InventoryInterface;

/**
 * @author Sevenless
 *
 */
public class World implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private FarmInterface playerFarm;
	private InventoryInterface<Berry> playerInventory;
	private Tile[][] worldTileSet;
	
	
	public World(int farmX, int farmY, int invX, int invY){
		
		playerFarm = new Farm(farmX, farmY);
		playerInventory = new Inventory<Berry>(invX, invY);
		worldTileSet = makeBlankTileSet(farmX,farmY);
		
		playerFarm.sowSeed(0,0, new Berry(150,150,150));
		
	}
	
	private Tile[][] makeBlankTileSet(int farmX, int farmY){
		
		Tile[][] newTileSet = new Tile[farmX][farmY];
		
		for (int j = 0; j < farmX; j++)
			for (int i = 0; i < farmY; i++)
				newTileSet[j][i] = new Tile(null);
		
		return newTileSet;
	}
	
	public void saveWorld(String path){
		//TODO
	}
	
}
