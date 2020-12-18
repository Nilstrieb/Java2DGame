package core.objects.core;

import core.math.Vector2D;
import core.objects.core.GameObject;
import core.physics.Collidable;
import core.physics.hitboxes.Hitbox;

import java.util.ArrayList;

/**
 * A specialization of GameObject with Collidable properties
 */
public abstract class CollGameObject extends GameObject implements Collidable {

    protected Hitbox hitbox;
    protected ArrayList<Class<?>> ignores = new ArrayList<>();
    protected boolean isTrigger = false;

    public CollGameObject(Vector2D position, Vector2D size, Hitbox hitbox) {
        super(position, size);
        this.hitbox = hitbox;
    }

    public CollGameObject(double x, double y, double xSize, double ySize, Hitbox hitbox) {
        super(x, y, xSize, ySize);
        this.hitbox = hitbox;
    }

    /**
     * A simple method to move the object to a Vector2D. This method should be called instead of doing it manually.
     * Does not move if it encounters a collision
     *
     * @param target The target position
     */
    @Override
    public void moveTo(Vector2D target) {
        Vector2D oldPos = position.copy();
        this.position = target;

        ((Collidable) this).getHitbox().moveTo(position, size);
        if (master.doesCollide(this) != null) {
            this.position = oldPos;
            ((Collidable) this).getHitbox().moveTo(oldPos, size);
        }
    }

    @Override
    public Hitbox getHitbox() {
        return hitbox;
    }

    @Override
    public Vector2D getCenterPos() {
        return getCenterPosition();
    }

    @Override
    public Vector2D getSize() {
        return size;
    }

    @Override
    public void onCollision() {
    }

    @Override
    public ArrayList<Class<?>> getIgnores() {
        return ignores;
    }

    @Override
    public boolean isTrigger() {
        return isTrigger;
    }
}
