package objects;

import core.Coords;
import core.Drawable;
import core.Master;
import core.math.Vector2D;
import core.physics.Collidable;

import java.awt.*;

/**
 * The GameObject class is the superclass of every GameObject that can be displayed on screen. It has the 2
 * {@link #update()} and {@link #draw(Graphics2D)} methods that have to be
 */
public abstract class GameObject implements Drawable {

    protected Vector2D position;
    protected Vector2D size;

    protected Vector2D velocity;

    protected Color mainColor;

    protected Master master;

    public GameObject(double x, double y, double xSize, double ySize) {
        this(new Vector2D(x, y), new Vector2D(xSize, ySize));
    }

    public GameObject(Vector2D position, Vector2D size) {
        this.position = position;
        this.size = size;
        this.velocity = new Vector2D();
        mainColor = Color.BLACK;
        this.master = Master.getMaster();
    }


    /**
     * <p>The update method is called every frame before the {@link #draw(Graphics2D)} method by the master object on each object. Everything
     * that is needed for the game to work should be here in this method.</p>
     * <p>No drawing should be made in this method. The {@code debug} method can be called on the master.</p>
     * <p>This function is <i>NOT</i> intended to be called manually.</p>
     */
    public abstract void update();

    /**
     * A simple method to move the object to a Vector2D. This method should be called instead of doing it manually.
     *
     * @param target The target position
     */
    public void moveTo(Vector2D target) {
        Vector2D oldPos = position.copy();
        this.position = target;

        if (this instanceof Collidable) {
            ((Collidable) this).getHitbox().moveTo(position, size);
            if (master.doesCollide((Collidable) this)) {
                this.position = oldPos;
                ((Collidable) this).getHitbox().moveTo(oldPos, size);
            }
        }
    }

    /**
     * This method draws a rectangle at the current position and size
     *
     * @param g2d The Graphics2D object provided by the master
     */
    public void drawRect(Graphics2D g2d) {
        Vector2D abs = Coords.getWorldCoords(position);
        Vector2D sizeAbs = Coords.getWorldCoordsSize(size);

        g2d.setPaint(mainColor);
        g2d.fillRect((int) abs.x, (int) abs.y, (int) sizeAbs.x, (int) sizeAbs.y);
    }

    /**
     * This method draws a rectangle at the current position and size
     *
     * @param g2d The Graphics2D object provided by the master
     */
    public void drawOval(Graphics2D g2d) {
        Vector2D abs = Coords.getWorldCoords(position);
        Vector2D sizeAbs = Coords.getWorldCoordsSize(size);

        g2d.setPaint(mainColor);
        g2d.fillOval((int) abs.x, (int) abs.y, (int) sizeAbs.x, (int) sizeAbs.y);
    }

    /**
     * This method draws a rounded rectangle at the current position and size
     *
     * @param g2d  The Graphics2D object provided by the master
     * @param arcW The arc width of the rectangle
     * @param arcH The arc height of the rectangle
     */
    public void drawRoundRect(Graphics2D g2d, int arcW, int arcH) {
        Vector2D abs = Coords.getWorldCoords(position);
        Vector2D sizeAbs = Coords.getWorldCoordsSize(size);

        g2d.setPaint(mainColor);
        g2d.fillRoundRect((int) abs.x, (int) abs.y, (int) sizeAbs.x, (int) sizeAbs.y, arcW, arcH);
    }

    public void destroy() {
        master.destroy(this);
    }


    public Vector2D getMapCoords(Vector2D value) {
        double x = (position.x + value.x / 100d * size.x);
        double y = (position.y + value.y / 100d * size.y);
        return new Vector2D(x, y);

    }

    public Vector2D getMapCoordsSize(Vector2D value) {
        double x = (value.x / 100d * size.x);
        double y = (value.y / 100d * size.y);
        return new Vector2D(x, y);

    }

    public Vector2D getWorldCoordsFromLocal(Vector2D value) {
        return Coords.getWorldCoords(getMapCoords(value));
    }

    public Vector2D getWorldCoordsFromLocalSize(Vector2D value) {
        return Coords.getWorldCoordsSize(getMapCoordsSize(value));
    }

    public Vector2D getCenterPosition() {
        return new Vector2D(position.x - size.x / 2, position.y - size.y / 2);
    }
}
