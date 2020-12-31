package core.rendering.renderer;

import core.objects.core.GameObject;

import java.awt.*;

/**
 * A filler {@code Renderer} that does nothing
 */
public class EmptyRenderer extends Renderer{

    /**
     * The superconstructor for every {@code Renderer} that sets the color and the {@code GameObject} the renderer is assigned to
     *
     * @param object The {@code GameObject} the {@code Renderer} os assigned to
     */
    public EmptyRenderer(GameObject object) {
        super(null, object);
    }

    @Override
    public void draw(Graphics2D g2d) {
    }
}
