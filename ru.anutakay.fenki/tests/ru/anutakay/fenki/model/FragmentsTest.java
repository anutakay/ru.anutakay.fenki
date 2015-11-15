package ru.anutakay.fenki.model;

import static org.junit.Assert.*;

import org.junit.Test;

import ru.anutakay.fenki.model.size.Size;

public class FragmentsTest {
    
    @Test
    public void create_test() {
        Threads fragments = new Threads(new Size());
        assertNotNull(fragments);
    }
    
    @Test
    public void  create_with_size() {
        Size size = new Size(5, 6);
        Threads fragments = new Threads(size);
        assertNotNull(fragments);
    }
    
    @Test 
    public void get_empty_test() {
        Size size = new Size(7, 2);
        Threads fragments = new Threads(size);
        ThreadIndex index = new ThreadIndex(1, 1);
        Integer actual = fragments.getThread(index);
        Integer expected = ThreadPool.createEmptyThread();
        assertEquals(expected, actual);
    }
    
    @Test 
    public void get_not_empty_test() {
        Size size = new Size(7, 2);
        Threads fragments = new Threads(size);
        ThreadIndex index = new ThreadIndex(1, 0);
        Integer actual = fragments.getThread(index);
        Integer expected = ThreadPool.createEmptyThread();
        assertNotEquals(expected, actual);
    }
    
    @Test (expected = MismatchedThreadException.class)
    public void set_get_test() {
        Size size = new Size(7, 2);
        Threads fragments = new Threads(size);
        ThreadIndex index = new ThreadIndex(1, 1);
        Integer thread = new ThreadPool().createThread();
        fragments.setThread(index, thread);
    }

}
