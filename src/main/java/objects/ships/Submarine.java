package objects.ships;

import core.math.Coords;
import core.math.Vector2D;
import core.physics.Collidable;
import core.physics.hitboxes.Hitbox;
import core.physics.hitboxes.RectHitBox;
import objects.GameObject;

import java.awt.*;

public class Submarine extends GameObject implements Collidable {

    private final RectHitBox hitbox;

    public Submarine(Vector2D position, Vector2D size) {
        super(position, size);
        this.hitbox = new RectHitBox(position, size);
        this.mainColor = Color.BLUE;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setPaint(Color.BLUE);
        drawOval(g2d);
    }

    @Override
    public void update() {
        Point mouse = master.getMouseLocation();
        Vector2D relPos = Coords.getMapCoordsFromWorld(new Vector2D(mouse.x, mouse.y));
        Vector2D centerRelPos = new Vector2D(relPos.x - size.x/2, relPos.y - size.y/2);
        moveTo(centerRelPos);
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
