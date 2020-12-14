package objects.world;

import core.math.Vector2D;
import core.physics.hitboxes.Hitbox;
import core.physics.hitboxes.RectHitBox;
import core.objects.core.CollGameObject;

import java.awt.*;

public class Wall extends CollGameObject {

    public Wall(double x, double y, double xSize, double ySize) {
        super(x, y, xSize, ySize, new RectHitBox(new Vector2D(x, y), new Vector2D(xSize, ySize)));
    }

    @Override
    public void draw(Graphics2D g2d) {
        drawRect(g2d);
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
