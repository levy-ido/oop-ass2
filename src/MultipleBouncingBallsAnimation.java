// Ido Levy 318949294

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Rectangle;
import java.util.Random;

/**
 * Represents a multiple bouncing balls animation.
 */
public class MultipleBouncingBallsAnimation {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int CRITICAL_RADIUS = 50;
    private static final long MS = 17L;
    private final Ball[] balls;

    /**
     * Constructs a new MultipleBouncingBallsAnimation object.
     *
     * @param radii      An integer array of radii for the balls
     * @param frame      A Rectangle object representing the frame in which the balls will bounce
     * @param frameColor A java.awt.Color object representing the color of the frame
     */
    public MultipleBouncingBallsAnimation(int[] radii, Rectangle frame, java.awt.Color frameColor) {
        this.balls = new Ball[radii.length];
        for (int i = 0; i < this.balls.length; ++i) {
            Rectangle adjustedFrame = createAdjustedFrame(frame, radii[i]);
            Point center = Point.random(adjustedFrame);
            java.awt.Color color = Color.generate(frameColor);
            this.balls[i] = new Ball(center, radii[i], color);
            Velocity velocity = generateBallVelocity(radii[i]);
            this.balls[i].setVelocity(velocity);
            this.balls[i].setFrame(frame);
        }
    }

    /**
     * Creates an adjusted frame used to generate a random center for a ball with the given radius.
     *
     * @param frame  A Rectangle object representing the balls' frame
     * @param radius An integer representing the balls' radius
     * @return A new Rectangle object representing the adjusted frame
     */
    private static Rectangle createAdjustedFrame(Rectangle frame, int radius) {
        int x = frame.x + radius;
        int y = frame.y + radius;
        int width = frame.width - 2 * radius;
        int height = frame.height - 2 * radius;
        return new Rectangle(x, y, width, height);
    }

    /**
     * Generates a velocity for a ball with the given radius.
     *
     * @param radius An integer representing the balls' radius
     * @return The new Velocity object
     */
    private static Velocity generateBallVelocity(int radius) {
        double speed;
        if (radius < CRITICAL_RADIUS) {
            speed = (double) CRITICAL_RADIUS / (double) radius;
        } else {
            speed = 1.0;
        }
        Random random = new Random();
        double angle = random.nextDouble(360.0);
        return Velocity.fromAngleAndSpeed(angle, speed);
    }

    /**
     * The entry point of the program which animates multiple bouncing balls.
     * Accepts an array of integers as command-line arguments, representing the radii of the balls.
     *
     * @param args A String array containing integers representing the balls radii
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Multiple Bouncing Balls Animation", WIDTH, HEIGHT);
        int[] radii = IntegerArray.parseInt(args);
        IntegerArray.raise(radii, 1);
        IntegerArray.cap(radii, Math.min(WIDTH, HEIGHT) / 2 - 1);
        Rectangle frame = new Rectangle(0, 0, WIDTH, HEIGHT);
        MultipleBouncingBallsAnimation anim = new MultipleBouncingBallsAnimation(radii, frame, java.awt.Color.WHITE);
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface drawSurface = gui.getDrawSurface();
            anim.drawAnimation(drawSurface);
            gui.show(drawSurface);
            sleeper.sleepFor(MS);
        }
    }

    /**
     * Draws a frame of the animation.
     *
     * @param drawSurface A DrawSurface object
     */
    public void drawAnimation(DrawSurface drawSurface) {
        for (Ball ball : this.balls) {
            ball.drawOn(drawSurface);
            ball.moveOneStep();
        }
    }
}
