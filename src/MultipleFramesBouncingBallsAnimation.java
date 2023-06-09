// Ido Levy 318949294

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.awt.Rectangle;

/**
 * Represents a multiple frames bouncing balls animation.
 */
public class MultipleFramesBouncingBallsAnimation {
    private static final Rectangle GRAY_FRAME = new Rectangle(50, 50, 450, 450);
    private static final Rectangle YELLOW_FRAME = new Rectangle(450, 450, 150, 150);
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final long MS = 17L;

    /**
     * Sets up the gray frame.
     *
     * @param args An array of Strings containing the radii of the balls in the gray frame
     * @return A MultipleBouncingBallsAnimation object representing the gray frame
     */
    private static MultipleBouncingBallsAnimation setupGrayFrame(String[] args) {
        int[] grayFrameRadii = IntegerArray.parseInt(args, 0, args.length / 2);
        IntegerArray.raise(grayFrameRadii, 1);
        IntegerArray.cap(grayFrameRadii, Math.min(GRAY_FRAME.width, GRAY_FRAME.height) / 2 - 1);
        return new MultipleBouncingBallsAnimation(grayFrameRadii, GRAY_FRAME, Color.GRAY);
    }

    /**
     * Sets up the yellow frame.
     *
     * @param args An array of Strings containing the radii of the balls in the yellow frame
     * @return A MultipleBouncingBallsAnimation object representing the yellow frame
     */
    private static MultipleBouncingBallsAnimation setupYellowFrame(String[] args) {
        int numOfBallsInYellowFrame = args.length - args.length / 2;
        int[] yellowAnimationRadii = IntegerArray.parseInt(args, args.length / 2, numOfBallsInYellowFrame);
        IntegerArray.raise(yellowAnimationRadii, 1);
        IntegerArray.cap(yellowAnimationRadii, Math.min(YELLOW_FRAME.width, YELLOW_FRAME.height) / 2 - 1);
        return new MultipleBouncingBallsAnimation(yellowAnimationRadii, YELLOW_FRAME, Color.YELLOW);
    }

    /**
     * Draws the given frame on the given DrawSurface object using the specified color.
     *
     * @param drawSurface A DrawSurface object
     * @param frame       A Rectangle object representing the frame to be drawn
     * @param color       A Color object representing the color to use when drawing the frame
     */
    private static void drawFrame(DrawSurface drawSurface, Rectangle frame, Color color) {
        drawSurface.setColor(color);
        drawSurface.fillRectangle(frame.x, frame.y, frame.width, frame.height);
    }

    /**
     * The main method that initializes the animation and draws it on the screen.
     *
     * @param args A String array containing integers representing the balls radii
     */
    public static void main(String[] args) {
        MultipleBouncingBallsAnimation grayFrame = setupGrayFrame(args);
        MultipleBouncingBallsAnimation yellowFrame = setupYellowFrame(args);
        GUI gui = new GUI("Multiple Frames Bouncing Balls Animation", WIDTH, HEIGHT);
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface drawSurface = gui.getDrawSurface();
            drawFrame(drawSurface, GRAY_FRAME, Color.GRAY);
            grayFrame.drawAnimation(drawSurface);
            drawFrame(drawSurface, YELLOW_FRAME, Color.YELLOW);
            yellowFrame.drawAnimation(drawSurface);
            gui.show(drawSurface);
            sleeper.sleepFor(MS);
        }
    }
}
