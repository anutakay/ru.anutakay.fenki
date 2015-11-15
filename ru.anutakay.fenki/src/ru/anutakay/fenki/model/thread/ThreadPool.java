package ru.anutakay.fenki.model.thread;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class ThreadPool {
    
    Random r = new Random();
    
    Set<Integer> threads = new HashSet<Integer>();
    
    public static Integer createEmptyThread() {
        return -1;
    }
    
    public Integer createThread() {
        int i;
        Integer result = null;
        do {
            i = r.nextInt();
            result = new Integer(i);
        } while(!threads.add(result));
        return result;
    }
    
    public boolean contains(Integer thread) {
        return threads.contains(thread);
    }

}
