import java.sql.*;
import java.util.Properties;

public class DataBase {
    private Connection dbConnection;

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
