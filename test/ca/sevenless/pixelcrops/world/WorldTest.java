/**
 * 
 */
package ca.sevenless.pixelcrops.world;

/**
 * @author Sevenless
 *
 */
public class WorldTest extends World{

	/**
	 * @param farmX
	 * @param farmY
	 * @param invX
	 * @param invY
	 * @param turnTime
	 */
	public WorldTest(int farmX, int farmY, int invX, int invY, int turnTime) {
		super(farmX, farmY, invX, invY, turnTime);
	}
	
	public static void main(String[] args){
		
		WorldTest test = new WorldTest(1,1,5,5,1000);
		test.startGame();
		//*
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//*/
		int turns = test.playerFarm.getTurnsPassed();
		
		test.pauseGame();
		System.out.println(turns);
	}

}
