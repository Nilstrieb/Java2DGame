package custom.objects.ships;

import core.math.Vector2D;
import core.objects.core.CollGameObject;
import core.physics.hitboxes.RectHitBox;
import core.rendering.renderer.CircleRenderer;

import java.awt.*;

/**
 * A shell fired by a cannon
 */
public class Shell extends CollGameObject {


    public Shell(Vector2D position, Vector2D size, Vector2D velocity) {
        super(position, size, new RectHitBox(position, size));
        this.velocity = velocity;
        this.mainColor = Color.ORANGE;
        this.ignores.add(Shell.class);
        this.isTrigger = true;

        this.setRenderer(new CircleRenderer(this, mainColor, size.x/2));
    }

    @Override
    public void update() {
        moveTo(Vector2D.add(position, velocity));
    }

    @Override
    public void onTrigger() {
        destroy();
    }
}
