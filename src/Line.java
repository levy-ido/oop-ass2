import biuoop.DrawSurface;

import java.awt.Rectangle;

/**
 * Represents a line segment between two points.
 */
public class Line {
    private static final double COLLINEAR = -1.0;
    private static final double NO_INTERSECTION = -2.0;
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
        boolean isStartLeftOfEnd = start.getX() < end.getX();
        boolean isVertical = Double.areEqual(start.getX(), end.getX());
        boolean isStartBelowEnd = start.getY() < end.getY();
        if (isStartLeftOfEnd || (isVertical && isStartBelowEnd)) {
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
     * @param frame A Rectangle object. The new line segment will be created in this frame
     * @return A new Line object representing a random line segment
     */
    public static Line random(Rectangle frame) {
        Point start = Point.random(frame);
        Point end = Point.random(frame);
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
        double x = (this.start.getX() + this.end.getX()) / 2;
        double y = (this.start.getY() + this.end.getY()) / 2;
        return new Point(x, y);
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
     * Computes the value of t1 for the intersection point between this line and
     * the specified line.
     * Refer to this video for more details:
     * <a href="https://www.youtube.com/watch?v=5FkOO1Wwb8w">Line Segment Intersection</a>
     *
     * @param other A Line object representing the line to compute the intersection with
     * @return A double representing the value of t1 for the intersection point, or COLLINEAR if the lines are
     * collinear, or NO_INTERSECTION if the lines do not intersect
     */
    private double computeT1(Line other) {
        Vector ab = new Vector(this.start, this.end);
        Vector cd = new Vector(other.start, other.end);
        if (ab.isLinearlyDependent(cd)) {
            return COLLINEAR;
        }
        Vector ac = new Vector(this.start, other.start);
        double t1 = ac.cross(cd) / ab.cross(cd);
        double t2 = -(ab.cross(ac) / ab.cross(cd));
        if (t1 < 0.0 || t1 > 1.0 || t2 < 0.0 || t2 > 1.0) {
            return NO_INTERSECTION;
        }
        return t1;
    }

    /**
     * @return A double representing the slope of this line
     */
    public double slope() {
        return (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
    }

    /**
     * @return A double representing the y-intercept of this line
     */
    public double intercept() {
        return this.start.getY() - this.slope() * this.start.getX();
    }

    /**
     * @return true if this line is vertical
     */
    public boolean isVertical() {
        return Double.areEqual(this.start.getX(), this.end.getX());
    }

    /**
     * Checks whether this line segment is intersecting with another line segment.
     *
     * @param other A Line object representing the other line segment to check for intersection with
     * @return true if this line segment intersects with the other line segment, false otherwise
     */
    public boolean isIntersecting(Line other) {
        double t1 = this.computeT1(other);
        if (t1 == NO_INTERSECTION) {
            return false;
        }
        if (t1 != COLLINEAR) {
            return true;
        }
        if (this.isVertical() && other.isVertical()) {
            boolean isThisBelowOther = this.end.getY() < other.start.getY();
            boolean isThisAboveOther = this.start.getY() > other.end.getY();
            return !isThisBelowOther && !isThisAboveOther;
        }
        if (!Double.areEqual(this.intercept(), other.intercept())) {
            return false;
        }
        boolean isThisLeftOfOther = this.end.getX() < other.start.getX();
        boolean isThisRightOfOther = this.start.getX() > other.end.getX();
        return !isThisLeftOfOther && !isThisRightOfOther;
    }

    /**
     * Returns the point of intersection between this line segment and another line segment.
     *
     * @param other A Line object representing the other line segment to compute intersection point with
     * @return A Point object representing the single point of intersection if it exists, otherwise returns null
     */
    public Point intersectionWith(Line other) {
        double t1 = this.computeT1(other);
        if (t1 == NO_INTERSECTION) {
            return null;
        }
        if (t1 != COLLINEAR) {
            double x = this.start.getX() + t1 * (this.end.getX() - this.start.getX());
            double y = this.start.getY() + t1 * (this.end.getY() - this.start.getY());
            return new Point(x, y);
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
        drawSurface.setColor(java.awt.Color.BLACK);
        int x1 = (int) this.start.getX();
        int y1 = (int) this.start.getY();
        int x2 = (int) this.end.getX();
        int y2 = (int) this.end.getY();
        drawSurface.drawLine(x1, y1, x2, y2);
    }
}