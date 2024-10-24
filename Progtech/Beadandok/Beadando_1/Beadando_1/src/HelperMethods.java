import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class HelperMethods {
    public static ArrayList<PlaneFigureEnclosableInRectangle> readFiguresFromFile(String filename, Scanner filescanner) throws FileNotFoundException, IllegalArgumentException {
        ArrayList<PlaneFigureEnclosableInRectangle> figures = new ArrayList<>();
        // Reading from file
        if (filescanner == null) {
            filescanner = new Scanner(new BufferedReader(new FileReader(filename)));
        }
        int numFigures = filescanner.nextInt();
        while (filescanner.hasNext()) {
            PlaneFigureEnclosableInRectangle figure;

            String type = filescanner.next();
            Point centerPoint = new Point(filescanner.nextFloat(), filescanner.nextFloat());
            float length = filescanner.nextFloat();
            figure = null;
            switch (type) {
                case "C":
                    figure = new Circle(centerPoint, length);
                    break;
                case "T":
                    figure = new RegularTriangle(centerPoint, length);
                    break;
                case "S":
                    figure = new Square(centerPoint, length);
                    break;
                case "H":
                    figure = new RegularHexagon(centerPoint, length);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown shape type.");
            }
            if (figure != null){
                figures.add(figure);
            }
        }

        filescanner.close();

        return figures;

    }
}
