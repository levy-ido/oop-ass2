import biuoop.DrawSurface;

import java.awt.Rectangle;
import java.awt.Color;
import java.util.Random;

/**
 * Represents a ball.
 */
public class Ball {
    private final int radius;
    private final Color color;
    private Point center;
    private Velocity velocity;

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
     * Creates a new Ball object with a random center, a random color and a given radius.
     *
     * @param frame  A Rectangle object representing the frame to create the ball in
     * @param random A Random object used in generating a random point and color
     * @param radius An integer representing the new balls' radius
     * @return A new Ball object representing a random ball
     */
    public static Ball generateRandom(Rectangle frame, Random random, int radius) {
        Rectangle adjustedFrame = new Rectangle(frame.x + radius,
                frame.y + radius,
                frame.width - 2 * radius,
                frame.height - 2 * radius);
        Point center = Point.generateRandom(adjustedFrame, random);
        return new Ball(center, radius, Util.createRandomColor(random));
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
     * @param drawSurface A DrawSurface object used for drawing
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
     * Moves the ball one step according to its velocity.
     */
    public void moveOneStep() {
        this.center = this.velocity.applyToPoint(this.center);
    }

    /**
     * Keeps this ball from leaving the given frame.
     *
     * @param frame A Rectangle object providing boundaries for this ball
     */
    public void keepInFrame(Rectangle frame) {
        int x = this.getX();
        double dx = this.velocity.getDx();
        int y = this.getY();
        double dy = this.velocity.getDy();
        if (x - this.radius < frame.x || x + this.radius > frame.x + frame.width) {
            dx = -dx;
        }
        if (y - this.radius < frame.y || y + this.radius > frame.y + frame.height) {
            dy = -dy;
        }
        this.setVelocity(dx, dy);
    }
}