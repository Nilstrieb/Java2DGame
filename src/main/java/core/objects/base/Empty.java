package core.objects.base;

import core.math.Vector2D;
import core.objects.core.GameObject;

public class Empty extends GameObject {

    public Empty(Vector2D position) {
        super(position, new Vector2D(0, 0));
    }

    @Override
    public void update() {
    }
}
