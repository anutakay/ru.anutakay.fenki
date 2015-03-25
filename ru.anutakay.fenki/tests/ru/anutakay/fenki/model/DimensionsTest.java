package ru.anutakay.fenki.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DimensionsTest {
	
	@Test
	public void constructorTest() {
		final Dimensions dimensions = new Dimensions();
		assertEquals(2, dimensions.getThreadNumber());
		assertEquals(1, dimensions.getColumnNumber());
		assertEquals(true, dimensions.firstCrossIsNode());
	}
	
	@Test
	public void constructorNegativeTest() {
		final Dimensions dimensions = new Dimensions(-9, 0);
		assertEquals(2, dimensions.getThreadNumber());
		assertEquals(1, dimensions.getColumnNumber());
		assertEquals(true, dimensions.firstCrossIsNode());
	}
	
	@Test
	public void constructorFullTest() {
		final Dimensions dimensions = new Dimensions(10, 11, false);
		assertEquals(10, dimensions.getThreadNumber());
		assertEquals(11, dimensions.getColumnNumber());
		assertEquals(false, dimensions.firstCrossIsNode());
	}
	
	@Test
	public void equalsTest() {
		final Dimensions dimensions1 = new Dimensions(10, 11, false);
		final Dimensions dimensions2 = new Dimensions(10, 11, false);
		assertEquals(true, dimensions1.equals(dimensions2));
		assertEquals(dimensions1.hashCode(), dimensions2.hashCode());
	}

}
