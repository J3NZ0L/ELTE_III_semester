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

    /**
     * Constructs a new TronGUI instance and initializes the graphical user interface for the Tron game.
     * This includes setting default properties for the JFrame, adding a menu bar with options for
     * starting a new game and viewing high scores, initializing the game model and database, and
     * setting up event listeners for keyboard inputs and menu interactions.
     *
     * The GUI contains a timer label to display elapsed time during the game, and it is configured
     * to handle player inputs for controlling the game. The frame is made non-resizable and set to be
     * visible upon construction.
     */
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

    /**
     * Initializes and starts a new game. This method sets up the game by prompting
     * the user to enter player names and select their respective colors. It also
     * computes lighter trail colors for both players, initializes the game model
     * with these values, and starts the game and display timers.
     *
     * The game dimensions and cell size are configured using class-level constants
     * for width, height, and delay. Once the setup is complete, the game is
     * rendered, and the game state is set to running.
     *
     * Responsibilities included:
     * - Collecting player names and colors through dialog inputs.
     * - Deriving lighter trail colors for each player.
     * - Configuring and initializing the game model.
     * - Starting the game loop and timers for gameplay and UI updates.
     * - Repainting the GUI to reflect the initialized game state.
     */
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

    /**
     * Displays the high scores stored in the database to the user in a dialog box.
     * Retrieves the top 10 high scores from the database using the `getHighScores`
     * method of the `database` object and presents them in a read-only message box.
     * If the high scores are successfully retrieved, they are shown in descending
     * order by score, with each entry including the player name and score.
     * The dialog box has "High Scores" as its title and uses an informational icon.
     *
     * Note: If the `getHighScores` method returns `null` due to an error, the
     * dialog box will display a `null` string.
     */
    private void showDataBaseHighScores() {
        StringBuilder sb = database.getHighScores();
        JOptionPane.showMessageDialog(this, sb.toString(), "High Scores", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Updates the timer display during the game if the game is currently running.
     * If the game is active (as determined by the model's isRunning() method),
     * increments the elapsed seconds counter and refreshes the timer label
     * with the updated time in seconds.
     */
    private void updateTimerDisplay() {
        if (model.isRunning()) {
            secondsElapsed++;
            timerLabel.setText("Time: " + secondsElapsed + "s");
        }
    }

    /**
     * Draws the trails for both players on the game board using the graphics object provided.
     *
     * @param g the Graphics object used to render the trails.
     */
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

    /**
     * Draws the players on the game screen. It uses the players' current positions and
     * colors to render them on the graphics context.
     *
     * @param g the Graphics object used for rendering the players.
     */
    private void drawPlayers(Graphics g) {
        g.setColor(model.player1.getColor());
        g.fillRect(model.player1.getCurrentPositionX(), model.player1.getCurrentPositionY(), CELL_SIZE, CELL_SIZE);

        g.setColor(model.player2.getColor());
        g.fillRect(model.player2.getCurrentPositionX(), model.player2.getCurrentPositionY(), CELL_SIZE, CELL_SIZE);
    }

    /**
     * Paints the graphical components of the game onto the screen. This method
     * is invoked whenever the graphical representation needs to be refreshed.
     * It delegates drawing game elements such as player trails and player positions
     * based on the current game state.
     *
     * @param g the {@link Graphics} object used for rendering graphics.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (model.isRunning()) {
            drawTrails(g);
            drawPlayers(g);
        }
    }

    /**
     * Handles the key press event for Player 1, delegating the input handling
     * to the game model's logic.
     *
     * @param key the key code representing the key pressed by Player 1
     */
    void handlePlayer1KeyPress(Integer key) {
        model.handlePlayer1KeyPress(key, player1Keys);

    }
    /**
     * Handles key press events for Player 2 by delegating the action to the model.
     *
     * @param key the key code representing the input from Player 2
     */
    void handlePlayer2KeyPress(Integer key) {
        model.handlePlayer2KeyPress(key, player2Keys);
    }
}
