/**
 *
 */
public class Line {
    private final Point start;
    private final Point end;
    /**
     * Constructor 1. Accepts two Point instances as input
     * @param start A point representing one end of the new line segment
     * @param end A point representing the other end of the new line segment
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor 2. Accepts two sets of coordinates, one for each end of the line segment
     * @param x1 Distance from the origin to one end of the line segment on the x-axis
     * @param y1 Distance from the origin to one end of the line segment on the y-axis
     * @param x2 Distance from the origin to the other end of the line segment on the x-axis
     * @param y2 Distance from the origin to the other end of the line segment on the y-axis
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     *
     * @return The length of this line segment
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     *
     * @return The point in the middle of this line segment
     */
    public Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / 2, (this.start.getY() + this.end.getY()) / 2);
    }

    /**
     *
     * @return The "starting point" of this line segment
     */
    public Point start() {
        return this.start;
    }

    /**
     *
     * @return The "ending point" of this line segment
     */
    public Point end() {
        return this.end;
    }

    /**
     *
     * @return The slope this line
     */
    public double slope() {
        return (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
    }

    /**
     *
     * @return The intercept of this line
     */
    public double intercept() {
        return this.start.getY() - this.slope() * this.start.getX();
    }
    /**
     *
     * @param other Other line segment
     * @return true if this line segment and a given other line segment intersect, else false
     */
    public boolean isIntersecting(Line other) {
        if (this.start.getX() > other.end().getX() || this.end.getX() < other.start().getX()) {
            return false;
        }
        if (this.start.getY() > other.end().getY())
    }

    /**
     *
     * @param other Other line segment
     * @return If this line intersects with a given other line returns the intersection point, else returns null
     */
    public Point intersectionWith(Line other) {
        if (Double.compare(this.slope(), other.slope()) == 0) {
            // Parallel line segments
            // Der anfang ist das ende
            if (this.start.equals(other.end())) {
                return this.start;
            }
            if (other.start().equals(this.end)) {
                return this.end;
            }
            return null;
        }
        double intersectionX = (other.intercept() - this.intercept()) / (this.slope() - other.slope());
        if (intersectionX < this.start.getX() || intersectionX > this.end.getX()) {
            return null;
        }
        if (intersectionX < other.start().getX() || intersectionX > other.end().getX()) {
            return null;
        }
        return new Point(intersectionX, this.slope() * intersectionX + this.intercept());
    }

    // equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) { }

}