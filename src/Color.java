import java.util.Random;

/**
 * This class generates random colors.
 */
public class Color {
    private static final int MAX_RGB_VAL = 255;

    /**
     * Generates a random color.
     *
     * @return A new java.awt.Color object with random RGB values
     */
    public static java.awt.Color generate() {
        Random random = new Random();
        int r = random.nextInt(MAX_RGB_VAL);
        int g = random.nextInt(MAX_RGB_VAL);
        int b = random.nextInt(MAX_RGB_VAL);
        return new java.awt.Color(r, g, b);
    }

    /**
     * Generates a random color that is not equal to the given color.
     *
     * @param exclude A java.awt.Color object representing the color to exclude from generation
     * @return A new java.awt.Color object that is not equal to the given color
     */
    public static java.awt.Color generate(java.awt.Color exclude) {
        java.awt.Color color;
        do {
            color = generate();
        } while (color.equals(exclude));
        return color;
    }
}
