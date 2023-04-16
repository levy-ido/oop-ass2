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
    private static final double MIN_SPEED = 1.0;
    private static final double ANGLE_BOUND = 360.0;
    private static final int MIN_RADIUS = 3;
    private static final int MAX_RADIUS = 150;
    private final Ball[] balls;
    /**
     * Constructs a new MultipleBouncingBallsAnimation object.
     * @param radii An integer array representing the balls radii
     * @param frame A Rectangle object to create the balls in
     * @param random A Random object used in generating random balls and angles
     */
    public MultipleBouncingBallsAnimation(int[] radii, Rectangle frame, Random random) {
        this.balls = new Ball[radii.length];
        for (int i = 0; i < this.balls.length; ++i) {
            this.balls[i] = Ball.generateRandom(frame, random, radii[i]);
            double speed;
            if (radii[i] <= CRITICAL_RADIUS) {
                speed = (double) CRITICAL_RADIUS / (double) radii[i];
            } else {
                speed = MIN_SPEED;
            }
            double angle = random.nextDouble(ANGLE_BOUND);
            this.balls[i].setVelocity(Velocity.fromAngleAndSpeed(angle, speed));
        }
    }
    /**
     * Draws a frame of the animation.
     * @param drawSurface A DrawSurface object to draw the animation on
     * @param frame A Rectangle object providing boundaries for the balls
     */
    public void drawAnimation(DrawSurface drawSurface, Rectangle frame) {
        for (int i = 0; i < this.balls.length; ++i) {
            this.balls[i].drawOn(drawSurface);
            this.balls[i].moveOneStep();
            this.balls[i].keepInFrame(frame);
        }
    }
    /**
     * The entry point of the program which animates multiple bouncing balls.
     * Accepts an array of integers as command-line arguments, representing the radii of the balls.
     * @param args A String array containing integers representing the balls radii
     */
    public static void main(String[] args) {
        int[] radii = Util.parseStringArray(args);
        if (!Util.isInRange(radii, MIN_RADIUS, MAX_RADIUS)) {
            System.out.println("One of the balls radius is not right");
            System.out.print("Try entering values in (" + MIN_RADIUS + ", " + MAX_RADIUS + ")");
            return;
        }
        GUI gui = new GUI("Multiple Bouncing Balls Animation", WIDTH, HEIGHT);
        Rectangle frame = new Rectangle(0, 0, WIDTH, HEIGHT);
        Random random = new Random();
        MultipleBouncingBallsAnimation animation = new MultipleBouncingBallsAnimation(radii, frame, random);
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface drawSurface = gui.getDrawSurface();
            animation.drawAnimation(drawSurface, frame);
            gui.show(drawSurface);
            sleeper.sleepFor(Util.MS);
        }
    }
}
