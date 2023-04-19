// Ido Levy 318949294

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
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
    private static final int MIN_RADIUS = 4;
    private final Ball[] balls;

    /**
     * Constructs a new MultipleBouncingBallsAnimation object with the given radii, frame, frame color and Random
     * object.
     * @param radii An integer array of radii for the balls
     * @param frame A Rectangle object representing the frame in which the balls will bounce
     * @param frameColor A Color object representing the color of the frame
     * @param random A Random object
     */
    public MultipleBouncingBallsAnimation(int[] radii, Rectangle frame, Color frameColor, Random random) {
        this.balls = new Ball[radii.length];
        ColorGenerator colorGenerator = new ColorGenerator(random);
        for (int i = 0; i < this.balls.length; ++i) {
            Rectangle adjustedFrame = new Rectangle(
                    frame.x + radii[i],
                    frame.y + radii[i],
                    frame.width - 2 * radii[i],
                    frame.height - 2 * radii[i]
            );
            this.balls[i] = new Ball(
                    Point.random(adjustedFrame, random),
                    radii[i],
                    colorGenerator.generate(frameColor)
            );
            this.balls[i].setFrame(frame);
            double speed;
            if (radii[i] < CRITICAL_RADIUS) {
                speed = (double) CRITICAL_RADIUS / (double) radii[i];
            } else {
                speed = MIN_SPEED;
            }
            this.balls[i].setVelocity(Velocity.fromAngleAndSpeed(random.nextDouble(ANGLE_BOUND), speed));
        }
    }

    /**
     * The entry point of the program which animates multiple bouncing balls.
     * Accepts an array of integers as command-line arguments, representing the radii of the balls.
     *
     * @param args A String array containing integers representing the balls radii
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Multiple Bouncing Balls Animation", WIDTH, HEIGHT);
        int[] radii = new StringArrayParser().parse(args);
        IntArrayModifier intArrayModifier = new IntArrayModifier();
        intArrayModifier.raise(radii, MIN_RADIUS);
        intArrayModifier.cap(radii, Math.min(WIDTH, HEIGHT) / 2 - 1);
        MultipleBouncingBallsAnimation multipleBouncingBallsAnimation = new MultipleBouncingBallsAnimation(
                radii,
                new Rectangle(0, 0, WIDTH, HEIGHT),
                Color.WHITE,
                new Random()
        );
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface drawSurface = gui.getDrawSurface();
            multipleBouncingBallsAnimation.drawAnimation(drawSurface);
            gui.show(drawSurface);
            sleeper.sleepFor(17);
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
