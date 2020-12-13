package core.physics;

import core.math.Vector2D;
import core.physics.hitboxes.Hitbox;

public interface Collidable {

    boolean collidesWith(Collidable o);
    Hitbox getHitbox();
    Vector2D getCenterPos();
    Vector2D getSize();

}
