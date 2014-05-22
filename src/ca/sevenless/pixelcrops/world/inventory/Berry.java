/**
 * 
 */
package ca.sevenless.pixelcrops.world.inventory;

import java.awt.Color;
import java.io.Serializable;

/**
 * @author Sevenless
 *
 */
public class Berry implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//RGB values of the berry
	private int red;
	private int blue;
	private int green;

	//Flag for whether or not this berry is a seed
	private boolean isSeed;
	//Maximum deviation higher or lower that a berry colour value can give to its seed.
	private int maxSeedVarience = 50;
	
	public Berry(int r, int b, int g){
		this.red = r;
		this.blue = b;
		this.green = g;
	}
	
	/**
	 * Converts this berry into a seed if it is not already by randomizing the colour values and setting the isSeed flag
	 */
	public void makeSeed(){
		
		if (!isSeed){
			red = recombineColour(red);
			blue = recombineColour(blue);
			green = recombineColour(green);
			
			red = limitColour(red);
			blue = limitColour(blue);
			green = limitColour(green);
			
			isSeed = true;
		}
		
	}
	
	/**
	 * Algorithm that decides the pattern with which colours change during the berry->seed process
	 * @param colourValue
	 * @return
	 */
	private int recombineColour(int colourValue){
		
		colourValue += (int) (Math.random()*maxSeedVarience*2 - maxSeedVarience);
		
		return colourValue;
		
	}

	/**
	 * Checks that the colour values are within the bounding ranges, of not it rounds them appropriately
	 * @param colourValue
	 * @return
	 */
	private int limitColour(int colourValue){
		
		if (colourValue > 255)
			colourValue = 255;
		else if (colourValue < 0)
			colourValue = 0;
		
		return colourValue;
	}
	
	/**
	 * @return the red
	 */
	public int getRed() {
		return red;
	}

	/**
	 * @return the blue
	 */
	public int getBlue() {
		return blue;
	}

	/**
	 * @return the green
	 */
	public int getGreen() {
		return green;
	}

	/**
	 * @return the isSeed
	 */
	public boolean isSeed() {
		return isSeed;
	}

	/**
	 * Prints the RGB
	 */
	private void testToString(){
		System.out.println("r" + red +" g" + green + " b" + blue);
	}
	
	/**
	 * Creates and returns a color object out of the RGB values of this berry/seed
	 * @return
	 */
	public Color getColor(){
		return new Color(red,green,blue);
	}
	
	/*
	 * Test harness
	 */
	public static void main(String[] args){
		Berry test = new Berry(150,150,150);
		int counter = 0;
		while (test.getRed() > 0){
			int plantRed = test.getRed();
			test.makeSeed();
			test.testToString();
			if (test.getRed() > plantRed){
				test.red = plantRed;
				System.out.print("Retry! ");
			}
			else
				System.out.println("Success!");
			test.isSeed = false;
			counter +=1;
		}
		System.out.println("After " + counter + " generations we have a 0 red plant");
	}
}
