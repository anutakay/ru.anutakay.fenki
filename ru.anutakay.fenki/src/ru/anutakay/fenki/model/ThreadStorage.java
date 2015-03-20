package ru.anutakay.fenki.model;
import java.util.ArrayList;

import ru.anutakay.fenki.model.Const.HDirection;
import ru.anutakay.fenki.model.Const.VDirection;



public class ThreadStorage {

	private NodeStoreDimensions mDimensions;
	
	private ArrayList<ArrayList<Thread>> threads;

	public ThreadStorage(final NodeStoreDimensions dimensions) {
		mDimensions = dimensions;
		threads = createArray();
	}
	
	private ArrayList<ArrayList<Thread>> createArray(){
		ArrayList<ArrayList<Thread>> array = new ArrayList<ArrayList<Thread>>();
		ArrayList<Thread> n;
		for (int i = 0; i < mDimensions.getThreadNumber(); i++) {
			n = new ArrayList<Thread>();
			for (int j = 0; j < mDimensions.getColumnNumber() + 1; j++) {
				if (j == 0) {
					n.add(new Thread(i));
				} else {
					n.add(new Thread());
				}
			}
			array.add(n);
		}
		return array;
	}

	public Thread getThread(final ThreadIndex ti) {
		return threads.get(ti.i).get(ti.j);
	}

	public void setThread(final ThreadIndex ti, final Thread thread) {
		threads.get(ti.i).set(ti.j, thread);
	}

	public Thread getNeighbor(
							final NodeIndex nodeIndex, 
							final HDirection hDirection,
							final VDirection vDirection) {	
		ThreadIndex threadIndex = getNeighborThreadIndex(nodeIndex, hDirection, vDirection);
		return getThread(threadIndex);
	}

	public void setNeighbor(final NodeIndex nodeIndex, 
							final HDirection hDirection, 
							final VDirection vDirection,
							final Thread thread) {
		ThreadIndex threadIndex = getNeighborThreadIndex(nodeIndex, hDirection, vDirection);
		setThread(threadIndex, thread);
	}
	
	private ThreadIndex getNeighborThreadIndex(
							final NodeIndex nodeIndex, 
							final HDirection hDirection, 
							final VDirection vDirection) {
		int i = nodeIndex.i;
		int j = nodeIndex.j;
		int t = mDimensions.isShort(j, HDirection.LEFT) ? 1 : 0;
		i = i * 2 + t;
		if (vDirection == VDirection.NEXT) {
			j = j + 1;
		}
		if (hDirection == HDirection.RIGHT) {
			i = i + 1;
		}
		return new ThreadIndex(i, j);
	}
}
