package ru.anutakay.fenki.model;

import java.util.ArrayList;

import ru.anutakay.fenki.model.size.Size;

public class Threads {

    private Size size;

    private ArrayList<ArrayList<ThreadFragment>> storage;

    public Threads(final Size dimensions) {
        this.size = dimensions;
        storage = createArray();
    }

    private ArrayList<ArrayList<ThreadFragment>> createArray() {
        ArrayList<ArrayList<ThreadFragment>> array 
                = new ArrayList<ArrayList<ThreadFragment>>();
        for (int i = 0; i < size.threads(); i++) {
            array.add(createThreadArray(i));
        }
        return array;
    }

    private ArrayList<ThreadFragment> createThreadArray(final int i) {
        ArrayList<ThreadFragment> n = new ArrayList<ThreadFragment>();
        for (int j = 0; j <= size.columns(); j++) {
            if (j == 0) {
                n.add(new ThreadFragment(new Thread(i), H.NONE));
            } else {
                n.add(new ThreadFragment());
            }
        }
        return n;
    }

    public ThreadFragment getThread(final ThreadIndex index) {
        return storage.get(index.i).get(index.j);
    }

}
