package custom.objects.world;

import core.math.Vector2D;
import core.objects.core.GameObject;
import core.rendering.renderer.CustomRenderer;

import java.awt.*;

/**
 * A basic background grid
 */
public class Grid extends GameObject {

    private static final int GRID_SPACING = 50;

    public Grid() {
        super(Vector2D.zero(), Vector2D.zero());
        setRenderer(new CustomRenderer(mainColor, this) {
            @Override
            public void draw(Graphics2D g2d) {
                g2d.setPaint(Color.LIGHT_GRAY);

                int w = master.getW();
                int h = w/16*9;

                g2d.drawRect(0, 0, w, h);

                int verticalLines = w / GRID_SPACING;
                int horizontalLines = h / GRID_SPACING;

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
            public int getLayer() {
                return object.getLayer();
            }
        });
    }

    @Override
    public void update() {

    }
}
