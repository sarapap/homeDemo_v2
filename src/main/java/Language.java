import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.util.Locale;
import java.util.ResourceBundle;

public class Language {
    @FXML
    private Label languageLabel;
    @FXML
    private ComboBox<String> languageComboBox;
    @FXML
    private Button saveButton;

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private Label nameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label emailLabel;

    private ResourceBundle resources;

    @FXML
    private void initialize() {
        resources = ResourceBundle.getBundle("Messages", Locale.ENGLISH);
        languageComboBox.getItems().addAll("English", "Farsi", "Japanese");
        languageComboBox.getSelectionModel().select("English");
        updateUI();

        languageComboBox.setOnAction(event -> handleLanguageSelection());
    }

    @FXML
    private void handleLanguageSelection() {
        String selectedLanguage = languageComboBox.getValue();
        switch (selectedLanguage) {
            case "Farsi":
                resources = ResourceBundle.getBundle("Messages", new Locale("fa"));
                break;
            case "Japanese":
                resources = ResourceBundle.getBundle("Messages", new Locale("ja"));
                break;
            default:
                resources = ResourceBundle.getBundle("Messages", Locale.ENGLISH);
                break;
        }
        updateUI();
    }

    private void updateUI() {
        languageLabel.setText(resources.getString("language"));
        firstNameField.setPromptText(resources.getString("name"));
        lastNameField.setPromptText(resources.getString("lastname"));
        emailField.setPromptText(resources.getString("email"));
        saveButton.setText(resources.getString("save"));
        nameLabel.setText(resources.getString("name"));
        lastNameLabel.setText(resources.getString("lastname"));
        emailLabel.setText(resources.getString("email"));
    }

    @FXML
    private void handleSave() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();

        String selectedLanguage = languageComboBox.getValue();
        if (selectedLanguage != null) {
            switch (selectedLanguage) {
                case "English":
                    Main.addEmployeeToEnglish(firstName, lastName, email);
                    break;
                case "Farsi":
                    Main.addEmployeeToFarsi(firstName, lastName, email);
                    break;
                case "Japanese":
                    Main.addEmployeeToJapanese(firstName, lastName, email);
                    break;
                default:
                    System.out.println("No language selected.");
            }
        }
    }
}
