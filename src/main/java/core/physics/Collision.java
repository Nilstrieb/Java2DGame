package core.physics;

/**
 * This class stores information about a collision
 */
public class Collision {

    private final Collidable a;
    private final Collidable b;

    public Collision(Collidable a, Collidable b) {
        this.a = a;
        this.b = b;
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
}
