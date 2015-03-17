package ru.anutakay.fenki.model;

import junit.framework.Assert;

import org.junit.Test;

import ru.anutakay.fenki.model.Const.HDirection;

public class NodeTest {
	
	@Test
	public void constructorTest(){
		Node node = new Node();
		Assert.assertEquals(Node.Direction.NONE, node.getDirection());
		Assert.assertEquals(HDirection.NONE, node.getEnter());
		Assert.assertEquals(HDirection.NONE, node.getExit());
	}
	
	@Test
	public void constructorNullTest(){
		Node node = new Node(null);
		Assert.assertEquals(Node.Direction.NONE, node.getDirection());
		Assert.assertEquals(HDirection.NONE, node.getEnter());
		Assert.assertEquals(HDirection.NONE, node.getExit());
	}
	
	@Test
	public void constructorDirectRightTest(){
		//Стрелка свеху справа вниз налево(напрямую)
		Node node = new Node(Node.Direction.DIRECT_RIGHT);
		Assert.assertEquals(Node.Direction.DIRECT_RIGHT, node.getDirection());
		Assert.assertEquals(HDirection.RIGHT, node.getEnter());
		Assert.assertEquals(HDirection.LEFT, node.getExit());
	}
	
	@Test
	public void constructorBackRightTest(){
		//Стрелка свеху справа вниз направо(с возвратом)
		Node node = new Node(Node.Direction.BACK_RIGHT);
		Assert.assertEquals(Node.Direction.BACK_RIGHT, node.getDirection());
		Assert.assertEquals(HDirection.RIGHT, node.getEnter());
		Assert.assertEquals(HDirection.RIGHT, node.getExit());
	}
	
	@Test
	public void constructorDirectLeftTest(){
		//Стрелка свеху слева вниз направо(напрямую)
		Node node = new Node(Node.Direction.DIRECT_LEFT);
		Assert.assertEquals(Node.Direction.DIRECT_LEFT, node.getDirection());
		Assert.assertEquals(HDirection.LEFT, node.getEnter());
		Assert.assertEquals(HDirection.RIGHT, node.getExit());
	}
	
	@Test
	public void constructorBackLeftTest(){
		//Стрелка свеху слева вниз налево(с возвратом)
		Node node = new Node(Node.Direction.BACK_LEFT);
		Assert.assertEquals(Node.Direction.BACK_LEFT, node.getDirection());
		Assert.assertEquals(HDirection.LEFT, node.getEnter());
		Assert.assertEquals(HDirection.LEFT, node.getExit());
	}

}
