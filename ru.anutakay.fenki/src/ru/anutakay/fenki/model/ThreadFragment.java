package ru.anutakay.fenki.model;

public class ThreadFragment {
	
	public enum Direction { NONE, RIGHT, LEFT }

	private int threadID = -1;
	
	private Direction topDirection = Direction.NONE;
	
	public ThreadFragment() {
	}
	
	public ThreadFragment(final int threadID, final Direction topDirection) {
		this.threadID = threadID;
		this.topDirection = topDirection;
	}
	
	public void setThreadID(final int threadID) {	
		this.threadID = threadID;
	}
	
	public int getThreadID() {
		return threadID;
	}
	
	public void setTopDirection(final Direction topDirection) {
		this.topDirection = topDirection;
	}
	
	public Direction getTopDirection() {
		return this.topDirection;
	}
	
}
