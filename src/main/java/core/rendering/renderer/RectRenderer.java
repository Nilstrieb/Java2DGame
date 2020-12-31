package core.rendering.renderer;

import core.math.Vector2D;
import core.objects.core.GameObject;

import java.awt.*;

public class RectRenderer extends Renderer{

    private Vector2D size;

    public RectRenderer(Color color, GameObject object, Vector2D size) {
        super(color, object);
        this.size = size;
    }

    @Override
    public void draw(Graphics2D g2d) {
        re.fillRect(object.getMapPosition(), size, color, object.getRotation());
    }
}
