package core.renderer;

import core.general.Master;
import core.math.Coordinates;
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
        Vector2D abs = Coordinates.getWorldCoordinates(object.getMapPosition());
        Vector2D sizeAbs = Coordinates.getWorldCoordinates(size);

        int xCenterAbs = (int) (abs.x + sizeAbs.x / 2);
        int yCenterAbs = (int) (abs.y + sizeAbs.y / 2);

        g2d.setPaint(color);

        g2d.rotate(object.getRotation(), xCenterAbs, yCenterAbs);

        g2d.fillRect((int) abs.x, (int) abs.y, (int) sizeAbs.x, (int) sizeAbs.y);

        g2d.rotate(-object.getRotation(), xCenterAbs, yCenterAbs);
    }
}
