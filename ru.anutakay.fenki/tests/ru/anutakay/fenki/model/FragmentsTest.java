package ru.anutakay.fenki.model;

import static org.junit.Assert.*;

import org.junit.Test;

import ru.anutakay.fenki.model.size.Size;
import ru.anutakay.fenki.model.thread.Thread;
import ru.anutakay.fenki.model.thread.ThreadPool;

public class FragmentsTest {
    
    @Test
    public void create_test() {
        Fragments fragments = new Fragments(new Size());
        assertNotNull(fragments);
    }
    
    @Test
    public void  create_with_size() {
        Size size = new Size(5, 6);
        Fragments fragments = new Fragments(size);
        assertNotNull(fragments);
    }
    
    @Test 
    public void get_empty_test() {
        Size size = new Size(7, 2);
        Fragments fragments = new Fragments(size);
        ThreadIndex index = new ThreadIndex(1, 1);
        Thread actual = fragments.getThread(index);
        Thread expected = ThreadPool.createEmptyThread();
        assertEquals(expected, actual);
    }
    
    @Test 
    public void get_not_empty_test() {
        Size size = new Size(7, 2);
        Fragments fragments = new Fragments(size);
        ThreadIndex index = new ThreadIndex(1, 0);
        Thread actual = fragments.getThread(index);
        Thread expected = ThreadPool.createEmptyThread();
        assertNotEquals(expected, actual);
    }
    
    @Test (expected = MismatchedThreadException.class)
    public void set_get_test() {
        Size size = new Size(7, 2);
        Fragments fragments = new Fragments(size);
        ThreadIndex index = new ThreadIndex(1, 1);
        Thread thread = new ThreadPool().createThread();
        fragments.setThread(index, thread);
    }

}
