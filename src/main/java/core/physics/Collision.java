package core.physics;

/**
 * This class stores information about a collision
 */
public class Collision {

    private final Collidable a;
    private final Collidable b;

    private final boolean isTrigger;

    public Collision(Collidable a, Collidable b) {
        this.a = a;
        this.b = b;

        this.isTrigger = a.isTrigger() || b.isTrigger();
    }

    /**
     * Whether the collision is a trigger and shouldn't impact movement
     * @return True if it's a trigger, false when it isn't
     */
    public boolean isTrigger() {
        return isTrigger;
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
