// Ido Levy 318949294

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

/**
 * Represents a multiple frames bouncing balls animation.
 */
public class MultipleFramesBouncingBallsAnimation {
    private static final Point GREY_UPPER_LEFT = new Point(50.0, 50.0);
    private static final int GREY_WIDTH = 450;
    private static final int GREY_HEIGHT = 450;
    private static final Point YELLOW_UPPER_LEFT = new Point(450.0, 450.0);
    private static final int YELLOW_WIDTH = 150;
    private static final int YELLOW_HEIGHT = 150;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int MIN_RADIUS = 3;
    private static final int MAX_RADIUS = 38;

    /**
     * Builds a multiple bouncing balls animation with the given radii for the balls.
     *
     * @param radii An array of integers representing the radii of the balls
     * @param frame The frame where the animation should take place
     * @param rand  A Random object used to randomize the balls' starting positions and velocities
     * @param balls The number of balls to animate
     * @param from  The starting index of the balls' radii in the radii array
     * @return A MultipleBouncingBallsAnimation object representing the animation
     */
    public static MultipleBouncingBallsAnimation build(int[] radii, Frame frame, Random rand, int balls, int from) {
        int[] frameRadii = new int[balls];
        System.arraycopy(radii, from, frameRadii, 0, balls);
        return new MultipleBouncingBallsAnimation(frameRadii, frame, rand);

    }

    /**
     * The main method that initializes the animation and draws it on the screen.
     *
     * @param args A String array containing integers representing the balls radii
     */
    public static void main(String[] args) {
        Frame grayFrame = new Frame(GREY_UPPER_LEFT, GREY_WIDTH, GREY_HEIGHT, Color.GRAY);
        int[] radii = Util.parseStringArray(args);
        if (!Util.isInRange(radii, MIN_RADIUS, MAX_RADIUS)) {
            System.out.println("One of the balls radius is not right");
            System.out.print("Try entering values in (" + MIN_RADIUS + " ," + MAX_RADIUS + ")");
            return;
        }
        Random rand = new Random();
        int half = radii.length / 2;
        MultipleBouncingBallsAnimation grayAnim = build(radii, grayFrame, rand, half, 0);
        Frame yellowFrame = new Frame(YELLOW_UPPER_LEFT, YELLOW_WIDTH, YELLOW_HEIGHT, Color.YELLOW);
        MultipleBouncingBallsAnimation yellowAnim = build(radii, yellowFrame, rand, radii.length - half, half);
        GUI gui = new GUI("Multiple Frames Bouncing Balls Animation", WIDTH, HEIGHT);
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface drawSurface = gui.getDrawSurface();
            grayFrame.drawOn(drawSurface);
            grayAnim.drawAnimation(drawSurface, grayFrame);
            yellowFrame.drawOn(drawSurface);
            yellowAnim.drawAnimation(drawSurface, yellowFrame);
            gui.show(drawSurface);
            sleeper.sleepFor(Util.MS);
        }
    }
}
