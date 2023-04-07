/**
 * Representing an objects' velocity.
 */
public class Velocity {
    private final double dx;
    private final double dy;
    /**
     * Constructs a new Velocity object with the given dx and dy values.
     * @param dx A double representing the x coordinate rate of change
     * @param dy A double representing the y coordinate rate of change
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**
     * Constructs a new Velocity object corresponding to the given angle and speed.
     * @param angle A double representing an angle in degrees
     * @param speed A double representing speed
     * @return A new Velocity object corresponding to the given angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.cos(angle) * speed;
        double dy = Math.sin(angle) * speed;
        return new Velocity(dx, dy);
    }
    /**
     * Applies this velocity to a point.
     * @param point A Point object to apply this velocity to
     * @return A new Point object representing the given points' location after applying this velocity to it
     */
    public Point applyToPoint(Point point) {
        return new Point(point.getX() + this.dx, point.getY() + this.dy);
    }
    /**
     * @return A double representing this velocitys' x coordinate rate of change
     */
    public double dx() {
        return dx;
    }
    /**
     * @return A double representing this velocitys' y coordinate rate of change
     */
    public double dy() {
        return dy;
    }
}