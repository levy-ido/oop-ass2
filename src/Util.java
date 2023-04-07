import biuoop.DrawSurface;

import java.awt.Color;
import java.util.Random;
/**
 * A class containing constants and utility functions.
 */
public class Util {
    private static final int MAX_RGB_VAL = 255;
    public static final long MS = 17;
    /**
     * Creates a random Color object.
     * @param random A Random object used in generating random int values
     * @return A Color object with random rgb values
     */
    public static Color createRandomColor(Random random) {
        int r = random.nextInt(MAX_RGB_VAL);
        int g = random.nextInt(MAX_RGB_VAL);
        int b = random.nextInt(MAX_RGB_VAL);
        return new Color(r, g, b);
    }
    /**
     * @param integers A String array containing integers
     * @return An integer array containing the above integers
     */
    public static int[] parseInt(String[] integers) {
        int[] parsedIntegers = new int[integers.length];
        for (int i = 0; i < parsedIntegers.length; ++i) {
            parsedIntegers[i] = Integer.parseInt(integers[i]);
        }
        return parsedIntegers;
    }
    /**
     * Marks a given point with a small circle.
     * @param point A Point object representing the point to be marked
     * @param color A Color object representing the color to mark with
     * @param drawSurface A DrawSurface object used for drawing
     * @param circleRadius An integer representing the circles' radius
     */
    public static void mark(Point point, Color color, DrawSurface drawSurface, int circleRadius) {
        drawSurface.setColor(color);
        drawSurface.fillCircle((int) point.getX(), (int) point.getY(), circleRadius);
    }
}
