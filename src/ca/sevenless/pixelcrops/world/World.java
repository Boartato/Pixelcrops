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
import ca.sevenless.pixelcrops.world.logic.GameTimer;

/**
 * @author Sevenless
 *
 */
public class World implements Serializable{

	private static final long serialVersionUID = 1L;
	
	protected FarmInterface playerFarm;
	private InventoryInterface<Berry> playerInventory;
	private Tile[][] worldTileSet;
	private GameTimer currentTimer;
	private int turnTime;
	
	public World(int farmX, int farmY, int invX, int invY, int turnTime){
		
		playerFarm = new Farm(farmX, farmY);
		playerInventory = new Inventory<Berry>(invX, invY);
		worldTileSet = makeBlankTileSet(farmX,farmY);
		currentTimer = null;
		this.turnTime = turnTime;
		//TODO this should come from the GameInitialization code, not hardcoded
		playerFarm.sowSeed(0,0, new Berry(150,150,150));
		playerFarm.sowSeed(farmX-1, farmY-1, new Berry(150,150,150));
	}
	
	/**
	 * Initiates a new instance of gameTimer and attaches it to the appropriate farm object
	 */
	public void startGame(){
		if (currentTimer == null){
			currentTimer = new GameTimer(turnTime, playerFarm);
			currentTimer.startTimer();
		}
	}
	
	/**
	 * Allows current instance of gameTimer to finish gracefully, before setting currentTimer null
	 * in preperation for the next startGame() call
	 */
	public void pauseGame(){
		if (currentTimer != null){
			currentTimer.stopTimer();
			currentTimer = null;
		}
	}
	
	private Tile[][] makeBlankTileSet(int farmX, int farmY){
		
		Tile[][] newTileSet = new Tile[farmX][farmY];
		
		for (int j = 0; j < farmX; j++)
			for (int i = 0; i < farmY; i++)
				newTileSet[j][i] = new Tile(null);
		
		return newTileSet;
	}
	
	/**
	 * Retrieves the interface for communicating with the game logic
	 * @return Returns the FarmInterface for the playerFarm
	 */
	public FarmInterface getFarmInterface(){
		return playerFarm;
	}
	
	public void saveWorld(String path){
		//TODO
	}
	
}
