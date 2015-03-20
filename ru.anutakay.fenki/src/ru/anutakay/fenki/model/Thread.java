package ru.anutakay.fenki.model;

public class Thread {

	private int index;
	
	public Thread(){
		this(-1);
	}
	
	public Thread(int index) {
		this.index = index;
	}
	
	public int getIndex() {
		return index;
	}
	
}
