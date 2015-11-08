package ru.anutakay.fenki.model;

import java.util.Random;

import ru.anutakay.fenki.view.CornerIndex;

public class SimpleSchema
{
	protected Size dimensions;
	private NodesImpl nodeStorage;
	private ThreadFragmentsStorage threadStorage;
	Random r;
		
	public SimpleSchema(final Size dimensions) {
	    NodeFactory factory = new NodeFactory();
		this.dimensions = dimensions;
		this.nodeStorage = new NodesImpl(this.dimensions, factory);
		this.threadStorage = new ThreadFragmentsStorage(this.dimensions);
	}
	
	public Size getDimensions() {
		return dimensions;
	}
	
	public Node getNode(final NodeIndex nodeIndex) {
		return nodeStorage.getNode(nodeIndex);
	}
	
	public ThreadFragment getThreadFragment(final ThreadIndex threadIndex) {
		return threadStorage.getThread(threadIndex);
	}
	
	public Node getNode(final int i, final int j) {
		return getNode(new NodeIndex(j, i));
	}	
	
	public ThreadFragment getThreadFragment(final int i, final int j) {
		return getThreadFragment(new ThreadIndex(i, j));
	}
	
	public Thread getCorner(final int j, final H hDirection) {
		if (hDirection == H.LEFT) {
			return getPrevThreadForNode(new NodeIndex(-1, j), H.RIGHT);
		} else {
			int n = new ColumnTemplate(j, this.dimensions).lenght( );
			return getPrevThreadForNode(new NodeIndex(n, j), H.LEFT);
		}
	}
	
	public Thread getCorner(final CornerIndex cornerIndex) {
		return getCorner(cornerIndex.i, cornerIndex.hDirection);
	}
	
	public Thread getPrevThreadForNode(final NodeIndex nodeIndex, final H hDirection) {
		ThreadIndex threadIndex = 
				NodeThreadNeighborer.getNeighborThreadIndex(this.dimensions, 
															nodeIndex, 
															hDirection, 
															V.PREV);
		ThreadFragment threadFragment =  getThreadFragment(threadIndex);
		return threadFragment.getThreadID();
	}
		
	public void setNextThreadForNode(final NodeIndex nodeIndex, 
										final H hDirection, final Thread threadID) {
		ThreadIndex threadIndex = 
				NodeThreadNeighborer.getNeighborThreadIndex(this.dimensions, 
															nodeIndex, 
															hDirection, 
															V.NEXT);
		threadStorage.getThread(threadIndex).setTopDirection(reverseDirection(hDirection));
		threadStorage.getThread(threadIndex).setThreadID(threadID);
	}
	
	private H reverseDirection(final H hDirection) {
		if (hDirection == H.RIGHT) {
			return H.LEFT;
		} else if (hDirection == H.LEFT) {
			return H.RIGHT;
		}
		return H.NONE;
	}
}