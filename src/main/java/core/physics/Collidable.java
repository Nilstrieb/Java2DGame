package core.physics;

import core.math.Vector2D;

public interface Collidable {

    boolean collidesWith(Collidable o);
    Hitbox getHitbox();
    Vector2D getCenterPos();
    Vector2D getSize();

}
