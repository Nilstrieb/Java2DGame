package core.math;


import java.awt.*;

/**
 * A 2-dimensional Vector that can be used to store position or velocity or size or whatever
 */
public class Vector2D {

    public static final int CLOCKWISE = -1;
    public static final int COUNTERCLOCKWISE = 1;

    /**
     * The x and y values of the vector
     */
    public double x, y;

    /**
     * Create a new Vector2D object
     *
     * @param x the x value
     * @param y the y value
     */
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Create a new empty Vector2D object
     */
    public Vector2D() {
        this(0, 0);
    }

    /**
     * Get a new Vector2D from a Point
     * @param point The point
     * @return The Vector2D
     */
    public static Vector2D fromPoint(Point point) {
        return new Vector2D(point.x, point.y);
    }


    /**
     * Add another Vector to this vector, modifies this object
     *
     * @param a The other Vector2D
     * @return this after the addition
     */
    public Vector2D add(Vector2D a) {
        x += a.x;
        y += a.y;
        return this;
    }

    /**
     * Multiply this vector with a simple factor, modifies this object
     *
     * @param a The multiplying factor
     * @return this after the multiplication
     */
    public Vector2D multiply(double a) {
        x *= a;
        y *= a;
        return this;
    }

    /**
     * Get the magnitude of the vector
     *
     * @return The magnitude of this vector
     */
    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * Get the normalized value of this vector, with magnitude 1, does not modify this object
     *
     * @return The normalized value
     */
    public Vector2D normalized() {
        double mag = magnitude();
        if (mag == 0) {
            return new Vector2D();
        } else {
            return new Vector2D(x / mag, y / mag);
        }
    }

    /**
     * Get the negative value of this vector, does not modify this object
     *
     * @return The negative value
     */
    public Vector2D negative() {
        return new Vector2D(-x, -y);
    }

    /**
     * Get the rotation of the {@code Vector2D} in radians
     * @return the rotation in radians
     */
    public double getRotation() {
        return Math.atan2(y, x);
    }


    /**
     * Add two Vectors
     *
     * @param a Vector a
     * @param b Vector b
     * @return The result
     */
    public static Vector2D add(Vector2D a, Vector2D b) {
        return new Vector2D(a.x + b.x, a.y + b.y);
    }

    /**
     * Subtract two Vectors
     *
     * @param a Vector a
     * @param b Vector b
     * @return The result
     */
    public static Vector2D subtract(Vector2D a, Vector2D b) {
        return new Vector2D(a.x - b.x, a.y - b.y);
    }

    /**
     * Divide a Vector by a scalar
     *
     * @param a Vector a
     * @param b Scalar b
     * @return The result
     */
    public static Vector2D divideS(Vector2D a, double b) {
        return new Vector2D(a.x / b, a.y / b);
    }

    /**
     * Divide a Vector by a Vector
     * @param a Vector a
     * @param b Vector b
     * @return The result
     */
    public static Vector2D divide(Vector2D a, Vector2D b) {
        return new Vector2D(a.x / b.x, a.y / b.y);
    }

    /**
     * Rotate a point around another point
     * <p>
     * This method can now be trusted
     *
     * @param center            The center of the rotation
     * @param value             The point to be rotated, absolut to the center
     * @param rotation          The rotation angle in radians
     * @param rotationDirection The direction, -1 for clockwise, 1 for counterclockwise
     * @return The rotated point
     */
    public static Vector2D rotateAround(Vector2D center, Vector2D value, double rotation, int rotationDirection) {

        Vector2D dif = Vector2D.subtract(value, center);

        rotation = rotationDirection * rotation;

        double rotatedX = dif.x * Math.cos(rotation) - dif.y * Math.sin(rotation);
        double rotatedY = dif.x * Math.sin(rotation) + dif.y * Math.cos(rotation);

        return new Vector2D(rotatedX + center.x, rotatedY + center.y);
    }

    /**
     * Rotate a point around another point
     * <p>
     * This method can now be trusted
     *
     * @param center   The center of the rotation
     * @param value    The point to be rotated, absolut to the center
     * @param rotation The rotation angle in radians
     * @return The rotated point
     */
    public static Vector2D rotateAround(Vector2D center, Vector2D value, double rotation) {
        return rotateAround(center, value, rotation, COUNTERCLOCKWISE);
    }


    /**
     * Get a unit vector with magnitude 1 and the direction
     *
     * @param direction The direction of the vector
     * @return The unit vector
     */
    public static Vector2D getUnitVector(double direction) {
        return rotateAround(new Vector2D(), new Vector2D(0, 1), direction);
    }

    /**
     * Get the distance between two {@code Vector2D}
     * @param a The first {@code Vector2D}
     * @param b The second {@code Vector2D}
     * @return The distance between the 2 {@code Vector2D}
     */
    public static double distance(Vector2D a, Vector2D b) {
        Vector2D dif = subtract(a, b);
        return Math.sqrt(dif.x * dif.x + dif.y + dif.y);
    }

    public static Vector2D zero() {
        return new Vector2D(0, 0);
    }


    /**
     * Copy this object
     *
     * @return A copy of this object
     */
    public Vector2D copy() {
        return new Vector2D(x, y);
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    /**
     * Overrides the equals method. True when the x and y values match
     *
     * @param o The other Vector
     * @return True if both x and values are the same
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector2D vector2D = (Vector2D) o;

        if (Double.compare(vector2D.x, x) != 0) return false;
        return Double.compare(vector2D.y, y) == 0;
    }
}