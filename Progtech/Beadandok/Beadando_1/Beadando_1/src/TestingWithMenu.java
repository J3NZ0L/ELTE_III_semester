import java.io.*;
import java.util.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Testing environment, working with user-friendly command line menu, and user-given test files.
 *
 * @author Jeney Zoltán
 */
// TODO: handle wrong user input
public class TestingWithMenu {
    private static Map<String, String> figureToCharMap = new HashMap<>();
    static {
        figureToCharMap.put("C", "Circle");
        figureToCharMap.put("S", "Square");
        figureToCharMap.put("T", "Regular Triangle");
        figureToCharMap.put("H", "Regular Hexagon");
    }

    public static String getFigure(String charCode) {
        return figureToCharMap.get(charCode);
    }

    public static void main(String[] args) {
        Scanner clscanner = new Scanner(System.in);

        printMenu();
        String initiaChoice = clscanner.next();
        clscanner.nextLine();  // Consume newline
        while (!initiaChoice.matches("[1-7]")){
            initiaChoice = clscanner.next();
            clscanner.nextLine();  // Consume newline
        }
        int choice = Integer.parseInt(initiaChoice);

        while (choice != 7) {
            switch (choice) {
                case 1:
                    testFigure(clscanner,"C");
                    break;
                case 2:
                    testFigure(clscanner,"T");
                    break;
                case 3:
                    testFigure(clscanner,"S");
                    break;
                case 4:
                    testFigure(clscanner,"H");
                    break;
                case 5:
                    runLargestEnclosingRectangleTest(clscanner);
                    break;
                case 6:
                    testFaultyInput(clscanner);
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
            printMenu();
            choice = clscanner.nextInt();
            clscanner.nextLine();  // Consume newline
        }
        System.out.println("Exiting...");
        clscanner.close();
    }

    public static void printMenu(){
        System.out.println();
        System.out.println("Welcome to the Testing Environment.");
        System.out.println("Choose the test you want to run:");
        System.out.println("1. Test Circle");
        System.out.println("2. Test Regular Triangle");
        System.out.println("3. Test Square");
        System.out.println("4. Test Regular Hexagon");
        System.out.println("5. Test Largest Enclosing Rectangle");
        System.out.println("6. Test Faulty Input (Negative Radius)");
        System.out.println("7. Exit");
        System.out.println("---------------------------------------");
    }

    public static void testFigure(Scanner clScanner, String figureChar){
        System.out.println("Testing "+getFigure(figureChar)+"...");
        System.out.println("Provide input file, containing the data of a "+getFigure(figureChar)+": ");
        String filename = clScanner.nextLine();
        try {
            Tuple<PlaneFigureEnclosableInRectangle, Float> resultTuple = readFigureFromFile(figureChar, filename);
            Float expectedArea = resultTuple.getSecond();
            PlaneFigureEnclosableInRectangle planeFigure = resultTuple.getFirst();
            assert Math.abs(planeFigure.areaOfEnclosingRectangle() - expectedArea) < 0.1f
                    : getFigure(figureChar) + " test failed.";
            System.out.println(getFigure(figureChar)+" test passed.");
        } catch (Exception e){
            System.out.println("Reading the test subject was unsuccessful:");
            System.out.println(e.getMessage());
            System.out.println("Try differently.");
        }
    }

    // Black box test for largest enclosing rectangle
    public static void runLargestEnclosingRectangleTest(Scanner clscanner) {
        System.out.println("Running Largest Enclosing Rectangle Test...");
        System.out.println("Enter the file path for test objects, keeping in mind that the first line should contain the expected results (letter of largest figure, area): ");
        String filename = clscanner.nextLine();
        try (Scanner fileScanner = new Scanner(new BufferedReader(new FileReader(filename)))){
            int numOflargestFigures = fileScanner.nextInt();
            String realLargestFigureChar = fileScanner.next();
            float realLargestFigureArea = fileScanner.nextFloat();

            ArrayList<PlaneFigureEnclosableInRectangle> testFigures = HelperMethods.readFiguresFromFile(filename, fileScanner);

            // Calculate which figure has the largest area, in terms of their enclosing rectangle
            ArrayList<PlaneFigureEnclosableInRectangle> largestTestFigures = new ArrayList<>();
            float largestTestArea = testFigures.getFirst().areaOfEnclosingRectangle();
            for (PlaneFigureEnclosableInRectangle figure : testFigures) {
                float area = figure.areaOfEnclosingRectangle();
                if (area > largestTestArea) {
                    largestTestArea = area;
                    largestTestFigures.clear();
                    largestTestFigures.add(figure);
                } else if (area == largestTestArea) {
                    largestTestFigures.add(figure);
                }
            }

            switch (realLargestFigureChar) {
                case "C":
                    for (PlaneFigureEnclosableInRectangle figure : largestTestFigures){
                        assert figure instanceof Circle : "Largest figure test failed (wrong figure).";
                    }
                    break;
                case "T":
                    for (PlaneFigureEnclosableInRectangle figure : largestTestFigures){
                        assert figure instanceof RegularTriangle : "Largest figure test failed (wrong figure).";
                    }                    break;
                case "S":
                    for (PlaneFigureEnclosableInRectangle figure : largestTestFigures){
                        assert figure instanceof Square : "Largest figure test failed (wrong figure).";
                    }                    break;
                case "H":
                    for (PlaneFigureEnclosableInRectangle figure : largestTestFigures){
                        assert figure instanceof RegularHexagon : "Largest figure test failed (wrong figure).";
                    }                    break;
                default:
                    System.out.println("Unknown type is specified as largest figure.");
                    return;
            }
            /*
            if (largestTestFigures.size() != numOflargestFigures){
                throw new AssertionError("Largest figure test failed (did not find all of the largest figures)");
            }
            */
            assert largestTestFigures.size() == numOflargestFigures: "Largest figure test failed (did not find all of the largest figures)";
            assert largestTestArea == realLargestFigureArea: "Largest figure test failed (wrong area).";
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Largest Enclosing Rectangle Test passed.");
    }

    // Faulty input test for invalid radius
    public static void testFaultyInput(Scanner clscanner) {
        System.out.println("Testing Faulty Input...");
        System.out.println("Enter the file path for test objects, keeping in mind that the file should contain faulty objects;");
        String filename = clscanner.nextLine();
        try {
            HelperMethods.readFiguresFromFile(filename, null);
            assert false : "Negative radius test failed.";

        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        catch (IllegalArgumentException e) {
            System.out.println("Negative radius test passed.");
        }
    }

    public static Tuple<PlaneFigureEnclosableInRectangle, Float> readFigureFromFile(String figureType, String filename) throws Exception{
        PlaneFigureEnclosableInRectangle figure = null;
       try (Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)))) {
           if (sc.hasNext()) {
               Float expectedArea = sc.nextFloat();
               String actualType = sc.next();
               Point centerPoint = new Point(sc.nextInt(), sc.nextInt());
               float length = sc.nextFloat();
               if (!figureType.equals(actualType)){
                    throw new Exception("The given file has specified a wrong type of plane figure.");
               }
               switch (actualType) {
                   case "C":
                       try {
                           figure = new Circle(centerPoint, length);
                       } catch (IllegalArgumentException e) {
                           System.out.println(e.getMessage());
                       }
                       break;
                   case "T":
                       try {
                           figure = new RegularTriangle(centerPoint, length);
                       } catch (IllegalArgumentException e) {
                           System.out.println(e.getMessage());
                       }                        break;
                   case "S":
                       try {
                           figure = new Square(centerPoint, length);
                       } catch (IllegalArgumentException e) {
                           System.out.println(e.getMessage());
                       }                        break;
                   case "H":
                       try {
                           figure = new RegularHexagon(centerPoint, length);
                       } catch (IllegalArgumentException e) {
                           System.out.println(e.getMessage());
                       }                        break;
                   default:
                       throw new IllegalArgumentException("Unknown shape type.");
               }
               if (figure != null){
                   return new Tuple<>(figure, expectedArea);
               }
           }
       } catch (FileNotFoundException e) {
           throw new RuntimeException(e);
       }
       return null;
   }
}
