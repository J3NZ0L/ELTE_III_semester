import java.awt.*;

public class Utils {

    public static Color deriveLighterColor(Color originalColor, float factor) {
        int r = originalColor.getRed();
        int g = originalColor.getGreen();
        int b = originalColor.getBlue();

        r = (int) Math.min(255, r + (255 - r) * factor);
        g = (int) Math.min(255, g + (255 - g) * factor);
        b = (int) Math.min(255, b + (255 - b) * factor);

        return new Color(r, g, b);
    }

    public static boolean pointOnSegment(Point start, Point end, Point point) {
        if (point.x >= Math.min(start.x, end.x) && point.x <= Math.max(start.x, end.x) &&
                point.y >= Math.min(start.y, end.y) && point.y <= Math.max(start.y, end.y)) {
            return (end.x - start.x) * (point.y - start.y) == (end.y - start.y) * (point.x - start.x);
        }
        return false;
    }

}
