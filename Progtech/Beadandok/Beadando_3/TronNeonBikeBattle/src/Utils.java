import java.awt.*;

public class Utils {

    public static Color deriveLighterColor(Color originalColor, float factor) {
        // Extract RGB components
        int r = originalColor.getRed();
        int g = originalColor.getGreen();
        int b = originalColor.getBlue();

        // Calculate the lighter color by blending with white (255)
        r = (int) Math.min(255, r + (255 - r) * factor);
        g = (int) Math.min(255, g + (255 - g) * factor);
        b = (int) Math.min(255, b + (255 - b) * factor);

        // Return the new lighter color
        return new Color(r, g, b);
    }

}
