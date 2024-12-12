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


    /**
     * Retrieves the x-coordinate of the player's current position.
     *
     * @return the x-coordinate of the current position as an integer.
     */
    public int getCurrentPositionX() {
        return currentPosition.x;
    }

    /**
     * Retrieves the Y-coordinate of the player's current position.
     *
     * @return the Y-coordinate of the current position.
     */
    public int getCurrentPositionY(){
        return currentPosition.y;
    }

    /**
     * Retrieves the current position of the player as a Point object.
     * A new Point instance is returned to ensure encapsulation and immutability
     * of the player's current position.
     *
     * @return the current position of the player as a Point object.
     */
    public Point getCurrentPosition() {
        return new Point(currentPosition);
    }

    /**
     * Retrieves a deep copy of the list of turning points.
     * Turning points are the positions where the direction of movement changes.
     *
     * @return An ArrayList containing deep copies of the turning points as Point objects.
     */
    public ArrayList<Point> getTurningPoints() {
        ArrayList<Point> deepCopy = new ArrayList<>();
        for (Point p : turningPoints) {
            deepCopy.add(new Point(p));
        }
        return deepCopy;
    }

    /**
     * Sets the horizontal movement direction of the player.
     *
     * @param dx the horizontal movement step, where a positive value represents
     *           movement to the right, a negative value represents movement to the left,
     *           and zero represents no horizontal movement.
     */
    public void setDx(int dx) {
        this.dx = dx;
    }

    /**
     * Sets the vertical movement direction of the player.
     *
     * @param dy the new vertical direction value to be assigned
     */
    public void setDy(int dy) {
        this.dy = dy;
    }

    /**
     * Retrieves the color associated with the player.
     *
     * @return the player's color.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Retrieves the name of the player.
     *
     * @return the name of the player as a String.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the horizontal movement magnitude (dx) of the player.
     *
     * @return The current horizontal movement value of the player.
     */
    public int getDx() {
        return dx;
    }

    /**
     * Retrieves the current vertical movement value of the player.
     *
     * @return the current vertical movement value (dy).
     */
    public int getDy() {
        return dy;
    }

    /**
     * Retrieves the size of the turningPoints list, indicating the number of turning points.
     *
     * @return the number of turning points currently stored.
     */
    public int getTurningPointsSize(){
        return turningPoints.size();
    }

    /**
     * Retrieves a turning point at the specified index from the list of turning points.
     *
     * @param index the index of the turning point to be retrieved
     * @return the turning point at the specified index
     */
    public Point getElementOfTurningPoints(int index){
        return turningPoints.get(index);
    }

    /**
     * Constructs a Player object with the specified name, color, trail color, starting position, and cell size.
     *
     * @param name        the name of the player
     * @param color       the color of the player
     * @param trailColor  the color of the player's trail
     * @param startX      the starting x-coordinate of the player
     * @param startY      the starting y-coordinate of the player
     * @param cellSize    the size of the grid cell for movement
     */
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

    /**
     * Retrieves the color of the player's trail.
     *
     * @return the trail color of the player as a Color object.
     */
    public Color getTrailColor() {
        return trailColor;
    }

    /**
     * Updates the current position of the player based on the movement vector (dx, dy).
     *
     * If the movement direction changes (i.e., the current dx, dy values differ from the previous dx, dy),
     * a new turning point is added to the list of turning points to record this change in direction.
     *
     * If the movement direction remains the same, the last turning point in the list is updated
     * to reflect the player's current position.
     *
     * This method modifies the player's position and turning points to reflect ongoing movement
     * and trajectory changes.
     */
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
