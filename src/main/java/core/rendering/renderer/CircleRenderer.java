package core.rendering.renderer;

import core.math.Vector2D;
import core.objects.core.GameObject;

import java.awt.*;

/**
 * A simple {@code Renderer} that renders a filled circle
 */
public class CircleRenderer extends Renderer {

    /**
     * The radius of the circle
     */
    private double radius;

    /**
     * The constructor for the circle
     * @param object The {@code GameObject} the {@code Renderer} is assigned to
     * @param color The {@code Color} of the circle
     * @param radius The radius of the circle
     */
    public CircleRenderer(GameObject object, Color color, double radius) {
        super(color, object);
        this.radius = radius;
    }

    @Override
    public void draw(Graphics2D g2d) {
        re.fillOval(object.getMapPosition(), new Vector2D(2 * radius, 2 * radius), color, object.getRotation());
    }
}
