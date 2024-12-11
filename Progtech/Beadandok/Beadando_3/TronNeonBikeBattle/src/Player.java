import java.awt.*;
import java.util.ArrayList;

public class Player {
    String name;
    Color color;
    Color trailColor;  // New attribute for trail color
    Point currentPosition;
    Point prevPosition;
    ArrayList<Point> turningPoints = new ArrayList<>();

    int dx, dy; // Initial direction
    int prevDX, prevDY;

    public Point getPrevPosition() {
        return prevPosition;
    }

    public int getCurrentPositionX() {
        return currentPosition.x;
    }

    public int getCurrentPositionY(){
        return currentPosition.y;
    }

    public Point getCurrentPosition() {
        return new Point(currentPosition);
    }

    public ArrayList<Point> getTurningPoints() {
        ArrayList<Point> deepCopy = new ArrayList<>();
        for (Point p : turningPoints) {
            deepCopy.add(new Point(p));
        }
        return deepCopy;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public void setPrevDX(int prevDX) {
        this.prevDX = prevDX;
    }

    public void setPrevDY(int prevDY) {
        this.prevDY = prevDY;
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public int getPrevDX() {
        return prevDX;
    }

    public int getPrevDY() {
        return prevDY;
    }

    public int getTurningPointsSize(){
        return turningPoints.size();
    }

    public Point getElementOfTurningPoints(int index){
        return turningPoints.get(index);
    }

    public Player(String name, Color color, Color trailColor, int startX, int startY, int cellSize) {
        this.name = name;
        this.color = color;
        this.trailColor = trailColor;
        this.currentPosition = new Point(startX, startY);
        this.prevPosition = new Point(currentPosition);
        this.turningPoints = new ArrayList<Point>();
        this.turningPoints.add(new Point(this.currentPosition)); // Add starting position
        this.dx = 0;
        this.dy = -cellSize; // Initial direction
        this.prevDX = this.dx;
        this.prevDY = this.dy;
        this.move();
        this.turningPoints.add(new Point(this.currentPosition));
    }

    public Color getTrailColor() {
        return trailColor;
    }

    public void move() {
        prevPosition = new Point(currentPosition);
        currentPosition.translate(dx, dy);
        // Add turning point if direction change
        //System.out.println("dx: " + dx + " dy: " + dy + " prevdx: " + prevDX + " prevdy: " + prevDY);
        if ((dx != prevDX || dy != prevDY)) {
            //System.out.println("belep");
            turningPoints.add(new Point(currentPosition)); // Add a new point for a direction change
        } // Set it to the player's position otherwise
        else if (turningPoints.size()>1){
            System.out.println("Belep az atrakasba");
            System.out.println(turningPoints);
            turningPoints.set(turningPoints.size() - 1, new Point(currentPosition)); // Update the last point
        }
        prevDX = dx;
        prevDY = dy;
    }


}
