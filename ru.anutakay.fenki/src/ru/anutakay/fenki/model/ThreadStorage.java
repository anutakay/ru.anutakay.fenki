package ru.anutakay.fenki.model;
import java.util.ArrayList;



public class ThreadStorage {

	private NodeStoreDimensions mDimensions;
	private ArrayList<ArrayList<Integer>> threads;

	public ThreadStorage(NodeStoreDimensions dimensions) {
		mDimensions = dimensions;

		ArrayList<Integer> n;
		threads = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < mDimensions.getThreadNumber(); i++) {
			n = new ArrayList<Integer>();
			for (int j = 0; j < mDimensions.getColumnNumber() + 1; j++) {
				if (j == 0) {
					n.add(i);
				} else {
					n.add(-1);
				}
			}
			threads.add(n);
		}
	}

	public int getThread(ThreadIndex ti) {
		return threads.get(ti.i).get(ti.j);
	}

	public void setThread(ThreadIndex ti, int value) {
		threads.get(ti.i).set(ti.j, value);
	}

	

	public int getNeighborThreadForNode(NodeIndex ni, int left_right,
			int prev_next) {	
		ThreadIndex ti = neighborThreadIndexForNodeIndex(ni, left_right, prev_next);
		return getThread(ti);
	}

	public void setNeighbor(NodeIndex ni, int left_right, int prev_next,
			int value) {
		ThreadIndex ti = neighborThreadIndexForNodeIndex(ni, left_right, prev_next);
		setThread(ti, value);
	}
	
	ThreadIndex neighborThreadIndexForNodeIndex(NodeIndex ni, int left_right, int prev_next)
	{
		int i = ni.i;
		int j = ni.j;
		int t = mDimensions.isShort(j, Const.LEFT) ? 1 : 0;
		i = i * 2 + t;
		if (prev_next == Const.NEXT) {
			j = j + 1;
		}
		if (left_right == Const.RIGHT) {
			i = i + 1;
		}
		return new ThreadIndex(i, j);
	}
}
