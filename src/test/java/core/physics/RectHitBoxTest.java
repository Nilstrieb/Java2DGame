package core.physics;

import core.math.Vector2D;
import core.physics.hitboxes.RectHitBox;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectHitBoxTest {

    @Test
    void constructorSimple(){
        RectHitBox a = new RectHitBox(new Vector2D(1, 1), new Vector2D(1, 1));

        assertEquals(a.getX1(), new Vector2D(1, 1));
        assertEquals(a.getX2(), new Vector2D(2, 1));
        assertEquals(a.getY1(), new Vector2D(1, 2));
        assertEquals(a.getY2(), new Vector2D(2, 2));
    }

    @Test
    void doCollideNoCross(){
        RectHitBox a = new RectHitBox(new Vector2D(-1, 1), new Vector2D(1, 1));
        RectHitBox b = new RectHitBox(new Vector2D(1, 1), new Vector2D(1, 1));
        assertFalse(a.collidesWith(b));
    }

    @Test
    void doCollideCornerTouch(){
        RectHitBox a = new RectHitBox(new Vector2D(0, 0), new Vector2D(1, 1));
        RectHitBox b = new RectHitBox(new Vector2D(1, 1), new Vector2D(1, 1));
        assertFalse(a.collidesWith(b));
    }

    @Test
    void doCollideCornerInside(){
        RectHitBox a = new RectHitBox(new Vector2D(0, 0), new Vector2D(2, 2));
        RectHitBox b = new RectHitBox(new Vector2D(1, 1), new Vector2D(2, 2));
        assertTrue(a.collidesWith(b));
    }

    @Test
    void doCollideEdgeInside(){
        RectHitBox a = new RectHitBox(new Vector2D(-2, 0), new Vector2D(4, 2));
        RectHitBox b = new RectHitBox(new Vector2D(-1, 1), new Vector2D(2, 2));
        assertTrue(a.collidesWith(b));
    }

    @Test
    void doCollideCrossCrossing(){
        RectHitBox a = new RectHitBox(new Vector2D(-1, 2), new Vector2D(2, 4));
        RectHitBox b = new RectHitBox(new Vector2D(-2, 1), new Vector2D(4, 2));
        assertTrue(a.collidesWith(b));
    }

    @Test
    void doCollideFullyInside(){
        RectHitBox a = new RectHitBox(new Vector2D(0, 0), new Vector2D(3, 3));
        RectHitBox b = new RectHitBox(new Vector2D(1, 1), new Vector2D(1, 1));
        assertTrue(a.collidesWith(b));
    }

}