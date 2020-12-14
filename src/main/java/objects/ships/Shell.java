package objects.ships;

import core.math.Vector2D;
import core.objects.core.GameObject;

import java.awt.*;

/**
 * A shell fired by a cannon
 */
public class Shell extends GameObject {


    public Shell(Vector2D position, Vector2D size, Vector2D velocity) {
        super(position, size/*, new RectHitBox(position, size)*/);
        this.velocity = velocity;
        this.mainColor = Color.ORANGE;
    }

    @Override
    public void draw(Graphics2D g2d) {
        fillOval(g2d);
    }

    @Override
    public void update() {
        moveTo(Vector2D.add(position, velocity));
    }
}
