import java.sql.*;
import java.util.Properties;

public class DataBase {
    private Connection dbConnection;

    /**
     * A constructor for the DataBase class that initializes a connection to a MariaDB database
     * with predefined parameters and ensures the availability of a "players" table.
     *
     * The database connection uses the following parameters:
     * - JDBC URL: jdbc:mariadb://localhost:3306/tron
     * - Username: root
     * - Password: root
     * - Server timezone: UTC
     *
     * Upon successful establishment of the connection, the constructor creates a "players" table
     * if it does not already exist. The "players" table has the following schema:
     * - id: an auto-incrementing primary key.
     * - name: a unique VARCHAR field with a maximum length of 50 characters, not null.
     * - score: an integer field defaulting to 0.
     *
     * Any SQL exceptions that occur during the connection setup or table creation are caught and
     * their stack traces are printed.
     */
    public DataBase() {
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
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(50) NOT NULL UNIQUE,
                    score INT DEFAULT 0
                );
            """);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Retrieves the top 10 high scores from the players table in the database.
     *
     * @return a StringBuilder containing the top 10 player names and scores
     *         in descending order of scores, or null if an error occurs.
     */
    public StringBuilder getHighScores() {
        try (Statement stmt = dbConnection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT name, score FROM players ORDER BY score DESC LIMIT 10")) {

            StringBuilder sb = new StringBuilder("Top 10 Players:\n");
            while (rs.next()) {
                sb.append(rs.getString("name")).append(": ").append(rs.getInt("score")).append("\n");
            }
            return sb;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    

    /**
     * Updates the score of a player in the "players" database table.
     * If the player does not exist in the table, a new entry is created with an initial score of 1.
     * If the player already exists, their score is incremented by 1.
     *
     * @param winner the name of the player whose score needs to be updated
     */
    public void updateScore(String winner) {
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


}
