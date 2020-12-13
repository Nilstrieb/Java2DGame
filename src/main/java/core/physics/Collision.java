package core.physics;

/**
 * This class stores information about a collision
 */
public class Collision {

    Collidable a;
    Collidable b;

    boolean haveCollided;

    public Collision(Collidable a, Collidable b, boolean haveCollided) {
        this.a = a;
        this.b = b;
        this.haveCollided = haveCollided;
    }

    /**
     * Get the Collidable a, the one that called the collision testing
     * @return The Collidable a
     */
    public Collidable getA() {
        return a;
    }

    /**
     * Get the Collidable b, the one that a collided width
     * @return The Collidable a
     */
    public Collidable getB() {
        return b;
    }

    public boolean isHaveCollided() {
        return haveCollided;
    }
}
