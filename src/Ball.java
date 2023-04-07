import biuoop.DrawSurface;
import java.awt.Color;
import java.util.Random;

/**
 * Represents a ball.
 */
public class Ball {
    private Point center;
    private final int radius;
    private final Color color;
    private Velocity velocity;
    /**
     * Constructs a new Ball object with the given center, radius and color.
     * @param center A point object representing the center of the ball
     * @param radius An integer representing the radius of the ball
     * @param color A Color object representing the color of the ball
     */
    public Ball(Point center, int radius, Color color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
    }
    /**
     * Constructs a new Ball object with the given center coordinates, radius and color.
     * @param x A double representing the x-coordinate of the balls' center
     * @param y A double representing the y-coordinate of the balls' center
     * @param radius An integer representing the radius of the ball
     * @param color A Color object representing the color of the ball
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
     * @param surface A DrawSurface object used for drawing
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
    }
    /**
     * Sets this balls' velocity to a given velocity.
     * @param velocity A Velocity object to set this balls' velocity to
     */
    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }
    /**
     * Sets this balls' velocity to a given velocity.
     * @param dx A double representing the x-coordinate rate of change
     * @param dy A double representing the y-coordinate rate of change
     */
    public void setVelocity(double dx, double dy) {
        setVelocity(new Velocity(dx, dy));
    }
    /**
     * @return A Velocity object representing this balls' velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }
    /**
     * Moves the ball one step according to its velocity. If the ball is about to leave the frame it's in it changes
     * direction before moving.
     * @param frame A Frame object representing the frame the ball is in
     */
    public void moveOneStep(Frame frame) {
        int x = this.getX();
        int y = this.getY();
        double dx = this.velocity.dx();
        double dy = this.velocity.dy();
        Point frameUpperLeftCorner = frame.getUpperLeftCorner();
        double frameUpperLeftCornerX = frameUpperLeftCorner.getX();
        double frameUpperLeftCornerY = frameUpperLeftCorner.getY();
        boolean isExitingRight = x + this.radius + dx > frameUpperLeftCornerX + frame.getWidth();
        boolean isExitingLeft = x - this.radius + dx < frameUpperLeftCornerX;
        if (isExitingRight || isExitingLeft) {
            dx = -dx;
        }
        boolean isExitingDown = y + this.radius + dy > frameUpperLeftCornerY + frame.getHeight();
        boolean isExitingUp = y - this.radius + dy < frameUpperLeftCornerY;
        if (isExitingDown || isExitingUp) {
            dy = -dy;
        }
        this.setVelocity(dx, dy);
        this.center = this.velocity.applyToPoint(this.center);
    }
    /**
     * Creates a new Ball object with a random center, a random color and a given radius.
     * @param frame A Frame object. The new Ball will be created in this frame
     * @param random A Random object used in generating random double values
     * @param radius An integer representing the new balls' radius
     * @return A new Ball object representing a random ball
     */
    public static Ball generateRandomBall(Frame frame, Random random, int radius) {
        Point upperLeftCorner = frame.getUpperLeftCorner();
        double upperLeftCornerX = upperLeftCorner.getX();
        double upperLeftCornerY = upperLeftCorner.getY();
        Point adjustedUpperLeftCorner = new Point(upperLeftCornerX + radius, upperLeftCornerY + radius);
        int adjustedWidth = frame.getWidth() - 2 * radius;
        int adjustedHeight = frame.getHeight() - 2 * radius;
        Frame adjustedFrame = new Frame(adjustedUpperLeftCorner, adjustedWidth, adjustedHeight, null);
        Point center = Point.generateRandomPoint(adjustedFrame, random);
        return new Ball(center, radius, Util.createRandomColor(random));
    }
}