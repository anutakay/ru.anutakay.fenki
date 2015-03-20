package ru.anutakay.fenki.model;

import java.util.Random;

import ru.anutakay.fenki.model.Const.HDirection;
import ru.anutakay.fenki.model.Const.VDirection;

public class NodesAndThreadFragmentsStorage extends NodeStorage
{
	private ThreadFragmentsStorage mThreadStorage;
	Random r;
		
	public NodesAndThreadFragmentsStorage(NodeStoreDimensions dimensions) 
	{
		super(dimensions);
		mThreadStorage = new ThreadFragmentsStorage(this.dimensions);
	}

	/*@Override
	protected Node createRandomObject(final int i, final int j) 
	{
		r = new Random();
		return new Node(Node.Direction.values()[Math.abs(r.nextInt())%4+1]);
	}*/
	
	public ThreadFragment getThread(final ThreadIndex threadIndex) {
		return mThreadStorage.getThread(threadIndex);
	}
	
	public int getCorner(final int j, final HDirection hDirection){
		if (hDirection == HDirection.LEFT) {
			return getPrevThreadForNode(new NodeIndex(-1, j), HDirection.RIGHT);
		} else {
			int n = this.dimensions.numberOfNodeInColumn(j);
			return getPrevThreadForNode(new NodeIndex(n, j), HDirection.LEFT);
		}
	}
	
	public int getPrevThreadForNode(final NodeIndex nodeIndex, final HDirection hDirection)
	{
		return mThreadStorage.getNeighbor(nodeIndex, hDirection, 
				VDirection.PREV).getThreadID();
	}
		
	public void setNextThreadForNode(final NodeIndex nodeIndex, 
										final HDirection hDirection, final int i)
	{
		mThreadStorage.setNeighbor(nodeIndex, hDirection,
				VDirection.NEXT, i);
	}
		
	public void build_node(final NodeIndex nodeIndex) 
	{
		Node node = getNode(nodeIndex);

		node.setLeftThreadID(getPrevThreadForNode(nodeIndex, HDirection.LEFT));
		node.setRightThreadID(getPrevThreadForNode(nodeIndex, HDirection.RIGHT));
	
		setNextThreadForNode(nodeIndex, HDirection.RIGHT, node.getBottomThreadID(HDirection.RIGHT));
		setNextThreadForNode(nodeIndex, HDirection.LEFT, node.getBottomThreadID(HDirection.LEFT));
		
	}
	
	public void build_corner(final int j, final HDirection hDirection) {
		if (this.dimensions.isShort(j, HDirection.LEFT) && hDirection == HDirection.LEFT) {
			NodeIndex index = new NodeIndex(-1, j);
			int value = getPrevThreadForNode(index, HDirection.RIGHT);
			setNextThreadForNode(index, HDirection.RIGHT, value);
		}
		if ((hDirection == HDirection.RIGHT && this.dimensions .isShort(j, HDirection.RIGHT))) {
			NodeIndex index = new NodeIndex(this.dimensions.numberOfNodeInColumn(j), j);
			int value = getPrevThreadForNode(index, HDirection.LEFT);
			setNextThreadForNode(index, HDirection.LEFT, value);
		}
	}
}