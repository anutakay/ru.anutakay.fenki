package ru.anutakay.fenki.model;

public class ThreadFragment {
	
	//public static final ThreadID NONE_THREAD_ID = ThreadID.emptyID();

	private Thread threadID = Thread.empty();
	
	private H topDirection = H.NONE;
	
	public ThreadFragment() {
	}
	
	public ThreadFragment(final Thread threadID, final H topDirection) {
		this.threadID = threadID;
		this.topDirection = topDirection;
	}
	
	public void setThreadID(final Thread threadID) {	
		this.threadID = threadID;
	}
	
	public Thread getThreadID() {
		return threadID;
	}
	
	public void setTopDirection(final H topDirection) {
		this.topDirection = topDirection;
	}
	
	public H getTopDirection() {
		return this.topDirection;
	}
	
}
