package ca.sevenless.pixelcrops.world.farm;
import java.util.*;

import ca.sevenless.pixelcrops.world.farm.Farm;
/**
 * 
 * @author anooptoor 
 *
 */
public class GameTimer implements Runnable  {
	
	
	Farm farm; 
	private volatile boolean complete;
	long sleepTime;
	Thread myThread = new Thread(this); 
	public GameTimer(long _sleepTime, Farm _farm){	
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
				System.out.println("You're older!"); 
				Thread.sleep(sleepTime);
			}
		} catch(InterruptedException e){System.out.println("GameTime Interrupt");}
		
		
	}

	
	

}
