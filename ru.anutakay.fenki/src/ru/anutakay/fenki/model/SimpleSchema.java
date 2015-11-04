package ru.anutakay.fenki.model;

import java.util.Random;

import ru.anutakay.fenki.view.CornerIndex;

public class SimpleSchema
{
	protected Size dimensions;
	private Nodes nodeStorage;
	private ThreadFragmentsStorage threadStorage;
	Random r;
		
	public SimpleSchema(final Size dimensions) {
		this.dimensions = dimensions;
		this.nodeStorage = new Nodes(this.dimensions);
		this.threadStorage = new ThreadFragmentsStorage(this.dimensions);
	}
	
	public Size getDimensions() {
		return dimensions;
	}
	
	public NodeImpl getNode(final NodeIndex nodeIndex) {
		return nodeStorage.getNode(nodeIndex);
	}
	
	public ThreadFragment getThreadFragment(final ThreadIndex threadIndex) {
		return threadStorage.getThread(threadIndex);
	}
	
	public NodeImpl getNode(final int i, final int j) {
		return getNode(new NodeIndex(j, i));
	}	
	
	public ThreadFragment getThreadFragment(final int i, final int j) {
		return getThreadFragment(new ThreadIndex(i, j));
	}
	
	public ThreadID getCorner(final int j, final Horizontal hDirection) {
		if (hDirection == Horizontal.LEFT) {
			return getPrevThreadForNode(new NodeIndex(-1, j), Horizontal.RIGHT);
		} else {
			int n = SchemaTemplate.numberOfNodeInColumn(this.dimensions, j);
			return getPrevThreadForNode(new NodeIndex(n, j), Horizontal.LEFT);
		}
	}
	
	public ThreadID getCorner(final CornerIndex cornerIndex) {
		return getCorner(cornerIndex.i, cornerIndex.hDirection);
	}
	
	public ThreadID getPrevThreadForNode(final NodeIndex nodeIndex, final Horizontal hDirection) {
		ThreadIndex threadIndex = 
				NodeThreadNeighborer.getNeighborThreadIndex(this.dimensions, 
															nodeIndex, 
															hDirection, 
															Vertical.PREV);
		ThreadFragment threadFragment =  getThreadFragment(threadIndex);
		return threadFragment.getThreadID();
	}
		
	public void setNextThreadForNode(final NodeIndex nodeIndex, 
										final Horizontal hDirection, final ThreadID threadID) {
		ThreadIndex threadIndex = 
				NodeThreadNeighborer.getNeighborThreadIndex(this.dimensions, 
															nodeIndex, 
															hDirection, 
															Vertical.NEXT);
		threadStorage.getThread(threadIndex).setTopDirection(reverseDirection(hDirection));
		threadStorage.getThread(threadIndex).setThreadID(threadID);
	}
	
	private Horizontal reverseDirection(final Horizontal hDirection) {
		if (hDirection == Horizontal.RIGHT) {
			return Horizontal.LEFT;
		} else if (hDirection == Horizontal.LEFT) {
			return Horizontal.RIGHT;
		}
		return Horizontal.NONE;
	}
}