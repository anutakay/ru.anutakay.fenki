package ru.anutakay.fenki.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class NodeTest {

    @Test
    public void constructorTest() {
        NodeImpl node = new NodeImpl();
        assertEquals(Direction.NONE, node.getDirection());
        assertEquals(Horizontal.NONE, node.getBegin());
        assertEquals(Horizontal.NONE, node.getEnd());
        assertEquals(ThreadFragment.NONE_THREAD_ID, node.getFirstThreadID());
        assertEquals(ThreadFragment.NONE_THREAD_ID, node.getSecondThreadID());
        assertEquals(ThreadFragment.NONE_THREAD_ID, node.getBeginThreadID(Horizontal.LEFT));
        assertEquals(ThreadFragment.NONE_THREAD_ID, node.getBeginThreadID(Horizontal.RIGHT));
        assertEquals(ThreadFragment.NONE_THREAD_ID,
                node.getEndThreadID(Horizontal.LEFT));
        assertEquals(ThreadFragment.NONE_THREAD_ID,
                node.getEndThreadID(Horizontal.RIGHT));

    }

    @Test
    public void constructorNullTest() {
        Node nullNode = new NodeImpl(null);
        Node node = new NodeImpl();
        assertEquals(node, nullNode);
    }

    @Test
    public void constructorRightDirectTest() {
        // Стрелка свеху справа вниз налево(напрямую)
        NodeImpl node = new NodeImpl(Direction.RIGHT_DIRECT);
        assertEquals(Direction.RIGHT_DIRECT, node.getDirection());
        assertEquals(Horizontal.RIGHT, node.getBegin());
        assertEquals(Horizontal.LEFT, node.getEnd());
    }

    @Test
    public void constructorRightBackTest() {
        // Стрелка свеху справа вниз направо(с возвратом)
        NodeImpl node = new NodeImpl(Direction.RIGHT_BACK);
        assertEquals(Direction.RIGHT_BACK, node.getDirection());
        assertEquals(Horizontal.RIGHT, node.getBegin());
        assertEquals(Horizontal.RIGHT, node.getEnd());
    }

    @Test
    public void constructorLeftDirectTest() {
        // Стрелка свеху слева вниз направо(напрямую)
        NodeImpl node = new NodeImpl(Direction.LEFT_DIRECT);
        assertEquals(Direction.LEFT_DIRECT, node.getDirection());
        assertEquals(Horizontal.LEFT, node.getBegin());
        assertEquals(Horizontal.RIGHT, node.getEnd());
    }

    @Test
    public void constructorLeftBackTest() {
        // Стрелка свеху слева вниз налево(с возвратом)
        NodeImpl node = new NodeImpl(Direction.LEFT_BACK);
        assertEquals(Direction.LEFT_BACK, node.getDirection());
        assertEquals(Horizontal.LEFT, node.getBegin());
        assertEquals(Horizontal.LEFT, node.getEnd());
    }

    @Test
    public void threadIDTest() {
        NodeImpl node = new NodeImpl();
        node.setLeftThreadID(0);
        node.setRightThreadID(1);
        assertEquals(ThreadFragment.NONE_THREAD_ID,
                node.getEndThreadID(Horizontal.LEFT));
        assertEquals(ThreadFragment.NONE_THREAD_ID,
                node.getEndThreadID(Horizontal.RIGHT));
        assertEquals(ThreadFragment.NONE_THREAD_ID, node.getFirstThreadID());
        assertEquals(ThreadFragment.NONE_THREAD_ID, node.getSecondThreadID());
    }

    @Test
    public void threadIDRightDirectTest() {
        NodeImpl node = new NodeImpl(Direction.RIGHT_DIRECT);
        node.setLeftThreadID(0);
        node.setRightThreadID(1);
        assertEquals(1, node.getEndThreadID(Horizontal.LEFT));
        assertEquals(0, node.getEndThreadID(Horizontal.RIGHT));
        assertEquals(1, node.getFirstThreadID());
        assertEquals(0, node.getSecondThreadID());
    }

    @Test
    public void threadIDLeftDirectTest() {
        NodeImpl node = new NodeImpl(Direction.LEFT_DIRECT);
        node.setLeftThreadID(0);
        node.setRightThreadID(1);
        assertEquals(1, node.getEndThreadID(Horizontal.LEFT));
        assertEquals(0, node.getEndThreadID(Horizontal.RIGHT));
        assertEquals(0, node.getFirstThreadID());
        assertEquals(1, node.getSecondThreadID());
    }

    @Test
    public void threadIDRightBackTest() {
        NodeImpl node = new NodeImpl(Direction.RIGHT_BACK);
        node.setLeftThreadID(0);
        node.setRightThreadID(1);
        assertEquals(0, node.getEndThreadID(Horizontal.LEFT));
        assertEquals(1, node.getEndThreadID(Horizontal.RIGHT));
        assertEquals(1, node.getFirstThreadID());
        assertEquals(0, node.getSecondThreadID());
    }

    @Test
    public void threadIDLeftBackTest() {
        NodeImpl node = new NodeImpl(Direction.LEFT_BACK);
        node.setLeftThreadID(0);
        node.setRightThreadID(1);
        assertEquals(0, node.getEndThreadID(Horizontal.LEFT));
        assertEquals(1, node.getEndThreadID(Horizontal.RIGHT));
        assertEquals(0, node.getFirstThreadID());
        assertEquals(1, node.getSecondThreadID());
    }

}
