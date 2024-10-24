import java.util.ArrayList;
import java.util.Arrays;

public class Tests {

    public static void main(String[] args) {
        runTests();
    }

    public static void runTests() {
        // White box tests
        System.out.println("Running white box tests...");

        Circle circle = new Circle(new Point(0, 0), 5);
        assert circle.areaOfEnclosingRectangle() == 100 : "Circle test failed.";

        RegularTriangle triangle = new RegularTriangle(new Point(0, 0), 6);
        assert Math.abs(triangle.areaOfEnclosingRectangle() - 15.588) < 0.001 : "Triangle test failed.";

        Square square = new Square(new Point(0, 0), 4);
        assert square.areaOfEnclosingRectangle() == 16 : "Square test failed.";

        RegularHexagon hexagon = new RegularHexagon(new Point(0, 0), 3);
        assert Math.abs(hexagon.areaOfEnclosingRectangle() - 27) < 0.001 : "Hexagon test failed.";

        // Black box tests
        System.out.println("Running black box tests...");

        //Largest enclosing rectangle
        ArrayList<PlaneFigureEnclosableInRectangle> testFigures = new ArrayList<>(Arrays.asList(
                new Circle(new Point(0, 0), 5),
                new RegularTriangle(new Point(0, 0), 6),
                new Square(new Point(0, 0), 4),
                new RegularHexagon(new Point(0, 0), 3)
        ));

        PlaneFigureEnclosableInRectangle largestTestFigure = null;
        float largestTestArea = 0;

        for (PlaneFigureEnclosableInRectangle figure : testFigures) {
            float area = figure.areaOfEnclosingRectangle();
            if (area > largestTestArea) {
                largestTestArea = area;
                largestTestFigure = figure;
            }
        }

        assert largestTestFigure instanceof Circle : "Largest figure test failed (wrong figure).";
        assert largestTestArea == 100 : "Largest figure test failed (wrong area).";

        // Faulty input (negative required attribute length) throws IllegalArgumentException
        try {
            new Circle(new Point(0, 0), -5);
            // if control gets here, it means that the exception hasn't been thrown, hence the test failed
            assert false : "Negative radius test failed.";
        } catch (IllegalArgumentException e) {
            System.out.println("Negative radius test passed.");
        }

        System.out.println("All tests passed.");
    }
}
