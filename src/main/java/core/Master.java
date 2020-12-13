package core;

import core.math.Vector2D;
import core.physics.Collidable;
import core.physics.Collision;
import objects.DebugPos;
import objects.ships.BattleShip;
import objects.core.GameObject;
import objects.ships.Submarine;
import objects.world.Grid;
import objects.world.Wall;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The main object that controls everything
 */
public class Master extends JPanel {

    /**
     * The ratio of height to width.
     */
    public static double SCREEN_RATIO = 16f / 9f;

    /**
     * The height of the relative coordinates shown on the screen.
     */
    public static final double SCREEN_Y_COORDINATES = 100d;

    /**
     * The master object
     */
    private static Master master;

    /**
     * All GameObjects that exist
     */
    private final ArrayList<GameObject> objects;

    /**
     * All GameObjects that can be drawn
     * Has different render layers, 0 is on the bottom
     */
    private final ArrayList<ArrayList<Drawable>> drawables;

    /**
     * All physics objects that exist
     */
    private final ArrayList<Collidable> collidables;

    /**
     * Stores all GameObjects that were created during a frame
     */
    private final ArrayList<GameObject> objectBuffer;

    /**
     * Whether the left mouse button has been pressed since the last frame
     */
    private boolean mousePressed = false;

    /**
     * The current width and height of the game area
     */
    private int w, h;

    /**
     * Create a new master object
     */
    public Master() {
        master = this;

        objects = new ArrayList<>();
        objectBuffer = new ArrayList<>();
        collidables = new ArrayList<>();
        drawables = new ArrayList<>();
        drawables.add(new ArrayList<>());

        create(new Grid());


        BattleShip battleShip = new BattleShip(Color.DARK_GRAY);
        BattleShip bs = new BattleShip(140, 10, 10, 80, Color.GREEN);
        /*for (int i = 0; i < 10; i++) {
            bs.addTurret(new Turret(bs, 25, 10 * i + 1, 50, i % 5));
        }*/
        create(bs);
        create(battleShip);

        create(new Submarine(new Vector2D(), new Vector2D(5, 5)));
        create(new Wall(20, 80, 50, 2));
    }

    public static Master getMaster() {
        return master;
    }

    /**
     * The mein drawing method, handles everything about drawing
     *
     * @param g
     */
    private void doDrawing(Graphics g) {

        if (getWidth() * 9 > getHeight() * 16) {
            h = getHeight();
            w = h / 9 * 16;
        } else {
            w = getWidth();
            h = w / 16 * 9;
        }

        Graphics2D g2d = (Graphics2D) g.create();

        drawables.forEach(l -> l.forEach(o -> o.draw(g2d)));
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    /**
     * Debug a position, creates a green dot at the position
     *
     * @param pos The position
     */
    public void debugPos(Vector2D pos) {
        create(new DebugPos(pos, new Vector2D(2, 2)), 3);
    }

    /**
     * Call this method when the user pressed the left mouse button
     */
    public void mousePressed() {
        mousePressed = true;
    }

    /**
     * This method is the entry method for each frame. It handles everything about the frame
     */
    public void refresh() {
        objects.addAll(objectBuffer);
        objectBuffer.clear();
        objects.forEach(GameObject::update);
        mousePressed = false;
        repaint();
    }

    /**
     * Get the current location of the mouse relative to the frame
     *
     * @return The location of the mouse, already normalized
     */
    public Point getMouseLocation() {
        Point p = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(p, this);
        return p;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    /**
     * This method has to be called for every newly created GameObject
     *
     * @param obj The new object
     */
    public void create(GameObject obj) {
        create(obj, obj.getLayer());
    }

    /**
     * This method has to be called for every newly created GameObject
     *
     * @param obj         The new object
     * @param renderLayer The render layer the object will be put on, 0 is below everything
     */
    public void create(GameObject obj, int renderLayer) {
        objectBuffer.add(obj);
        if (obj instanceof Collidable) {
            collidables.add((Collidable) obj);
        }
        addDrawable(obj, renderLayer);

    }

    /**
     * Add a new Drawable to the render list
     *
     * @param d     The drawable
     * @param layer The layer it should be put on (>=0)
     */
    public void addDrawable(Drawable d, int layer) {

        if (layer < 0) {

            throw new IllegalArgumentException("Layer must be at least 9");
        }

        //layer exists check
        int layerDif = layer - (drawables.size() - 1);
        if (layerDif > 0) {
            for (int i = 0; i < layerDif; i++) {
                drawables.add(new ArrayList<>());
            }
        }

        drawables.get(layer).add(d);
    }


    /**
     * Check whether a collidables collide with another one
     *
     * @param collidable The collidable to be checked
     * @return True if it collides with something, false if it doesn't - Should return a Collision
     */
    public Collision doesCollide(Collidable collidable) {
        Collision collides = null;

        for (Collidable other : collidables) {
            double distance = Vector2D.distance(other.getCenterPos(), collidable.getCenterPos());

            if (other != collidable && (distance < other.getHitbox().getSize() + collidable.getHitbox().getSize())) {

                if (other.getHitbox().collidesWith(collidable.getHitbox())) {
                    collides = new Collision(collidable, other);
                }
            }
        }
        return collides;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public void destroy(GameObject gameObject) {
        objects.remove(gameObject);
        objectBuffer.remove(gameObject);
        drawables.get(gameObject.getLayer()).remove(gameObject);
        if (gameObject instanceof Collidable) {
            collidables.remove(gameObject);
        }
    }
}
