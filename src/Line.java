import biuoop.DrawSurface;

import java.awt.Color;
import java.util.Random;
/**
 * Represents a line segment between two points.
 */
public class Line {
    private final Point start;
    private final Point end;
    private final Color color;
    /**
     * Constructs a new Line object with a given start and end points.
     * Makes sure that the starting point is to the left of the ending point.
     * If the start and end points have the same x-coordinate the starting point will be below the ending point.
     * @param start A Point object representing the start of the new line segment
     * @param end A Point object representing the end of the new line segment
     * @param color A Color object representing the new line segments' color
     */
    public Line(Point start, Point end, Color color) {
        int xCmpRes = Double.compare(start.getX(), end.getX());
        int yCmpRes = Double.compare(start.getY(), end.getY());
        if (xCmpRes < 0 || xCmpRes == 0 && yCmpRes <= 0) {
            this.start = start;
            this.end = end;
        } else {
            // Der Anfang Ist Das Ende Und Das Ende Ist Der Anfang
            this.start = end;
            this.end = start;
        }
        this.color = color;
    }
    /**
     * Constructs a new Line object with the specified start and end points.
     * @param x1 A double representing the x-coordinate of the start point of the new line segment
     * @param y1 A double representing the y-coordinate of the start point of the new line segment
     * @param x2 A double representing the x-coordinate of the end point of the new line segment
     * @param y2 A double representing the y-coordinate of the end point of the new line segment
     * @param color A Color object representing the new line segments' color
     */
    public Line(double x1, double y1, double x2, double y2, Color color) {
        this(new Point(x1, y1), new Point(x2, y2), color);
    }
    /**
     * Calculates the length of this line segment.
     * @return A double representing the length of this line segment
     */
    public double length() {
        return this.start.distance(this.end);
    }
    /**
     * @return A Point object representing the point in the middle of this line segment
     */
    public Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / 2, (this.start.getY() + this.end.getY()) / 2);
    }
    /**
     * @return A Point object representing the starting point of this line segment
     */
    public Point start() {
        return this.start;
    }
    /**
     * @return A Point object representing the ending point of this line segment
     */
    public Point end() {
        return this.end;
    }
    /**
     * @return A double representing the slope of this line segment
     */
    public double slope() {
        return (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
    }
    /**
     * @return A double representing the intercept of this line segment
     */
    public double intercept() {
        return this.start.getY() - this.slope() * this.start.getX();
    }
    /**
     * Checks if this line segment is parallel to another line segment.
     * @param other A Line object representing the other line to compare for parallelism
     * @return true if this line segment is parallel to the other line segment, false otherwise
     */
    public boolean isParallel(Line other) {
        return Double.compare(this.slope(), other.slope()) == 0;
    }
    /**
     * Checks whether this line segment intersects a vertical line at the given x-coordinate.
     * @param x A double representing the x-coordinate of the vertical line to check for intersection
     * @return true if this line segment intersects the vertical line at the given x-coordinate, false otherwise
     */
    public boolean intersectsVerticalLine(double x) {
        return Double.compare(x, this.start.getX()) >= 0 && Double.compare(x, this.end.getX()) <= 0;
    }
    /**
     * Assuming there's intersection between this line segment and a given other line segment, computes the
     * x-coordinate of the intersection point.
     * @param other A Line object representing the other line to compute intersection point x-coordinate with
     * @return A double representing the intersection points' x-coordinate
     */
    public double computeIntersectionX(Line other) {
        return (other.intercept() - this.intercept()) / (this.slope() - other.slope());
    }
    /**
     * Checks whether this line segment is intersecting with another line segment.
     * @param other A Line object representing the other line segment to check for intersection with
     * @return true if this line segment intersects with the other line segment, false otherwise
     */
    public boolean isIntersecting(Line other) {
        if (this.isParallel(other)) {
            if (Double.compare(this.intercept(), other.intercept()) == 0) {
                boolean cmpRes1 = Double.compare(this.start.getX(), other.end.getX()) <= 0;
                boolean cmpRes2 = Double.compare(this.end.getX(), other.start.getX()) >= 0;
                return cmpRes1 && cmpRes2;
            }
            return false;
        }
        double intersectionX = this.computeIntersectionX(other);
        return this.intersectsVerticalLine(intersectionX) && other.intersectsVerticalLine(intersectionX);
    }
    /**
     * Returns the point of intersection between this line segment and another line segment.
     * @param other A Line object representing the other line segment to check intersection with
     * @return A Point object representing the single point of intersection if it exists, otherwise returns null
     */
    public Point intersectionWith(Line other) {
        if (!this.isIntersecting(other)) {
            return null;
        }
        if (this.isParallel(other)) {
            if (this.start.equals(other.end)) {
                return this.start;
            }
            if (this.end.equals(other.start)) {
                return this.end;
            }
            return null;
        }
        double intersectionX = this.computeIntersectionX(other);
        double intersectionY = this.slope() * intersectionX + this.intercept();
        return new Point(intersectionX, intersectionY);
    }
    /**
     * Returns whether this line segment is equal to the given line segment.
     * @param other A Line object representing the other line segment to compare to
     * @return true if the line segments are equal, false otherwise
     */
    public boolean equals(Line other) {
        return this.start.equals(other.start) && this.end.equals(other.end);
    }
    /**
     * Draws this line segment.
     * @param drawSurface A DrawSurface object used for drawing
     */
    public void drawOn(DrawSurface drawSurface) {
        drawSurface.setColor(this.color);
        drawSurface.drawLine((int) this.start.getX(),
                (int) this.start.getY(),
                (int) this.end.getX(),
                (int) this.end.getY());
    }
    /**
     * Creates a new Line object with random ends.
     * @param frame A Frame object. The new line segment will be created in this frame
     * @param random A Random object used in generating random double values
     * @param color A Color object representing the new line segments' color
     * @return A new Line object representing a random line segment
     */
    public static Line generateRandomLine(Frame frame, Random random, Color color) {
        Point start = Point.generateRandomPoint(frame, random);
        Point end = Point.generateRandomPoint(frame, random);
        return new Line(start, end, color);
    }
}