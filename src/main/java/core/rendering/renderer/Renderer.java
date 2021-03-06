package core.rendering.renderer;

import core.general.Master;
import core.objects.core.GameObject;
import core.rendering.RenderEngine;

import java.awt.*;

/**
 * The base renderer class for all renderers
 */
public abstract class Renderer{

    protected RenderEngine re;

    protected Color color;
    protected GameObject object;

    /**
     * The superconstructor for every {@code Renderer} that sets the color and the {@code GameObject} the renderer is assigned to
     * @param color The color of the object the {@code Renderer} renders
     * @param object The {@code GameObject} the {@code Renderer} os assigned to
     */
    public Renderer(Color color, GameObject object) {
        this.re = Master.getMaster().getRenderEngine();
        this.color = color;
        this.object = object;
    }

    /**
     * This method is called on every renderer for every {@code GameObject}
     */
    public abstract void draw();

    public int getLayer() {
        return object.getLayer();
    }
}
