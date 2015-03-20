package ru.anutakay.fenki.model;

import ru.anutakay.fenki.model.Const.HDirection;

public class Schema 
{
	private NodesAndThreadFragmentsStorage mNodeStorage;
	private NodeStoreDimensions mDimensions;

	public Schema() 
	{
		this(2, 1, true);
	}

	public Schema(int thread_num, int column_num, boolean first) 
	{
		mDimensions = (new NodeStoreDimensions(thread_num, column_num, first));
		mNodeStorage = new NodesAndThreadFragmentsStorage(mDimensions);	
	}

	public void build() {
		for (int j = 0; j < mDimensions.getColumnNumber(); j++) {
			mNodeStorage.build_corner(j, HDirection.LEFT);
			for (int i = 0; i < mDimensions.numberOfNodeInColumn(j); i++) {
				mNodeStorage.build_node(new NodeIndex(i, j));
			}
			mNodeStorage.build_corner(j, HDirection.RIGHT);
		}
	}
	
	public NodeStoreDimensions getDimensions(){
		return mDimensions;
	}
	
	public Node node(int i, int j) {
		return mNodeStorage.getNode(new NodeIndex(j, i));
	}
	
	public ThreadFragment thread(int i, int j) {
		return mNodeStorage.getThread(new ThreadIndex(i,j));
	}
	
	/*public int corner(int i, int j) {
		int left_right = Const.RIGHT;
		if(i == -1){
			left_right = Const.LEFT;
		}
		return mNodeStorage.getCorner(j, left_right);
	}*/
	
	public int corner(int j, HDirection left_right){
		return mNodeStorage.getCorner(j, left_right);
	}

}