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
        int xAbs = (int) getWorldCoords(position.x, true);
        int yAbs = (int) getWorldCoords(position.y, false);
        int sizeXAbs = (int) getWorldCoords(size.x, true);
        int sizeYAbs = (int) getWorldCoords(size.y, false);

        g2d.setPaint(Color.BLACK);
        g2d.fillRect(xAbs, yAbs, sizeXAbs, sizeYAbs);
    }

    @Override
    public void update(Master master) {
    }
}
