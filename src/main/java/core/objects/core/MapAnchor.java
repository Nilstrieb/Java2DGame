package core.objects.core;

import core.math.Vector2D;

import java.awt.*;

/**
 * The {@code mapAnchor} objects is the default parent of every new {@code GameObject}.
 */
public class MapAnchor extends GameObject {

    public MapAnchor() {
        super(new Vector2D(0, 0), new Vector2D(0, 0));
    }

    @Override
    public void draw(Graphics2D g2d) {

    }

    @Override
    public void update() {

    }

    @Override
    public Vector2D getMapCoords(Vector2D value) {
        return value.copy();
    }
}
