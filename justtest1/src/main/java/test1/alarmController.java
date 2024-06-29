package test1;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class alarmController {

    @FXML
    private Label currentTimeLabel;

    @FXML
    private ComboBox<String> hourComboBox;

    @FXML
    private ComboBox<String> minuteComboBox;

    @FXML
    private Label countdownLabel;

    @FXML
    private Button btnBack;

    @FXML
    private Button cancelAlarmButton;

    @FXML
    private CheckBox repeatCheckBox;

    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private Timeline clock;
    private Timer timer;

    @FXML
    private void initialize() {
        // Populate hour and minute ComboBoxes
        for (int i = 0; i < 24; i++) {
            hourComboBox.getItems().add(String.format("%02d", i));
        }
        for (int i = 0; i < 60; i++) {
            minuteComboBox.getItems().add(String.format("%02d", i));
        }

        // Current time updater
        clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            currentTimeLabel.setText(LocalTime.now().format(timeFormatter));
        }), new KeyFrame(Duration.seconds(1)));

        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    @FXML
    private void onSetAlarmClick() {
        if (timer != null) {
            timer.cancel();
        }

        String hourText = hourComboBox.getValue();
        String minuteText = minuteComboBox.getValue();

        if (hourText == null || minuteText == null) {
            return;
        }

        int hour = Integer.parseInt(hourText);
        int minute = Integer.parseInt(minuteText);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime alarmTime = now.withHour(hour).withMinute(minute).withSecond(0);

        if (alarmTime.isBefore(now)) {
            alarmTime = alarmTime.plusDays(1);
        }

        long secondsUntilAlarm = ChronoUnit.SECONDS.between(now, alarmTime);

        updateCountdownLabel(secondsUntilAlarm);

        boolean repeat = repeatCheckBox.isSelected();
        timer = new Timer();
        TimerTask task = new TimerTask() {
            long secondsLeft = secondsUntilAlarm;

            @Override
            public void run() {
                Platform.runLater(() -> {
                    secondsLeft--;
                    if (secondsLeft >= 0) {
                        updateCountdownLabel(secondsLeft);
                    } else {
                        showAlert("Alarm", "Time's up!");
                        if (repeat) {
                            secondsLeft = secondsUntilAlarm;
                            updateCountdownLabel(secondsLeft);
                        } else {
                            timer.cancel();
                        }
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }

    @FXML
    private void onCancelAlarmClick() {
        if (timer != null) {
            timer.cancel();
            countdownLabel.setText("Alarm canceled.");
        }
    }

    private void updateCountdownLabel(long seconds) {
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;
        countdownLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, secs));
    }

    private void showAlert(String title, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void onBackBtn() throws IOException {
        if (timer != null) {
            timer.cancel();
        }
        App.showMenu();
    }
}
