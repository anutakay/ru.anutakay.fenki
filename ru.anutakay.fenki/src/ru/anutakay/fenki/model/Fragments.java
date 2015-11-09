package ru.anutakay.fenki.model;

import java.util.ArrayList;

import ru.anutakay.fenki.model.size.Size;
import ru.anutakay.fenki.model.thread.ThreadFactory;

public class Fragments {

    private Size size;

    private ArrayList<ArrayList<ThreadFragment>> storage;
    
    private ThreadFactory factory = new ThreadFactory();

    public Fragments(final Size size) {
        this.size = size;
        storage = createStorage();
    }

    private ArrayList<ArrayList<ThreadFragment>> createStorage() {
        ArrayList<ArrayList<ThreadFragment>> array = new ArrayList<>();
        for (int i = 0; i < size.threads(); i++) {
            array.add(createThreadArray(i));
        }
        return array;
    }

    private ArrayList<ThreadFragment> createThreadArray(final int i) {
        ArrayList<ThreadFragment> n = new ArrayList<ThreadFragment>();
        
        for (int j = 0; j <= size.columns(); j++) {
            if (j == 0) {
                n.add(new ThreadFragment(factory.createThread(), H.NONE));
            } else {
                n.add(new ThreadFragment(ThreadFactory.createEmptyThread(), H.NONE));
            }
        }
        return n;
    }

    public ThreadFragment getThread(final ThreadIndex index) {
        return storage.get(index.i).get(index.j);
    }

}
