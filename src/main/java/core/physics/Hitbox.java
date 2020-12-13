package core.physics;

import core.Drawable;
import core.math.Vector2D;

public abstract class Hitbox implements Drawable {

    public abstract void moveTo(Vector2D x1, Vector2D size);

    @Override
    public String toString() {
        return super.toString();
    }
}
