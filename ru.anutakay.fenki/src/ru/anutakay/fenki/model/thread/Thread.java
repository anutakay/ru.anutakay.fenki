package ru.anutakay.fenki.model.thread;

public class Thread {
    
    private int id;
    
    Thread(int id) {
        this.id = id;
    }
    
    public int getID() {
        return id;
    } 

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
        Thread other = (Thread) obj;
        if (id != other.id)
            return false;
        return true;
    }  
}