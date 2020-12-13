package objects;

import core.math.Vector2D;
import objects.core.GameObject;

import java.awt.*;

/**
 * A GameObject used for debugging
 */
public class DebugPos extends GameObject {
    public DebugPos(Vector2D position, Vector2D size) {
        super(position.copy(), size);
        this.velocity = new Vector2D();
        this.mainColor = Color.GREEN;
    }

    @Override
    public void draw(Graphics2D g2d) {
        drawOval(g2d);
    }

    @Override
    public void update() {
    }
}