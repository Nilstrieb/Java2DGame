package core.objects.core;

import core.math.Coordinates;
import core.general.Master;
import core.math.Vector2D;
import core.rendering.renderer.Renderer;

import java.awt.*;
import java.util.ArrayList;

/**
 * The GameObject class is the superclass of every {@code GameObject} that can be displayed on screen. It has the
 * {@link #update()} and method that have to be overridden
 */
public abstract class GameObject {

    protected boolean doesDespawn = true;

    protected Vector2D position;
    protected double rotation;
    protected Vector2D size;
    protected Vector2D velocity;

    protected Color mainColor;
    protected Master master;
    protected int layer;
    private Renderer renderer;

    protected GameObject parent;
    protected ArrayList<GameObject> children = new ArrayList<>();

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
    public void startUpdate() {
        if (doesDespawn && Coordinates.outOfBounds(position, size)) {
            destroy();
        }
        update();
    }

    /**
     * <p>The update method is called every before drawing. Everything
     * that is needed for the game to work should be here in this method.</p>
     * <p>No drawing should be made in this method. The {@code debug} method can be called on the master.</p>
     * <p>This function is <i>NOT</i> intended to be called manually.</p>
     */
    protected abstract void update();


    /**
     * A simple method to move the object to a Vector2D. This method should be called instead of doing it manually.
     *
     * @param target The target position
     */
    public void moveTo(Vector2D target) {
        this.position = target;
    }


    /**
     * Destroy this {@code GameObject}
     */
    public void destroy() {
        master.destroy(this);
    }


    //COORDINATE METHODS-------------------------------------------
    /**
     * Returns the value as map coords
     *
     * @param value The value relative to the parent
     * @return The value in global map coordinates
     */
    public Vector2D getMapCoords(Vector2D value) {
        Vector2D rotated = Vector2D.rotateAround(parent.getCenterPositionLocal(), value, parent.rotation);
        double x = parent.position.x + rotated.x;
        double y = parent.position.y + rotated.y;
        return parent.getMapCoords(new Vector2D(x, y));
    }


    /**
     * Returns the value as world coordinates (only called on a parent)
     *
     * @param value The value relative to the parent
     * @return The absolute world coordinates
     */
    public Vector2D getWorldCoordsFromLocal(Vector2D value) {
        return Coordinates.getWorldCoordinates(getMapCoords(value));
    }
    //-----------------------------------------------------------

    /**
     * Get the center position of the object
     *
     * @return The center position
     */
    public Vector2D getCenterPosition() {
        return new Vector2D(position.x + size.x / 2, position.y + size.y / 2);
    }

    /**
     * Get the local center position of the object
     *
     * @return The center position
     */
    public Vector2D getCenterPositionLocal() {
        return new Vector2D(size.x / 2, size.y / 2);
    }


    public int getLayer() {
        return layer;
    }

    protected Vector2D getV2DRotation() {
        return Vector2D.getUnitVector(rotation);
    }

    /**
     * Create a new {@code GameObject}
     * @param gameObject The {@code GameObject}
     * @return The {@code GameObject}
     */
    protected <T extends GameObject> T create(T gameObject) {
        master.create(gameObject);
        return gameObject;
    }

    //----------- Getters and setters -----------------

    public Vector2D getMapPosition() {
        return getMapCoords(position);
    }

    public Vector2D getPosition() {
        return position;
    }

    public double getRotation() {
        return rotation;
    }

    public Vector2D getSize() {
        return size;
    }

    protected void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

    public void addChild(GameObject obj){
        children.add(obj);
    }

    public void removeChild(GameObject obj){
        children.remove(obj);
    }

    public GameObject getParent() {
        return parent;
    }

    public Renderer getRenderer(){
        return renderer;
    }
}
