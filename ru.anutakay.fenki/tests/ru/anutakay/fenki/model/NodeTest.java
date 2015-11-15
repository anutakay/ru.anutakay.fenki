package ru.anutakay.fenki.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ru.anutakay.fenki.model.thread.Thread;
import ru.anutakay.fenki.model.thread.ThreadPool;

public class NodeTest {

    @Test
    public void constructorTest() {
        NodeImpl node = new NodeImpl();

        nodeIsEmpty(node);
        assertEquals(Arrow.NONE, node.getArrow());

        Thread empty = ThreadPool.createEmptyThread();

        assertEquals(empty, node.getFirst());
        assertEquals(empty, node.getSecond());
        assertEquals(empty, node.getBegin(H.LEFT));
        assertEquals(empty, node.getBegin(H.RIGHT));
        assertEquals(empty, node.getEnd(H.LEFT));
        assertEquals(empty, node.getEnd(H.RIGHT));  

    }

    @Test(expected = NullPointerException.class)
    public void constructorNullTest() {
       new NodeImpl(null);
    }

    @Test
    public void constructorRightDirectTest() {
        NodeImpl node = new NodeImpl(Arrow.RIGHT_DIRECT);
        assertEquals(Arrow.RIGHT_DIRECT, node.getArrow());
    }

    @Test
    public void constructorRightBackTest() {
        NodeImpl node = new NodeImpl(Arrow.RIGHT_BACK);
        assertEquals(Arrow.RIGHT_BACK, node.getArrow());
    }

    @Test
    public void constructorLeftDirectTest() {
        NodeImpl node = new NodeImpl(Arrow.LEFT_DIRECT);
        assertEquals(Arrow.LEFT_DIRECT, node.getArrow());
    }

    @Test
    public void constructorLeftBackTest() {
        NodeImpl node = new NodeImpl(Arrow.LEFT_BACK);
        assertEquals(Arrow.LEFT_BACK, node.getArrow());
    }

    @Test
    public void threadIDTest() {
        NodeImpl node = new NodeImpl();
        ThreadPool factory = new ThreadPool();

        node.setBegin(factory.createThread(), factory.createThread());

        nodeIsEmpty(node);
    }

    private void nodeIsEmpty(NodeImpl node) {
        Thread empty = ThreadPool.createEmptyThread();
        checkNode(node, empty, empty, empty, empty);
    }
    
    @Before
    public void createThreads() {
        ThreadPool factory = new ThreadPool();
        first = factory.createThread();
        second = factory.createThread();
    }
    
    Thread first;
    Thread second;

    @Test
    public void threadIDRightDirectTest() {
        NodeImpl node = createNode(Arrow.RIGHT_DIRECT, second, first);
        checkNode(node, first, second, first, second);
    }

    @Test
    public void threadIDLeftDirectTest() {
        NodeImpl node = createNode(Arrow.LEFT_DIRECT, second, first);
        checkNode(node, first, second, second, first);
    }

    @Test
    public void threadIDRightBackTest() {
        NodeImpl node = createNode(Arrow.RIGHT_BACK, second, first);
        checkNode(node, second, first, first, second);
    }

    @Test
    public void threadIDLeftBackTest() {
        NodeImpl node = createNode(Arrow.LEFT_BACK, second, first);
        checkNode(node, second, first, second, first);
    }
    
    private NodeImpl createNode(Arrow d, Thread left, Thread right) {
        NodeImpl node = new NodeImpl(d);
        node.setBegin(left, right);
        return node;
    }

    private void checkNode(Node node, Thread left, Thread right,
            Thread first, Thread second) {
        assertEquals(left, node.getEnd(H.LEFT));
        assertEquals(right, node.getEnd(H.RIGHT));
        assertEquals(first, node.getFirst());
        assertEquals(second, node.getSecond());
    }

}
