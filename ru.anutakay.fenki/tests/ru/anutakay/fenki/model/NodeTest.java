package ru.anutakay.fenki.model;

import junit.framework.Assert;

import org.junit.Test;

public class NodeTest {
	
	@Test
	public void constructorTest(){
		Node node = new Node();
		Assert.assertEquals(Node.Direction.NONE, node.getDirection());
		Assert.assertEquals(-1, node.getEnter());
		Assert.assertEquals(-1, node.getExit());
	}
	
	@Test
	public void constructorNullTest(){
		Node node = new Node(null);
		Assert.assertEquals(Node.Direction.NONE, node.getDirection());
		Assert.assertEquals(-1, node.getEnter());
		Assert.assertEquals(-1, node.getExit());
	}
	
	@Test
	public void constructorDirectRightTest(){
		//Стрелка свеху справа вниз налево(напрямую)
		Node node = new Node(Node.Direction.DIRECT_RIGHT);
		Assert.assertEquals(Node.Direction.DIRECT_RIGHT, node.getDirection());
		Assert.assertEquals(Const.RIGHT, node.getEnter());
		Assert.assertEquals(Const.LEFT, node.getExit());
	}
	
	@Test
	public void constructorBackRightTest(){
		//Стрелка свеху справа вниз направо(с возвратом)
		Node node = new Node(Node.Direction.BACK_RIGHT);
		Assert.assertEquals(Node.Direction.BACK_RIGHT, node.getDirection());
		Assert.assertEquals(Const.RIGHT, node.getEnter());
		Assert.assertEquals(Const.RIGHT, node.getExit());
	}
	
	@Test
	public void constructorDirectLeftTest(){
		//Стрелка свеху слева вниз направо(напрямую)
		Node node = new Node(Node.Direction.DIRECT_LEFT);
		Assert.assertEquals(Node.Direction.DIRECT_LEFT, node.getDirection());
		Assert.assertEquals(Const.LEFT, node.getEnter());
		Assert.assertEquals(Const.RIGHT, node.getExit());
	}
	
	@Test
	public void constructorBackLeftTest(){
		//Стрелка свеху слева вниз налево(с возвратом)
		Node node = new Node(Node.Direction.BACK_LEFT);
		Assert.assertEquals(Node.Direction.BACK_LEFT, node.getDirection());
		Assert.assertEquals(Const.LEFT, node.getEnter());
		Assert.assertEquals(Const.LEFT, node.getExit());
	}

}
