package ru.anutakay.fenki.model;

public class ThreadFragment {
	
	//public static final ThreadID NONE_THREAD_ID = ThreadID.emptyID();

	private ThreadID threadID = ThreadID.emptyID();
	
	private Horizontal topDirection = Horizontal.NONE;
	
	public ThreadFragment() {
	}
	
	public ThreadFragment(final ThreadID threadID, final Horizontal topDirection) {
		this.threadID = threadID;
		this.topDirection = topDirection;
	}
	
	public void setThreadID(final ThreadID threadID) {	
		this.threadID = threadID;
	}
	
	public ThreadID getThreadID() {
		return threadID;
	}
	
	public void setTopDirection(final Horizontal topDirection) {
		this.topDirection = topDirection;
	}
	
	public Horizontal getTopDirection() {
		return this.topDirection;
	}
	
}
