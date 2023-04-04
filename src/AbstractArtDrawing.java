import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

/**
 * An abstract art generator.
 */
public class AbstractArtDrawing {
    private static final int WIDTH = 400; // Canvas WIDTH
    private static final int HEIGHT = 300; // Canvas HEIGHT
    private static final int DOT_RADIUS = 3; // Radius of the blue and red dots in the drawing
    private static final int NUM_OF_LINES = 10; // Number of lines to draw
    /**
     * @param r A Random object used in generating random double values
     * @return A Line object representing the new random line segment
     */
    public Line generateRandomLine(Random r) {
        double x1 = r.nextDouble(AbstractArtDrawing.WIDTH);
        double y1 = r.nextDouble(AbstractArtDrawing.HEIGHT);
        double x2 = r.nextDouble(AbstractArtDrawing.WIDTH);
        double y2 = r.nextDouble(AbstractArtDrawing.HEIGHT);
        return new Line(x1, y1, x2, y2);
    }
    /**
     * Draws a given line segment on the canvas.
     * @param d A DrawSurface object used for drawing on the GUI object
     * @param l  A Line object representing the line segment to be drawn
     */
    public void drawLine(DrawSurface d, Line l) {
        Point start = l.start();
        Point end = l.end();
        d.drawLine((int) start.getX(), (int) start.getY(), (int) end.getX(), (int) end.getY());
    }
    /**
     * Marks the middle of a given line segment with a blue dot.
     * @param d A DrawSurface object used for drawing on the GUI object
     * @param l  A Line object representing the line segment to be marked
     */
    public void markMiddle(DrawSurface d, Line l) {
        Point middle = l.middle();
        d.setColor(Color.BLUE);
        d.fillCircle((int) middle.getX(), (int) middle.getY(), AbstractArtDrawing.DOT_RADIUS);
        d.setColor(Color.BLACK);
    }
    /**
     * Marks a given intersection point with a red dot.
     * @param d A DrawSurface object used for drawing on the GUI object
     * @param intersectionPoint A Point object representing the intersection point between two line segments
     */
    public void markIntersectionPoint(DrawSurface d, Point intersectionPoint) {
        d.setColor(Color.RED);
        d.fillCircle((int) intersectionPoint.getX(), (int) intersectionPoint.getY(), AbstractArtDrawing.DOT_RADIUS);
        d.setColor(Color.BLACK);
    }
    /**
     * Draws ten random line segments on a blank canvas. Marks their middle points with blue dots. Marks intersection
     * points between line segments with red dots.
     */
    public void draw() {
        Random r = new Random();
        GUI gui = new GUI("Abstract Art Drawing", AbstractArtDrawing.WIDTH, AbstractArtDrawing.HEIGHT);
        DrawSurface d = gui.getDrawSurface();
        Line[] lines = new Line[AbstractArtDrawing.NUM_OF_LINES];
        for (int i = 0; i < AbstractArtDrawing.NUM_OF_LINES; ++i) {
            lines[i] = generateRandomLine(r);
            drawLine(d, lines[i]);
            markMiddle(d, lines[i]);
        }
        for (int i = 0; i < AbstractArtDrawing.NUM_OF_LINES - 1; ++i) {
            for (int j = i + 1; j < AbstractArtDrawing.NUM_OF_LINES; ++j) {
                Point intersectionPoint = lines[i].intersectionWith(lines[j]);
                if (intersectionPoint != null) {
                    markIntersectionPoint(d, intersectionPoint);
                }
            }
        }
        gui.show(d);
    }
    /**
     * @param args A string array. ignored
     */
    public static void main(String[] args) {
        AbstractArtDrawing abstractArtDrawing = new AbstractArtDrawing();
        abstractArtDrawing.draw();
    }
}