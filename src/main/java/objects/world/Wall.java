package objects.world;

import core.Master;
import core.math.Vector2D;
import core.physics.Collidable;
import core.physics.Hitbox;
import core.physics.RectHitBox;
import objects.GameObject;

import java.awt.*;

public class Wall extends GameObject implements Collidable {

    private final RectHitBox hitbox;

    public Wall(double x, double y, double xSize, double ySize) {
        super(x, y, xSize, ySize);
        this.hitbox = new RectHitBox(new Vector2D(x, y), new Vector2D(xSize, ySize));
    }

    @Override
    public void draw(Graphics2D g2d, int w, Master master) {
        drawRect(g2d, w);
    }

    @Override
    public void update() {
    }

    @Override
    public boolean collidesWith(Collidable o) {
        return this.hitbox.collidesWith(o.getHitbox());
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
