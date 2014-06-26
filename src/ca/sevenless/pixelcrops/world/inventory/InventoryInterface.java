/**
 * 
 */
package ca.sevenless.pixelcrops.world.inventory;

/**
 * @author Sevenless
 *
 */
public interface InventoryInterface<T> {
	
	/**
	 * Removes and returns the item at slot x,y
	 * @param x
	 * @param y
	 * @return
	 */
	public T removeItem(int x, int y);
	
	/**
	 * Returns the item at slot x,y without removing it
	 * @param x
	 * @param y
	 * @return see description
	 */
	public T peekItem(int x, int y);
	
	/**
	 * Adds the given item to the first available inv slot, or adds it to the overflow list that is invisible
	 * to the outside user
	 * @param newItem
	 */
	public void addItem(T newItem);
	
	/**
	 * Adds the given item to the specific inventory slot if it is empty and returns true. Otherwise returns false
	 * and does nothing.
	 * @param newItem
	 * @param x
	 * @param y
	 * @return false if the addition was unsuccessful
	 */
	public boolean addItem(T newItem, int x, int y);
	
}
