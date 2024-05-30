package test1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class noteController {

    @FXML
    private TextArea noteField;

    @FXML
    private TextArea textArea;

    @FXML
    private Button btnBack;

    @FXML
    private Button deleteNoteButton;

    @FXML
    private void initialize() {
        deleteNoteButton.setDisable(true);

        textArea.textProperty().addListener((observable, oldValue, newValue) -> {
            deleteNoteButton.setDisable(newValue.trim().isEmpty());
        });

        updateStyles();
    }

    public void updateStyles() {
        if (App.isNightMode) {
            textArea.setStyle("-fx-text-fill: white; -fx-control-inner-background: #3c3f41;");
            noteField.setStyle("-fx-text-fill: white; -fx-control-inner-background: #3c3f41;");
        } else {
            textArea.setStyle("-fx-text-fill: black; -fx-control-inner-background: white;");
            noteField.setStyle("-fx-text-fill: black; -fx-control-inner-background: white;");
        }
    }

    @FXML
    private void onSaveClick() throws IOException {
        String note = noteField.getText();
        if (!note.isEmpty()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(App.getNoteSaveLocation(), true))) {
                writer.write(note);
                writer.newLine();
            }
            noteField.clear();
        }
    }

    @FXML
    private void onShowClick() throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(App.getNoteSaveLocation()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        textArea.setText(content.toString());
    }

    @FXML
    private void onDeleteNoteClick() throws IOException {
        String selectedText = textArea.getSelectedText();
        if (!selectedText.isEmpty()) {
            StringBuilder content = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(App.getNoteSaveLocation()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.equals(selectedText.trim())) {
                        content.append(line).append("\n");
                    }
                }
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(App.getNoteSaveLocation()))) {
                writer.write(content.toString());
            }
            onShowClick();
        }
    }

    @FXML
    private void onBackBtn() throws IOException {
        App.showMenu();
    }
}
