package core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2DTest {


    @Test
    void addOK() {
        Vector2D a = new Vector2D(1, 2);
        Vector2D b = new Vector2D(1, 2);
        Vector2D c = new Vector2D(-1 ,-1);
        Vector2D d = new Vector2D(0, 1);

        assertEquals(new Vector2D(2, 4), Vector2D.add(a, b));
        assertEquals(new Vector2D(0, 1), Vector2D.add(a, c));
        assertEquals(new Vector2D(-1, 0), Vector2D.add(c, d));
    }

    @Test
    void normalized() {
        Vector2D a = new Vector2D(1, 1);
        Vector2D b = new Vector2D(1, 0);

        assertEquals(new Vector2D(1 / Math.sqrt(2), 1 / Math.sqrt(2)), a.normalized());
        assertEquals(new Vector2D(1,0), b.normalized());
    }

    @Test
    void subtractOK() {
        Vector2D a = new Vector2D(1, 2);
        Vector2D b = new Vector2D(1, 2);
        Vector2D c = new Vector2D(-1 ,-1);
        Vector2D d = new Vector2D(0, 1);

        assertEquals(new Vector2D(0, 0), Vector2D.subtract(a, b));
        assertEquals(new Vector2D(2, 3), Vector2D.subtract(a, c));
        assertEquals(new Vector2D(-1, -2), Vector2D.subtract(c, d));
    }

    @Test
    void rotateAroundTest(){
        Vector2D cent = new Vector2D(0, 0);
        Vector2D a = new Vector2D(1, 0);
        Vector2D exceptedA = new Vector2D(6.123233995736766E-17 ,-1); //floating point precision probably

        Vector2D centB = new Vector2D(1, 1);
        Vector2D b = new Vector2D(2, 1);
        Vector2D exceptedB = new Vector2D(1 ,2);


        Vector2D rotated = Vector2D.rotateAround(cent, a, Math.toRadians(90), Vector2D.CLOCKWISE);
        Vector2D rotatedb = Vector2D.rotateAround(centB, b, Math.toRadians(90), Vector2D.COUNTERCLOCKWISE);

        assertEquals(exceptedA, rotated);
        assertEquals(exceptedB, rotatedb);
    }
}