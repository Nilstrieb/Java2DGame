package core.objects.base;

import core.math.Vector2D;
import core.objects.core.GameObject;

import java.awt.*;

public class Empty extends GameObject {

    public Empty(Vector2D position) {
        super(position, new Vector2D(0, 0));
    }

    @Override
    public void draw(Graphics2D g2d) {
    }

    @Override
    public void update() {
    }
}
