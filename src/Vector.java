/**
 * Representing a 2D vector.
 */
public class Vector {
    private double x;
    private double y;

    /**
     * Constructs a new Vector object with the given x and y components.
     *
     * @param x A double representing the x-component of the new vector
     * @param y A double representing the y-component of the new vector
     */
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a new Vector object representing the vector pointing from point a to point b.
     *
     * @param a A Point object representing a given point
     * @param b A Point object representing another given point
     */
    public Vector(Point a, Point b) {
        this(b.getX() - a.getX(), b.getY() - a.getY());
    }

    /**
     * Calculates the cross product of this vector and another given vector.
     *
     * @param other A Vector object representing a given vector
     * @return A double representing the cross product of this vector and the given vector
     */
    public double product(Vector other) {
        return this.x * other.y - other.x * this.y;
    }

    /**
     * Scales this vector by a given scalar.
     *
     * @param scalar A double representing the scalar used to scale this vector
     */
    public void scale(double scalar) {
        this.x = scalar * this.x;
        this.y = scalar * this.y;
    }

    /**
     * @return A double representing this vectors' x-component
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return A double representing this vectors' y-component
     */
    public double getY() {
        return this.y;
    }
}