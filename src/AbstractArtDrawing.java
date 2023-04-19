// Ido Levy 318949294

import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;

/**
 * Represents an abstract art drawing.
 */
public class AbstractArtDrawing {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private static final int NUM_OF_LINES = 10;
    private static final int CIRCLE_RADIUS = 3;

    /**
     * This method generates an abstract art drawing by randomly generating lines and marking their middle points and
     * intersections.
     * The drawing is displayed on a GUI window.
     *
     * @param args A String array. This parameter is not used.
     */
    public static void main(String[] args) {
        Rectangle frame = new Rectangle(0, 0, WIDTH, HEIGHT);
        Random random = new Random();
        Line[] lines = new Line[NUM_OF_LINES];
        GUI gui = new GUI("Abstract Art Drawing", WIDTH, HEIGHT);
        DrawSurface drawSurface = gui.getDrawSurface();
        for (int i = 0; i < NUM_OF_LINES; ++i) {
            lines[i] = Line.random(frame, random);
            lines[i].drawOn(drawSurface);
            new Ball(lines[i].middle(), CIRCLE_RADIUS, Color.BLUE).drawOn(drawSurface);
        }
        for (int i = 0; i < NUM_OF_LINES - 1; ++i) {
            for (int j = i + 1; j < NUM_OF_LINES; ++j) {
                Point intersectionPoint = lines[i].intersectionWith(lines[j]);
                if (intersectionPoint != null) {
                    new Ball(intersectionPoint, CIRCLE_RADIUS, Color.RED).drawOn(drawSurface);
                }
            }
        }
        gui.show(drawSurface);
    }
}