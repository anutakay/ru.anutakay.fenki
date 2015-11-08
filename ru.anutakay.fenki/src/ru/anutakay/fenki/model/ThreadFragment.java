package ru.anutakay.fenki.model;

public class ThreadFragment {

    private Thread thread = Thread.empty();

    private H top = H.NONE;

    public ThreadFragment() {}

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
