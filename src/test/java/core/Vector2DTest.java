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
    void rotateAroundTestSimple(){
        Vector2D cent = new Vector2D(0, 0);
        Vector2D a = new Vector2D(1, 0);
        Vector2D excepted = new Vector2D(0 ,-1); //floating point precision probably
        Vector2D rotated = Vector2D.rotateAround(cent, a, Math.toRadians(90), Vector2D.CLOCKWISE);
        assertEquals(round(excepted), round(rotated));
    }

    @Test
    void rotateAroundTestMovedCenter(){
        Vector2D cent = new Vector2D(1, 1);
        Vector2D a = new Vector2D(2, 1);
        Vector2D excepted = new Vector2D(1 ,2);
        Vector2D rotated = Vector2D.rotateAround(cent, a, Math.toRadians(90), Vector2D.COUNTERCLOCKWISE);
        assertEquals(round(excepted), round(rotated));
    }

    @Test
    void rotateAroundTestExact(){
        Vector2D cent = new Vector2D();
        Vector2D a = new Vector2D(5, 6);
        Vector2D rotated = Vector2D.rotateAround(cent, a, Math.toRadians(120), Vector2D.COUNTERCLOCKWISE);
        Vector2D expected = new Vector2D(-7.6962, 1.3301);
        assertEquals(round(expected), round(rotated));
    }

    public static Vector2D round(Vector2D a){
        return new Vector2D(Math.round(a.x*10000d)/10000d, Math.round(a.y*10000d)/10000d);
    }
}