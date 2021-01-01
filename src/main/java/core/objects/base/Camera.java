package core.objects.base;

import core.math.Vector2D;
import core.objects.core.GameObject;
import core.rendering.renderer.EmptyRenderer;

/**
 * The base Camera that shifts all objects
 */
public class Camera extends GameObject {
    public Camera(Vector2D position, Vector2D size) {
        super(position, size);
        setRenderer(new EmptyRenderer(this));
        this.doesDespawn = false;
    }

    @Override
    protected void update() {
    }
}
