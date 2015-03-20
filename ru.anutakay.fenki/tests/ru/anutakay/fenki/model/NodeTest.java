package ru.anutakay.fenki.model;

import static org.junit.Assert.*;

import org.junit.Test;

import ru.anutakay.fenki.model.Const.HDirection;

public class NodeTest {
	
	@Test
	public void constructorTest() {
		Node node = new Node();
		assertEquals(Node.Direction.NONE, node.getDirection());
		assertEquals(HDirection.NONE, node.getBegin());
		assertEquals(HDirection.NONE, node.getEnd());
	}
	
	@Test
	public void constructorNullTest() {
		Node node = new Node(null);
		assertEquals(Node.Direction.NONE, node.getDirection());
		assertEquals(HDirection.NONE, node.getBegin());
		assertEquals(HDirection.NONE, node.getEnd());
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
		assertEquals(-1, node.getBottomThreadID(HDirection.LEFT));
		assertEquals(-1, node.getBottomThreadID(HDirection.RIGHT));
		assertEquals(-1, node.getFirstThreadID());
		assertEquals(-1, node.getSecondThreadID());
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
