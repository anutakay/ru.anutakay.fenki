package ru.anutakay.fenki.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class NodeStorageTest {
	
	@Test
	public void constructorEmptyTest() {
		final Size dimensions = new Size();
		final Nodes nodeStorage = new Nodes(dimensions);
		final NodeIndex nodeIndex = new NodeIndex(0, 0);
		assertEquals(Arrow.NONE, nodeStorage.getNode(nodeIndex).getArrow());
		assertEquals(Thread.empty(), nodeStorage.getNode(nodeIndex).getFirst());
		assertEquals(Thread.empty(), nodeStorage.getNode(nodeIndex).getSecond());
		assertEquals(Thread.empty(), nodeStorage.getNode(nodeIndex).getBegin(H.LEFT));
		assertEquals(Thread.empty(), nodeStorage.getNode(nodeIndex).getBegin(H.RIGHT));
		assertEquals(Thread.empty(), nodeStorage.getNode(nodeIndex).getEnd(H.LEFT));
		assertEquals(Thread.empty(), nodeStorage.getNode(nodeIndex).getEnd(H.RIGHT));	
		assertEquals(new NodeImpl(), nodeStorage.getNode(nodeIndex));		
	}
	
	@Test
	public void simpleSetDataNotEqualsTest() {
		final Size dimensions = new Size();
		final Nodes nodeStorage = new Nodes(dimensions);
		final NodeIndex nodeIndex = new NodeIndex(0, 0);
		final NodeImpl node = nodeStorage.getNode(nodeIndex);
		node.setBegin(new Thread(2), new Thread(3));	
		assertFalse(new NodeImpl().equals(nodeStorage.getNode(nodeIndex)));
	}
	
	@Test
	public void simpleSetDataEqualsTest() {
		final Size dimensions = new Size();
		final Nodes nodeStorage = new Nodes(dimensions);
		final NodeIndex nodeIndex = new NodeIndex(0, 0);
		final NodeImpl node = nodeStorage.getNode(nodeIndex);
		node.setBegin(new Thread(2), new Thread(3));
		node.setArrow(Arrow.LEFT_BACK);
		final NodeImpl node2 = new NodeImpl();
		node2.setBegin(new Thread(2), new Thread(3));
		node2.setArrow(Arrow.LEFT_BACK);
		assertTrue(node2.equals(nodeStorage.getNode(nodeIndex)));
	}

}
