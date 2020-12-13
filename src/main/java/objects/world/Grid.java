package objects.world;

import core.Master;
import objects.GameObject;

import java.awt.*;

/**
 * A basic background grid
 */
public class Grid extends GameObject {

    private int gridSpacing = 50;

    public Grid() {
        super(0, 0, 0, 0);
    }

    @Override
    public void draw(Graphics2D g2d, int w, Master master) {
        g2d.setPaint(Color.LIGHT_GRAY);

        this.w = w;
        h = w/16*9;

        g2d.drawRect(0, 0, w, h);

        int verticalLines = w / gridSpacing;
        int horizontalLines = h / gridSpacing;

        for (int i = 0; i < horizontalLines; i++) {
            int y = i * h / horizontalLines;
            g2d.drawLine(0, y, w, y);
        }

        for (int i = 0; i < verticalLines; i++) {
            int x = i * w / verticalLines;
            g2d.drawLine(x, 0, x, h);
        }

    }

    @Override
    public void update() {

    }
}
