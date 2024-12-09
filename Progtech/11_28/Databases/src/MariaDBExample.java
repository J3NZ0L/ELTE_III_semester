import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDBExample {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mariadb://localhost:3306/login_schema";
        String username = "root";
        String password = "root";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {
            System.out.println("Connected to MariaDB successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
