package ru.anutakay.fenki.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArrowTest {
    
    @Test
    public void create_test() {
        Arrow arrow = Arrow.NONE;
        assertNotEquals(null, arrow);
    }
    
    @Test
    public void arrow_none_test() {
        Arrow arrow = Arrow.NONE;
        assertEquals(H.NONE, arrow.getBegin());
        assertEquals(H.NONE, arrow.getEnd());
    }
    
    @Test
    public void arrow_right_direct() {
        Arrow arrow = Arrow.RIGHT_DIRECT;
        assertEquals(H.RIGHT, arrow.getBegin());
        assertEquals(H.LEFT, arrow.getEnd());
    }
    
    @Test
    public void arrow_right_back() {
        Arrow arrow = Arrow.RIGHT_BACK;
        assertEquals(H.RIGHT, arrow.getBegin());
        assertEquals(H.RIGHT, arrow.getEnd());
    }
    
    @Test
    public void arrow_left_direct() {
        Arrow arrow = Arrow.LEFT_DIRECT;
        assertEquals(H.LEFT, arrow.getBegin());
        assertEquals(H.RIGHT, arrow.getEnd());
    }
    
    @Test
    public void arrow_left_back() {
        Arrow arrow = Arrow.LEFT_BACK;
        assertEquals(H.LEFT, arrow.getBegin());
        assertEquals(H.LEFT, arrow.getEnd());
    }
    
    @Test
    public void eq_none_test() {
        Arrow arrow1 = Arrow.NONE;
        Arrow arrow2 = Arrow.NONE;
        assertEquals(arrow1, arrow2);
    }

}
