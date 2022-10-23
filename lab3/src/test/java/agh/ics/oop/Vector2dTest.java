package oop;

import org.junit.jupiter.api.Test;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static org.junit.jupiter.api.Assertions.*;


class Vector2dTest {

    int x1 = 2, x2 = 1, x3 = 2, x4 = -2, y1 = 3, y2 = 1, y3 = 1, y4 = -4;
    private final Vector2d testVector1 = new Vector2d(x1, y1);
    private final Vector2d testVector2 = new Vector2d(x2, y2);
    private final Vector2d testVector3 = new Vector2d(x3, y3);
    private final Vector2d testVector4 = new Vector2d(x4, y4);

    @Test
    void testToString() {
        assertEquals(testVector1.toString(), "(%d, %d)".formatted(x1, y1));
        assertEquals(testVector2.toString(), "(%d, %d)".formatted(x2, y2));
        assertEquals(testVector3.toString(), "(%d, %d)".formatted(x3, y3));
        assertEquals(testVector4.toString(), "(%d, %d)".formatted(x4, y4));
    }

    @Test
    void precedes() {
        assertFalse(testVector1.precedes(testVector2));
        assertFalse(testVector1.precedes(testVector3));
        assertFalse(testVector1.precedes(testVector4));
        assertTrue(testVector2.precedes(testVector3));
        assertFalse(testVector2.precedes(testVector4));
        assertFalse(testVector3.precedes(testVector4));
    }

    @Test
    void follows() {
        assertTrue(testVector1.precedes(testVector2));
        assertTrue(testVector1.precedes(testVector3));
        assertTrue(testVector1.precedes(testVector4));
        assertFalse(testVector2.precedes(testVector3));
        assertTrue(testVector2.precedes(testVector4));
        assertTrue(testVector3.precedes(testVector4));
    }

    @Test
    void add() {
        assertEquals(testVector1.add(testVector2), new Vector2d(x1 + x2, y1 + y2));
        assertEquals(testVector1.add(testVector3), new Vector2d(x1 + x3, y1 + y3));
        assertEquals(testVector1.add(testVector4), new Vector2d(x1 + x4, y1 + y4));
        assertEquals(testVector2.add(testVector3), new Vector2d(x2 + x3, y2 + y3));
        assertEquals(testVector2.add(testVector4), new Vector2d(x2 + x4, y2 + y4));
        assertEquals(testVector3.add(testVector4), new Vector2d(x3 + x4, y3 + y4));
    }

    @Test
    void subtract() {
        assertEquals(testVector1.subtract(testVector2), new Vector2d(x1 - x2, y1 - y2));
        assertEquals(testVector1.subtract(testVector3), new Vector2d(x1 - x3, y1 - y3));
        assertEquals(testVector1.subtract(testVector4), new Vector2d(x1 - x4, y1 - y4));
        assertEquals(testVector2.subtract(testVector3), new Vector2d(x2 - x3, y2 - y3));
        assertEquals(testVector2.subtract(testVector4), new Vector2d(x2 - x4, y2 - y4));
        assertEquals(testVector3.subtract(testVector4), new Vector2d(x3 - x4, y3 - y4));
    }

    @Test
    void upperRight() {
        assertEquals(testVector1.upperRight(testVector2), new Vector2d(max(x1, x2), max(y1, y2)));
        assertEquals(testVector1.upperRight(testVector3), new Vector2d(max(x1, x3), max(y1, y3)));
        assertEquals(testVector1.upperRight(testVector4), new Vector2d(max(x1, x4), max(y1, y4)));
        assertEquals(testVector2.upperRight(testVector3), new Vector2d(max(x2, x3), max(y2, y3)));
        assertEquals(testVector2.upperRight(testVector4), new Vector2d(max(x2, x4), max(y2, y4)));
        assertEquals(testVector3.upperRight(testVector4), new Vector2d(max(x3, x4), max(y3, y4)));
    }

    @Test
    void lowerLeft() {
        assertEquals(testVector1.lowerLeft(testVector2), new Vector2d(min(x1, x2), min(y1, y2)));
        assertEquals(testVector1.lowerLeft(testVector3), new Vector2d(min(x1, x3), min(y1, y3)));
        assertEquals(testVector1.lowerLeft(testVector4), new Vector2d(min(x1, x4), min(y1, y4)));
        assertEquals(testVector2.lowerLeft(testVector3), new Vector2d(min(x2, x3), min(y2, y3)));
        assertEquals(testVector2.lowerLeft(testVector4), new Vector2d(min(x2, x4), min(y2, y4)));
        assertEquals(testVector3.lowerLeft(testVector4), new Vector2d(min(x3, x4), min(y3, y4)));
    }

    @Test
    void opposite() {
        assertEquals(testVector1.opposite(), new Vector2d(-x1, -y1));
        assertEquals(testVector2.opposite(), new Vector2d(-x2, -y2));
        assertEquals(testVector3.opposite(), new Vector2d(-x3, -y3));
        assertEquals(testVector4.opposite(), new Vector2d(-x4, -y4));

    }

    @Test
    void testEquals() {
        assertNotEquals(testVector1, testVector3);
        assertEquals(testVector2, testVector2);
        assertNotEquals(testVector2, testVector4);
        assertEquals(testVector3, new Vector2d(x3, y3));
        assertNotEquals(testVector3, testVector4);
        assertEquals(testVector4, testVector4);
        assertNotEquals(testVector4, testVector1);
    }

}