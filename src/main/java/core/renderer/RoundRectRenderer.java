package core.renderer;

import core.general.Master;
import core.math.Coordinates;
import core.math.Vector2D;
import core.objects.core.GameObject;

import java.awt.*;

public class RoundRectRenderer extends Renderer {

    private Vector2D size;
    private double cornerFactorX, cornerFactorY;
    private Master master;

    public RoundRectRenderer(Color color, GameObject object, Vector2D size, double cornerFactorX, double cornerFactorY) {
        super(color, object);
        this.size = size;
        this.master = Master.getMaster();
        this.cornerFactorX = cornerFactorX;
        this.cornerFactorY = cornerFactorY;
    }

    @Override
    public void draw(Graphics2D g2d) {
        Vector2D abs = Coordinates.getWorldCoordinates(object.getMapPosition());
        Vector2D sizeAbs = Coordinates.getWorldCoordinates(size);

        int xCenterAbs = (int) (abs.x + sizeAbs.x / 2);
        int yCenterAbs = (int) (abs.y + sizeAbs.y / 2);

        master.debugPos(object.getCenterPosition(), 500);

        g2d.setPaint(color);

        g2d.rotate(object.getRotation(), xCenterAbs, yCenterAbs);
        g2d.fillRoundRect((int) abs.x, (int) abs.y, (int) sizeAbs.x, (int) sizeAbs.y, (int) (master.getW() / cornerFactorX), (int) (master.getH() / cornerFactorY));

        g2d.rotate(-object.getRotation(), xCenterAbs, yCenterAbs);
    }
}
