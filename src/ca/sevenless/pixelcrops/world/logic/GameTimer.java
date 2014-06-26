package ca.sevenless.pixelcrops.world.logic;

import java.util.*;

import ca.sevenless.pixelcrops.world.farm.Farm;
import ca.sevenless.pixelcrops.world.farm.FarmInterface;
/**
 * 
 * @author anooptoor 
 *
 */
public class GameTimer implements Runnable  {
	
	
	FarmInterface farm; 
	private volatile boolean complete;
	long sleepTime;
	Thread myThread = new Thread(this); 
	
	public GameTimer(long _sleepTime, FarmInterface _farm){	
		
		farm = _farm;
		complete = false ; 
		sleepTime = _sleepTime; 
		
	}
	
	
	/**
	 * starts GameTimer 
	 */
	public void startTimer()
	{
		complete = false; 
		myThread.start();
		
	}
	/**
	 * stops GameTimer
	 */
	public void stopTimer()
	{
		complete = true;
		myThread.interrupt();
		
	}


	/**runs the increment age, for every increment of sleepTime.
	 * 
	 */
	public void run() {
		
		try{
			
			while(complete != true) //viable way to stop thread due to iterative nature of .sleep 
			{
				farm.incrementAge();
				Thread.sleep(sleepTime);
			}
		
		} catch(InterruptedException e) {
			//Expected when game is paused
			System.out.println("Game paused, currentTimer shutdown");
		}
		
		
	}

	
	

}
