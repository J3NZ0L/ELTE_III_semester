import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * TronModel is a class that represents the logic and state of a Tron-like
 * game. It maintains the game state, handles player interactions, and checks
 * for collisions during the game cycle.
 */
public class TronModel {

    public Player player1, player2;

    private boolean running = false;

    public Timer gameTimer;

    private boolean collisionCheckEnabled = false;

    private int modelWidth, modelHeight;

    /**
     * Starts a new game by initializing two players and setting the game state to running.
     * The players are positioned at their respective starting positions, and the game model dimensions
     * and cell size are configured.
     *
     * @param player1Name the name of the first player
     * @param player2Name the name of the second player
     * @param player1Color the color of the first player
     * @param player2Color the color of the second player
     * @param player1TrailColor the trail color of the first player
     * @param player2TrailColor the trail color of the second player
     * @param Width the width of the game model in units
     * @param Height the height of the game model in units
     * @param cellSize the size of a single cell in the game model
     */
    public void startNewGame(String player1Name, String player2Name, Color player1Color, Color player2Color, Color player1TrailColor, Color player2TrailColor, int Width, int Height, int cellSize) {

        player1 = new Player(player1Name, player1Color, player1TrailColor, Width / 4, Height / 2, cellSize);
        player2 = new Player(player2Name, player2Color, player2TrailColor, 3 * Width / 4, Height / 2, cellSize);

        running = true;
        modelHeight = Height;
        modelWidth = Width;
        if (gameTimer != null) {
            gameTimer.stop();
        }

    }

    /**
     * Determines whether the game is currently running.
     *
     * @return true if the game is running, false otherwise.
     */
    public boolean isRunning(){
        return running;
    }

    /**
     * Checks if either of the player objects (player1 or player2) is null.
     *
     * @return true if either player1 or player2 is null, false otherwise.
     */
    public boolean arePlayersNull(){
        return player1 == null || player2 == null;
    }

    /**
     * Retrieves the trail color of Player 1.
     *
     * @return the Color object representing Player 1's trail color.
     */
    public Color getPlayer1TrailColor(){
        return player1.getTrailColor();
    }

    /**
     * Retrieves the trail color associated with Player 2.
     *
     * @return the Color object representing the trail color of Player 2.
     */
    public Color getPlayer2TrailColor(){
        return player2.getTrailColor();
    }



    /**
     * Checks for collisions involving two players in the Tron game.
     * The method evaluates if either player has collided with the game boundaries or with a trail.
     * Collisions are determined through helper methods that analyze positions and segments.
     *
     * @return CollisionResultEnum indicating the collision state:
     *         - PLAYER1COLLIDED: Player 1 has collided with a wall or trail.
     *         - PLAYER2COLLIDED: Player 2 has collided with a wall or trail.
     *         - NOONECOLLIDED: No collision occurred for either player.
     */
    private CollisionResultEnum checkCollisions() {
        if (checkWallCollision(player1.getCurrentPosition()) || checkTrailCollision(player1.getCurrentPosition(), player1.getTurningPoints()) || checkTrailCollision(player1.getCurrentPosition(), player2.getTurningPoints())) {
            running = false;
            return CollisionResultEnum.PLAYER1COLLIDED;
        }

        if (checkWallCollision(player2.getCurrentPosition()) || checkTrailCollision(player2.getCurrentPosition(), player2.getTurningPoints()) || checkTrailCollision(player2.getCurrentPosition(), player1.getTurningPoints())) {
            running = false;
            return CollisionResultEnum.PLAYER2COLLIDED;
        }

        // in case of no collision:
        return CollisionResultEnum.NOONECOLLIDED;
    }

    /**
     * Executes a single cycle of the game. This involves moving the players,
     * enabling collision checks, and evaluating any collisions that have occurred.
     * If the game is not running, the game timer is stopped and no further actions are executed.
     *
     * @return A {@code CollisionResultEnum} indicating the result of the collision checks:
     *         {@code PLAYER1COLLIDED}, {@code PLAYER2COLLIDED}, or {@code NOONECOLLIDED}.
     */
    public CollisionResultEnum gameCycle(){
        CollisionResultEnum collisionres = CollisionResultEnum.NOONECOLLIDED;
        if (!running) {
            gameTimer.stop();
            return collisionres;
        }

        player1.move();
        player2.move();

        // Enable collision checks after the first movement
        collisionCheckEnabled = true;

        if (collisionCheckEnabled) {
            collisionres = checkCollisions();
        }
        return collisionres;
    }

    /**
     * Stops the game timer.
     * This method halts the ongoing timer associated with the game,
     * effectively pausing the game's progression. Useful for scenarios
     * where the game needs to be stopped or paused, such as when the game ends
     * or during an intermission.
     */
    public void stopGameTimer(){
        gameTimer.stop();
    }

    /**
     * Checks if the given position collides with the boundaries of the game area.
     *
     * @param position the current position to be checked, represented as a Point object.
     * @return true if the position is outside the boundaries (collides with the wall), otherwise false.
     */
    private boolean checkWallCollision(Point position) {
        return position.x < 0 || position.x >= modelWidth || position.y < 0 || position.y >= modelHeight;
    }

    /**
     * Checks if the player's current position intersects with any segment of their trail.
     *
     * @param currentPosition the current position of the player to check for collisions
     * @param turningPoints the list of points representing the player's trail, including turning points
     * @return true if a collision is detected with any part of the trail, false otherwise
     */
    private boolean checkTrailCollision(Point currentPosition, ArrayList<Point> turningPoints) {
        for (int i = 0; i < turningPoints.size() - 1; i++) {
            Point start = turningPoints.get(i);
            Point end = turningPoints.get(i + 1);

            // Skip the last segment if the player's current position is at its end
            if (i == turningPoints.size() - 2 && currentPosition.equals(end)) {
                continue;
            }

            // Skip the segment the player just added to the trail during a turn
            if (currentPosition.equals(start) || currentPosition.equals(end)) {
                continue;
            }

            // Check if the current position lies on the segment
            if (Utils.pointOnSegment(start, end, currentPosition)) {
                running = false;
                return true;
            }
        }
        return false;
    }

    /**
     * Handles the key press for Player 1 by updating their movement direction based on the input key.
     *
     * @param key the key code representing the input from the player
     * @param keys the set of keys and movement configuration associated with the player
     */
    void handlePlayer1KeyPress(Integer key, KeySet keys){
        int currentDx = player1.getDx();
        int currentDy = player1.getDy();
        player1.setDx(keys.getDX(key, currentDx));
        player1.setDy(keys.getDY(key, currentDy));
    }

    /**
     * Handles key press events for Player 2.
     * This method updates Player 2's movement direction (dx, dy) based on the provided key input.
     *
     * @param key the key code representing the player's input
     * @param keys an instance of KeySet containing the key mappings and movement logic
     */
    void handlePlayer2KeyPress(Integer key, KeySet keys){
        int currentDx = player2.getDx();
        int currentDy = player2.getDy();
        player2.setDx(keys.getDX(key, currentDx));
        player2.setDy(keys.getDY(key, currentDy));
    }
}
