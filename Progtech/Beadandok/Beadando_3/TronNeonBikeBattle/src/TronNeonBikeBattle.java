import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TronNeonBikeBattle extends JPanel implements ActionListener, KeyListener {
    private Timer timer;
    private final int GRID_SIZE = 10;
    private final int PANEL_WIDTH = 800;
    private final int PANEL_HEIGHT = 800;
    private final int DELAY = 50;

    private ArrayList<Point> turningPointsPlayer1 = new ArrayList<>();
    private ArrayList<Point> turningPointsPlayer2 = new ArrayList<>();

    private Point currentPositionPlayer1 = new Point(200, 400);
    private Point currentPositionPlayer2 = new Point(600, 400);

    private Point directionPlayer1 = new Point(GRID_SIZE, 0); // Start moving right
    private Point directionPlayer2 = new Point(-GRID_SIZE, 0); // Start moving left

    private boolean isRunning = true;
    private boolean collisionCheckEnabled = false;

    public TronNeonBikeBattle() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        turningPointsPlayer1.add(new Point(currentPositionPlayer1));
        turningPointsPlayer2.add(new Point(currentPositionPlayer2));

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isRunning) {
            drawTrails(g);
            drawPlayers(g);
        } else {
            drawGameOver(g);
        }
    }

    private void drawTrails(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Draw Player 1's trail
        g2d.setColor(Color.BLUE);
        for (int i = 1; i < turningPointsPlayer1.size(); i++) {
            Point start = turningPointsPlayer1.get(i - 1);
            Point end = turningPointsPlayer1.get(i);
            g2d.drawLine(start.x, start.y, end.x, end.y);
        }

        // Draw Player 2's trail
        g2d.setColor(Color.RED);
        for (int i = 1; i < turningPointsPlayer2.size(); i++) {
            Point start = turningPointsPlayer2.get(i - 1);
            Point end = turningPointsPlayer2.get(i);
            g2d.drawLine(start.x, start.y, end.x, end.y);
        }
    }

    private void drawPlayers(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(currentPositionPlayer1.x, currentPositionPlayer1.y, GRID_SIZE, GRID_SIZE);

        g.setColor(Color.RED);
        g.fillRect(currentPositionPlayer2.x, currentPositionPlayer2.y, GRID_SIZE, GRID_SIZE);
    }

    private void drawGameOver(Graphics g) {
        String message = "Game Over";
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        int x = (PANEL_WIDTH - metrics.stringWidth(message)) / 2;
        int y = (PANEL_HEIGHT - metrics.getHeight()) / 2 + metrics.getAscent();
        g.drawString(message, x, y);
    }

    private void checkCollisions() {
        // Player 1 collisions
        if (checkWallCollision(currentPositionPlayer1) || checkTrailCollision(currentPositionPlayer1, turningPointsPlayer1) || checkTrailCollision(currentPositionPlayer1, turningPointsPlayer2)) {
            isRunning = false;
        }

        // Player 2 collisions
        if (checkWallCollision(currentPositionPlayer2) || checkTrailCollision(currentPositionPlayer2, turningPointsPlayer2) || checkTrailCollision(currentPositionPlayer2, turningPointsPlayer1)) {
            isRunning = false;
        }
    }

    private boolean checkWallCollision(Point position) {
        return position.x < 0 || position.x >= PANEL_WIDTH || position.y < 0 || position.y >= PANEL_HEIGHT;
    }

    private boolean checkTrailCollision(Point currentPosition, ArrayList<Point> trail) {
        for (int i = 0; i < trail.size() - 1; i++) {
            Point start = trail.get(i);
            Point end = trail.get(i + 1);

            // Skip the segment if it's the player's current segment
            if (currentPosition.equals(start) || currentPosition.equals(end)) {
                return false;
            }

            if (linesIntersect(start, end, currentPosition, currentPosition)) {
                isRunning = false;
                return true;
            }
        }
        return false;
    }

    private boolean linesIntersect(Point p1, Point p2, Point p3, Point p4) {
        // Simplified 2D line segment intersection test
        int d1 = direction(p3, p4, p1);
        int d2 = direction(p3, p4, p2);
        int d3 = direction(p1, p2, p3);
        int d4 = direction(p1, p2, p4);

        return ((d1 != d2) && (d3 != d4));
    }

    private int direction(Point pi, Point pj, Point pk) {
        return (pk.x - pi.x) * (pj.y - pi.y) - (pj.x - pi.x) * (pk.y - pi.y);
    }

    private void updateGame() {
        if (!isRunning) return;

        // Move players
        movePlayer(currentPositionPlayer1, directionPlayer1, turningPointsPlayer1);
        movePlayer(currentPositionPlayer2, directionPlayer2, turningPointsPlayer2);

        // Enable collision checks after the first movement
        collisionCheckEnabled = true;

        if (collisionCheckEnabled) {
            checkCollisions();
        }

        repaint();
    }

    private void movePlayer(Point currentPosition, Point direction, ArrayList<Point> turningPoints) {
        currentPosition.translate(direction.x, direction.y);

        // Add turning point if direction changed
        if (!turningPoints.get(turningPoints.size() - 1).equals(currentPosition)) {
            turningPoints.add(new Point(currentPosition));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateGame();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        // Player 1 controls
        if (key == KeyEvent.VK_W && directionPlayer1.y == 0) directionPlayer1.setLocation(0, -GRID_SIZE);
        if (key == KeyEvent.VK_S && directionPlayer1.y == 0) directionPlayer1.setLocation(0, GRID_SIZE);
        if (key == KeyEvent.VK_A && directionPlayer1.x == 0) directionPlayer1.setLocation(-GRID_SIZE, 0);
        if (key == KeyEvent.VK_D && directionPlayer1.x == 0) directionPlayer1.setLocation(GRID_SIZE, 0);

        // Player 2 controls
        if (key == KeyEvent.VK_UP && directionPlayer2.y == 0) directionPlayer2.setLocation(0, -GRID_SIZE);
        if (key == KeyEvent.VK_DOWN && directionPlayer2.y == 0) directionPlayer2.setLocation(0, GRID_SIZE);
        if (key == KeyEvent.VK_LEFT && directionPlayer2.x == 0) directionPlayer2.setLocation(-GRID_SIZE, 0);
        if (key == KeyEvent.VK_RIGHT && directionPlayer2.x == 0) directionPlayer2.setLocation(GRID_SIZE, 0);
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("LightBike Game");
        TronNeonBikeBattle game = new TronNeonBikeBattle();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
