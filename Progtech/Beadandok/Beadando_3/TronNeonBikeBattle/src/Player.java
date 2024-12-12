import java.awt.*;
import java.util.ArrayList;

public class Player {
    private String name;
    private Color color;
    private Color trailColor;
    private Point currentPosition;
    private ArrayList<Point> turningPoints;

    private int dx, dy; // initial direction
    private int prevDX, prevDY;


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
        this.turningPoints = new ArrayList<Point>();
        this.turningPoints.add(new Point(this.currentPosition)); // add starting point
        this.dx = 0;
        this.dy = -cellSize; // init to upwards direction
        this.prevDX = this.dx;
        this.prevDY = this.dy;
        this.move();
        this.turningPoints.add(new Point(this.currentPosition));
    }

    public Color getTrailColor() {
        return trailColor;
    }

    public void move() {
        currentPosition.translate(dx, dy);
        // Add turning point if direction changes
        if ((dx != prevDX || dy != prevDY)) {
            turningPoints.add(new Point(currentPosition)); // Add a new point for a direction change
        } // Set it to the player's position otherwise
        else if (turningPoints.size()>1){
            turningPoints.set(turningPoints.size() - 1, new Point(currentPosition)); // Update the last point
        }
        prevDX = dx;
        prevDY = dy;
    }

}
