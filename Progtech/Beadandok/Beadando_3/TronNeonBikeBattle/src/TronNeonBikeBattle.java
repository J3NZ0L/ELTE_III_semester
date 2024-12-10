import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class TronNeonBikeBattle extends JFrame {
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private static final int CELL_SIZE = 10;

    private Player player1, player2;
    private boolean running = false;
    private Timer gameTimer;
    private Timer displayTimer; // Timer for the displayed time

    private ArrayList<Point> lightTrailsPlayer1 = new ArrayList<>();
    private ArrayList<Point> lightTrailsPlayer2 = new ArrayList<>();

    private Connection dbConnection;

    private JLabel timerLabel;  // JLabel to display the timer
    private int secondsElapsed = 0;  // Elapsed time in seconds

    public TronNeonBikeBattle() {
        setTitle("Tron Light Cycle Game");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        JMenuItem newGame = new JMenuItem("New Game");
        JMenuItem highScores = new JMenuItem("High Scores");
        JMenuItem pauseGame = new JMenuItem("Pause Game"); // Pause functionality
        gameMenu.add(newGame);
        gameMenu.add(highScores);
        gameMenu.add(pauseGame);  // Added the pause option
        menuBar.add(gameMenu);
        setJMenuBar(menuBar);

        newGame.addActionListener(e -> startNewGame());
        highScores.addActionListener(e -> showHighScores());
        pauseGame.addActionListener(e -> toggleGamePause()); // Pause on click

        // Timer label for displaying time played
        timerLabel = new JLabel("Time: 0s", JLabel.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(timerLabel, BorderLayout.NORTH);  // Add timer label to the top of the frame

        // Database setup
        setupDatabase();

        // KeyListener for player controls
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (player1 != null) player1.handleKeyPress(e);
                if (player2 != null) player2.handleKeyPress(e);
            }
        });
        setFocusable(true);
        setVisible(true);
    }

    private void startNewGame() {
        String player1Name = JOptionPane.showInputDialog(this, "Enter Player 1 name:");
        String player2Name = JOptionPane.showInputDialog(this, "Enter Player 2 name:");
        Color player1Color = JColorChooser.showDialog(this, "Choose Player 1 color", Color.RED);
        Color player2Color = JColorChooser.showDialog(this, "Choose Player 2 color", Color.BLUE);
        Color player1TrailColor = Utils.deriveLighterColor(player1Color, 0.4f);
        Color player2TrailColor = Utils.deriveLighterColor(player2Color, 0.4f);
        player1 = new Player(player1Name, player1Color, player1TrailColor, WIDTH / 4, HEIGHT / 2, KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D);
        player2 = new Player(player2Name, player2Color, player2TrailColor, 3 * WIDTH / 4, HEIGHT / 2, KeyEvent.VK_UP, KeyEvent.VK_LEFT, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT);

        lightTrailsPlayer1.clear();
        lightTrailsPlayer2.clear();
        running = true;

        if (gameTimer != null) {
            gameTimer.stop();
        }

        gameTimer = new Timer(200, new GameLoop());
        gameTimer.start();

        // Timer for tracking elapsed time
        secondsElapsed = 0;
        if (displayTimer != null) {
            displayTimer.stop();  // Stop any existing display timer
        }
        // Update the timer label every second
        displayTimer = new Timer(1000, e -> updateTimerDisplay());  // Trigger every 1 second
        displayTimer.start();  // Start the display timer

        repaint();
    }

    private void updateTimerDisplay() {
        if (running) {
            secondsElapsed++;  // Increment the time
            timerLabel.setText("Time: " + secondsElapsed + "s");  // Update the label
        }
    }

    private void showHighScores() {
        try (Statement stmt = dbConnection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT name, score FROM players ORDER BY score DESC LIMIT 10")) {

            StringBuilder sb = new StringBuilder("Top 10 Players:\n");
            while (rs.next()) {
                sb.append(rs.getString("name")).append(": ").append(rs.getInt("score")).append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString(), "High Scores", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void toggleGamePause() {
        if (running) {
            running = false; // Pause the game
            gameTimer.stop(); // Stop the game timer
        } else {
            running = true; // Resume the game
            gameTimer.start(); // Start the game timer
        }
        repaint();
    }

    private void setupDatabase() {
        try {
            Properties connectionProps = new Properties();
            connectionProps.put("user", "root");
            connectionProps.put("password", "root");
            connectionProps.put("serverTimezone", "UTC");
            String dbURL = "jdbc:mariadb://localhost:3306/tron";
            dbConnection = DriverManager.getConnection(dbURL, connectionProps);

            try (Statement stmt = dbConnection.createStatement()) {
                stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS players (
                    name VARCHAR(50) PRIMARY KEY,
                    score INT DEFAULT 0
                )
            """);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private class GameLoop implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!running) {
                gameTimer.stop();
                return;
            }

            player1.move();
            player2.move();

            Point p1 = new Point(player1.x, player1.y);
            Point p2 = new Point(player2.x, player2.y);

            if (lightTrailsPlayer1.contains(p1) || lightTrailsPlayer1.contains(p2) ||
                    lightTrailsPlayer2.contains(p1) || lightTrailsPlayer2.contains(p2) ||
                    p1.x < 0 || p1.x >= WIDTH || p1.y < 0 || p1.y >= HEIGHT ||
                    p2.x < 0 || p2.x >= WIDTH || p2.y < 0 || p2.y >= HEIGHT) {

                running = false;
                String winner = (lightTrailsPlayer1.contains(p1) || lightTrailsPlayer2.contains(p1) ? player2.name : player1.name);
                updateScore(winner);
                gameTimer.stop();
                displayTimer.stop();
                JOptionPane.showMessageDialog(TronNeonBikeBattle.this, winner + " wins! Time played: "+ secondsElapsed + " s", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            }

            lightTrailsPlayer1.add(p1);
            lightTrailsPlayer2.add(p2);

            repaint();
        }
    }

    private void updateScore(String winner) {
        try (PreparedStatement pstmt = dbConnection.prepareStatement("""
            INSERT INTO players (name, score)
            VALUES (?, 1)
            ON DUPLICATE KEY UPDATE score = score + 1
            """)) {
            pstmt.setString(1, winner);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (running) {
            for (Point p : lightTrailsPlayer1) {
                g.setColor(player1.trailColor);
                g.fillRect(p.x, p.y, CELL_SIZE, CELL_SIZE);
            }
            for (Point p : lightTrailsPlayer2) {
                g.setColor(player2.trailColor);
                g.fillRect(p.x, p.y, CELL_SIZE, CELL_SIZE);
            }
            player1.draw(g);
            player2.draw(g);
        }
    }

    //@Override
    public void paintJustOneSegment(Graphics g) {
        super.paint(g);
        if (running) {
            // Draw the new segments of the trail for player 1
            g.setColor(player1.trailColor);
            g.fillRect(player1.prevX, player1.prevY, CELL_SIZE, CELL_SIZE);

            // Draw the new segments of the trail for player 2
            g.setColor(player2.trailColor);
            g.fillRect(player2.prevX, player2.prevY, CELL_SIZE, CELL_SIZE);

            // Draw the players at their current positions
            player1.draw(g);
            player2.draw(g);
        }
    }

    public void paintWithLines(Graphics g){
        super.paint(g);
        if (running) {
            Graphics2D g2d = (Graphics2D) g;

            // Set the stroke (line width) to half the tile size
            int lineWidth = CELL_SIZE / 2;
            g2d.setStroke(new BasicStroke(lineWidth));

            Point p, prev;
            // Draw the light trails for Player 1 (Red)
            g2d.setColor(player1.getTrailColor());
            for (int i=1; i<lightTrailsPlayer1.size(); i++) {
                p = lightTrailsPlayer1.get(i);
                prev = lightTrailsPlayer1.get(i-1);
                g2d.drawLine(prev.x, prev.y, p.x, p.y);
            }

            // Draw the light trails for Player 2 (Blue)
            g2d.setColor(player2.getTrailColor());
            for (int i=1; i<lightTrailsPlayer2.size(); i++) {
                p = lightTrailsPlayer2.get(i);
                prev = lightTrailsPlayer2.get(i-1);
                g2d.drawLine(prev.x, prev.y, p.x, p.y);
            }
            player1.draw(g);
            player2.draw(g);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TronNeonBikeBattle::new);
    }

    private static class Player {
        String name;
        Color color;
        Color trailColor;  // New attribute for trail color
        int x, y;
        int prevX, prevY;
        int dx = 0, dy = -CELL_SIZE; // Initial direction
        int upKey, leftKey, downKey, rightKey;

        Player(String name, Color color, Color trailColor, int startX, int startY, int upKey, int leftKey, int downKey, int rightKey) {
            this.name = name;
            this.color = color;
            this.trailColor = trailColor;
            this.x = startX;
            this.y = startY;
            this.prevX = startX;  // Initialize previous position
            this.prevY = startY;  // Initialize previous position
            this.upKey = upKey;
            this.leftKey = leftKey;
            this.downKey = downKey;
            this.rightKey = rightKey;
        }

        public Color getTrailColor() {
            return trailColor;
        }

        void move() {
            prevX = x;  // Store current position as previous
            prevY = y;
            x += dx;  // Update position for example (this would be based on direction)
            y += dy;
        }

        void draw(Graphics g) {
            g.setColor(color);
            g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
        }

        void handleKeyPress(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == upKey && dy == 0) {
                dx = 0; dy = -CELL_SIZE; // Up
            } else if (key == downKey && dy == 0) {
                dx = 0; dy = CELL_SIZE; // Down
            } else if (key == leftKey && dx == 0) {
                dx = -CELL_SIZE; dy = 0; // Left
            } else if (key == rightKey && dx == 0) {
                dx = CELL_SIZE; dy = 0; // Right
            }
        }
    }
}
