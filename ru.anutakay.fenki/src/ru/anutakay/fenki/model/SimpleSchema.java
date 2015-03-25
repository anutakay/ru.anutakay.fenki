package ru.anutakay.fenki.model;

import java.util.Random;

import ru.anutakay.fenki.model.Node.HDirection;
import ru.anutakay.fenki.model.Node.VDirection;
import ru.anutakay.fenki.model.ThreadFragment.Direction;
import ru.anutakay.fenki.view.CornerIndex;

public class SimpleSchema
{
	protected Dimensions dimensions;
	private NodeStorage nodeStorage;
	private ThreadFragmentsStorage threadStorage;
	Random r;
		
	public SimpleSchema(final Dimensions dimensions) {
		this.dimensions = dimensions;
		this.nodeStorage = new NodeStorage(this.dimensions);
		threadStorage = new ThreadFragmentsStorage(this.dimensions);
	}
	
	public Dimensions getDimensions() {
		return dimensions;
	}
	
	public Node getNode(NodeIndex nodeIndex) {
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
	
	public int getCorner(final int j, final HDirection hDirection) {
		if (hDirection == HDirection.LEFT) {
			return getPrevThreadForNode(new NodeIndex(-1, j), HDirection.RIGHT);
		} else {
			int n = SchemaTemplate.numberOfNodeInColumn(this.dimensions, j);
			return getPrevThreadForNode(new NodeIndex(n, j), HDirection.LEFT);
		}
	}
	
	public int getCorner(final CornerIndex cornerIndex) {
		return getCorner(cornerIndex.i, cornerIndex.hDirection);
	}
	
	public int getPrevThreadForNode(final NodeIndex nodeIndex, final HDirection hDirection) {
		ThreadIndex threadIndex = 
				NodeThreadNeighborer.getNeighborThreadIndex(this.dimensions, 
															nodeIndex, 
															hDirection, 
															VDirection.PREV);
		ThreadFragment threadFragment =  getThreadFragment(threadIndex);
		return threadFragment.getThreadID();
	}
		
	public void setNextThreadForNode(final NodeIndex nodeIndex, 
										final HDirection hDirection, final int threadID) {
		ThreadIndex threadIndex = 
				NodeThreadNeighborer.getNeighborThreadIndex(this.dimensions, 
															nodeIndex, 
															hDirection, 
															VDirection.NEXT);
		threadStorage.getThread(threadIndex).setTopDirection(reverseDirection(hDirection));
		threadStorage.getThread(threadIndex).setThreadID(threadID);
	}
	
	private Direction reverseDirection(final HDirection hDirection) {
		if (hDirection == HDirection.RIGHT) {
			return Direction.LEFT;
		} else if (hDirection == HDirection.LEFT) {
			return Direction.RIGHT;
		}
		return Direction.NONE;
	}
}