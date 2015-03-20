package ru.anutakay.fenki.model;

public class ThreadFragment {

	private int threadID;
	
	public ThreadFragment(){
		this(-1);
	}
	
	public ThreadFragment(int threadID) {
		this.threadID = threadID;
	}
	
	public int getIndex() {
		return threadID;
	}
	
}
