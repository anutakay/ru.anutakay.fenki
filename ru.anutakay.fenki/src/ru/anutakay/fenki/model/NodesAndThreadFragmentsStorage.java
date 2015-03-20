package ru.anutakay.fenki.model;

import java.util.Random;

import ru.anutakay.fenki.model.Const.HDirection;
import ru.anutakay.fenki.model.Const.VDirection;

public class NodesAndThreadFragmentsStorage extends NodeStorage<Node> 
{
	private ThreadFragmentsStorage mThreadStorage;
	Random r;
		
	public NodesAndThreadFragmentsStorage(NodeStoreDimensions dimensions) 
	{
		super(dimensions);
		mThreadStorage = new ThreadFragmentsStorage(mDimensions);
		
	}

	@Override
	protected Node createRandomObject(int i, int j) 
	{
		r = new Random();
		return new Node(Node.Direction.values()[Math.abs(r.nextInt())%4+1]);
	}
	
	public ThreadFragment getThread(ThreadIndex ti) {
		return mThreadStorage.getThread(ti);
	}
	
	public int getCorner(int j, HDirection left_right){
		if (left_right == HDirection.LEFT) {
			return getPrevThreadForNode(new NodeIndex(-1, j), HDirection.RIGHT);
		} else {
			int n = mDimensions.numberOfNodeInColumn(j);
			return getPrevThreadForNode(new NodeIndex(n, j), HDirection.LEFT);
		}
	}
	
	public int getPrevThreadForNode(NodeIndex ni, HDirection right)
	{
		return mThreadStorage.getNeighbor(ni, right, 
				VDirection.PREV).getThreadID();
	}
		
	public void setNextThreadForNode(NodeIndex ni, HDirection right, int i)
	{
		mThreadStorage.setNeighbor(ni, right,
				VDirection.NEXT, i);
	}
		
	public void build_node(NodeIndex ni) 
	{
		Node node = getNode(ni);
		HDirection enter = node.getBegin();
		HDirection exit = node.getEnd();
		int leftColor = getPrevThreadForNode(ni, HDirection.LEFT);
		int rightColor = getPrevThreadForNode(ni, HDirection.RIGHT);
		if(enter == HDirection.NONE){
			setNextThreadForNode(ni, HDirection.RIGHT, -1);
			setNextThreadForNode(ni, HDirection.LEFT, -1);
			return;
		}else if(enter == HDirection.RIGHT){
			node.setFirstThreadID(rightColor);
			node.setSecondThreadID(leftColor);
		}else{
			node.setFirstThreadID(leftColor);
			node.setSecondThreadID(rightColor);
		}
		if(exit == HDirection.RIGHT){
			setNextThreadForNode(ni, HDirection.RIGHT, node.getFirstThreadID());
			setNextThreadForNode(ni, HDirection.LEFT, node.getSecondThreadID());
		}else{
			setNextThreadForNode(ni, HDirection.RIGHT, node.getSecondThreadID());
			setNextThreadForNode(ni, HDirection.LEFT, node.getFirstThreadID());
		}
	}
	
	public void build_corner(int j, HDirection left_right) {
		if (mDimensions.isShort(j, HDirection.LEFT) && left_right == HDirection.LEFT) {
			NodeIndex index = new NodeIndex(-1, j);
			int value = getPrevThreadForNode(index, HDirection.RIGHT);
			setNextThreadForNode(index, HDirection.RIGHT, value);
		}
		if ((left_right == HDirection.RIGHT && mDimensions .isShort(j, HDirection.RIGHT))) {
			NodeIndex index = new NodeIndex(mDimensions.numberOfNodeInColumn(j), j);
			int value = getPrevThreadForNode(index, HDirection.LEFT);
			setNextThreadForNode(index, HDirection.LEFT, value);
		}
	}
}