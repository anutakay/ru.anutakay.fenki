package ru.anutakay.fenki.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class NodeStorageTest {
	
	@Test
	public void constructorEmptyTest() {
		final Dimensions dimensions = new Dimensions();
		final NodeStorage nodeStorage = new NodeStorage(dimensions);
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

}
