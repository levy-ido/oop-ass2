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
     * Draws a given line segment on the canvas.
     * @param drawSurface A DrawSurface object used for drawing on the GUI object
     * @param l  A Line object representing the line segment to be drawn
     */
    public void drawLine(DrawSurface drawSurface, Line l) {
        Point start = l.start();
        Point end = l.end();
        drawSurface.drawLine((int) start.getX(), (int) start.getY(), (int) end.getX(), (int) end.getY());
    }
    /**
     * Marks the middle of a given line segment with a blue dot.
     * @param drawSurface A DrawSurface object used for drawing on the GUI object
     * @param l  A Line object representing the line segment to be marked
     */
    public void markMiddle(DrawSurface drawSurface, Line l) {
        Point middle = l.middle();
        drawSurface.setColor(Color.BLUE);
        drawSurface.fillCircle((int) middle.getX(), (int) middle.getY(), DOT_RADIUS);
        drawSurface.setColor(Color.BLACK);
    }
    /**
     * Marks a given intersection point with a red dot.
     * @param drawSurface A DrawSurface object used for drawing on the GUI object
     * @param intersectionPoint A Point object representing the intersection point between two line segments
     */
    public void markIntersectionPoint(DrawSurface drawSurface, Point intersectionPoint) {
        drawSurface.setColor(Color.RED);
        drawSurface.fillCircle((int) intersectionPoint.getX(), (int) intersectionPoint.getY(), DOT_RADIUS);
        drawSurface.setColor(Color.BLACK);
    }
    /**
     * Draws ten random line segments on a blank canvas. Marks their middle points with blue dots. Marks intersection
     * points between line segments with red dots.
     */
    public void draw() {
        Random random = new Random();
        GUI gui = new GUI("Abstract Art Drawing", WIDTH, HEIGHT);
        DrawSurface drawSurface = gui.getDrawSurface();
        Line[] lines = new Line[NUM_OF_LINES];
        for (int i = 0; i < NUM_OF_LINES; ++i) {
            lines[i] = Line.generateRandomLine(WIDTH, HEIGHT, random);
            drawLine(drawSurface, lines[i]);
            markMiddle(drawSurface, lines[i]);
        }
        for (int i = 0; i < NUM_OF_LINES - 1; ++i) {
            for (int j = i + 1; j < NUM_OF_LINES; ++j) {
                Point intersectionPoint = lines[i].intersectionWith(lines[j]);
                if (intersectionPoint != null) {
                    markIntersectionPoint(drawSurface, intersectionPoint);
                }
            }
        }
        gui.show(drawSurface);
    }
    /**
     * @param args A string array. ignored
     */
    public static void main(String[] args) {
        AbstractArtDrawing abstractArtDrawing = new AbstractArtDrawing();
        abstractArtDrawing.draw();
    }
}