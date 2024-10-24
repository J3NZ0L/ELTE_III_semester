import java.io.*;
import java.util.*;
/**
 * Main program, solving the assignment.
 * @author Jeney Zoltán
 */
public class Main {

    public static void main(String[] args) {
        ArrayList<PlaneFigureEnclosableInRectangle> figures;

        try {
            // Reading from file
            figures = HelperMethods.readFiguresFromFile("figures0.txt", null);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
        catch (FileNotFoundException e) {
            System.out.println("File could not be found");
            return;
        }
        catch (InputMismatchException e){
            System.out.println("The the next token does not match the Integer regular expression, or is out of range");
            return;
        }
        catch (NoSuchElementException e) {
            System.out.println("The input is exhausted");
            return;
        }
        catch (IllegalStateException e){
            System.out.println("The input is already in use");
            return;
        }

        // Calculate which figure has the largest area, in terms of their enclosing rectangle
        ArrayList<PlaneFigureEnclosableInRectangle> largestFigures = new ArrayList<>();
        float largestArea = figures.getFirst().areaOfEnclosingRectangle();
        for (PlaneFigureEnclosableInRectangle figure : figures) {
            float area = figure.areaOfEnclosingRectangle();
            if (area > largestArea) {
                largestArea = area;
                largestFigures.clear();
                largestFigures.add(figure);
            } else if (area == largestArea) {
                largestFigures.add(figure);
            }
        }

        if (largestFigures.size() == 0) {
            System.out.println("No figures in file");
        } else if (largestFigures.size() == 1) {
            System.out.println("The figure with the largest enclosing rectangle is a " + largestFigures.getFirst() +", and has an area of: " + largestArea);
        } else {
            System.out.println("The largest figures are:");
            for (int i = 0; i < largestFigures.size(); i++) {
                System.out.println(largestFigures.get(i).toString()+ " area: "+ largestArea);
            }
        }
    }


}