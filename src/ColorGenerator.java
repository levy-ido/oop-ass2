import java.awt.Color;
import java.util.Random;

/**
 * This class generates random colors.
 */
public class ColorGenerator {
    private static final int MAX_RGB_VAL = 255;
    private final Random random;

    /**
     * Constructs a new ColorGenerator object with the given Random object.
     *
     * @param random The Random object to use for generating random numbers
     */
    public ColorGenerator(Random random) {
        this.random = random;
    }

    /**
     * Generates a random color.
     *
     * @return A new Color object with random RGB values
     */
    public Color generate() {
        return new Color(
                this.random.nextInt(MAX_RGB_VAL),
                this.random.nextInt(MAX_RGB_VAL),
                this.random.nextInt(MAX_RGB_VAL)
        );
    }

    /**
     * Generates a random color that is not equal to the given color.
     *
     * @param exclude A Color object representing the color to exclude from generation
     * @return A new Color object that is not equal to the given color
     */
    public Color generate(Color exclude) {
        boolean isEqual;
        int r, g, b;
        do {
            r = this.random.nextInt(MAX_RGB_VAL);
            g = this.random.nextInt(MAX_RGB_VAL);
            b = this.random.nextInt(MAX_RGB_VAL);
            isEqual = r == exclude.getRed() && g == exclude.getGreen() && b == exclude.getBlue();
        } while (isEqual);
        return new Color(r, g, b);
    }
}
