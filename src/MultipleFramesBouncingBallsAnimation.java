import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

/**
 * Represents a multiple frames bouncing balls animation.
 */
public class MultipleFramesBouncingBallsAnimation {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final Point GREY_UPPER_LEFT = new Point(50.0, 50.0);
    private static final int GREY_WIDTH = 450;
    private static final int GREY_HEIGHT = 450;
    private static final Point YELLOW_UPPER_LEFT = new Point(450.0, 450.0);
    private static final int YELLOW_WIDTH = 150;
    private static final int YELLOW_HEIGHT = 150;
    /**
     * The main method that initializes the animation and draws it on the screen.
     * @param args A String array containing integers representing the balls radii
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Multiple Frames Bouncing Balls Animation", WIDTH, HEIGHT);
        Frame grayFrame = new Frame(GREY_UPPER_LEFT, GREY_WIDTH, GREY_HEIGHT, Color.GRAY);
        int[] radii = Util.parseStringArray(args);
        int halfNumberOfBalls = radii.length / 2;
        int[] grayRadi = new int[halfNumberOfBalls];
        System.arraycopy(radii, 0, grayRadi, 0, halfNumberOfBalls);
        Random rand = new Random();
        MultipleBouncingBallsAnimation grayAnim = new MultipleBouncingBallsAnimation(grayRadi, grayFrame, rand);
        Frame yellowFrame = new Frame(YELLOW_UPPER_LEFT, YELLOW_WIDTH, YELLOW_HEIGHT, Color.YELLOW);
        int ballsLeft = radii.length - halfNumberOfBalls;
        int[] yellowRadi = new int[ballsLeft];
        System.arraycopy(radii, halfNumberOfBalls, yellowRadi, 0, ballsLeft);
        MultipleBouncingBallsAnimation yellowAnim = new MultipleBouncingBallsAnimation(yellowRadi, yellowFrame, rand);
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
