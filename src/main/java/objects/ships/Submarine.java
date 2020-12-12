package objects.ships;

import core.Master;
import core.math.Vector2D;
import core.physics.Collidable;
import core.physics.Hitbox;
import core.physics.RectHitBox;
import objects.GameObject;

import java.awt.*;

public class Submarine extends GameObject implements Collidable {

    private RectHitBox hitbox;

    public Submarine(Vector2D position, Vector2D size) {
        super(position, size);
        this.hitbox = new RectHitBox(position, size);
    }

    @Override
    public void draw(Graphics2D g2d, int w, Master master) {
        g2d.setPaint(Color.BLUE);
        g2d.fillOval((int) (position.x - size.x / 2), (int) (position.y - size.y / 2), (int) size.x, (int) size.y);
    }

    @Override
    public void update(Master master) {
        Point mouse = master.getMouseLocation();
        moveTo(new Vector2D(mouse.x, mouse.y), master);
    }

    @Override
    public boolean collidesWith(Collidable o) {
        return hitbox.collidesWith(o.getHitbox());
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
}
