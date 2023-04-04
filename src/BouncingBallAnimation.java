import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * Draws a bouncing ball animation according to specification.
 */
public class BouncingBallAnimation {
    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;
    private static final int BALL_RADIUS = 30;
    private static void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("Bouncing Ball Animation", WIDTH, HEIGHT);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(start, BALL_RADIUS, java.awt.Color.BLACK);
        ball.setVelocity(dx, dy);
        while (true) {
            ball.moveOneStep(BouncingBallAnimation.WIDTH, BouncingBallAnimation.HEIGHT);
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
    /**
     * Runs drawAnimation with the arguments from the command line.
     * @param args [startingPointX, startingPointY, dx, dy]
     */
    public static void main(String[] args) {
        double x = Double.parseDouble(args[0]);
        if (x - BALL_RADIUS < 0 || x + BALL_RADIUS > WIDTH) {
            x = BALL_RADIUS;
        }
        double y = Double.parseDouble(args[1]);
        if (y - BALL_RADIUS < 0 || y + BALL_RADIUS > HEIGHT) {
            y = BALL_RADIUS;
        }
        double dx = Double.parseDouble(args[2]);
        double dy = Double.parseDouble(args[3]);
        BouncingBallAnimation.drawAnimation(new Point(x, y), dx, dy);
    }
}
