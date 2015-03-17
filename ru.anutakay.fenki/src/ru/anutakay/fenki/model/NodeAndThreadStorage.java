package ru.anutakay.fenki.model;

import java.util.Random;

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
	
	public int getCorner(int j, int left_right){
		if (left_right == Const.LEFT) {
			return getPrevThreadForNode(new NodeIndex(-1, j), Const.RIGHT);
		} else {
			int n = mDimensions.numberOfNodeInColumn(j);
			return getPrevThreadForNode(new NodeIndex(n, j), Const.LEFT);
		}
	}
	
	public int getPrevThreadForNode(NodeIndex ni, int left_right)
	{
		return mThreadStorage.getNeighborThreadForNode(ni, left_right, 
				Const.PREV);
	}
		
	public void setNextThreadForNode(NodeIndex ni, int left_right, int value)
	{
		mThreadStorage.setNeighbor(ni, left_right,
				Const.NEXT, value);
	}
		
	public void build_node(NodeIndex ni) 
	{
		Node node = getNode(ni);
		int enter = node.getEnter();
		int exit = node.getExit();
		int leftColor = getPrevThreadForNode(ni, Const.LEFT);
		int rightColor = getPrevThreadForNode(ni, Const.RIGHT);
		if(enter == -1){
			setNextThreadForNode(ni, Const.RIGHT, -1);
			setNextThreadForNode(ni, Const.LEFT, -1);
			return;
		}else if(enter == Const.RIGHT){
			node.setColor(rightColor);
			node.setSecondColor(leftColor);
		}else{
			node.setColor(leftColor);
			node.setSecondColor(rightColor);
		}
		if(exit == Const.RIGHT){
			setNextThreadForNode(ni, Const.RIGHT, node.getColor());
			setNextThreadForNode(ni, Const.LEFT, node.getSecondColor());
		}else{
			setNextThreadForNode(ni, Const.RIGHT, node.getSecondColor());
			setNextThreadForNode(ni, Const.LEFT, node.getColor());
		}
	}
	
	public void build_corner(int j, int left_right) {
		if (mDimensions.isShort(j, Const.LEFT) && left_right == Const.LEFT) {
			NodeIndex index = new NodeIndex(-1, j);
			int value = getPrevThreadForNode(index, Const.RIGHT);
			setNextThreadForNode(index, Const.RIGHT, value);
		}
		if ((left_right == Const.RIGHT && mDimensions .isShort(j, Const.RIGHT))) {
			NodeIndex index = new NodeIndex(mDimensions.numberOfNodeInColumn(j), j);
			int value = getPrevThreadForNode(index, Const.LEFT);
			setNextThreadForNode(index, Const.LEFT, value);
		}
	}
}