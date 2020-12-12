package objects;

import core.Drawable;
import core.Master;
import core.math.Vector2D;
import core.physics.Collidable;

import java.awt.*;

/**
 * The GameObject class is the superclass of every GameObject that can be displayed on screen. It has the 2
 * {@link #update(Master)} and {@link #draw(Graphics2D, int, Master)} methods that have to be
 */
public abstract class GameObject implements Drawable {

    protected int w;
    protected int h;

    protected Vector2D position;
    protected Vector2D size;

    protected Vector2D velocity;

    protected Color mainColor;

    public GameObject(double x, double y, double xSize, double ySize) {
        this(new Vector2D(x, y), new Vector2D(xSize, ySize));
    }

    public GameObject(Vector2D position, Vector2D size) {
        this.position = position;
        this.size = size;
        this.velocity = new Vector2D();
        mainColor = Color.BLACK;
    }


    /**
     * <p>The update method is called every frame before the {@link #draw(Graphics2D, int, Master)} method by the master object on each object. Everything
     * that is needed for the game to work should be here in this method.</p>
     * <p>No drawing should be made in this method. The {@code debug} method can be called on the master.</p>
     * <p>This function is <i>NOT</i> intended to be called manually.</p>
     *
     * @param master The master object itself
     */
    public abstract void update(Master master);

    /**
     * A simple method to move the object to a Vector2D. This method should be called instead of doing it manually.
     *
     * @param target The target position
     */
    public void moveTo(Vector2D target, Master master) {
        Vector2D oldPos = position;
        this.position = target;

        if (this instanceof Collidable) {
            if (master.doesCollide((Collidable) this)) {
                this.position = oldPos;
            }
        }
    }

    /**
     * This method draws a rectangle at the current position and size
     *
     * @param g2d The Graphics2D object provided by the master
     * @param w   The width of the screen
     */
    public void drawRect(Graphics2D g2d, int w) {
        this.w = w;
        h = (int) (this.w / Master.SCREEN_RATIO);
        int xAbs = (int) getWorldCoords(position.x, true);
        int yAbs = (int) getWorldCoords(position.y, false);
        int sizeXAbs = (int) getWorldCoordsSize(size.x, true);
        int sizeYAbs = (int) getWorldCoordsSize(size.y, false);

        g2d.setPaint(mainColor);
        g2d.fillRect(xAbs, yAbs, sizeXAbs, sizeYAbs);
    }

    /**
     * This method draws a rounded rectangle at the current position and size
     *
     * @param g2d  The Graphics2D object provided by the master
     * @param w    The width of the screen
     * @param arcW The arc width of the rectangle
     * @param arcH The arc height of the rectangle
     */
    public void drawRoundRect(Graphics2D g2d, int w, int arcW, int arcH) {
        this.w = w;
        h = (int) (w / Master.SCREEN_RATIO);
        int xAbs = (int) getWorldCoords(position.x, true);
        int yAbs = (int) getWorldCoords(position.y, false);
        int sizeXAbs = (int) getWorldCoordsSize(size.x, true);
        int sizeYAbs = (int) getWorldCoordsSize(size.y, false);

        g2d.setPaint(mainColor);
        g2d.fillRoundRect(xAbs, yAbs, sizeXAbs, sizeYAbs, arcW, arcH);
    }

    public double getWorldCoords(double value, boolean isX) {
        if (isX) {
            return (value / (Master.SCREEN_Y_COORDINATES * Master.SCREEN_RATIO) * w);
        } else {
            return (value / Master.SCREEN_Y_COORDINATES * h);
        }
    }

    public double getWorldCoordsSize(double value, boolean isX) {
        if (isX) {
            return (value / Master.SCREEN_Y_COORDINATES * w);
        } else {
            return (value / Master.SCREEN_Y_COORDINATES * h);
        }
    }


    public double getMapCoords(double value, boolean isX) {
        if (isX) {
            return (position.x + value / 100d * size.x);
        } else {
            return (position.y + value / 100d * size.y);
        }
    }

    public double getMapCoordsSize(double value, boolean useX) {
        if (useX) {
            return (value / 100d * size.x);
        } else {
            return (value / 100d * size.y);
        }
    }

    public int getWorldCoordsFromLocal(double value, boolean isX) {
        return (int) getWorldCoords(getMapCoords(value, isX), isX);
    }

    public int getWorldCoordsFromLocalSize(double value, boolean useX) {
        return (int) getWorldCoordsSize(getMapCoordsSize(value, useX), useX);
    }

    public Vector2D getCenterPosition(){
        return new Vector2D(position.x + size.x, position.y + size.y);
    }
}
