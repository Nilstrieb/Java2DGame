package core.objects.base;

import core.math.Coordinates;
import core.math.Vector2D;
import core.objects.core.GameObject;
import core.rendering.renderer.CustomRenderer;

import java.awt.*;

/**
 * A GameObject used for debugging
 */
public class DebugPos extends GameObject {

    private final long lifeTime;
    private long spawnTime;

    public DebugPos(Vector2D position, Vector2D size, long lifeTime) {
        super(position.copy(), size);
        this.velocity = new Vector2D();
        this.mainColor = Color.GREEN;
        this.lifeTime = lifeTime;
        this.spawnTime = System.currentTimeMillis();
        this.layer = 3;

        this.setRenderer(new CustomRenderer(Color.GREEN, this) {
            @Override
            public void draw(Graphics2D g2d) {
                Vector2D positionAbs = Coordinates.getWorldCoordinates(getMapPosition());
                g2d.setColor(color);
                g2d.setStroke(new BasicStroke((int) Coordinates.getWorldCoordinates(new Vector2D(0.5, 0)).x, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_BEVEL));
                g2d.drawLine((int)positionAbs.x - 20, (int)positionAbs.y, (int)positionAbs.x + 20, (int)positionAbs.y);
                g2d.drawLine((int)positionAbs.x, (int)positionAbs.y-20, (int)positionAbs.x, (int)positionAbs.y+20);
            }

            @Override
            public int getLayer() {
                return layer;
            }
        });
    }

    @Override
    public void update() {
        long current = System.currentTimeMillis();
        if (current - spawnTime > lifeTime) {
            destroy();
        }

    }
}