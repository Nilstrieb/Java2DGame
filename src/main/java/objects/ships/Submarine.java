package objects.ships;

import core.math.Coordinates;
import core.math.Vector2D;
import core.physics.hitboxes.RectHitBox;
import core.objects.core.CollGameObject;

import java.awt.*;

public class Submarine extends CollGameObject {

    public Submarine(Vector2D position, Vector2D size) {
        super(position, size, new RectHitBox(position, size));
        this.mainColor = Color.BLUE;
        doesDespawn = false;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setPaint(Color.BLUE);
        fillOval(g2d);
    }

    @Override
    public void update() {
        Point mouse = master.getMouseLocation();
        Vector2D relPos = Coordinates.getMapCoordinatesFromWorld(new Vector2D(mouse.x, mouse.y));
        Vector2D centerRelPos = new Vector2D(relPos.x - size.x/2, relPos.y - size.y/2);
        moveTo(centerRelPos);
    }
}
