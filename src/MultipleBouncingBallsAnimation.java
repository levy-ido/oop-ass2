import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.util.Random;
/**
 * Represents a multiple bouncing balls animation.
 */
public class MultipleBouncingBallsAnimation {
    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;
    private static final int CRITICAL_RADIUS = 50;
    private final Ball[] balls;
    /**
     * Constructs a new MultipleBouncingBallsAnimation object.
     * @param radi An integer array representing the balls radi
     * @param frame A Frame object to create the balls in
     * @param random A Random object used in generating random balls
     */
    public MultipleBouncingBallsAnimation(int[] radi, Frame frame, Random random) {
        this.balls = new Ball[radi.length];
        for (int i = 0; i < this.balls.length; ++i) {
            this.balls[i] = Ball.generateRandomBall(frame, random, radi[i]);
            double speed;
            if (radi[i] < CRITICAL_RADIUS) {
                speed = (double) CRITICAL_RADIUS / (double) radi[i];
            } else {
                speed = 1.0;
            }
            double angle = random.nextDouble(360.0);
            this.balls[i].setVelocity(Velocity.fromAngleAndSpeed(angle, speed));
        }
    }
    /**
     * Draws a frame of the animation.
     * @param drawSurface A DrawSurface object to draw the animation on
     * @param frame A Frame object providing boundaries for the balls
     */
    public void draw(DrawSurface drawSurface, Frame frame) {
        for (int i = 0; i < this.balls.length; ++i) {
            this.balls[i].drawOn(drawSurface);
            this.balls[i].moveOneStep();
            this.balls[i].keepInFrame(frame);
        }
    }
    /**
     * @param args A String array containing integers representing the balls radi
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Multiple Bouncing Balls Animation", WIDTH, HEIGHT);
        int[] radi = Util.parseInt(args);
        Frame frame = new Frame(Frame.GUI_UPPER_LEFT, WIDTH, HEIGHT, null);
        Random random = new Random();
        MultipleBouncingBallsAnimation animation = new MultipleBouncingBallsAnimation(radi, frame, random);
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface drawSurface = gui.getDrawSurface();
            animation.draw(drawSurface, frame);
            gui.show(drawSurface);
            sleeper.sleepFor(Util.MS);
        }
    }
}
