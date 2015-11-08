package ru.anutakay.fenki.model;

import ru.anutakay.fenki.model.thread.Thread;
import ru.anutakay.fenki.model.thread.ThreadFactory;

public class ThreadFragment {

    private Thread thread = ThreadFactory.createEmptyThread();

    private H top;

    public ThreadFragment(final Thread thread, final H top) {
        this.thread = thread;
        this.top = top;
    }

    public void setThread(final Thread thread) {
        this.thread = thread;
    }

    public Thread getThread() {
        return thread;
    }

    public void setTop(final H top) {
        this.top = top;
    }

    public H getTop() {
        return this.top;
    }

}
