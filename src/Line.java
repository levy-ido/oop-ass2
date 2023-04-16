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
        int xCmpRes = Util.compareDoubles(start.getX(), end.getX());
        int yCmpRes = Util.compareDoubles(start.getY(), end.getY());
        if (xCmpRes < 0 || xCmpRes == 0 && yCmpRes <= 0) {
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
    public static Line generateRandom(Rectangle frame, Random random) {
        Point start = Point.generateRandom(frame, random);
        Point end = Point.generateRandom(frame, random);
        return new Line(start, end);
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
     * @return A Line object representing an inverted version of this line segment
     */
    public Line invert() {
        return new Line(this.start.invert(), this.end.invert());
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
     * Checks whether this line segment is intersecting with another line segment.
     *
     * @param other A Line object representing the other line segment to check for intersection with
     * @return true if this line segment intersects with the other line segment, false otherwise
     */
    public boolean isIntersecting(Line other) {
        Vector ab = new Vector(this.start, this.end);
        Vector cd = new Vector(other.start, other.end);
        if (Util.compareDoubles(ab.getX(), 0.0) == 0 && Util.compareDoubles(cd.getX(), 0.0) == 0) {
            return this.invert().isIntersecting(other.invert());
        }
        double abCrossCd = ab.crossProduct(cd);
        if (Util.compareDoubles(abCrossCd, 0.0) != 0) {
            Vector ac = new Vector(this.start, other.start);
            double t = ac.crossProduct(cd) / abCrossCd;
            double u = -(ab.crossProduct(ac) / abCrossCd);
            return Util.isInRange(t, 0.0, 1.0) && Util.isInRange(u, 0.0, 1.0);
        }
        if (Util.compareDoubles(this.intercept(), other.intercept()) != 0) {
            return false;
        }
        boolean isThisLeftOfOther = Util.compareDoubles(this.end.getX(), other.start.getX()) < 0;
        boolean isThisRightOfOther = Util.compareDoubles(this.start.getX(), other.end.getX()) > 0;
        return !isThisLeftOfOther && !isThisRightOfOther;
    }

    /**
     * Returns the point of intersection between this line segment and another line segment.
     *
     * @param other A Line object representing the other line segment to compute intersection point with
     * @return A Point object representing the single point of intersection if it exists, otherwise returns null
     */
    public Point intersectionWith(Line other) {
        if (!this.isIntersecting(other)) {
            return null;
        }
        Vector ab = new Vector(this.start, this.end);
        Vector cd = new Vector(other.start, other.end);
        double abCrossCd = ab.crossProduct(cd);
        if (Util.compareDoubles(abCrossCd, 0.0) != 0) {
            Vector ac = new Vector(this.start, other.start);
            double t = ac.crossProduct(cd) / abCrossCd;
            return this.start.add(ab.scale(t));
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
     * @param drawSurface A DrawSurface object used for drawing
     */
    public void drawOn(DrawSurface drawSurface) {
        drawSurface.setColor(Color.BLACK);
        drawSurface.drawLine(
                (int) this.start.getX(),
                (int) this.start.getY(),
                (int) this.end.getX(),
                (int) this.end.getY());
    }
}