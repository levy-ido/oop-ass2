import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;
/**
 * Represents a bouncing ball animation.
 */
public class BouncingBallAnimation {
    private static final int BALL_RADIUS = 30;
    private final Ball ball;
    /**
     * Constructs a new BouncingBallAnimation object with the given parameters.
     * @param x A double representing the ball center x-coordinate
     * @param y A double representing the ball center y-coordinate
     * @param dx A double representing the ball center x-coordinate rate of change
     * @param dy A double representing the ball center y-coordinate rate of change
     * @param color A Color object representing the balls' color
     * @param radius An integer representing the balls' radius
     */
    public BouncingBallAnimation(double x, double y, double dx, double dy, Color color, int radius) {
        this.ball = new Ball(new Point(x, y), radius, color);
        this.ball.setVelocity(dx, dy);
    }
    /**
     * Draws one frame of the animation.
     * @param drawSurface A DrawSurface object to draw the animation on
     * @param frame A Frame object to draw the animation in
     */
    private void draw(DrawSurface drawSurface, Frame frame) {
        ball.drawOn(drawSurface);
        ball.moveOneStep(frame);
    }
    /**
     * @param args [startingPointX, startingPointY, dx, dy]
     */
    public static void main(String[] args) {
        int width = 400;
        int height = 300;
        GUI gui = new GUI("Bouncing Ball Animation", width, height);
        Frame frame = new Frame(new Point(0.0, 0.0), width, height, null);
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);
        double dx = Double.parseDouble(args[2]);
        double dy = Double.parseDouble(args[3]);
        BouncingBallAnimation animation = new BouncingBallAnimation(x, y, dx, dy, Color.BLACK, BALL_RADIUS);
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface drawSurface = gui.getDrawSurface();
            animation.draw(drawSurface, frame);
            gui.show(drawSurface);
            sleeper.sleepFor(Util.MS);
        }
    }
}
