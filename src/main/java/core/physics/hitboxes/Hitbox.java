package core.physics.hitboxes;

import core.math.Vector2D;

public abstract class Hitbox{


    public static final int HITBOX_RENDER_LAYER = 1;

    private final boolean isTrigger; //TODO trigger

    protected Hitbox(boolean isTrigger) {
        this.isTrigger = isTrigger;
    }

    public abstract void moveTo(Vector2D x1, Vector2D size);

    public abstract double getSize();

    public abstract boolean collidesWith(Hitbox other);

    @Override
    public String toString() {
        return super.toString();
    }
}
