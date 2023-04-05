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
     * If the start and end points have the same x coordinate the starting point will be below the ending point.
     * @param start A Point object representing one end of the new line segment
     * @param end   A Point object representing the other end of the new line segment
     */
    public Line(Point start, Point end) {
        int xCmpRes = Double.compare(start.getX(), end.getX());
        int yCmpRes = Double.compare(start.getY(), end.getY());
        if (xCmpRes < 0 || xCmpRes == 0 && yCmpRes <= 0) {
            this.start = start;
            this.end = end;
        } else {
            this.start = end;
            this.end = start;
        }
    }
    /**
     * Constructs a new Line object with the specified start and end points.
     * @param x1 A double representing the x-coordinate of the start point of the line segment
     * @param y1 A double representing the y-coordinate of the start point of the line segment
     * @param x2 A double representing the x-coordinate of the end point of the line segment
     * @param y2 A double representing the y-coordinate of the end point of the line segment
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
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
     * @param x1 A double representing the x-coordinate of the vertical line to check for intersection
     * @return true if this line segment intersects the vertical line at the given x-coordinate, false otherwise
     */
    public boolean intersectsVerticalLine(double x1) {
        return Double.compare(x1, this.start.getX()) >= 0 && Double.compare(x1, this.end.getX()) <= 0;
    }
    /**
     * Checks whether this line segment is intersecting with another line segment.
     * @param other A Line object representing the other line segment to check for intersection with
     * @return true if this line segment intersects with the other line segment, false otherwise
     */
    public boolean isIntersecting(Line other) {
        if (this.isParallel(other)) {
            if (Double.compare(this.intercept(), other.intercept()) == 0) {
                return Double.compare(this.start.getX(), other.end.getX()) <= 0
                        && Double.compare(this.end.getX(), other.start.getX()) >= 0;
            }
            return false;
        }
        double intersectionX = (other.intercept() - this.intercept()) / (this.slope() - other.slope());
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
        double intersectionX = (other.intercept() - this.intercept()) / (this.slope() - other.slope());
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
     * Creates a new Line object with random ends.
     * @param width An integer. The new Line object's ends x coordinate will be in [0, width)
     * @param height An integer. The new Line object's ends y coordinate will be in [0, height)
     * @param random A Random object used in generating random double values
     * @return A new Line object representing a random line segment
     */
    public static Line generateRandomLine(int width, int height, Random random) {
        Point start = Point.generateRandomPoint(width, height, random);
        Point end = Point.generateRandomPoint(width, height, random);
        return new Line(start, end);
    }
}