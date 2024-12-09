import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.HashSet;
import java.util.Properties;
import java.awt.event.*;


public class TronNeonBikeBattle extends JFrame {
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private static final int CELL_SIZE = 10;

    private Player player1, player2;
    private boolean running = false;
    private Timer timer;

    private HashSet<Point> lightTrails = new HashSet<>();
    private Connection dbConnection;

    public TronNeonBikeBattle() {
        setTitle("Tron Neon Bike Battle");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        JMenuItem newGame = new JMenuItem("New Game");
        JMenuItem highScores = new JMenuItem("High Scores");
        gameMenu.add(newGame);
        gameMenu.add(highScores);
        menuBar.add(gameMenu);
        setJMenuBar(menuBar);

        newGame.addActionListener(e -> startNewGame());
        highScores.addActionListener(e -> showHighScores());

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

        player1 = new Player(player1Name, player1Color, WIDTH / 4, HEIGHT / 2, KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D);
        player2 = new Player(player2Name, player2Color, 3 * WIDTH / 4, HEIGHT / 2, KeyEvent.VK_UP, KeyEvent.VK_LEFT, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT);

        lightTrails.clear();
        running = true;

        if (timer != null) {
            timer.stop();
        }
        timer = new Timer(100, new GameLoop());
        timer.start();
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
                timer.stop();
                return;
            }

            player1.move();
            player2.move();

            Point p1 = new Point(player1.x, player1.y);
            Point p2 = new Point(player2.x, player2.y);

            if (lightTrails.contains(p1) || lightTrails.contains(p2) ||
                    p1.x < 0 || p1.x >= WIDTH || p1.y < 0 || p1.y >= HEIGHT ||
                    p2.x < 0 || p2.x >= WIDTH || p2.y < 0 || p2.y >= HEIGHT) {

                running = false;
                String winner = (lightTrails.contains(p1) ? player2.name : player1.name);
                updateScore(winner);
                JOptionPane.showMessageDialog(TronNeonBikeBattle.this, winner + " wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            }

            lightTrails.add(p1);
            lightTrails.add(p2);

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
            for (Point p : lightTrails) {
                g.setColor(Color.WHITE);
                g.fillRect(p.x, p.y, CELL_SIZE, CELL_SIZE);
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
        int x, y;
        int dx = 0, dy = -CELL_SIZE; // Initial direction
        int upKey, leftKey, downKey, rightKey;

        Player(String name, Color color, int startX, int startY, int upKey, int leftKey, int downKey, int rightKey) {
            this.name = name;
            this.color = color;
            this.x = startX;
            this.y = startY;
            this.upKey = upKey;
            this.leftKey = leftKey;
            this.downKey = downKey;
            this.rightKey = rightKey;
        }

        void move() {
            x += dx;
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

