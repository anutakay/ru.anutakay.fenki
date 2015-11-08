package ru.anutakay.fenki.model.thread;


public class ThreadFactory {
    
    int current = 0;
    
    public static Thread createEmptyThread() {
        return new Thread(-1);
    }
    
    public Thread createThread() {
        Thread result = new Thread(current);
        current++;
        return result;
    }

}
