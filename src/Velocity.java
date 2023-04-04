// Velocity specifies the change in position on the `x` and the `y` axes.

/**
 * Representing a ball Object's velocity.
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
     * @param angle A double representing the direction of movement
     * @param speed A double representing the speed of movement
     * @return A new Velocity object corresponding to the given angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.cos(angle) * speed;
        double dy = Math.sin(angle) * speed;
        return new Velocity(dx, dy);
    }
    /**
     * Applies the Velocity object's data to a Point object.
     * @param p A Point object to apply this velocity to
     * @return A new Point object whose coordinates are (x + dx, y + dy)
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
    /**
     * @return A double representing this velocity's x coordinate rate of change
     */
    public double dx() {
        return dx;
    }
    /**
     * @return A double representing this velocity's y coordinate rate of change
     */
    public double dy() {
        return dy;
    }
}