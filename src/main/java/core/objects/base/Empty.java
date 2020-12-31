package core.objects.base;

import core.math.Vector2D;
import core.objects.core.GameObject;
import core.rendering.renderer.EmptyRenderer;

public class Empty extends GameObject {

    public Empty(Vector2D position) {
        super(position, new Vector2D(0, 0));
        setRenderer(new EmptyRenderer(this));
    }

    @Override
    public void update() {
    }
}
