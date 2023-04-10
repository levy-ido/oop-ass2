import biuoop.DrawSurface;
import java.awt.Color;
import java.util.Random;
/**
 * A class containing constants and utility functions.
 */
public class Util {
    private static final int MAX_RGB_VAL = 255;
    public static final long MS = 17;
    public static final double EPSILON = 1e-07;
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
     * Parses an array of strings into an array of integers.
     * @param stringArray The array of strings to parse
     * @return An array of integers
     */
    public static int[] parseStringArray(String[] stringArray) {
        int[] intArray = new int[stringArray.length];
        for (int i = 0; i < intArray.length; ++i) {
            intArray[i] = Integer.parseInt(stringArray[i]);
        }
        return intArray;
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
    /**
     * Determines whether a given double value is within a specified range.
     * @param x The double value to be checked
     * @param a A double representing the lower bound of the range
     * @param b A double representing the upper bound of the range
     * @return true if x is between a and b (inclusive), false otherwise
     */
    public static boolean isInRange(double x, double a, double b) {
        return (Util.compareDoubles(x, a) >= 0) && (Util.compareDoubles(x, b) <= 0);
    }
    /**
     * Compares two double values within a set tolerance level.
     * @param a The first double to compare
     * @param b The second double to compare
     * @return 0 if a and b are equal within the set tolerance, -1 if a is less than b, 1 otherwise
     */
    public static int compareDoubles(double a, double b) {
        if (Math.abs(a - b) < EPSILON) {
            return 0;
        }
        if (a < b) {
            return -1;
        }
        return 1;
    }
    /**
     * Checks whether an array is composed of solely natural numbers or not.
     * @param intArray An integer array
     * @return true if intArray is composed of solely natural numbers, false otherwise
     */
    public static boolean isNatural(int[] intArray) {
        for (int num: intArray) {
            if (num <= 0) {
                return false;
            }
        }
        return true;
    }
}
