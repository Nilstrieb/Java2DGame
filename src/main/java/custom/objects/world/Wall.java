package custom.objects.world;

import core.math.Vector2D;
import core.physics.hitboxes.Hitbox;
import core.physics.hitboxes.RectHitBox;
import core.objects.core.CollGameObject;
import core.rendering.renderer.RectRenderer;

import java.awt.*;

public class Wall extends CollGameObject {

    public Wall(Vector2D position, Vector2D size) {
        super(position, size, new RectHitBox(position, size));

        setRenderer(new RectRenderer(Color.BLACK, this, this.size));
    }

    @Override
    public void update() {
    }

    @Override
    public Hitbox getHitbox() {
        return hitbox;
    }

    @Override
    public Vector2D getCenterPos() {
        return position;
    }

    @Override
    public Vector2D getSize() {
        return size;
    }
}
