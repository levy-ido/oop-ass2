/**
 * This class provides methods integer arrays.
 */
public class IntegerArray {

    /**
     * Caps all elements of the given array at the given maximum value.
     *
     * @param array The integer array to modify
     * @param max   An integer representing the maximum value to cap elements at
     */
    public static void cap(int[] array, int max) {
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
    public static void raise(int[] array, int min) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i] < min) {
                array[i] = min;
            }
        }
    }

    /**
     * Parses an array of Strings into an array of integers.
     *
     * @param stringArray A String array to parse
     * @return An array of integers parsed from the given array of Strings
     */
    public static int[] parseInt(String[] stringArray) {
        int[] intArray = new int[stringArray.length];
        for (int i = 0; i < intArray.length; ++i) {
            intArray[i] = Integer.parseInt(stringArray[i]);
        }
        return intArray;
    }

    /**
     * Parses a subarray of Strings into an array of integers.
     *
     * @param stringArray The array of Strings to parse
     * @param i           An integer representing the starting index of the subarray to parse
     * @param length      An integer representing the length of the subarray to parse
     * @return An array of integers parsed from the given subarray of Strings
     */
    public static int[] parseInt(String[] stringArray, int i, int length) {
        int[] intArray = new int[length];
        for (int j = i; j < i + length; ++j) {
            intArray[j - i] = Integer.parseInt(stringArray[j]);
        }
        return intArray;
    }
}