// Ido Levy 318949294

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;

/**
 * Represents a multiple frames bouncing balls animation.
 */
public class MultipleFramesBouncingBallsAnimation {
    private static final Rectangle GRAY = new Rectangle(50, 50, 450, 450);
    private static final Rectangle YELLOW = new Rectangle(450, 450, 150, 150);
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int MIN_RADIUS = 3;
    private static final int MAX_RADIUS = 38;

    /**
     * Builds a multiple bouncing balls animation with the given radii for the balls.
     *
     * @param radii An array of integers representing the radii of the balls
     * @param frame The frame where the animation should take place
     * @param rand  A Random object used in building the animation
     * @param balls The number of balls to animate
     * @param from  The starting index of the balls' radii in the radii array
     * @return A MultipleBouncingBallsAnimation object representing the animation
     */
    public static MultipleBouncingBallsAnimation build(int[] radii, Rectangle frame, Random rand, int balls, int from) {
        int[] animRadii = new int[balls];
        System.arraycopy(radii, from, animRadii, 0, balls);
        return new MultipleBouncingBallsAnimation(animRadii, frame, rand);

    }

    /**
     * The main method that initializes the animation and draws it on the screen.
     *
     * @param args A String array containing integers representing the balls radii
     */
    public static void main(String[] args) {
        int[] radii = Util.parseStringArray(args);
        if (!Util.isInRange(radii, MIN_RADIUS, MAX_RADIUS)) {
            System.out.println("One of the balls radius is not right");
            System.out.print("Try entering values in (" + MIN_RADIUS + " ," + MAX_RADIUS + ")");
            return;
        }
        Random rand = new Random();
        int half = radii.length / 2;
        MultipleBouncingBallsAnimation grayAnim = build(radii, GRAY, rand, half, 0);
        MultipleBouncingBallsAnimation yellowAnim = build(radii, YELLOW, rand, radii.length - half, half);
        GUI gui = new GUI("Multiple Frames Bouncing Balls Animation", WIDTH, HEIGHT);
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface drawSurface = gui.getDrawSurface();
            drawSurface.setColor(Color.GRAY);
            drawSurface.fillRectangle(GRAY.x, GRAY.y, GRAY.width, GRAY.height);
            grayAnim.drawAnimation(drawSurface, GRAY);
            drawSurface.setColor(Color.YELLOW);
            drawSurface.fillRectangle(YELLOW.x, YELLOW.y, YELLOW.width, YELLOW.height);
            yellowAnim.drawAnimation(drawSurface, YELLOW);
            gui.show(drawSurface);
            sleeper.sleepFor(Util.MS);
        }
    }
}
