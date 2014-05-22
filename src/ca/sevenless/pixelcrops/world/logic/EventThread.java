/**
 * 
 */
package ca.sevenless.pixelcrops.world.logic;

/**
 * @author Sevenless
 *
 */
public abstract class EventThread implements Runnable {
	
	//Amount of time in milliseconds to wait before running another event cycle
	int timeIncrement;
	
	//Thread object representing this EventThread, used for graceful shutdown
	Thread myThread;
	//Flag for shutdown events and graceful thread termination
	boolean shuttingdown;
	
	/**
	 * Creates and starts an EventThread
	 * 
	 * @param timeIncrement
	 */
	public EventThread(int timeIncrement){
		this.timeIncrement = timeIncrement;
		shuttingdown = false;
		
		myThread = new Thread(this);
		myThread.start();
	}

	/**
	 * Sleeps for the desired timeIncrement, then executes the event task
	 */
	@Override
	public void run() {
		
		if (!shuttingdown){
			
			eventTask();
			
			try {
				Thread.sleep(timeIncrement);
			} catch (InterruptedException e) {
				//Expected in cases of shutdown, no other handling required
			}
			
		}
	}	
	
	/**
	 * Terminates this thread gracefully for shutdown events
	 */
	public void shutdown(){
		shuttingdown = true;
		myThread.interrupt();
	}
	
	/**
	 * Runs the code required of this particular implementation of EventThread
	 */
	protected abstract void eventTask();

}
