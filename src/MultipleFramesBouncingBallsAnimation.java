import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

/**
 * Represents a multiple frames bouncing balls animation.
 */
public class MultipleFramesBouncingBallsAnimation {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    /**
     * @param args A String array containing integers representing the balls radi
     */
    public static void main(String[] args) {
        Frame grayFrame = new Frame(new Point(50.0, 50.0), 450, 450, Color.GRAY);
        Frame yellowFrame = new Frame(new Point(450.0, 450.0), 150, 150, Color.YELLOW);
        int[] radi = Util.parseInt(args);
        int halfNumberOfBalls = radi.length / 2;
        int[] grayRadi = new int[halfNumberOfBalls];
        System.arraycopy(radi, 0, grayRadi, 0, halfNumberOfBalls);
        int restOfBalls = radi.length - halfNumberOfBalls;
        int[] yellowRadi = new int[restOfBalls];
        System.arraycopy(radi, halfNumberOfBalls, yellowRadi, 0, restOfBalls);
        Random rand = new Random();
        MultipleBouncingBallsAnimation grayAnim = new MultipleBouncingBallsAnimation(grayRadi, grayFrame, rand);
        MultipleBouncingBallsAnimation yellowAnim = new MultipleBouncingBallsAnimation(yellowRadi, yellowFrame, rand);
        GUI gui = new GUI("Multiple Frames Bouncing Balls Animation", WIDTH, HEIGHT);
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface drawSurface = gui.getDrawSurface();
            grayFrame.drawOn(drawSurface);
            grayAnim.draw(drawSurface, grayFrame);
            yellowFrame.drawOn(drawSurface);
            yellowAnim.draw(drawSurface, yellowFrame);
            gui.show(drawSurface);
            sleeper.sleepFor(Util.MS);
        }
    }
}
