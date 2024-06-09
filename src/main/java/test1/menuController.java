package test1;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class menuController {

    @FXML
    private Button btnCalcu, btnPass, btnNote, btnAlarm, btnSetting;

    @FXML
    private Label timeLabel;

    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @FXML
    private void initialize() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            timeLabel.setText(currentTime.format(timeFormatter));
        }), new KeyFrame(Duration.seconds(1)));

        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    @FXML
    private void onCalcuBtn() throws IOException {
        App.showCalcu();
    }

    @FXML
    private void onPassBtn() throws IOException {
        App.showPass();
    }

    @FXML
    private void onNoteBtn() throws IOException {
        App.showNote();
    }

    @FXML
    private void onAlarmBtn() throws IOException {
        App.showAlarm();
    }

    @FXML
    private void onSettingBtn() throws IOException {
        App.showSetting();
    }
}
