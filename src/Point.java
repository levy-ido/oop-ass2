import java.awt.Rectangle;
import java.util.Random;

/**
 * Represents a point in a two-dimensional coordinate system.
 */
public class Point {
    private final double x;
    private final double y;

    /**
     * Constructs a new Point object with the given x and y coordinates.
     *
     * @param x A double representing the x-coordinate of the new point
     * @param y A double representing the y-coordinate of the new point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a new Point object with random x and y coordinates.
     *
     * @param frame A Rectangle object. The new point will be created in this frame
     * @return A new Point object with random x and y coordinates
     */
    public static Point random(Rectangle frame) {
        Random random = new Random();
        double x = random.nextDouble(frame.x, frame.x + frame.width);
        double y = random.nextDouble(frame.y, frame.y + frame.height);
        return new Point(x, y);
    }

    /**
     * Calculates the distance between this point and a given other point.
     *
     * @param other A Point object representing the point to calculate the distance to
     * @return A double representing the distance from this point to the other point
     */
    public double distance(Point other) {
        double dx = other.x - this.x;
        double dy = other.y - this.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Compares this point with a given other point for equality. Two points are considered equal if their x and y
     * coordinates are exactly the same, up to a small difference.
     *
     * @param other A Point object representing the other point to compare to
     * @return true if the two points are equal, false otherwise
     */
    public boolean equals(Point other) {
        return Double.areEqual(this.x, other.x) && Double.areEqual(this.y, other.y);
    }

    /**
     * @return A double representing the x-coordinate of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return A double representing the y-coordinate of this point
     */
    public double getY() {
        return this.y;
    }
}