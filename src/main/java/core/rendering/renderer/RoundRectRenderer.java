package core.rendering.renderer;

import core.general.Master;
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
    public void draw() {
        re.fillRoundRect(object.getMapPosition(), size, new Vector2D(cornerFactorX, cornerFactorY), color, object.getRotation());
    }
}
