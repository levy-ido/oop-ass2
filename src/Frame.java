import java.awt.Color;
import biuoop.DrawSurface;
/**
 * Represents a frame.
 */
public class Frame {
    public static final Point GUI_UPPER_LEFT = new Point(0.0, 0.0);
    private final Point upperLeftCorner;
    private final int width;
    private final int height;
    private final Color color;
    /**
     * Constructs a new Frame object with the given upper left corner, width, height and color.
     * @param upperLeftCorner A Point object representing the upper left corner of the frame
     * @param width An integer representing the frames' width
     * @param height An integer representing the frames' height
     * @param color A Color object representing the frames' color
     */
    public Frame(Point upperLeftCorner, int width, int height, Color color) {
        this.upperLeftCorner = upperLeftCorner;
        this.width = width;
        this.height = height;
        this.color = color;
    }
    /**
     * Draws this frame on a given draw surface.
     * @param drawSurface A DrawSurface object to draw this frame on
     */
    public void drawOn(DrawSurface drawSurface) {
        drawSurface.setColor(this.color);
        drawSurface.fillRectangle((int) upperLeftCorner.getX(), (int) upperLeftCorner.getY(), width, height);
    }
    /**
     * @return An integer representing this frames' width
     */
    public int getWidth() {
        return this.width;
    }
    /**
     * @return An integer representing this frames' height
     */
    public int getHeight() {
        return this.height;
    }
    /**
     * @return A Point object representing this frames' upper left corner
     */
    public Point getUpperLeftCorner() {
        return this.upperLeftCorner;
    }
}
