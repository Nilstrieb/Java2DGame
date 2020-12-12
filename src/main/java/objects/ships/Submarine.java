package objects.ships;

import core.Master;
import core.math.Vector2D;
import objects.GameObject;

import java.awt.*;

public class Submarine extends GameObject {

    public Submarine(Vector2D position, Vector2D size) {
        super(position, size);
    }

    @Override
    public void draw(Graphics2D g2d, int w, Master master) {
        g2d.setPaint(Color.BLUE);
        g2d.fillOval((int) (position.x - size.x / 2), (int) (position.y - size.y / 2), (int) size.x, (int) size.y);
    }

    @Override
    public void update(Master master) {
        Point mouse = master.getMouseLocation();
        moveTo(new Vector2D(mouse.x, mouse.y));
    }
}
