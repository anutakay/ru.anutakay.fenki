package ru.anutakay.fenki.model.thread;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class ThreadPool {
    
    Random r = new Random();
    
    Set<Thread> threads = new HashSet<Thread>();
    
    public static Thread createEmptyThread() {
        return new Thread(-1);
    }
    
    public Thread createThread() {
        int i;
        Thread result = null;
        do {
            i = r.nextInt();
            result = new Thread(i);
        } while(!threads.add(result));
        return result;
    }
    
    public boolean contains(Thread thread) {
        return threads.contains(thread);
    }

}
