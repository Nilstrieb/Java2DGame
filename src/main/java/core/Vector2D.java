package core;

/**
 * A 2-dimensional Vector that can be used to store position or velocity
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
        x = 0;
        y = 0;
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
     * Mulitply this vector with a simple scalar, modifies this object
     *
     * @param a The scalar
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
     * Add two Vectors
     * @param a Vector a
     * @param b Vector b
     * @return The result
     */
    public static Vector2D add(Vector2D a, Vector2D b) {
        return new Vector2D(a.x + b.x, a.y + b.y);
    }

    /**
     * Subtract two Vectors
     * @param a Vector a
     * @param b Vector b
     * @return The result
     */
    public static Vector2D subtract(Vector2D a, Vector2D b) {
        return new Vector2D(a.x - b.x, a.y - b.y);
    }

    /**
     * Rotate a point around another point
     * @param center The center of the rotation
     * @param value The point to be rotated, absolut to the center
     * @param rotation The rotation angle in radians
     * @param rotationDirection The direction, -1 for clockwise, 1 for counterclockwise
     * @return The rotated point
     */
    public static Vector2D rotateAround(Vector2D center, Vector2D value, double rotation, int rotationDirection){

        Vector2D dif = Vector2D.subtract(value, center);

        double rotatedX = Math.cos(rotation * rotationDirection) * dif.x;
        double rotatedY = Math.sin(rotation * rotationDirection) * dif.x;

        return new Vector2D(rotatedX + center.x, rotatedY + center.y);
    }

    public Vector2D copy(){
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