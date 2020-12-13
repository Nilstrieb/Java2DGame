package core.physics.hitboxes;

import core.Drawable;
import core.math.Vector2D;

public abstract class Hitbox implements Drawable {

    private final boolean isTrigger;

    protected Hitbox(boolean isTrigger) {
        this.isTrigger = isTrigger;
    }

    public abstract void moveTo(Vector2D x1, Vector2D size);

    public abstract double getSize();

    @Override
    public String toString() {
        return super.toString();
    }
}