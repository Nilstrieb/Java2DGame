package objects;

import core.general.Master;
import core.math.Vector2D;

import java.awt.*;

/**
 * The GameObject class is the superclass of every GameObject that can be displayed on screen. It has the 2
 * {@link #update(Master)} and {@link #draw(Graphics2D, int, Master)} methods that have to be
 */
public abstract class GameObject {

    protected int w;
    protected int h;

    protected Vector2D position;
    protected Vector2D size;

    protected Vector2D velocity;

    public GameObject(double x, double y, double xSize, double ySize) {
        this(new Vector2D(x, y), new Vector2D(xSize, ySize));
    }

    public GameObject(Vector2D position, Vector2D size) {
        this.position = position;
        this.size = size;
        this.velocity = new Vector2D();
    }

    public abstract void draw(Graphics2D g2d, int w, Master master);
    public abstract void update(Master master);


    public double getMapCoords(double value, boolean isX){
        if (isX){
            return (position.x + value / 100 * size.x);
        } else {
            return (position.y + value / 100 * size.y);
        }
    }

    public double getMapCoordsSize(double value, boolean useX){
        if (useX){
            return (value / 100 * size.x);
        } else {
            return (value / 100 * size.y);
        }
    }

    public double getWorldCoords(double value, boolean isX){
        if (isX){
            return (value / 100 * w);
        } else {
            return (value / 100 * h);
        }
    }

    public int getWorldCoordsFromLocal(double value, boolean isX){
        return (int) getWorldCoords(getMapCoords(value, isX), isX);
    }

    public int getWorldCoordsFromLocalSize(double value, boolean useX){
        return (int) getWorldCoords(getMapCoordsSize(value, useX), useX);
    }
}
