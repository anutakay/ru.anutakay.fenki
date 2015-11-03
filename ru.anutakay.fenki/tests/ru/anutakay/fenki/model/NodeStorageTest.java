package ru.anutakay.fenki.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class NodeStorageTest {
	
	@Test
	public void constructorEmptyTest() {
		final Size dimensions = new Size();
		final Nodes nodeStorage = new Nodes(dimensions);
		final NodeIndex nodeIndex = new NodeIndex(0, 0);
		assertEquals(Node.Direction.NONE, nodeStorage.getNode(nodeIndex).getDirection());
		assertEquals(Node.HDirection.NONE, nodeStorage.getNode(nodeIndex).getBegin());
		assertEquals(Node.HDirection.NONE, nodeStorage.getNode(nodeIndex).getEnd());
		assertEquals(ThreadFragment.NONE_THREAD_ID, nodeStorage.getNode(nodeIndex).getFirstThreadID());
		assertEquals(ThreadFragment.NONE_THREAD_ID, nodeStorage.getNode(nodeIndex).getSecondThreadID());
		assertEquals(ThreadFragment.NONE_THREAD_ID, nodeStorage.getNode(nodeIndex).getLeftThreadID());
		assertEquals(ThreadFragment.NONE_THREAD_ID, nodeStorage.getNode(nodeIndex).getRightThreadID());
		assertEquals(ThreadFragment.NONE_THREAD_ID, nodeStorage.getNode(nodeIndex).getBottomThreadID(Node.HDirection.LEFT));
		assertEquals(ThreadFragment.NONE_THREAD_ID, nodeStorage.getNode(nodeIndex).getBottomThreadID(Node.HDirection.RIGHT));	
		assertEquals(new Node(), nodeStorage.getNode(nodeIndex));		
	}
	
	@Test
	public void simpleSetDataNotEqualsTest() {
		final Size dimensions = new Size();
		final Nodes nodeStorage = new Nodes(dimensions);
		final NodeIndex nodeIndex = new NodeIndex(0, 0);
		final Node node = nodeStorage.getNode(nodeIndex);
		node.setLeftThreadID(2);
		node.setRightThreadID(3);	
		assertFalse(new Node().equals(nodeStorage.getNode(nodeIndex)));
	}
	
	@Test
	public void simpleSetDataEqualsTest() {
		final Size dimensions = new Size();
		final Nodes nodeStorage = new Nodes(dimensions);
		final NodeIndex nodeIndex = new NodeIndex(0, 0);
		final Node node = nodeStorage.getNode(nodeIndex);
		node.setLeftThreadID(2);
		node.setRightThreadID(3);
		node.setDirection(Node.Direction.LEFT_BACK);
		final Node node2 = new Node();
		node2.setLeftThreadID(2);
		node2.setRightThreadID(3);
		node2.setDirection(Node.Direction.LEFT_BACK);
		assertTrue(node2.equals(nodeStorage.getNode(nodeIndex)));
	}

}
