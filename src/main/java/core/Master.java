package core;

import objects.DebugPos;
import objects.ships.BattleShip;
import objects.GameObject;
import objects.ships.Submarine;
import objects.ships.Turret;
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

    @Deprecated
    public static final double SCREEN_X_COORDINATES = 100d * SCREEN_RATIO;

    /**
     * All GameObjects that exist
     */
    private final ArrayList<GameObject> objects;

    /**
     * Stores all GameObjects that were created during a frame
     */
    private final ArrayList<GameObject> objectBuffer;

    private boolean mousePressed = false;

    /**
     * Create a new master object
     */
    public Master() {
        objects = new ArrayList<>();
        objectBuffer = new ArrayList<>();

        objects.add(new Grid());


        BattleShip battleShip = new BattleShip(Color.DARK_GRAY);
        BattleShip bs = new BattleShip(70, 10, 5, 80, Color.GREEN);
        /*for (int i = 0; i < 10; i++) {
            bs.addTurret(new Turret(bs, 25, 10 * i + 1, 50, i % 5));
        }*/
        objects.add(bs);
        objects.add(battleShip);

        objects.add(new Submarine(new Vector2D(), new Vector2D(20, 20)));
        objects.add(new Wall(20, 80, 50, 2));
    }

    /**
     * The mein drawing method, handles everything about drawing
     * @param g
     */
    private void doDrawing(Graphics g) {

        int w, h;
        if (getWidth() * 9 > getHeight() * 16) {
            h = getHeight();
            w = h / 9 * 16;
        } else {
            w = getWidth();
            h = w / 16 * 9;
        }

        Graphics2D g2d = (Graphics2D) g.create();

        objects.forEach(o -> o.draw(g2d, w, this));
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    /**
     * Debug a position, creates a green dot at the position
     * @param pos
     */
    public void debugPos(Vector2D pos){
        create(new DebugPos(pos, new Vector2D(10, 10)));
        System.out.println(pos);
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
        objects.forEach(t -> t.update(this));
        mousePressed = false;
        repaint();
    }

    /**
     * Get the current location of the mouse relative to the frame
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
     * @param obj
     */
    public void create(GameObject obj) {
        objectBuffer.add(obj);
    }
}
