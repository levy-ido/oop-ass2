import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.util.Random;
import java.awt.Color;
/**
 * Draws a multiple bouncing balls animation according to specification.
 */
public class MultipleBouncingBallsAnimation {
    private static final int WIDTH = 480;
    private static final int HEIGHT = 600;
    private static final int MAX_RGB_VAL = 255;
    private static final long MS = 17; // About right to get 60FPS
    /**
     * @param args A String array containing integers that represent radi sizes
     * @return An integer array containing the same integers
     */
    public int[] getRadi(String[] args) {
        int[] radi = new int[args.length];
        for (int i = 0; i < args.length; ++i) {
            radi[i] = Integer.parseInt(args[i]);
        }
        return radi;
    }
    /**
     * Creates a random Color object.
     * @param random A Random object used in generating random int values
     * @return A Color object with random rgb values
     */
    public Color createRandomColor(Random random) {
        int r = random.nextInt(MAX_RGB_VAL);
        int g = random.nextInt(MAX_RGB_VAL);
        int b = random.nextInt(MAX_RGB_VAL);
        return new Color(r, g, b);
    }
    /**
     * Creates a new Ball array. Each ball's center is a random point on the canvas. The balls radi are given as an
     * argument. The balls are colored randomly.
     * @param radi An integer array representing the balls radi.
     * @param random A Random object used to generate random points
     * @return A new Ball array with random centers, colors and the specified radi
     */
    public Ball[] createBalls(int[] radi, Random random) {
        Ball[] balls = new Ball[radi.length];
        for (int i = 0; i < radi.length; ++i) {
            Point center = Point.generateRandomPoint(WIDTH - 2 * radi[i], HEIGHT - 2 * radi[i], random);
            center = new Point(center.getX() + radi[i], center.getY() + radi[i]);
            balls[i] = new Ball(center, radi[i], createRandomColor(random));
        }
        return balls;
    }
    /**
     * Sets the balls speed according to their size, and the balls direction randomly.
     * @param balls A Ball array to set velocities to
     * @param random A Random object used to generate random directions
     */
    public void setBallsVelocities(Ball[] balls, Random random) {
        for (int i = 0; i < balls.length; ++i) {
            int radius = balls[i].getSize();
            double speed;
            if (radius < 50) {
                speed = 50.0 / radius;
            } else {
                speed = 1.0;
            }
            double angle = random.nextDouble(360.0);
            balls[i].setVelocity(Velocity.fromAngleAndSpeed(angle, speed));
        }
    }
    /**
     * Draws the animation.
     * @param args A String array representing the command line input.
     */
    public void draw(String[] args) {
        int[] radi = getRadi(args);
        Random random = new Random();
        Ball[] balls = createBalls(radi, random);
        setBallsVelocities(balls, random);
        GUI gui = new GUI("Multiple Bouncing Balls Animation", WIDTH, HEIGHT);
        Sleeper sleeper = new Sleeper();
        while (true) {
            for (int i = 0; i < balls.length; ++i) {
                balls[i].moveOneStep(WIDTH, HEIGHT);
            }
            DrawSurface drawSurface = gui.getDrawSurface();
            for (int i = 0; i < balls.length; ++i) {
                balls[i].drawOn(drawSurface);
            }
            gui.show(drawSurface);
            sleeper.sleepFor(MS);
        }
    }
    /**
     *
     * @param args An array of integers representing ball radi
     */
    public static void main(String[] args) {
        MultipleBouncingBallsAnimation multipleBouncingBallsAnimation = new MultipleBouncingBallsAnimation();
        multipleBouncingBallsAnimation.draw(args);
    }
}
