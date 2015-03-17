package ru.anutakay.fenki.model;

import java.util.Random;

import ru.anutakay.fenki.model.Const.HDirection;
import ru.anutakay.fenki.model.Const.VDirection;

public class NodeAndThreadStorage extends NodeStorage<Node> 
{
	private ThreadStorage mThreadStorage;
	Random r;
		
	public NodeAndThreadStorage(NodeStoreDimensions dimensions) 
	{
		super(dimensions);
		mThreadStorage = new ThreadStorage(mDimensions);
		
	}

	@Override
	protected Node createRandomObject(int i, int j) 
	{
		r = new Random();
		return new Node(Node.Direction.values()[Math.abs(r.nextInt())%4+1]);
	}
	
	public int getThread(ThreadIndex ti) {
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
		return mThreadStorage.getNeighborThreadForNode(ni, right, 
				VDirection.PREV);
	}
		
	public void setNextThreadForNode(NodeIndex ni, HDirection right, int value)
	{
		mThreadStorage.setNeighbor(ni, right,
				VDirection.NEXT, value);
	}
		
	public void build_node(NodeIndex ni) 
	{
		Node node = getNode(ni);
		HDirection enter = node.getEnter();
		HDirection exit = node.getExit();
		int leftColor = getPrevThreadForNode(ni, HDirection.LEFT);
		int rightColor = getPrevThreadForNode(ni, HDirection.RIGHT);
		if(enter == HDirection.NONE){
			setNextThreadForNode(ni, HDirection.RIGHT, -1);
			setNextThreadForNode(ni, HDirection.LEFT, -1);
			return;
		}else if(enter == HDirection.RIGHT){
			node.setFirstColor(rightColor);
			node.setSecondColor(leftColor);
		}else{
			node.setFirstColor(leftColor);
			node.setSecondColor(rightColor);
		}
		if(exit == HDirection.RIGHT){
			setNextThreadForNode(ni, HDirection.RIGHT, node.getFirstColor());
			setNextThreadForNode(ni, HDirection.LEFT, node.getSecondColor());
		}else{
			setNextThreadForNode(ni, HDirection.RIGHT, node.getSecondColor());
			setNextThreadForNode(ni, HDirection.LEFT, node.getFirstColor());
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