/**
 * This class provides methods for modifying integer arrays.
 */
public class IntArrayModifier {
    /**
     * Makes all elements of the given array positive by applying the unary - operator to negative elements.
     *
     * @param array The integer array to modify
     */
    public void abs(int[] array) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i] < 0) {
                array[i] = -array[i];
            }
        }
    }

    /**
     * Caps all elements of the given array at the given maximum value.
     *
     * @param array The integer array to modify
     * @param max   An integer representing the maximum value to cap elements at
     */
    public void cap(int[] array, int max) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i] > max) {
                array[i] = max;
            }
        }
    }

    /**
     * Raises all elements of the given array to the given minimum value.
     *
     * @param array The integer array to modify
     * @param min   An integer representing the minimum value to raise elements to
     */
    public void raise(int[] array, int min) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i] < min) {
                array[i] = min;
            }
        }
    }
}