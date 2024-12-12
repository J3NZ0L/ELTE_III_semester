import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TronGUI extends JFrame {
    private TronModel model;
    private DataBase database;
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private static final int CELL_SIZE = 10;

    private final int DELAY = 75;

    private Timer displayTimer;

    private final KeySet player1Keys = new KeySet(KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, CELL_SIZE);
    private final KeySet player2Keys = new KeySet(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT,  KeyEvent.VK_RIGHT, CELL_SIZE);

    private JLabel timerLabel;

    private int secondsElapsed = 0;

    public TronGUI(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new BorderLayout());
        setResizable(false);

        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        JMenuItem newGame = new JMenuItem("New Game");
        JMenuItem highScores = new JMenuItem("High Scores");
        gameMenu.add(newGame);
        gameMenu.add(highScores);
        menuBar.add(gameMenu);
        setJMenuBar(menuBar);

        newGame.addActionListener(e -> startNewGame());
        highScores.addActionListener(e -> showDataBaseHighScores());

        timerLabel = new JLabel("Time: 0s", JLabel.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(timerLabel, BorderLayout.NORTH);

        model = new TronModel();

        database = new DataBase();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (model.arePlayersNull()){
                    return;
                }
                if (player1Keys.contains(keyCode)){
                    handlePlayer1KeyPress(keyCode);
                }
                if (player2Keys.contains(keyCode)){
                    handlePlayer2KeyPress(keyCode);
                }
            }
        });

        setFocusable(true);
        setVisible(true);
    }

    public void startNewGame(){
        String player1Name = JOptionPane.showInputDialog(this, "Enter Player 1 name:");
        String player2Name = JOptionPane.showInputDialog(this, "Enter Player 2 name:");
        Color player1Color = JColorChooser.showDialog(this, "Choose Player 1 color", Color.RED);
        Color player2Color = JColorChooser.showDialog(this, "Choose Player 2 color", Color.BLUE);
        Color player1TrailColor = Utils.deriveLighterColor(player1Color, 0.4f);
        Color player2TrailColor = Utils.deriveLighterColor(player2Color, 0.4f);
        model.startNewGame(player1Name, player2Name, player1Color, player2Color, player1TrailColor, player2TrailColor, WIDTH, HEIGHT, CELL_SIZE);
        model.gameTimer = new Timer(DELAY, new GameLoop());
        model.gameTimer.start();

        secondsElapsed = 0;
        if (displayTimer != null) {
            displayTimer.stop();
        }

        displayTimer = new Timer(1000, e -> updateTimerDisplay());
        displayTimer.start();

        repaint();
    }

    private class GameLoop implements ActionListener {
        private CollisionResultEnum collisionres;

        @Override
        public void actionPerformed(ActionEvent e) {
            collisionres = model.gameCycle();

            if (collisionres != CollisionResultEnum.NOONECOLLIDED){
                String winner = (collisionres == CollisionResultEnum.PLAYER1COLLIDED ? model.player2.getName() : model.player1.getName());
                database.updateScore(winner);
                model.stopGameTimer();
                displayTimer.stop();
                JOptionPane.showMessageDialog(TronGUI.this, winner + " wins! Time played: "+ secondsElapsed + " s", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            }

            repaint();
        }
    }

    private void showDataBaseHighScores() {
        StringBuilder sb = database.getHighScores();
        JOptionPane.showMessageDialog(this, sb.toString(), "High Scores", JOptionPane.INFORMATION_MESSAGE);
    }

    private void updateTimerDisplay() {
        if (model.isRunning()) {
            secondsElapsed++;
            timerLabel.setText("Time: " + secondsElapsed + "s");
        }
    }

    private void drawTrails(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int lineWidth = CELL_SIZE / 2;
        g2d.setStroke(new BasicStroke(lineWidth));

        // Draw Player 1's trail
        g2d.setColor(model.getPlayer1TrailColor());
        for (int i = 1; i < model.player1.getTurningPointsSize(); i++) {
            Point start = model.player1.getElementOfTurningPoints(i - 1);
            Point end = model.player1.getElementOfTurningPoints(i);
            g2d.drawLine(start.x, start.y, end.x, end.y);
        }

        // Draw Player 2's trail
        g2d.setColor(model.getPlayer2TrailColor());
        for (int i = 1; i < model.player2.getTurningPointsSize(); i++) {
            Point start = model.player2.getElementOfTurningPoints(i - 1);
            Point end = model.player2.getElementOfTurningPoints(i);
            g2d.drawLine(start.x, start.y, end.x, end.y);
        }
    }

    private void drawPlayers(Graphics g) {
        g.setColor(model.player1.getColor());
        g.fillRect(model.player1.getCurrentPositionX(), model.player1.getCurrentPositionY(), CELL_SIZE, CELL_SIZE);

        g.setColor(model.player2.getColor());
        g.fillRect(model.player2.getCurrentPositionX(), model.player2.getCurrentPositionY(), CELL_SIZE, CELL_SIZE);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (model.isRunning()) {
            drawTrails(g);
            drawPlayers(g);
        }
    }

    void handlePlayer1KeyPress(Integer key) {
        model.handlePlayer1KeyPress(key, player1Keys);

    }
    void handlePlayer2KeyPress(Integer key) {
        model.handlePlayer2KeyPress(key, player2Keys);
    }
}
