package objects.ships;

import core.Master;
import core.math.Vector2D;
import objects.GameObject;

import java.awt.*;

public class Shell extends GameObject {


    public Shell(Vector2D position, Vector2D size, Vector2D velocity) {
        super(position, size);
        this.velocity = velocity;
    }

    @Override
    public void draw(Graphics2D g2d, int w, Master master) {
        g2d.setPaint(Color.orange);
        g2d.fillOval((int) position.x, (int) position.y, (int) size.x, (int) size.y);
    }

    @Override
    public void update() {
        position.add(velocity);
    }
}
