package core.renderer;

import core.objects.core.GameObject;

import java.awt.*;

/**
 * The base renderer class for all renderers
 */
public abstract class Renderer {

    protected Color color;
    protected GameObject object;

    /**
     * The superconstructor for every {@code Renderer} that sets the color and the {@code GameObject} the renderer is assigned to
     * @param color The color of the object the {@code Renderer} renders
     * @param object The {@code GameObject} the {@code Renderer} os assigned to
     */
    public Renderer(Color color, GameObject object) {
        this.color = color;
        this.object = object;
    }

    /**
     * This method is called on every renderer for every {@code GameObject}
     * @param g2d the {@code Graphics2D} object
     */
    public abstract void draw(Graphics2D g2d);
}
