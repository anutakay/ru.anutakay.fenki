package ru.anutakay.fenki.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class NodeStorageTest {
	
	@Test
	public void constructorEmptyTest() {
		final Size dimensions = new Size();
		final Nodes nodeStorage = new Nodes(dimensions);
		final NodeIndex nodeIndex = new NodeIndex(0, 0);
		assertEquals(Direction.NONE, nodeStorage.getNode(nodeIndex).getDirection());
		assertEquals(Horizontal.NONE, nodeStorage.getNode(nodeIndex).getBegin());
		assertEquals(Horizontal.NONE, nodeStorage.getNode(nodeIndex).getEnd());
		assertEquals(ThreadID.emptyID(), nodeStorage.getNode(nodeIndex).getFirstThreadID());
		assertEquals(ThreadID.emptyID(), nodeStorage.getNode(nodeIndex).getSecondThreadID());
		assertEquals(ThreadID.emptyID(), nodeStorage.getNode(nodeIndex).getBeginThreadID(Horizontal.LEFT));
		assertEquals(ThreadID.emptyID(), nodeStorage.getNode(nodeIndex).getBeginThreadID(Horizontal.RIGHT));
		assertEquals(ThreadID.emptyID(), nodeStorage.getNode(nodeIndex).getEndThreadID(Horizontal.LEFT));
		assertEquals(ThreadID.emptyID(), nodeStorage.getNode(nodeIndex).getEndThreadID(Horizontal.RIGHT));	
		assertEquals(new NodeImpl(), nodeStorage.getNode(nodeIndex));		
	}
	
	@Test
	public void simpleSetDataNotEqualsTest() {
		final Size dimensions = new Size();
		final Nodes nodeStorage = new Nodes(dimensions);
		final NodeIndex nodeIndex = new NodeIndex(0, 0);
		final NodeImpl node = nodeStorage.getNode(nodeIndex);
		node.setLeftThreadID(new ThreadID(2));
		node.setRightThreadID(new ThreadID(3));	
		assertFalse(new NodeImpl().equals(nodeStorage.getNode(nodeIndex)));
	}
	
	@Test
	public void simpleSetDataEqualsTest() {
		final Size dimensions = new Size();
		final Nodes nodeStorage = new Nodes(dimensions);
		final NodeIndex nodeIndex = new NodeIndex(0, 0);
		final NodeImpl node = nodeStorage.getNode(nodeIndex);
		node.setLeftThreadID(new ThreadID(2));
		node.setRightThreadID(new ThreadID(3));
		node.setDirection(Direction.LEFT_BACK);
		final NodeImpl node2 = new NodeImpl();
		node2.setLeftThreadID(new ThreadID(2));
		node2.setRightThreadID(new ThreadID(3));
		node2.setDirection(Direction.LEFT_BACK);
		assertTrue(node2.equals(nodeStorage.getNode(nodeIndex)));
	}

}
