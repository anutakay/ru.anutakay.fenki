package ru.anutakay.fenki.model;

import static org.junit.Assert.*;

import org.junit.Test;

import ru.anutakay.fenki.model.Node.HDirection;

public class NodeTest {
	
	@Test
	public void constructorTest() {
		Node node = new Node();
		assertEquals(Node.Direction.NONE, node.getDirection());
		assertEquals(Node.HDirection.NONE, node.getBegin());
		assertEquals(Node.HDirection.NONE, node.getEnd());
		assertEquals(ThreadFragment.NONE_THREAD_ID, node.getFirstThreadID());
		assertEquals(ThreadFragment.NONE_THREAD_ID, node.getSecondThreadID());
		assertEquals(ThreadFragment.NONE_THREAD_ID, node.getLeftThreadID());
		assertEquals(ThreadFragment.NONE_THREAD_ID, node.getRightThreadID());
		assertEquals(ThreadFragment.NONE_THREAD_ID, node.getBottomThreadID(Node.HDirection.LEFT));
		assertEquals(ThreadFragment.NONE_THREAD_ID, node.getBottomThreadID(Node.HDirection.RIGHT));	
		
	}
	
	@Test
	public void constructorNullTest() {
		Node nullNode = new Node(null);
		Node node = new Node();
		assertEquals(node, nullNode);
	}
	
	@Test
	public void constructorRightDirectTest() {
		//Стрелка свеху справа вниз налево(напрямую)
		Node node = new Node(Node.Direction.RIGHT_DIRECT);
		assertEquals(Node.Direction.RIGHT_DIRECT, node.getDirection());
		assertEquals(HDirection.RIGHT, node.getBegin());
		assertEquals(HDirection.LEFT, node.getEnd());
	}
	
	@Test
	public void constructorRightBackTest() {
		//Стрелка свеху справа вниз направо(с возвратом)
		Node node = new Node(Node.Direction.RIGHT_BACK);
		assertEquals(Node.Direction.RIGHT_BACK, node.getDirection());
		assertEquals(HDirection.RIGHT, node.getBegin());
		assertEquals(HDirection.RIGHT, node.getEnd());
	}
	
	@Test
	public void constructorLeftDirectTest() {
		//Стрелка свеху слева вниз направо(напрямую)
		Node node = new Node(Node.Direction.LEFT_DIRECT);
		assertEquals(Node.Direction.LEFT_DIRECT, node.getDirection());
		assertEquals(HDirection.LEFT, node.getBegin());
		assertEquals(HDirection.RIGHT, node.getEnd());
	}
	
	@Test
	public void constructorLeftBackTest() {
		//Стрелка свеху слева вниз налево(с возвратом)
		Node node = new Node(Node.Direction.LEFT_BACK);
		assertEquals(Node.Direction.LEFT_BACK, node.getDirection());
		assertEquals(HDirection.LEFT, node.getBegin());
		assertEquals(HDirection.LEFT, node.getEnd());
	}
	
	@Test 
	public void threadIDTest() {
		Node node = new Node();
		node.setLeftThreadID(0);
		node.setRightThreadID(1);
		assertEquals(ThreadFragment.NONE_THREAD_ID, node.getBottomThreadID(HDirection.LEFT));
		assertEquals(ThreadFragment.NONE_THREAD_ID, node.getBottomThreadID(HDirection.RIGHT));
		assertEquals(ThreadFragment.NONE_THREAD_ID, node.getFirstThreadID());
		assertEquals(ThreadFragment.NONE_THREAD_ID, node.getSecondThreadID());
	}
	
	@Test 
	public void threadIDRightDirectTest() {
		Node node = new Node(Node.Direction.RIGHT_DIRECT);
		node.setLeftThreadID(0);
		node.setRightThreadID(1);
		assertEquals(1, node.getBottomThreadID(HDirection.LEFT));
		assertEquals(0, node.getBottomThreadID(HDirection.RIGHT));
		assertEquals(1, node.getFirstThreadID());
		assertEquals(0, node.getSecondThreadID());
	}
	
	@Test 
	public void threadIDLeftDirectTest() {
		Node node = new Node(Node.Direction.LEFT_DIRECT);
		node.setLeftThreadID(0);
		node.setRightThreadID(1);
		assertEquals(1, node.getBottomThreadID(HDirection.LEFT));
		assertEquals(0, node.getBottomThreadID(HDirection.RIGHT));
		assertEquals(0, node.getFirstThreadID());
		assertEquals(1, node.getSecondThreadID());
	}
	
	@Test 
	public void threadIDRightBackTest() {
		Node node = new Node(Node.Direction.RIGHT_BACK);
		node.setLeftThreadID(0);
		node.setRightThreadID(1);
		assertEquals(0, node.getBottomThreadID(HDirection.LEFT));
		assertEquals(1, node.getBottomThreadID(HDirection.RIGHT));
		assertEquals(1, node.getFirstThreadID());
		assertEquals(0, node.getSecondThreadID());
	}
	
	@Test 
	public void threadIDLeftBackTest() {
		Node node = new Node(Node.Direction.LEFT_BACK);
		node.setLeftThreadID(0);
		node.setRightThreadID(1);
		assertEquals(0, node.getBottomThreadID(HDirection.LEFT));
		assertEquals(1, node.getBottomThreadID(HDirection.RIGHT));
		assertEquals(0, node.getFirstThreadID());
		assertEquals(1, node.getSecondThreadID());
	}

}
