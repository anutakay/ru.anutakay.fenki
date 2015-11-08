package ru.anutakay.fenki.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class NodeTest {

    @Test
    public void constructorTest() {
        NodeImpl node = new NodeImpl();

        nodeIsEmpty(node);
        assertEquals(Arrow.NONE, node.getArrow());

        Thread empty = Thread.empty();

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
        node.setBegin(new Thread(0), new Thread(1));

        nodeIsEmpty(node);
    }

    private void nodeIsEmpty(NodeImpl node) {
        Thread empty = Thread.empty();
        checkNode(node, empty, empty, empty, empty);
    }

    @Test
    public void threadIDRightDirectTest() {
        NodeImpl node = createNode(Arrow.RIGHT_DIRECT, 0, 1);
        checkNode(node, 1, 0, 1, 0);
    }

    @Test
    public void threadIDLeftDirectTest() {
        NodeImpl node = createNode(Arrow.LEFT_DIRECT, 0, 1);
        checkNode(node, 1, 0, 0, 1);
    }

    @Test
    public void threadIDRightBackTest() {
        NodeImpl node = createNode(Arrow.RIGHT_BACK, 0, 1);
        checkNode(node, 0, 1, 1, 0);
    }

    @Test
    public void threadIDLeftBackTest() {
        NodeImpl node = createNode(Arrow.LEFT_BACK, 0, 1);
        checkNode(node, 0, 1, 0, 1);
    }
    
    private NodeImpl createNode(Arrow d, int left, int right) {
        NodeImpl node = new NodeImpl(d);
        node.setBegin(new Thread(left), new Thread(right));
        return node;
    }

    private void checkNode(Node node, Thread left, Thread right,
            Thread first, Thread second) {
        assertEquals(left, node.getEnd(H.LEFT));
        assertEquals(right, node.getEnd(H.RIGHT));
        assertEquals(first, node.getFirst());
        assertEquals(second, node.getSecond());
    }

    private void checkNode(Node node, int left, int right,
            int first, int second) {
        checkNode(node, new Thread(left), new Thread(right), new Thread(
                first), new Thread(second));
    }

}
