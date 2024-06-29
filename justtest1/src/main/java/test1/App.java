package test1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;
    public static boolean isNightMode = false;
    private static String passSaveLocation = "passwords.txt";
    private static String noteSaveLocation = "notes.txt";
    private static noteController noteControllerInstance; 

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("menu"));
        stage.setScene(scene);
        stage.show();
        updateNightMode();
    }

    static void setSceneRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        updateNightMode();
    }

    static Scene getScene() {
        return scene;
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/" + fxml + ".fxml"));
        Parent root = fxmlLoader.load();
        if (fxml.equals("note")) {
            noteControllerInstance = fxmlLoader.getController(); 
        }
        return root;
    }

    static void showCalcu() throws IOException {
        setSceneRoot("calcu");
    }

    static void showPass() throws IOException {
        setSceneRoot("pass");
    }

    static void showNote() throws IOException {
        setSceneRoot("note");
    }

    static void showAlarm() throws IOException {
        setSceneRoot("alarm");
    }

    static void showMenu() throws IOException {
        setSceneRoot("menu");
    }

    static void showSetting() throws IOException {
        setSceneRoot("setting");
    }

    static void toggleNightMode() {
        isNightMode = !isNightMode;
        updateNightMode();
    }

    private static void updateNightMode() {
        if (isNightMode) {
            scene.getStylesheets().add(App.class.getResource("/dark-theme.css").toExternalForm());
        } else {
            scene.getStylesheets().remove(App.class.getResource("/dark-theme.css").toExternalForm());
        }
    }

    public static void updateNoteStyles() {
        if (noteControllerInstance != null) {
            noteControllerInstance.updateStyles();
        }
    }

    public static String getPassSaveLocation() {
        return passSaveLocation;
    }

    public static void setPassSaveLocation(String location) {
        passSaveLocation = location;
    }

    public static String getNoteSaveLocation() {
        return noteSaveLocation;
    }

    public static void setNoteSaveLocation(String location) {
        noteSaveLocation = location;
    }
}
