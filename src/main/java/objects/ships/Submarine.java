package objects.ships;

import core.math.Coordinates;
import core.math.Vector2D;
import core.physics.hitboxes.RectHitBox;
import core.objects.core.CollGameObject;
import core.renderer.CircleRenderer;
import core.renderer.CustomRenderer;
import core.renderer.RectRenderer;

import java.awt.*;

public class Submarine extends CollGameObject {

    public Submarine(Vector2D position, Vector2D size) {
        super(position, size, new RectHitBox(position, size));
        setRenderer(new CircleRenderer(this, Color.BLUE, size.x/2));
        this.mainColor = Color.BLUE;
        doesDespawn = false;
    }

    @Override
    public void update() {
        Point mouse = master.getMouseLocation();
        Vector2D relPos = Coordinates.getMapCoordinatesFromWorld(new Vector2D(mouse.x, mouse.y));
        Vector2D centerRelPos = new Vector2D(relPos.x - size.x/2, relPos.y - size.y/2);
        moveTo(centerRelPos);
    }
}
