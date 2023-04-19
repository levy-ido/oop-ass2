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
    private static final Rectangle GRAY_FRAME = new Rectangle(50, 50, 450, 450);
    private static final Rectangle YELLOW_FRAME = new Rectangle(450, 450, 150, 150);
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int MIN_RADIUS = 4;

    /**
     * The main method that initializes the animation and draws it on the screen.
     *
     * @param args A String array containing integers representing the balls radii
     */
    public static void main(String[] args) {
        Random random = new Random();
        StringArrayParser stringArrayParser = new StringArrayParser();
        int[] grayAnimationRadii = stringArrayParser.parse(args, 0, args.length / 2);
        IntArrayModifier intArrayModifier = new IntArrayModifier();
        intArrayModifier.raise(grayAnimationRadii, MIN_RADIUS);
        intArrayModifier.cap(grayAnimationRadii, Math.min(GRAY_FRAME.width, GRAY_FRAME.height) / 2 - 1);
        MultipleBouncingBallsAnimation grayAnimation = new MultipleBouncingBallsAnimation(
                grayAnimationRadii,
                GRAY_FRAME,
                Color.GRAY,
                random
        );
        int[] yellowAnimationRadii = stringArrayParser.parse(args, args.length / 2);
        intArrayModifier.raise(yellowAnimationRadii, MIN_RADIUS);
        intArrayModifier.cap(yellowAnimationRadii, Math.min(YELLOW_FRAME.width, YELLOW_FRAME.height) / 2 - 1);
        MultipleBouncingBallsAnimation yellowAnimation = new MultipleBouncingBallsAnimation(
                yellowAnimationRadii,
                YELLOW_FRAME,
                Color.YELLOW,
                random
        );
        GUI gui = new GUI("Multiple Frames Bouncing Balls Animation", WIDTH, HEIGHT);
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface drawSurface = gui.getDrawSurface();
            drawSurface.setColor(Color.GRAY);
            drawSurface.fillRectangle(GRAY_FRAME.x, GRAY_FRAME.y, GRAY_FRAME.width, GRAY_FRAME.height);
            grayAnimation.drawAnimation(drawSurface);
            drawSurface.setColor(Color.YELLOW);
            drawSurface.fillRectangle(YELLOW_FRAME.x, YELLOW_FRAME.y, YELLOW_FRAME.width, YELLOW_FRAME.height);
            yellowAnimation.drawAnimation(drawSurface);
            gui.show(drawSurface);
            sleeper.sleepFor(17);
        }
    }
}
