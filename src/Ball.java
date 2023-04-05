import biuoop.DrawSurface;
/**
 * Represents a ball.
 */
public class Ball {
    private Point center;
    private final int radius;
    private final java.awt.Color brush;
    private Velocity velocity;
    /**
     * Constructs a new Ball object with the given center, radius and color.
     * @param center A point object representing the center of the ball
     * @param radius An integer representing the radius of the ball
     * @param color A Color object representing the color of the ball
     */
    public Ball(Point center, int radius, java.awt.Color color) {
        this.center = center;
        this.radius = radius;
        this.brush = color;
    }
    /**
     * Constructs a new Ball object with the given center coordinates, radius and color.
     * @param x A double representing the x coordinate of the ball's center
     * @param y A double representing the y coordinate of the ball's center
     * @param radius An integer representing the radius of the ball
     * @param color A Color object representing the color of the ball
     */
    public Ball(double x, double y, int radius, java.awt.Color color) {
        this(new Point(x, y), radius, color);
    }
    /**
     * @return An integer representing the x coordinate of the ball's center
     */
    public int getX() {
        return (int) this.center.getX();
    }
    /**
     * @return An integer representing the y coordinate of the ball's center
     */
    public int getY() {
        return (int) this.center.getY();
    }
    /**
     * @return An integer representing the radius of the ball
     */
    public int getSize() {
        return this.radius;
    }
    /**
     * @return A Color object representing the color of the ball
     */
    public java.awt.Color getColor() {
        return this.brush;
    }
    /**
     * Draws this Ball object on the given DrawSurface object.
     * @param surface A DrawSurface object to draw the ball on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }
    /**
     * Sets this ball's velocity to a given Velocity object.
     * @param velocity A Velocity object to set this ball's velocity to
     */
    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }
    /**
     * Sets this ball's velocity to a new Velocity object with the given dx and dy values.
     * @param dx A double representing the x coordinate rate of change
     * @param dy A double representing the y coordinate rate of change
     */
    public void setVelocity(double dx, double dy) {
        setVelocity(new Velocity(dx, dy));
    }
    /**
     * @return This ball's velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }
    /**
     * Applies this ball's velocity to it's center.
     * @param width An integer representing the canvas width
     * @param height An integer representing the canvas height
     */
    public void moveOneStep(int width, int height) {
        int x = this.getX();
        int y = this.getY();
        double dx = this.velocity.dx();
        double dy = this.velocity.dy();
        if (x + this.radius + dx > width || x - this.radius + dx < 0) {
            dx = -dx;
        }
        if (y + this.radius + dy > height || y - this.radius + dy < 0) {
            dy = -dy;
        }
        this.setVelocity(dx, dy);
        this.center = this.velocity.applyToPoint(this.center);
    }
}