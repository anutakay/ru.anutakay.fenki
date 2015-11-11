package ru.anutakay.fenki.model;

import static org.junit.Assert.*;

import org.junit.Test;

import ru.anutakay.fenki.model.size.Size;

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

}
