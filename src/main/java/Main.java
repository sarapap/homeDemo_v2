import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main extends Application {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/database_localization";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static void addEmployeeToEnglish(String firstName, String lastName, String email) {
        addEmployee("employee_en", firstName, lastName, email);
    }

    public static void addEmployeeToFarsi(String firstName, String lastName, String email) {
        addEmployee("employee_fa", firstName, lastName, email);
    }

    public static void addEmployeeToJapanese(String firstName, String lastName, String email) {
        addEmployee("employee_ja", firstName, lastName, email);
    }

    private static void addEmployee(String tableName, String firstName, String lastName, String email) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
                String sql = "INSERT INTO " + tableName + " (first_name, last_name, email) VALUES (?, ?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, firstName);
                    statement.setString(2, lastName);
                    statement.setString(3, email);

                    statement.executeUpdate();
                    System.out.println("Record inserted successfully into " + tableName + ".");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("language.fxml"));
        Parent root = loader.load();
        stage.setTitle("Internationalization!");
        stage.setScene(new Scene (root));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}



