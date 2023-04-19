/**
 * This class provides a method for comparing double values with a specified precision.
 */
public class DoubleComparer {
    private static final double EPSILON = 10e-05;

    /**
     * Compares two double values with a specified precision.
     *
     * @param x A double representing the first value to compare
     * @param y A double representing the second value to compare
     * @return 0 if the two values are considered equal,
     * -1 if the first value is less than the second value,
     * 1 if the first value is greater than the second value
     */
    public int compare(double x, double y) {
        if (Math.abs(x - y) < EPSILON) {
            return 0;
        }
        if (x < y) {
            return -1;
        }
        return 1;
    }
}