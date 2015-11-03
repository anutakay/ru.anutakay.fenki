package ru.anutakay.fenki.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DimensionsTest {
	
	@Test
	public void constructorTest() {
		final Size dimensions = new Size();
		assertEquals(2, dimensions.getThreadNumber());
		assertEquals(1, dimensions.getColumnNumber());
		assertEquals(true, dimensions.firstCrossIsNode());
	}
	
	@Test
	public void constructorNegativeTest() {
		final Size dimensions = new Size(-9, 0);
		assertEquals(2, dimensions.getThreadNumber());
		assertEquals(1, dimensions.getColumnNumber());
		assertEquals(true, dimensions.firstCrossIsNode());
	}
	
	@Test
	public void constructorFullTest() {
		final Size dimensions = new Size(10, 11, false);
		assertEquals(10, dimensions.getThreadNumber());
		assertEquals(11, dimensions.getColumnNumber());
		assertEquals(false, dimensions.firstCrossIsNode());
	}
	
	@Test
	public void equalsTest() {
		final Size dimensions1 = new Size(10, 11, false);
		final Size dimensions2 = new Size(10, 11, false);
		assertEquals(true, dimensions1.equals(dimensions2));
		assertEquals(dimensions1.hashCode(), dimensions2.hashCode());
	}

}
