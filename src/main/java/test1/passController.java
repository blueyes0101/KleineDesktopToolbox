package test1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class passController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TableView<UserPass> tableView;

    @FXML
    private TableColumn<UserPass, String> usernameColumn;

    @FXML
    private TableColumn<UserPass, String> passwordColumn;

    @FXML
    private Button btnBack;

    @FXML
    private Button deletePassButton;

    @FXML
    private Button editPassButton;

    private ObservableList<UserPass> userPassList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        deletePassButton.setDisable(true);
        editPassButton.setDisable(true);

        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        tableView.setItems(userPassList);

        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            deletePassButton.setDisable(newValue == null);
            editPassButton.setDisable(newValue == null);
        });

        updateStyles();
    }

    private void updateStyles() {
        if (App.isNightMode) {
            tableView.setStyle("-fx-text-fill: white; -fx-background-color: #3c3f41;");
            usernameField.setStyle("-fx-text-fill: white; -fx-background-color: #3c3f41;");
            passwordField.setStyle("-fx-text-fill: white; -fx-background-color: #3c3f41;");
        } else {
            tableView.setStyle("");
            usernameField.setStyle("");
            passwordField.setStyle("");
        }
    }

    @FXML
    private void onSaveClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (!username.isEmpty() && !password.isEmpty()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(App.getPassSaveLocation(), true))) {
                writer.write(username + ":" + password);
                writer.newLine();
            } catch (IOException e) {
                showError("Error", "Unable to write to file: " + App.getPassSaveLocation());
            }
            usernameField.clear();
            passwordField.clear();
            onShowClick();
        }
    }

    @FXML
    private void onShowClick() {
        userPassList.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(App.getPassSaveLocation()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    userPassList.add(new UserPass(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            showError("Error", "Unable to read from file: " + App.getPassSaveLocation());
        }
    }

    @FXML
    private void onDeletePassClick() {
        UserPass selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            userPassList.remove(selected);
            try (BufferedReader reader = new BufferedReader(new FileReader(App.getPassSaveLocation()));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(App.getPassSaveLocation() + ".tmp"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.trim().equals(selected.getUsername() + ":" + selected.getPassword())) {
                        writer.write(line);
                        writer.newLine();
                    }
                }
            } catch (IOException e) {
                showError("Error", "Unable to update file: " + App.getPassSaveLocation());
            }

            try {
                java.nio.file.Files.move(
                        java.nio.file.Paths.get(App.getPassSaveLocation() + ".tmp"),
                        java.nio.file.Paths.get(App.getPassSaveLocation()),
                        java.nio.file.StandardCopyOption.REPLACE_EXISTING
                );
            } catch (IOException e) {
                showError("Error", "Unable to replace file: " + App.getPassSaveLocation());
            }
        }
    }

    @FXML
    private void onEditPassClick() {
        UserPass selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/editPass.fxml"));
                Parent root = loader.load();

                editPassController controller = loader.getController();
                controller.setUserPass(selected);

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Edit UserPass");
                stage.show();
            } catch (IOException e) {
                showError("Error", "Unable to open edit window.");
            }
        }
    }

    @FXML
    private void onBackBtn() throws IOException {
        App.showMenu();
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
