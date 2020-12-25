package core.objects.core;

import core.math.Coordinates;
import core.general.Drawable;
import core.general.Master;
import core.math.Vector2D;

import java.awt.*;

/**
 * The GameObject class is the superclass of every {@code GameObject} that can be displayed on screen. It has the 2
 * {@link #update()} and {@link #draw(Graphics2D)} methods that have to be overridden
 */
public abstract class GameObject implements Drawable {

    protected boolean doesDespawn = true;

    protected Vector2D position;
    protected double rotation;
    protected Vector2D size;
    protected Vector2D velocity;

    protected Color mainColor;

    protected Master master;

    protected int layer;

    protected GameObject parent;

    public GameObject(double x, double y, double xSize, double ySize) {
        this(new Vector2D(x, y), new Vector2D(xSize, ySize));
    }

    public GameObject(Vector2D position, Vector2D size) {
        this.position = position;
        this.size = size;
        this.velocity = new Vector2D();
        this.mainColor = Color.BLACK;
        this.master = Master.getMaster();
        this.layer = 0;
        this.parent = Coordinates.MAP_ANCHOR;
    }

    /**
     * Gets called at the start of the update method
     */
    public void startUpdate(){
        if(doesDespawn && Coordinates.outOfBounds(position, size)){
            destroy();
        }
        update();
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
        this.position = target;
    }

    /**
     * This method draws a rectangle at the current position and size
     *
     * @param g2d The Graphics2D object provided by the master
     */
    public void drawRect(Graphics2D g2d) {
        Vector2D abs = Coordinates.getWorldCoordinates(position);
        Vector2D sizeAbs = Coordinates.getWorldCoordinates(size);

        g2d.setPaint(mainColor);
        g2d.fillRect((int) abs.x, (int) abs.y, (int) sizeAbs.x, (int) sizeAbs.y);
    }

    /**
     * This method draws a rectangle at the current position and size
     *
     * @param g2d The Graphics2D object provided by the master
     */
    public void fillOval(Graphics2D g2d) {
        Vector2D abs = Coordinates.getWorldCoordinates(position);
        Vector2D sizeAbs = Coordinates.getWorldCoordinates(size);

        g2d.setPaint(mainColor);
        g2d.fillOval((int) abs.x, (int) abs.y, (int) sizeAbs.x, (int) sizeAbs.y);
    }

    /**
     * This method draws an oval at the current position and size
     *
     * @param g2d The Graphics2D object provided by the master
     */
    public void drawOval(Graphics2D g2d) {
        drawOval(g2d, "");
    }

    /**
     * This method draws an oval at the current position and size with arguments
     *
     * @param g2d The Graphics2D object provided by the master
     * @param arg Arguments like "center" for the object being drawn in the center
     */
    public void drawOval(Graphics2D g2d, String arg) {

        Vector2D abs;

        if(arg.contains("center")){
            abs = Coordinates.getWorldCoordinates(getCenterPosition());
        } else {
            abs = Coordinates.getWorldCoordinates(position);
        }

        Vector2D sizeAbs = Coordinates.getWorldCoordinates(size);

        g2d.setPaint(mainColor);
        g2d.drawOval((int) abs.x, (int) abs.y, (int) sizeAbs.x, (int) sizeAbs.y);
    }

    /**
     * This method draws a rounded rectangle at the current position and size
     *
     * @param g2d  The Graphics2D object provided by the master
     * @param arcW The arc width of the rectangle
     * @param arcH The arc height of the rectangle
     */
    public void drawRoundRect(Graphics2D g2d, int arcW, int arcH) {

        Vector2D abs = Coordinates.getWorldCoordinates(position);
        Vector2D sizeAbs = Coordinates.getWorldCoordinates(size);

        int xCenterAbs = (int) (abs.x + sizeAbs.x / 2);
        int yCenterAbs = (int) (abs.y + sizeAbs.y / 2);

        g2d.setPaint(mainColor);

        g2d.rotate(rotation, xCenterAbs, yCenterAbs);
        g2d.fillRoundRect((int) abs.x, (int) abs.y, (int) sizeAbs.x, (int) sizeAbs.y, arcW, arcH);

        g2d.rotate(-rotation, xCenterAbs, yCenterAbs);
    }

    /**
     * Destroy this {@code GameObject}
     */
    public void destroy() {
        master.destroy(this);
    }


    /**
     * Returns the value as map coords
     * @param value The value relative to the parent
     * @return The value in global map coordinates
     */
    public Vector2D getMapCoords(Vector2D value) {
        double x = parent.position.x + value.x;
        double y = parent.position.y + value.y;
        return parent.getMapCoords(new Vector2D(x, y));
    }


    /**
     * Returns the value as world coordinates (only called on a parent)
     * @param value The value relative to the parent
     * @return The absolute world coordinates
     */
    public Vector2D getWorldCoordsFromLocal(Vector2D value) {
        return Coordinates.getWorldCoordinates(getMapCoords(value));
    }

    /**
     * Get the center position of the object
     * @return The center position
     */
    public Vector2D getCenterPosition(){
        return new Vector2D(position.x + size.x / 2, position.y + size.y / 2);
    }

    /**
     * Get the render layer of the object
     * @return The render layer
     */
    public int getLayer() {
        return layer;
    }

    /**
     * Get the rotation of the object as a Vector2D
     * @return The rotation
     */
    protected Vector2D getV2DRotation(){
        return Vector2D.getUnitVector(rotation);
    }
}
