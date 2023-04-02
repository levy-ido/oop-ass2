/**
 *
 */
public class Point {
    private final double x;
    private final double y;
    /**
     * Constructor.
     * @param x Distance from the origin on the x-axis
     * @param y Distance from the origin on the y-axis
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     *
     * @param other Other point
     * @return The distance from this point to a given other point.
     */
    public double distance(Point other) {
        return Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y));
    }
    /**
     * Determines if this point and a given other point are the same point.
     * @param other Other point
     * @return true if this point and a given other point are the same point, else false
     */
    public boolean equals(Point other) {
        return Double.compare(this.x, other.x) == 0 && Double.compare(this.y, other.y) == 0;
    }
    /**
     *
     * @return this.x
     */
    public double getX() {
        return this.x;
    }
    /**
     *
     * @return this.y
     */
    public double getY() {
        return this.y;
    }
}