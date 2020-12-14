package core.physics.hitboxes;

import core.math.Coords;
import core.Master;
import core.math.Vector2D;

import java.awt.*;

/**
 * A rectangular hitbox
 */
public class RectHitBox extends Hitbox {

    /**
     * The corners of the rectangle like this:
     * <p>x1    x2</p>
     * <p>y1    y2</p>
     */
    private Vector2D x1, y1, x2, y2;

    /**
     * Create a new RectHitbox with the position of the top left point {@code x1} and the size
     *
     * @param x1   The top left point
     * @param size the size
     * @param isTrigger Whether the hitbox is a trigger (only collision detection, not restricting movement
     */
    public RectHitBox(Vector2D x1, Vector2D size, boolean isTrigger) {
        super(isTrigger);
        this.x1 = x1;
        this.x2 = Vector2D.add(x1, new Vector2D(size.x, 0));
        this.y1 = Vector2D.add(x1, new Vector2D(0, size.y));
        this.y2 = Vector2D.add(x1, new Vector2D(size.x, size.y));
        Master.getMaster().addDrawable(this, 1);
    }

    /**
     * Create a new RectHitbox with the position of the top left point {@code x1} and the size
     *
     * @param x1   The top left point
     * @param size the size
     */
    public RectHitBox(Vector2D x1, Vector2D size) {
        this(x1, size, false);
        this.x1 = x1;
        this.x2 = Vector2D.add(x1, new Vector2D(size.x, 0));
        this.y1 = Vector2D.add(x1, new Vector2D(0, size.y));
        this.y2 = Vector2D.add(x1, new Vector2D(size.x, size.y));
        Master.getMaster().addDrawable(this, 1);
    }

    /**
     * Move the hitbox to a new position
     *
     * @param x1 The new position
     * @param size The new size
     */
    @Override
    public void moveTo(Vector2D x1, Vector2D size) {
        this.x1 = x1.copy();
        this.x2 = Vector2D.add(x1, new Vector2D(size.x, 0));
        this.y1 = Vector2D.add(x1, new Vector2D(0, size.y));
        this.y2 = Vector2D.add(x1, new Vector2D(size.x, size.y));
    }

    @Override
    public double getSize() {
        Vector2D size = Vector2D.subtract(y2, x1);
        return size.magnitude();
    }

    /**
     * Check whether two hitboxes collide with each other
     *
     * @param o The other rectangular hitbox, should currently only be a {@link RectHitBox}
     * @return {@code true} when they collide {@code false} when they don't
     */
    public boolean collidesWith(Hitbox o) {
        if (o instanceof RectHitBox) {
            return collidesWith((RectHitBox) o);
        } else {
            return false;
        }
    }

    /**
     * Check whether two hitboxes collide with each other
     *
     * @param o The other rectangular hitbox
     * @return {@code true} when they collide {@code false} when they don't
     */
    public boolean collidesWith(RectHitBox o) {

        //all points right of x2, left of x1, above x1, below y1 -> no collision

        //if all points are right of x2
        boolean allRight = true;
        for (int i = 0; i < 4; i++) {
            if (o.getPoint(i).x < x2.x) {
                allRight = false;
                break;
            }
        }

        //if all points are left of x1
        boolean allLeft = true;
        for (int i = 0; i < 4; i++) {
            if (o.getPoint(i).x > x1.x) {
                allLeft = false;
                break;
            }
        }

        //if all points are above x1
        boolean allAbove = true;
        for (int i = 0; i < 4; i++) {
            if (o.getPoint(i).y > x1.y) {
                allAbove = false;
                break;
            }
        }

        //if all points are below y1
        boolean allBelow = true;
        for (int i = 0; i < 4; i++) {
            if (o.getPoint(i).y < y1.y) {
                allBelow = false;
                break;
            }
        }

        return !(allAbove || allBelow || allLeft || allRight);
    }

    /**
     * Get a point like this
     * <p>0  1</p>
     * <p>2  3</p>
     *
     * @param i the point number
     * @throws IllegalArgumentException when i is not between 0 and 3
     */
    public Vector2D getPoint(int i) {
        return switch (i) {
            case 0 -> x1;
            case 1 -> x2;
            case 2 -> y1;
            case 3 -> y2;
            default -> throw new IllegalArgumentException("i has to be between 0 and 3");
        };
    }

    public Vector2D getX1() {
        return x1;
    }

    public Vector2D getY1() {
        return y1;
    }

    public Vector2D getX2() {
        return x2;
    }

    public Vector2D getY2() {
        return y2;
    }

    @Override
    public String toString() {
        return "RectHitBox{" + x1 + " " + x2 + "\n" + y1 + " " + y2 + "}";
    }

    @Override
    public void draw(Graphics2D g2d) {

        Vector2D abs = Coords.getWorldCoords(x1);
        Vector2D sizeAbs = Coords.getWorldCoords(Vector2D.subtract(y2, x1));

        g2d.drawRect((int)abs.x, (int)abs.y, (int)sizeAbs.x, (int)sizeAbs.y);
        g2d.setPaint(Color.MAGENTA);
    }
}
