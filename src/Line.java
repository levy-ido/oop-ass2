import biuoop.DrawSurface;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;

/**
 * Represents a line segment between two points.
 */
public class Line {
    private final Point start;
    private final Point end;

    /**
     * Constructs a new Line object with a given start and end points.
     * Makes sure that the starting point is to the left of the ending point.
     * If the start and end points have the same x-coordinate the starting point will be below the ending point.
     *
     * @param start A Point object representing the start of the new line segment
     * @param end   A Point object representing the end of the new line segment
     */
    public Line(Point start, Point end) {
        int xCmpRes = Double.compare(start.getX(), end.getX());
        if (xCmpRes < 0 || xCmpRes == 0 && Double.compare(start.getY(), end.getY()) <= 0) {
            this.start = start;
            this.end = end;
        } else {
            // Der Anfang Ist Das Ende Und Das Ende Ist Der Anfang
            this.start = end;
            this.end = start;
        }
    }

    /**
     * Constructs a new Line object with the specified start and end points.
     *
     * @param x1 A double representing the x-coordinate of the start point of the new line segment
     * @param y1 A double representing the y-coordinate of the start point of the new line segment
     * @param x2 A double representing the x-coordinate of the end point of the new line segment
     * @param y2 A double representing the y-coordinate of the end point of the new line segment
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * Creates a new Line object with random ends.
     *
     * @param frame  A Rectangle object. The new line segment will be created in this frame
     * @param random A Random object used in generating random points
     * @return A new Line object representing a random line segment
     */
    public static Line random(Rectangle frame, Random random) {
        return new Line(Point.random(frame, random), Point.random(frame, random));
    }

    /**
     * Calculates the length of this line segment.
     *
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
     * Checks whether this line segment is intersecting with another line segment.
     *
     * @param other A Line object representing the other line segment to check for intersection with
     * @return true if this line segment intersects with the other line segment, false otherwise
     */
    public boolean isIntersecting(Line other) {
        OrientationCalculator orientationCalculator = new OrientationCalculator();
        int p1q1p2 = orientationCalculator.calculate(this.start, this.end, other.start);
        int p1q1q2 = orientationCalculator.calculate(this.start, this.end, other.end);
        int p2q2p1 = orientationCalculator.calculate(other.start, other.end, this.start);
        int p2q2q1 = orientationCalculator.calculate(other.start, other.end, this.end);
        if (p1q1p2 != p1q1q2 && p2q2p1 != p2q2q1) {
            return true;
        }
        if (
                p1q1p2 != OrientationCalculator.COLLINEAR
                        || p1q1q2 != OrientationCalculator.COLLINEAR
                        || p2q2p1 != OrientationCalculator.COLLINEAR
                        || p2q2q1 != OrientationCalculator.COLLINEAR
        ) {
            return false;
        }
        return Double.compare(this.end.getX(), other.start.getX()) >= 0
                && Double.compare(this.start.getX(), other.end.getX()) <= 0
                && Double.compare(this.end.getY(), other.start.getY()) >= 0
                && Double.compare(this.start.getY(), other.end.getY()) <= 0;
    }

    /**
     * Returns the point of intersection between this line segment and another line segment.
     *
     * @param other A Line object representing the other line segment to compute intersection point with
     * @return A Point object representing the single point of intersection if it exists, otherwise returns null
     */
    public Point intersectionWith(Line other) {
        Vector ab = new Vector(this.start, this.end);
        Vector cd = new Vector(other.start, other.end);
        double abCrossCd = ab.product(cd);
        if (Double.compare(abCrossCd, 0.0) != 0) {
            Vector ac = new Vector(this.start, other.start);
            double t = ac.product(cd) / abCrossCd;
            double u = -(ab.product(ac) / abCrossCd);
            if (
                    Double.compare(t, 0.0) < 0
                    || Double.compare(t, 1.0) > 0
                    || Double.compare(u, 0.0) < 0
                    || Double.compare(u, 1.0) > 0
            ) {
                return null;
            }
            ab.scale(t);
            return new Point(this.start.getX() + ab.getX(), this.start.getY() + ab.getY());
        }
        if (this.end.equals(other.start)) {
            return this.end;
        }
        if (this.start.equals(other.end)) {
            return this.start;
        }
        return null;
    }

    /**
     * Returns whether this line segment is equal to the given line segment.
     *
     * @param other A Line object representing the other line segment to compare to
     * @return true if the line segments are equal, false otherwise
     */
    public boolean equals(Line other) {
        return this.start.equals(other.start) && this.end.equals(other.end);
    }

    /**
     * Draws this line segment.
     *
     * @param drawSurface A DrawSurface object
     */
    public void drawOn(DrawSurface drawSurface) {
        drawSurface.setColor(Color.BLACK);
        drawSurface.drawLine(
                (int) this.start.getX(),
                (int) this.start.getY(),
                (int) this.end.getX(),
                (int) this.end.getY()
        );
    }
}