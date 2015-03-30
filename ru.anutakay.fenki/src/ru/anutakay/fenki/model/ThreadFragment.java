package ru.anutakay.fenki.model;

public class ThreadFragment {
	
	public static final int NONE_THREAD_ID = -1;

	public enum Direction { NONE, RIGHT, LEFT }

	private int threadID = NONE_THREAD_ID;
	
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
