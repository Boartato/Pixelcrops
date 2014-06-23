package ca.sevenless.pixelcrops.gui;

public class Holder<T> {
	T item;
	public Holder(T t){
		this.item = t;
		
	}
	public T getItem() {
		return item;
	}
	public void setItem(T item) {
		this.item = item;
	}
	
	
}
