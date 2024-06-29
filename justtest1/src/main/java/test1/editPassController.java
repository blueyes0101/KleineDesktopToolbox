package test1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class editPassController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private UserPass selectedUserPass;

    @FXML
    private void initialize() {
        if (selectedUserPass != null) {
            usernameField.setText(selectedUserPass.getUsername());
            passwordField.setText(selectedUserPass.getPassword());
        }
    }

    public void setUserPass(UserPass userPass) {
        this.selectedUserPass = userPass;
        if (usernameField != null && passwordField != null) {
            usernameField.setText(userPass.getUsername());
            passwordField.setText(userPass.getPassword());
        }
    }

    @FXML
    private void onSaveClick() {
        String newUsername = usernameField.getText();
        String newPassword = passwordField.getText();
        if (newUsername.isEmpty() || newPassword.isEmpty()) {
            showError("Error", "Username and Password cannot be empty.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(App.getPassSaveLocation()));
             BufferedWriter writer = new BufferedWriter(new FileWriter(App.getPassSaveLocation() + ".tmp"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals(selectedUserPass.getUsername() + ":" + selectedUserPass.getPassword())) {
                    writer.write(newUsername + ":" + newPassword);
                } else {
                    writer.write(line);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            showError("Error", "Unable to update file: " + App.getPassSaveLocation());
            return;
        }

        try {
            java.nio.file.Files.move(
                    java.nio.file.Paths.get(App.getPassSaveLocation() + ".tmp"),
                    java.nio.file.Paths.get(App.getPassSaveLocation()),
                    java.nio.file.StandardCopyOption.REPLACE_EXISTING
            );
        } catch (IOException e) {
            showError("Error", "Unable to replace file: " + App.getPassSaveLocation());
            return;
        }

        try {
            App.showPass();
        } catch (IOException e) {
            showError("Error", "Unable to go back to PassSave screen.");
        }
    }

    @FXML
    private void onCancelClick() throws IOException {
        App.showPass();
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
