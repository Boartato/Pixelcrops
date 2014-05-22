/**
 * 
 */
package ca.sevenless.pixelcrops.world.farm;

import java.awt.Color;
import java.io.Serializable;

import ca.sevenless.pixelcrops.world.inventory.Berry;

/**
 * @author Sevenless
 *
 */
public class Plant implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Berry this plant was grown from
	private Berry seed;
	
	private Color leafColor = Color.green;
	
	//Age of the plant
	private int age;
	
	//Whether or not a berry can be harvested from this plant
	private boolean harvestable;
	//Whether or not a plant is mature and able to produce berries if it is not thirsty
	private boolean mature;
	//Whether or not the plant needs to be watered. Prevents maturation and berry production if true
	boolean thirsty;
	
	//The value where (0->value) which is added onto the age each age increment to determine maturation
	private int maturationRandomMax;
	//The value of Age+Random(0->maturationRandomMax) required to mature this plant on any given age increment
	private int maturationThreshhold;
	
	//Chance (0->1.0) of a berry ripening on a mature plant per age increase
	private double productionChance;
	//Chance (0->1.0) if a plant becoming thirsty per age increase
	private double thirstyChance;
	
	/**
	 *	Creates a new plant out of the provided Berry object 
	 * @param seed
	 */
	public Plant(Berry seed) {
		//TODO decide if we want to use these values statically, or dynamically
		productionChance = 0.25;
		thirstyChance = 0.25;
		maturationThreshhold = 20;
		maturationRandomMax = 10;
		
		
		this.seed = seed;
		
		age = 0;
		mature = false;
		
		thirsty = true;
	}

	/**
	 * Sets thirsty false
	 */
	protected void water(){
		thirsty = false;
	}
	
	/**
	 * Increases the age of this plant. If immature, checks for maturation. If mature, checks for berry production
	 * Also checks if plant should become thirsty
	 */
	protected void incrementAge(){
		age +=1;
		
		if (!mature && !thirsty && age + Math.random()*maturationRandomMax >= maturationThreshhold){
			mature = true;
		}
		else if (Math.random() < productionChance && !thirsty && mature)
			harvestable = true;
		
		if (Math.random() < thirstyChance)
			thirsty = true;
	}
	
	/**
	 * Returns the colour object used for drawing the plant
	 * @return
	 */
	public Color getBerryColor(){
		return seed.getColor();
	}
	
	public Color getLeafColor(){
		return leafColor;
	}
	
	/**
	 * @return the red
	 */
	public int getRed() {
		return seed.getRed();
	}

	/**
	 * @return the green
	 */
	public int getGreen() {
		return seed.getGreen();
	}

	/**
	 * @return the blue
	 */
	public int getBlue() {
		return seed.getBlue();
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @return the harvestable
	 */
	public boolean isHarvestable() {
		return harvestable;
	}

	/**
	 * @return the mature
	 */
	public boolean isMature() {
		return mature;
	}

	/**
	 * @return the thirsty
	 */
	public boolean isThirsty() {
		return thirsty;
	}

	private void printStatus(){
		System.out.println(seed.getRed() +","+ seed.getGreen() +","+ seed.getBlue() + " Harv:"+harvestable+" Mat:"+mature+ " Thir:"+thirsty);
	}
	
	/*
	 * Test Harness
	 */
	
	public static void main(String[] args){
		Plant test = new Plant(new Berry(100,100,100));
		
		test.printStatus();		
		
		for (; !test.mature && !test.harvestable ;){
			test.water();
			test.incrementAge();
		}
		System.out.println(test.age);
		
	}
}
