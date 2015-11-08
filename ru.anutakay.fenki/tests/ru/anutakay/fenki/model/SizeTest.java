package ru.anutakay.fenki.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SizeTest {

    @Test
    public void constructorTest() {
        final Size size = new Size();
        testSizeEquals(size, 2, 1, true);
    }

    @Test
    public void constructorNegativeTest() {
        final Size size = new Size(-9, 0);
        testSizeEquals(size, 2, 1, true);
    }

    @Test
    public void constructorFullTest() {
        final Size size = new Size(10, 11, false);
        testSizeEquals(size, 10, 11, false);
    }

    private void testSizeEquals(final Size size, int thread, int column,
            boolean first) {
        int actualThreadNum = size.threads();
        int actualColumnNum = size.columns();
        boolean actualFirstNode = size.first();

        assertEquals(thread, actualThreadNum);
        assertEquals(column, actualColumnNum);
        assertEquals(first, actualFirstNode);
    }

    @Test
    public void equalsTest() {
        final Size size1 = new Size(10, 11, false);
        final Size size2 = new Size(10, 11, false);
        assertEquals(size1, size2);
        assertEquals(size1.hashCode(), size2.hashCode());
    }
}
