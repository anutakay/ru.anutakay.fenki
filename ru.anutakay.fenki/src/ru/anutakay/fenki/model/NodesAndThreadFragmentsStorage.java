package ru.anutakay.fenki.model;

import java.util.Random;

import ru.anutakay.fenki.model.Const.HDirection;
import ru.anutakay.fenki.model.Const.VDirection;

public class NodesAndThreadFragmentsStorage
{
	protected NodeStoreDimensions dimensions;
	private NodeStorage nodeStorage;
	private ThreadFragmentsStorage threadStorage;
	Random r;
		
	public NodesAndThreadFragmentsStorage(final NodeStoreDimensions dimensions) 
	{
		this.dimensions = dimensions;
		this.nodeStorage = new NodeStorage(this.dimensions);
		threadStorage = new ThreadFragmentsStorage(this.dimensions);
	}
	
	public Node getNode(NodeIndex nodeIndex) {
		return nodeStorage.getNode(nodeIndex);
	}
	
	public ThreadFragment getThreadFragment(final ThreadIndex threadIndex) {
		return threadStorage.getThread(threadIndex);
	}
	
	public int getCorner(final int j, final HDirection hDirection){
		if (hDirection == HDirection.LEFT) {
			return getPrevThreadForNode(new NodeIndex(-1, j), HDirection.RIGHT);
		} else {
			int n = FieldTemplate.numberOfNodeInColumn(this.dimensions, j);
			return getPrevThreadForNode(new NodeIndex(n, j), HDirection.LEFT);
		}
	}
	
	public int getPrevThreadForNode(final NodeIndex nodeIndex, final HDirection hDirection)
	{
		ThreadIndex threadIndex = 
				NodeThreadNeighborer.getNeighborThreadIndex(this.dimensions, 
															nodeIndex, 
															hDirection, 
															VDirection.PREV);
		ThreadFragment threadFragment =  getThreadFragment(threadIndex);
		return threadFragment.getThreadID();
	}
		
	public void setNextThreadForNode(final NodeIndex nodeIndex, 
										final HDirection hDirection, final int threadID){
		ThreadIndex threadIndex = 
				NodeThreadNeighborer.getNeighborThreadIndex(this.dimensions, 
															nodeIndex, 
															hDirection, 
															VDirection.NEXT);
		threadStorage.setThread(threadIndex, threadID);
	}
}