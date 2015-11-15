package ru.anutakay.fenki.model;

import java.util.ArrayList;

import ru.anutakay.fenki.model.size.Size;

public class Threads {

    private Size size;

    private ArrayList<ArrayList<Integer>> storage;
    
    private ThreadPool pool = new ThreadPool();

    public Threads(final Size size) {
        this.size = size;
        storage = createStorage();
    }

    private ArrayList<ArrayList<Integer>> createStorage() {
        ArrayList<ArrayList<Integer>> array = new ArrayList<>();
        for (int i = 0; i < size.threads(); i++) {
            array.add(createThreadArray(i));
        }
        return array;
    }

    private ArrayList<Integer> createThreadArray(final int i) {
        ArrayList<Integer> n = new ArrayList<Integer>();
        
        for (int j = 0; j <= size.columns(); j++) {
            if (j == 0) {
                n.add(pool.createThread());
            } else {
                n.add(ThreadPool.createEmptyThread());
            }
        }
        return n;
    }

    public Integer getThread(final ThreadIndex index) {
        return storage.get(index.i).get(index.j);
    }
    
    public void setThread(final ThreadIndex index, final Integer thread) {
        if(!pool.contains(thread)) {
            throw new MismatchedThreadException();
        }
        storage.get(index.i).set(index.j, thread);
    }

}
