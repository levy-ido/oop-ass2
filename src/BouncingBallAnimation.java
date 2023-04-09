import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;
/**
 * Represents a bouncing ball animation.
 */
public class BouncingBallAnimation {
    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;
    private static final int BALL_RADIUS = 30;
    /**
     * Draws a bouncing ball animation that starts at the given point and moves with the given velocity.
     * @param start The starting point of the ball
     * @param dx The horizontal velocity of the ball
     * @param dy The vertical velocity of the ball
     */
    private static void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("Bouncing Ball Animation", WIDTH, HEIGHT);
        Ball ball = new Ball(start.getX(), start.getY(), BALL_RADIUS, Color.BLACK);
        ball.setVelocity(dx, dy);
        Frame frame = new Frame(Frame.GUI_UPPER_LEFT, WIDTH, HEIGHT, null);
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface drawSurface = gui.getDrawSurface();
            ball.drawOn(drawSurface);
            ball.moveOneStep();
            ball.keepInFrame(frame);
            gui.show(drawSurface);
            sleeper.sleepFor(Util.MS);
        }
    }
    /**
     * Parses the command line arguments and starts the animation.
     * The command line arguments should be in the following format: [startingPointX, startingPointY, dx, dy].
     * @param args A String array representing the command line arguments
     */
    public static void main(String[] args) {
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);
        double dx = Double.parseDouble(args[2]);
        double dy = Double.parseDouble(args[3]);
        BouncingBallAnimation.drawAnimation(new Point(x, y), dx, dy);
    }
}
