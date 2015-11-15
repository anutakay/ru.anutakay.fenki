package ru.anutakay.fenki.model;

import java.util.ArrayList;

import ru.anutakay.fenki.model.size.Size;
import ru.anutakay.fenki.model.thread.ThreadPool;
import ru.anutakay.fenki.model.thread.Thread;

public class Fragments {

    private Size size;

    private ArrayList<ArrayList<Thread>> storage;
    
    private ThreadPool pool = new ThreadPool();

    public Fragments(final Size size) {
        this.size = size;
        storage = createStorage();
    }

    private ArrayList<ArrayList<Thread>> createStorage() {
        ArrayList<ArrayList<Thread>> array = new ArrayList<>();
        for (int i = 0; i < size.threads(); i++) {
            array.add(createThreadArray(i));
        }
        return array;
    }

    private ArrayList<Thread> createThreadArray(final int i) {
        ArrayList<Thread> n = new ArrayList<Thread>();
        
        for (int j = 0; j <= size.columns(); j++) {
            if (j == 0) {
                n.add(pool.createThread());
            } else {
                n.add(ThreadPool.createEmptyThread());
            }
        }
        return n;
    }

    public Thread getThread(final ThreadIndex index) {
        return storage.get(index.i).get(index.j);
    }
    
    public void setThread(final ThreadIndex index, final Thread thread) {
        if(!pool.contains(thread)) {
            throw new MismatchedThreadException();
        }
        storage.get(index.i).set(index.j, thread);
    }

}
