import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;
/**
 * Represents a bouncing ball animation.
 */
public class BouncingBallAnimation {
    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;
    /**
     * @param args [startingPointX, startingPointY, dx, dy]
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Bouncing Ball Animation", WIDTH, HEIGHT);
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);
        double dx = Double.parseDouble(args[2]);
        double dy = Double.parseDouble(args[3]);
        Ball ball = new Ball(new Point(x, y), 30, Color.BLACK);
        ball.setVelocity(dx, dy);
        Frame frame = new Frame(Frame.GUI_UPPER_LEFT, WIDTH, HEIGHT, null);
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface drawSurface = gui.getDrawSurface();
            ball.drawOn(drawSurface);
            ball.moveOneStep();
            ball.keepInFrame(frame);
            gui.show(drawSurface);
            sleeper.sleepFor(Util.MS);
        }
    }
}
