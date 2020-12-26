package core.renderer;

import core.math.Vector2D;
import core.objects.core.GameObject;

import java.awt.*;

public class RectRenderer extends Renderer{

    private int width;
    private int height;

    public RectRenderer(Color color, GameObject object) {
        super(color, object);
    }

    @Override
    public void draw(Graphics2D g2d) {

    }
}
