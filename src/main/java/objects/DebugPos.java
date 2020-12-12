package objects;

import core.Master;
import core.math.Vector2D;

import java.awt.*;

/**
 * A GameObject used for debugging
 */
public class DebugPos extends GameObject {
    public DebugPos(Vector2D position, Vector2D size) {
        super(position.copy(), size);
        this.velocity = new Vector2D();
    }

    @Override
    public void draw(Graphics2D g2d, int w, Master master) {
        g2d.setPaint(Color.green);
        g2d.drawOval((int) (position.x - size.x / 2), (int) (position.y - size.y / 2), (int) size.x, (int) size.y);
    }

    @Override
    public void update(Master master) {
    }
}