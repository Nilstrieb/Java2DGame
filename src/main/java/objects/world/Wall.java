package objects.world;

import core.Master;
import objects.GameObject;

import java.awt.*;

public class Wall extends GameObject {

    public Wall(double x, double y, double xSize, double ySize) {
        super(x, y, xSize, ySize);
    }

    @Override
    public void draw(Graphics2D g2d, int w, Master master) {
        drawRect(g2d, w);
    }

    @Override
    public void update(Master master) {
    }
}
