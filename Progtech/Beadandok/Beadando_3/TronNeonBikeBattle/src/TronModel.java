import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TronModel {

    public Player player1, player2;

    private boolean running = false;

    public Timer gameTimer;

    private boolean collisionCheckEnabled = false;

    private int modelWidth, modelHeight;

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

    public boolean isRunning(){
        return running;
    }

    public boolean arePlayersNull(){
        return player1 == null || player2 == null;
    }

    public Color getPlayer1TrailColor(){
        return player1.getTrailColor();
    }

    public Color getPlayer2TrailColor(){
        return player2.getTrailColor();
    }



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

    public void stopGameTimer(){
        gameTimer.stop();
    }

    private boolean checkWallCollision(Point position) {
        return position.x < 0 || position.x >= modelWidth || position.y < 0 || position.y >= modelHeight;
    }

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

    void handlePlayer1KeyPress(Integer key, KeySet keys){
        int currentDx = player1.getDx();
        int currentDy = player1.getDy();
        player1.setDx(keys.getDX(key, currentDx));
        player1.setDy(keys.getDY(key, currentDy));
    }

    void handlePlayer2KeyPress(Integer key, KeySet keys){
        int currentDx = player2.getDx();
        int currentDy = player2.getDy();
        player2.setDx(keys.getDX(key, currentDx));
        player2.setDy(keys.getDY(key, currentDy));
    }
}
