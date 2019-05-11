package calendar.ui.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        primaryStage.getIcons().add(
                new Image( "calendar/ui/icons/com_android_calendar.png" ));

        primaryStage.setTitle("4K-planner");
        primaryStage.setScene(new Scene(root, 1350, 880));
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
