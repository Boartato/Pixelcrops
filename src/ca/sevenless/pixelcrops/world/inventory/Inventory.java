/**
 * 
 */
package ca.sevenless.pixelcrops.world.inventory;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A generic inventory class which allows an inventory to be created with x*y slots, and a list for holding overflowed
 * objects
 * 
 * @author Sevenless
 *
 */
public class Inventory<T> implements Serializable, InventoryInterface<T>{
	
	private static final long serialVersionUID = 1L;
	
	//The visible inventory the player will have a graphical representation of
	T[][] invSlots;
	//Items that were unable to be placed in the inventory and are waiting for slots to be shown to the player
	ArrayList<T> overflow;
	
	/**
	 * Creates a new inventory of size x*y and instantiates an overflow list
	 * @param x
	 * @param y
	 */
	@SuppressWarnings("unchecked")
	public Inventory(int x, int y){
		invSlots = (T[][]) new Object[x][y];
		overflow = new ArrayList<T>();
	}
	
	/**
	 * Removes the item at location x,y from the inventory. If there are items waiting in the overflow list,
	 * places them into the now empty slot
	 * @param x
	 * @param y
	 */
	public T removeItem(int x, int y){
		T tempItem = invSlots[x][y];
		invSlots[x][y] = null;
		if (!overflow.isEmpty())
			addItem(overflow.remove(0));
		return tempItem;
	}
	
	/**
	 * Adds an item to the first available slot in the inventory, if none available adds it to the overflow list
	 * @param newItem Item being added
	 */
	public void addItem(T newItem){
		for (int j = 0; j < invSlots.length; j++)
			for (int i = 0; i < invSlots[j].length; i++)
				if (invSlots[j][i] == null){
					invSlots[j][i] = newItem;
					return;
				}
		overflow.add(newItem);
	}
	
	/**
	 * Adds the given item to the specific inventory slot if it is empty and returns true. Otherwise returns false
	 * and does nothing.
	 * @param newItem
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean addItem(T newItem, int x, int y) {
		if (invSlots[x][y] == null){
			invSlots[x][y] = newItem;
			return true;
		}
		return false;
	}
	
	/**
	 * Returns a stored object for use without modifying the inventory
	 * @param x
	 * @param y
	 * @return
	 */
	public T peekItem(int x, int y){
		return invSlots[x][y];
	}
	
	/*
	 * Test Harness
	 */
	public static void main(String[] args){
		Integer x = 5;
		Integer y = 5;
		
		Inventory<Integer> test = new Inventory<Integer>(x,y);
		
		for (int j = 0; j < x; j++)
			for (int i = 0; i < y - 1; i++)
				test.addItem(new Integer(i));
		
		test.addItem(new Integer(5));
		test.addItem(new Integer(4));
		System.out.println(test.removeItem(1,1));
		test.addItem(new Integer(9), 1,1);
		test.addItem(new Integer(9), 4,4);
		System.out.println(test.addItem(new Integer(8),1,1));
		//test.removeItem(2,2);
		
		for (int j = 0; j < x; j++){
			System.out.println(" ");
			for (int i = 0; i < y; i++)
				System.out.print(" " + test.peekItem(j,i));
		}	
		
		
	}

	
}
