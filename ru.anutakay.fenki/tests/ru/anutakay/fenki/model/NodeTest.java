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
        assertEquals(ThreadID.emptyID(), node.getFirstThreadID());
        assertEquals(ThreadID.emptyID(), node.getSecondThreadID());
        assertEquals(ThreadID.emptyID(), node.getBeginThreadID(Horizontal.LEFT));
        assertEquals(ThreadID.emptyID(),
                node.getBeginThreadID(Horizontal.RIGHT));
        assertEquals(ThreadID.emptyID(), node.getEndThreadID(Horizontal.LEFT));
        assertEquals(ThreadID.emptyID(), node.getEndThreadID(Horizontal.RIGHT));

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
        node.setLeftThreadID(new ThreadID(0));
        node.setRightThreadID(new ThreadID(1));
        assertEquals(ThreadID.emptyID(), node.getEndThreadID(Horizontal.LEFT));
        assertEquals(ThreadID.emptyID(), node.getEndThreadID(Horizontal.RIGHT));
        assertEquals(ThreadID.emptyID(), node.getFirstThreadID());
        assertEquals(ThreadID.emptyID(), node.getSecondThreadID());
    }

    @Test
    public void threadIDRightDirectTest() {
        NodeImpl node = new NodeImpl(Direction.RIGHT_DIRECT);
        node.setLeftThreadID(new ThreadID(0));
        node.setRightThreadID(new ThreadID(1));
        assertEquals(new ThreadID(1), node.getEndThreadID(Horizontal.LEFT));
        assertEquals(new ThreadID(0), node.getEndThreadID(Horizontal.RIGHT));
        assertEquals(new ThreadID(1), node.getFirstThreadID());
        assertEquals(new ThreadID(0), node.getSecondThreadID());
    }

    @Test
    public void threadIDLeftDirectTest() {
        NodeImpl node = new NodeImpl(Direction.LEFT_DIRECT);
        node.setLeftThreadID(new ThreadID(0));
        node.setRightThreadID(new ThreadID(1));
        assertEquals(new ThreadID(1), node.getEndThreadID(Horizontal.LEFT));
        assertEquals(new ThreadID(0), node.getEndThreadID(Horizontal.RIGHT));
        assertEquals(new ThreadID(0), node.getFirstThreadID());
        assertEquals(new ThreadID(1), node.getSecondThreadID());
    }

    @Test
    public void threadIDRightBackTest() {
        NodeImpl node = new NodeImpl(Direction.RIGHT_BACK);
        node.setLeftThreadID(new ThreadID(0));
        node.setRightThreadID(new ThreadID(1));
        assertEquals(new ThreadID(0), node.getEndThreadID(Horizontal.LEFT));
        assertEquals(new ThreadID(1), node.getEndThreadID(Horizontal.RIGHT));
        assertEquals(new ThreadID(1), node.getFirstThreadID());
        assertEquals(new ThreadID(0), node.getSecondThreadID());
    }

    @Test
    public void threadIDLeftBackTest() {
        NodeImpl node = new NodeImpl(Direction.LEFT_BACK);
        node.setLeftThreadID(new ThreadID(0));
        node.setRightThreadID(new ThreadID(1));
        assertEquals(new ThreadID(0), node.getEndThreadID(Horizontal.LEFT));
        assertEquals(new ThreadID(1), node.getEndThreadID(Horizontal.RIGHT));
        assertEquals(new ThreadID(0), node.getFirstThreadID());
        assertEquals(new ThreadID(1), node.getSecondThreadID());
    }

}
