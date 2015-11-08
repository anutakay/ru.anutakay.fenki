package ru.anutakay.fenki.model;

import java.util.ArrayList;

public class ThreadFragmentsStorage {

	private Size dimensions;
	
	private ArrayList<ArrayList<ThreadFragment>> threads;

	public ThreadFragmentsStorage(final Size dimensions) {
		this.dimensions = dimensions;
		threads = createArray();
	}
	
	private ArrayList<ArrayList<ThreadFragment>> createArray() {
		ArrayList<ArrayList<ThreadFragment>> array = new ArrayList<ArrayList<ThreadFragment>>();
		for (int i = 0; i < dimensions.threads(); i++) {
			array.add(createThreadArray(i));
		}
		return array;
	}

	private ArrayList<ThreadFragment> createThreadArray(final int i) {
		ArrayList<ThreadFragment> n = new ArrayList<ThreadFragment>();
		for (int j = 0; j < dimensions.columns() + 1; j++) {
			if (j == 0) {
				n.add(new ThreadFragment(new Thread(i), H.NONE));
			} else {
				n.add(new ThreadFragment());
			}
		}
		return n;	
	}

	public ThreadFragment getThread(final ThreadIndex threadIndex) {
		return threads.get(threadIndex.i).get(threadIndex.j);
	}

}
