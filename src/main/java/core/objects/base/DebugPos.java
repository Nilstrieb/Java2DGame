package core.objects.base;

import core.math.Vector2D;
import core.objects.core.GameObject;

import java.awt.*;

/**
 * A GameObject used for debugging
 */
public class DebugPos extends GameObject {

    private final long lifeTime;
    private long spawnTime;

    public DebugPos(Vector2D position, Vector2D size) {
        this(position, size, Long.MAX_VALUE);
    }

    public DebugPos(Vector2D position, Vector2D size, long lifeTime) {
        super(position.copy(), size);
        this.velocity = new Vector2D();
        this.mainColor = Color.GREEN;
        this.lifeTime = lifeTime;
    }

    @Override
    public void draw(Graphics2D g2d) {
        drawOval(g2d, "center");
    }

    @Override
    public void update() {
        long current = System.currentTimeMillis();
        if (current - spawnTime > lifeTime) {
            destroy();
        }

    }
}