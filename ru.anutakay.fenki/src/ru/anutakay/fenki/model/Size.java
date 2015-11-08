package ru.anutakay.fenki.model;

public class Size {

    private static final int MIN_NUM_OF_COLUMN = 1;

    private static final int MIN_NUM_OF_THREAD = 2;

    private boolean first = true;

    private int threads;

    private int columns;

    public Size() {
        this(MIN_NUM_OF_THREAD, MIN_NUM_OF_COLUMN);
    }

    public Size(final int threads, final int columns, final boolean first) {
        this(threads, columns);
        this.first = first;
    }

    public Size(final int threads, final int columns) {
        this.threads = threads;
        this.columns = columns;

        checkNums();
    }

    private void checkNums() {
        if (threads < MIN_NUM_OF_THREAD) {
            threads = MIN_NUM_OF_THREAD;
        }
        if (columns < MIN_NUM_OF_COLUMN) {
            columns = MIN_NUM_OF_COLUMN;
        }
    }

    public int threads() {
        return threads;
    }

    public int columns() {
        return columns;
    }

    public boolean first() {
        return first;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (first ? 1231 : 1237);
        result = prime * result + columns;
        result = prime * result + threads;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Size other = (Size) obj;
        if (first != other.first)
            return false;
        if (columns != other.columns)
            return false;
        if (threads != other.threads)
            return false;
        return true;
    }

}
