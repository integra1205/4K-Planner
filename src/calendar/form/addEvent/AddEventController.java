package calendar.form.addEvent;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class AddEventController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }



    @FXML
    private Label exit;
    @FXML
    private Label save;
    @FXML
    private Label cancel;

    @FXML
    private AnchorPane rootPane;


    public void exit(MouseEvent mouseEvent) {

    }

    public void save(MouseEvent mouseEvent) {

    }

    public void cancel(MouseEvent mouseEvent) {

    }
}
