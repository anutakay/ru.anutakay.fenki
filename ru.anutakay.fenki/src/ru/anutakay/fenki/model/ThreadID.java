package ru.anutakay.fenki.model;

public class ThreadID {
    
    public ThreadID(int id) {
        this.id = id;
    }

    private int id;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
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
        ThreadID other = (ThreadID) obj;
        if (id != other.id)
            return false;
        return true;
    }

    public int getID() {
        // TODO Auto-generated method stub
        return id;
    }

    public static ThreadID emptyID() {
        // TODO Auto-generated method stub
        return new ThreadID(-1);
    }
    
}
