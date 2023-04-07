import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;
/**
 * Represents an abstract art drawing.
 */
public class AbstractArtDrawing {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private static final int CIRCLE_RADIUS = 3;
    private final int numOfLines;
    /**
     * Constructs a new AbstractArtDrawing object with the given number of line segments.
     * @param numOfLines An integer representing the number of line segments featured in the drawing
     */
    public AbstractArtDrawing(int numOfLines) {
        this.numOfLines = numOfLines;
    }
    /**
     * Draws random line segments in the given frame. Marks their middle points blue.
     * Marks intersection points between line segments red.
     * @param drawSurface A DrawSurface object to draw on
     * @param frame A Frame object to draw in
     * @param random A Random object to generate random line segments with
     */
    public void draw(DrawSurface drawSurface, Frame frame, Random random) {
        Line[] lines = new Line[this.numOfLines];
        for (int i = 0; i < this.numOfLines; ++i) {
            lines[i] = Line.generateRandomLine(frame, random, Color.BLACK);
            lines[i].drawOn(drawSurface);
            Util.mark(lines[i].middle(), Color.BLUE, drawSurface, CIRCLE_RADIUS);
        }
        for (int i = 0; i < this.numOfLines - 1; ++i) {
            for (int j = i + 1; j < this.numOfLines; ++j) {
                Point intersectionPoint = lines[i].intersectionWith(lines[j]);
                if (intersectionPoint != null) {
                    Util.mark(intersectionPoint, Color.RED, drawSurface, CIRCLE_RADIUS);
                }
            }
        }
    }
    /**
     * @param args A string array. ignored
     */
    public static void main(String[] args) {
        AbstractArtDrawing abstractArtDrawing = new AbstractArtDrawing(10);
        GUI gui = new GUI("Abstract Art Drawing", WIDTH, HEIGHT);
        DrawSurface drawSurface = gui.getDrawSurface();
        Frame frame = new Frame(new Point(0.0, 0.0), WIDTH, HEIGHT, null);
        Random random = new Random();
        abstractArtDrawing.draw(drawSurface, frame, random);
        gui.show(drawSurface);
    }
}