package core.renderer;

import core.math.Coordinates;
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
        Vector2D abs = Coordinates.getWorldCoordinates(object.getMapPosition());
        Vector2D sizeAbs = Coordinates.getWorldCoordinates(new Vector2D(radius * 2, radius * 2));

        g2d.setPaint(color);
        g2d.fillOval((int) abs.x, (int) abs.y, (int) sizeAbs.x, (int) sizeAbs.y);
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
