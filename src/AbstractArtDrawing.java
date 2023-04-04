import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

/**
 * An abstract art generator.
 */
public class AbstractArtDrawing {
    private static int width = 400; // Canvas width
    private static int height = 300; // Canvas height
    private static int dotRadius = 3; // Radius of the blue and red dots in the drawing
    private static int numOfLines = 10; // Number of lines to draw
    /**
     * @param r A Random object used in generating random double values
     * @return A Line object representing the new random line segment
     */
    public Line generateRandomLine(Random r) {
        double x1 = r.nextDouble(AbstractArtDrawing.width);
        double y1 = r.nextDouble(AbstractArtDrawing.height);
        double x2 = r.nextDouble(AbstractArtDrawing.width);
        double y2 = r.nextDouble(AbstractArtDrawing.height);
        return new Line(x1, y1, x2, y2);
    }
    /**
     * Draws a given line segment on the canvas.
     * @param ds A DrawSurface object used for drawing on the GUI object
     * @param l  A Line object representing the line segment to be drawn
     */
    public void drawLine(DrawSurface ds, Line l) {
        Point start = l.start();
        Point end = l.end();
        ds.drawLine((int) start.getX(), (int) start.getY(), (int) end.getX(), (int) end.getY());
    }
    /**
     * Marks the middle of a given line segment with a blue dot.
     * @param ds A DrawSurface object used for drawing on the GUI object
     * @param l  A Line object representing the line segment to be marked
     */
    public void markMiddle(DrawSurface ds, Line l) {
        Point middle = l.middle();
        ds.setColor(Color.BLUE);
        ds.fillCircle((int) middle.getX(), (int) middle.getY(), AbstractArtDrawing.dotRadius);
        ds.setColor(Color.BLACK);
    }
    /**
     * Marks a given intersection point with a red dot.
     * @param ds A DrawSurface object used for drawing on the GUI object
     * @param intersectionPoint A Point object representing the intersection point between two line segments
     */
    public void markIntersectionPoint(DrawSurface ds, Point intersectionPoint) {
        ds.setColor(Color.RED);
        ds.fillCircle((int) intersectionPoint.getX(), (int) intersectionPoint.getY(), AbstractArtDrawing.dotRadius);
        ds.setColor(Color.BLACK);
    }
    /**
     * Draws ten random line segments on a blank canvas. Marks their middle points with blue dots. Marks intersection
     * points between line segments with red dots.
     */
    public void draw() {
        Random r = new Random();
        GUI gui = new GUI("Abstract Art Drawing", AbstractArtDrawing.width, AbstractArtDrawing.height);
        DrawSurface ds = gui.getDrawSurface();
        Line[] lines = new Line[AbstractArtDrawing.numOfLines];
        for (int i = 0; i < AbstractArtDrawing.numOfLines; ++i) {
            lines[i] = generateRandomLine(r);
            drawLine(ds, lines[i]);
            markMiddle(ds, lines[i]);
        }
        for (int i = 0; i < AbstractArtDrawing.numOfLines - 1; ++i) {
            for (int j = i + 1; j < AbstractArtDrawing.numOfLines; ++j) {
                Point intersectionPoint = lines[i].intersectionWith(lines[j]);
                if (intersectionPoint != null) {
                    markIntersectionPoint(ds, intersectionPoint);
                }
            }
        }
        gui.show(ds);
    }
    /**
     * @param args A string array. ignored
     */
    public static void main(String[] args) {
        AbstractArtDrawing abstractArtDrawing = new AbstractArtDrawing();
        abstractArtDrawing.draw();
    }
}