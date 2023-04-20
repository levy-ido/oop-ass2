// Ido Levy 318949294

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Rectangle;

/**
 * Represents a bouncing ball animation.
 */
public class BouncingBallAnimation {
    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;
    private static final int BALL_RADIUS = 30;
    private static final long MS = 17L;

    /**
     * Draws a bouncing ball animation that starts at the given point and moves with the given velocity.
     *
     * @param start A Point object representing the starting point of the ball
     * @param dx    A double representing the horizontal velocity of the ball
     * @param dy    A double representing the vertical velocity of the ball
     */
    private static void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("Bouncing Ball Animation", WIDTH, HEIGHT);
        Ball ball = new Ball(start, BALL_RADIUS, java.awt.Color.BLACK);
        ball.setVelocity(dx, dy);
        ball.setFrame(new Rectangle(0, 0, WIDTH, HEIGHT));
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface drawSurface = gui.getDrawSurface();
            ball.drawOn(drawSurface);
            gui.show(drawSurface);
            ball.moveOneStep();
            sleeper.sleepFor(MS);
        }
    }

    /**
     * Parses the command line arguments and starts the animation.
     * The command line arguments should be in the following format: [startingPointX, startingPointY, dx, dy] where
     * startingPointX, startingPointY, dx and dy are integers.
     *
     * @param args A String array representing the command line arguments
     */
    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        if (x < BALL_RADIUS || x + BALL_RADIUS > WIDTH || y < BALL_RADIUS || y + BALL_RADIUS > HEIGHT) {
            x = BALL_RADIUS;
            y = BALL_RADIUS;
        }
        int dx = Integer.parseInt(args[2]);
        int dy = Integer.parseInt(args[3]);
        BouncingBallAnimation.drawAnimation(new Point(x, y), dx, dy);
    }
}
