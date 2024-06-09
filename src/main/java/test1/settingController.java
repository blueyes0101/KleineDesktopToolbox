package test1;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class settingController {

    @FXML
    private TextField passSaveLocation;

    @FXML
    private TextField noteSaveLocation;

    @FXML
    private CheckBox nightModeCheckBox;

    @FXML
    private void initialize() {
        passSaveLocation.setText(App.getPassSaveLocation());
        noteSaveLocation.setText(App.getNoteSaveLocation());
        nightModeCheckBox.setSelected(App.isNightMode);
    }

    @FXML
    private void onBrowsePassSave() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(new Stage());
        if (selectedDirectory != null) {
            File passSaveFile = new File(selectedDirectory, "passwords.txt");
            if (passSaveFile.exists() || createFile(passSaveFile)) {
                passSaveLocation.setText(passSaveFile.getAbsolutePath());
                App.setPassSaveLocation(passSaveFile.getAbsolutePath());
            } else {
                showError("Error", "Unable to create file: " + passSaveFile.getAbsolutePath());
            }
        }
    }

    @FXML
    private void onBrowseNoteSave() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(new Stage());
        if (selectedDirectory != null) {
            File noteSaveFile = new File(selectedDirectory, "notes.txt");
            if (noteSaveFile.exists() || createFile(noteSaveFile)) {
                noteSaveLocation.setText(noteSaveFile.getAbsolutePath());
                App.setNoteSaveLocation(noteSaveFile.getAbsolutePath());
            } else {
                showError("Error", "Unable to create file: " + noteSaveFile.getAbsolutePath());
            }
        }
    }

    @FXML
    private void onNightModeToggle() {
        App.toggleNightMode();
        nightModeCheckBox.setSelected(App.isNightMode);
        App.updateNoteStyles();
    }

    @FXML
    private void onBackBtn() throws IOException {
        App.showMenu();
    }

    private boolean createFile(File file) {
        try {
            return file.createNewFile();
        } catch (IOException e) {
            return false;
        }
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
