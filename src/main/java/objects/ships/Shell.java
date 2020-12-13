package objects.ships;

import core.math.Vector2D;
import core.physics.hitboxes.RectHitBox;
import objects.core.CollGameObject;
import objects.core.GameObject;

import java.awt.*;

/**
 * A shell fired by a cannon
 */
//TODO why tf do shells not use map coords...
public class Shell extends GameObject {


    public Shell(Vector2D position, Vector2D size, Vector2D velocity) {
        super(position, size/*, new RectHitBox(position, size)*/);
        this.velocity = velocity;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setPaint(Color.orange);
        g2d.fillOval((int) position.x, (int) position.y, (int) size.x, (int) size.y);
    }

    @Override
    public void update() {
        moveTo(Vector2D.add(position, velocity));
    }
}
