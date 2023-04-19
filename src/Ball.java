import biuoop.DrawSurface;

import java.awt.Color;
import java.awt.Rectangle;

/**
 * Represents a ball.
 */
public class Ball {
    private final int radius;
    private final Color color;
    private Point center;
    private Velocity velocity;
    private Rectangle frame;

    /**
     * Constructs a new Ball object with the given center, radius and color.
     *
     * @param center A point object representing the center of the ball
     * @param radius An integer representing the radius of the ball
     * @param color  A Color object representing the color of the ball
     */
    public Ball(Point center, int radius, Color color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
    }

    /**
     * Constructs a new Ball object with the given center coordinates, radius and color.
     *
     * @param x      A double representing the x-coordinate of the balls' center
     * @param y      A double representing the y-coordinate of the balls' center
     * @param radius An integer representing the radius of the ball
     * @param color  A Color object representing the color of the ball
     */
    public Ball(double x, double y, int radius, Color color) {
        this(new Point(x, y), radius, color);
    }

    /**
     * @return An integer representing the x-coordinate of the balls' center
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return An integer representing the y-coordinate of the balls' center
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
    public Color getColor() {
        return this.color;
    }

    /**
     * Draws this Ball object on the given DrawSurface object.
     *
     * @param drawSurface A DrawSurface object
     */
    public void drawOn(DrawSurface drawSurface) {
        drawSurface.setColor(this.color);
        drawSurface.fillCircle(this.getX(), this.getY(), this.radius);
    }

    /**
     * Sets this balls' velocity to a given velocity.
     *
     * @param dx A double representing the x-coordinate rate of change
     * @param dy A double representing the y-coordinate rate of change
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * @return A Velocity object representing this balls' velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Sets this balls' velocity to a given velocity.
     *
     * @param velocity A Velocity object to set this balls' velocity to
     */
    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    /**
     * Moves the ball one step based on its current position and velocity.
     * If the ball hits a boundary, its velocity is reversed in the corresponding direction.
     */
    public void moveOneStep() {
        int x = this.getX();
        double dx = this.velocity.getDx();
        if (x - this.frame.x < this.radius || this.frame.x + this.frame.width - x < this.radius) {
            dx = -dx;
        }
        int y = this.getY();
        double dy = this.velocity.getDy();
        if (y - this.frame.y < this.radius || this.frame.y + this.frame.height - y < this.radius) {
            dy = -dy;
        }
        this.setVelocity(dx, dy);
        this.center = this.velocity.applyToPoint(this.center);
    }

    /**
     * Sets this balls' frame to a given Rectangle object.
     *
     * @param frame A given Rectangle object
     */
    public void setFrame(Rectangle frame) {
        this.frame = frame;
    }
}