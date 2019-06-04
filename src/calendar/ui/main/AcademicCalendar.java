package calendar.ui.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Locale;

public class AcademicCalendar extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Locale.setDefault(Locale.ENGLISH);
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        // Set main window icon
        stage.getIcons().add(
                new Image("calendar/ui/icons/app_icon_2.png"));
        stage.setTitle("JavaFX Planner");

        // Maximize window at launch
        stage.setMaximized(false);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
