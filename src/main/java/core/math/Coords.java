package core.math;

import core.Master;
import core.math.Vector2D;

/**
 * This class provides everything about the local coordinate system the game uses
 * <p>In this system, the screen is always 100 high and 100 * the screen ration wide.
 * This class is the used to convert these coordinates into the true Java 2D coordinates for the drawing</p>
 */
public class Coords {

    /**
     * This utility class should not be instantiated
     */
    private Coords() {
    }

    /**
     * The master object used to get the screen size
     */
    public static Master master = Master.getMaster();


    public static Vector2D getWorldCoords(Vector2D value) {
        double x = (value.x / (Master.SCREEN_Y_COORDINATES * Master.SCREEN_RATIO) * master.getW());
        double y = (value.y / Master.SCREEN_Y_COORDINATES * master.getH());
        return new Vector2D(x, y);
    }

    /**
     * Get local map coordinates (h=100) from the absolute Java 2D coordinates
     *
     * @param value The Vector2D position in Java 2D coordinates
     * @return The Vector2D position in map coordinates
     */
    public static Vector2D getMapCoordsFromWorld(Vector2D value) {
        double x = (value.x / master.getW()) * Master.SCREEN_Y_COORDINATES * Master.SCREEN_RATIO;
        double y = (value.y / master.getH()) * Master.SCREEN_Y_COORDINATES;
        return new Vector2D(x, y);
    }

    public static Vector2D getWorldCoordsSize(Vector2D value) {
        double x = (value.x / Master.SCREEN_Y_COORDINATES * master.getH());
        double y = (value.y / Master.SCREEN_Y_COORDINATES * master.getH());
        return new Vector2D(x, y);
    }

}