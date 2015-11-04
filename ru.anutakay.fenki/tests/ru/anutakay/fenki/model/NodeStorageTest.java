package ru.anutakay.fenki.model;

import static org.junit.Assert.*;

import org.junit.Test;

import ru.anutakay.fenki.model.NodeImpl.Horizontal;

public class NodeStorageTest {
	
	@Test
	public void constructorEmptyTest() {
		final Size dimensions = new Size();
		final Nodes nodeStorage = new Nodes(dimensions);
		final NodeIndex nodeIndex = new NodeIndex(0, 0);
		assertEquals(NodeImpl.Direction.NONE, nodeStorage.getNode(nodeIndex).getDirection());
		assertEquals(Horizontal.NONE, nodeStorage.getNode(nodeIndex).getBegin());
		assertEquals(Horizontal.NONE, nodeStorage.getNode(nodeIndex).getEnd());
		assertEquals(ThreadFragment.NONE_THREAD_ID, nodeStorage.getNode(nodeIndex).getFirstThreadID());
		assertEquals(ThreadFragment.NONE_THREAD_ID, nodeStorage.getNode(nodeIndex).getSecondThreadID());
		assertEquals(ThreadFragment.NONE_THREAD_ID, nodeStorage.getNode(nodeIndex).getBeginThreadID(Horizontal.LEFT));
		assertEquals(ThreadFragment.NONE_THREAD_ID, nodeStorage.getNode(nodeIndex).getBeginThreadID(Horizontal.RIGHT));
		assertEquals(ThreadFragment.NONE_THREAD_ID, nodeStorage.getNode(nodeIndex).getEndThreadID(Horizontal.LEFT));
		assertEquals(ThreadFragment.NONE_THREAD_ID, nodeStorage.getNode(nodeIndex).getEndThreadID(Horizontal.RIGHT));	
		assertEquals(new NodeImpl(), nodeStorage.getNode(nodeIndex));		
	}
	
	@Test
	public void simpleSetDataNotEqualsTest() {
		final Size dimensions = new Size();
		final Nodes nodeStorage = new Nodes(dimensions);
		final NodeIndex nodeIndex = new NodeIndex(0, 0);
		final NodeImpl node = nodeStorage.getNode(nodeIndex);
		node.setLeftThreadID(2);
		node.setRightThreadID(3);	
		assertFalse(new NodeImpl().equals(nodeStorage.getNode(nodeIndex)));
	}
	
	@Test
	public void simpleSetDataEqualsTest() {
		final Size dimensions = new Size();
		final Nodes nodeStorage = new Nodes(dimensions);
		final NodeIndex nodeIndex = new NodeIndex(0, 0);
		final NodeImpl node = nodeStorage.getNode(nodeIndex);
		node.setLeftThreadID(2);
		node.setRightThreadID(3);
		node.setDirection(NodeImpl.Direction.LEFT_BACK);
		final NodeImpl node2 = new NodeImpl();
		node2.setLeftThreadID(2);
		node2.setRightThreadID(3);
		node2.setDirection(NodeImpl.Direction.LEFT_BACK);
		assertTrue(node2.equals(nodeStorage.getNode(nodeIndex)));
	}

}
