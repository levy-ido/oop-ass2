/**
 * This class parses an array of Strings into an array of the desired data type.
 */
public class StringArrayParser {
    /**
     * Parses an array of Strings into an array of integers.
     *
     * @param stringArray A String array to parse
     * @return An array of integers parsed from the given array of Strings
     */
    public int[] parse(String[] stringArray) {
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
    public int[] parse(String[] stringArray, int i, int length) {
        int[] intArray = new int[length];
        for (int j = i; j < i + length; ++j) {
            intArray[j - i] = Integer.parseInt(stringArray[j]);
        }
        return intArray;
    }

    /**
     * Parses a subarray of Strings into an array of integers.
     *
     * @param stringArray The array of Strings to parse
     * @param i           An integer representing the starting index of the subarray to parse
     * @return An array of integers parsed from the given subarray of strings
     */
    public int[] parse(String[] stringArray, int i) {
        int[] intArray = new int[stringArray.length - i];
        for (int j = i; j < stringArray.length; ++j) {
            intArray[j - i] = Integer.parseInt(stringArray[j]);
        }
        return intArray;
    }
}
