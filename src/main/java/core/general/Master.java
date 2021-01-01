package core.general;

import core.rendering.RenderEngine;
import core.math.Vector2D;
import core.objects.core.CollGameObject;
import core.physics.Collidable;
import core.physics.Collision;
import core.objects.base.DebugPos;
import core.objects.core.GameObject;
import custom.Init;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The main object that controls everything
 */
public class Master {

    public static double SCREEN_RATIO = 16f / 9f;

    public static final double SCREEN_Y_COORDINATES = 100d;

    private static Master master;

    private final List<GameObject> objects;

    private final List<Collidable> collidables;

    private final List<GameObject> objectBuffer;

    private final List<Collidable> collidablesBuffer;

    private RenderEngine renderEngine;


    public Master() {
        master = this;

        objects = new ArrayList<>();
        objectBuffer = new ArrayList<>();
        collidables = new ArrayList<>();
        collidablesBuffer = new ArrayList<>();
    }

    /**
     * Called once after the constructor
     */
    public void init() {
        renderEngine = new RenderEngine();
    }

    public static Master getMaster() {
        return master;
    }


    /**
     * Debug a position, creates a green dot at the position
     *
     * @param pos The position
     */
    public static void debugPos(Vector2D pos) {
        debugPos(pos, Long.MAX_VALUE);
    }

    /**
     * Debug a position, creates a green dot at the position
     *
     * @param pos The position
     * @param lifeTime The lifetime of the {@code DebugPos} in ms
     */
    public static void debugPos(Vector2D pos, long lifeTime) {
        master.debugPosObj(pos, lifeTime);
    }

    private void debugPosObj(Vector2D pos, long lifeTime) {
        create(new DebugPos(pos, new Vector2D(2, 2), lifeTime));
    }


    /**
     * This method is the entry method for each frame. It handles everything about the frame
     */
    public void refresh() {
        long time = System.currentTimeMillis();
        Input.calculate();
        objects.clear();
        objects.addAll(objectBuffer);
        collidables.clear();
        collidables.addAll(collidablesBuffer);
        objects.forEach(GameObject::startUpdate);
        long time2 = System.currentTimeMillis();
        Input.frameReset();
        renderEngine.flush();
        renderEngine.repaint();
        System.out.println("Frame took " + (System.currentTimeMillis() - time) + "ms, " + (time2 - time) + "ms for update, " + (System.currentTimeMillis() - time2) + "ms for draw");
    }

    /**
     * Get the current location of the mouse relative to the frame
     *
     * @return The location of the mouse, normalized in J2D coordinates
     */
    public Point getMouseLocation() {
        Point p = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(p, renderEngine);
        return p;
    }


    /**
     * This method has to be called for every newly created GameObject
     *
     * @param obj The new object
     */
    public <T extends GameObject> T create(T obj) {
        objectBuffer.add(obj);
        if (obj instanceof CollGameObject) {
            collidablesBuffer.add((Collidable) obj);
        }
        if(obj.getRenderer() == null){
            System.out.println(obj);
            throw new NullPointerException("oh god oh fuck its null wtf pls no");
        }
        renderEngine.addRenderer(obj.getRenderer());
        return obj;
    }

    public void destroy(GameObject gameObject) {
        objectBuffer.remove(gameObject);
        gameObject.getParent().removeChild(gameObject);

        renderEngine.removeRenderer(gameObject.getRenderer());

        if (gameObject instanceof Collidable) {
            collidablesBuffer.remove(gameObject);

            if (Init.DEBUG_MODE) {
                //add hitbox renderer
            }
        }
    }

    /**
     * Check whether a collidables collide with another one
     *
     * @param collidable The collidable to be checked
     * @return True if it collides with something, false if it doesn't - Should return a Collision
     */
    public Collision doesCollide(Collidable collidable) {
        Collision collision = null;

        for (Collidable other : collidables) {

            if (!collidable.getIgnores().contains(other.getClass()) && !other.isTrigger()) {  //ONLY calculate when it's not ignored

                double distance = Vector2D.distance(other.getCenterPos(), collidable.getCenterPos());
                if (other != collidable && (distance < other.getHitbox().getSize() + collidable.getHitbox().getSize())) {

                    if (other.getHitbox().collidesWith(collidable.getHitbox())) {
                        collision = new Collision(collidable, other);
                        if(collision.isTrigger()){
                            collidable.onTrigger();
                        } else {
                            collidable.onCollision();
                        }
                    }
                }
            }
        }
        return collision;
    }

    public int getW() {
        return renderEngine.getW();
    }

    public int getH() {
        return renderEngine.getH();
    }

    public RenderEngine getRenderEngine() {
        return renderEngine;
    }
}