package ru.anutakay.fenki.model;
import java.util.ArrayList;

import ru.anutakay.fenki.model.Const.HDirection;
import ru.anutakay.fenki.model.Const.VDirection;



public class ThreadStorage {

	private NodeStoreDimensions mDimensions;
	
	private ArrayList<ArrayList<Thread>> threads;

	public ThreadStorage(final NodeStoreDimensions dimensions) {
		mDimensions = dimensions;

		ArrayList<Thread> n;
		threads = new ArrayList<ArrayList<Thread>>();
		for (int i = 0; i < mDimensions.getThreadNumber(); i++) {
			n = new ArrayList<Thread>();
			for (int j = 0; j < mDimensions.getColumnNumber() + 1; j++) {
				if (j == 0) {
					n.add(new Thread(i));
				} else {
					n.add(new Thread(-1));
				}
			}
			threads.add(n);
		}
	}

	public Thread getThread(final ThreadIndex ti) {
		return threads.get(ti.i).get(ti.j);
	}

	public void setThread(final ThreadIndex ti, final Thread thread) {
		threads.get(ti.i).set(ti.j, thread);
	}

	

	public Thread getNeighborThreadForNode(
							final NodeIndex ni, 
							final HDirection left_right,
							final VDirection prev) {	
		ThreadIndex ti = neighborThreadIndexForNodeIndex(ni, left_right, prev);
		return getThread(ti);
	}

	public void setNeighbor(final NodeIndex ni, 
							final HDirection right, 
							final VDirection prev_next,
							final Thread value) {
		ThreadIndex ti = neighborThreadIndexForNodeIndex(ni, right, prev_next);
		setThread(ti, value);
	}
	
	ThreadIndex neighborThreadIndexForNodeIndex(
							final NodeIndex ni, 
							final HDirection right, 
							final VDirection prev) {
		int i = ni.i;
		int j = ni.j;
		int t = mDimensions.isShort(j, HDirection.LEFT) ? 1 : 0;
		i = i * 2 + t;
		if (prev == VDirection.NEXT) {
			j = j + 1;
		}
		if (right == HDirection.RIGHT) {
			i = i + 1;
		}
		return new ThreadIndex(i, j);
	}
}
