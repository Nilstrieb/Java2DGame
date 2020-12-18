package core.physics;

import core.math.Vector2D;
import core.physics.hitboxes.Hitbox;

import java.util.ArrayList;

public interface Collidable {

    Hitbox getHitbox();
    Vector2D getCenterPos();
    Vector2D getSize();
    void onCollision();
    ArrayList<Class<?>> getIgnores();
    boolean isTrigger();
}
