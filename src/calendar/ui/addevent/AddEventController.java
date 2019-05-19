
//Packages and Imports

package calendar.ui.addevent;

import calendar.data.model.MyCalendar;
import calendar.database.DBHandler;
import calendar.ui.main.FXMLDocumentController;
import com.jfoenix.controls.*;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddEventController implements Initializable {

    // Controllers
    private FXMLDocumentController mainController;


    //--------------------------------------------------------------------
    //---------Database Object -------------------------------------------
    DBHandler databaseHandler;
    //--------------------------------------------------------------------


    //Set main controller
    public void setMainController(FXMLDocumentController mainController) {
        this.mainController = mainController;
    }

    // Structure
    @FXML
    private Label topLabel;
    @FXML
    private AnchorPane rootPane;

    // Text fields
    @FXML
    private JFXTextField subject;
    @FXML
    private JFXTextArea comment;

    @FXML
    private JFXComboBox<String> categorieSelect;

    // Buttons
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXButton cancelButton;

    // Date and Time Picker
    @FXML
    private JFXTimePicker startTime;
    @FXML
    private JFXDatePicker startDate;

    @FXML
    private JFXTimePicker endTime;
    @FXML
    private JFXDatePicker endDate;

    // These fields are for mouse dragging of window
    private double xOffset;
    private double yOffset;

    @FXML
    void exit(MouseEvent event) {
        // Close the window
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void cancel(MouseEvent event) {
        // Close the window
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    //Function that inserts a new event in the database
    @FXML
    void save(MouseEvent event) {

        // Get the calendar name
        String calendarName = MyCalendar.getInstance().calendar_name;

        // Define Date format '2011-12-03'
        DateTimeFormatter myFormat = DateTimeFormatter.ISO_LOCAL_DATE;
        // Define Time format '10:15:30'
        DateTimeFormatter myTimeFormat = DateTimeFormatter.ISO_LOCAL_TIME;


        //Check if the user inputted information in all required fields!
        if (subject.getText().isEmpty() || categorieSelect.getSelectionModel().isEmpty()
                || startDate.getValue() == null || startTime.getValue() == null
                || endDate.getValue() == null || endTime.getValue() == null) {
            Alert alertMessage = new Alert(Alert.AlertType.ERROR);
            alertMessage.setHeaderText(null);
            alertMessage.setContentText("Please fill out all fields");
            alertMessage.showAndWait();
            return;
        }

        //Check if the event descritption contains the character ~ because it cannot contain it due to database and filtering issues
        if (subject.getText().contains("~") || comment.getText().contains("~")) {
            //Show message indicating that the event description cannot contain the character ~
            Alert alertMessage = new Alert(Alert.AlertType.WARNING);
            alertMessage.setHeaderText(null);
            alertMessage.setContentText("Event Description cannot contain the character ~");
            alertMessage.showAndWait();
            return;
        }

        //If all data is inputted correctly and validated, then add the event:

        // Get values from the DatePickers and TimePickers
        String sDate = startDate.getValue().format(myFormat);
        String sTime = startTime.getValue().format(myTimeFormat);

        String eDate = endDate.getValue().format(myFormat);
        String eTime = endTime.getValue().format(myTimeFormat);

        // Subject and comment for the event
        String eventSubject = subject.getText();
        String eventComment = comment.getText();

        // Get term that was selected by the user
        String categorie = categorieSelect.getValue();

        // variable that holds the ID value of the term selected by the user. It set to 0 becasue no selection has been made yet
        int chosenCategorieID = 0;

        // Get the ID of the selected term from the database based on the selected term's name
        chosenCategorieID = databaseHandler.getCategorieID(categorie);

        //---------------------------------------------------------
        //Insert new event into the EVENTS table in the database

        //Query to get ID for the selected Term
        String insertQuery = "INSERT INTO EVENTS(EventDescription,CategorieID,CalendarName,EventStartDate,EventStartTime,EventEndDate,EventEndTime,EventComment) " +
                "VALUES ("
                + "'" + eventSubject + "', "
                + chosenCategorieID + ", "
                + "'" + calendarName + "', "
                + "'" + sDate + "', "
                + "'" + sTime + "', "
                + "'" + eDate + "', "
                + "'" + eTime + "', "
                + "'" + eventComment + "'"
                + ")";


        //Check if insertion into database was successful, and show message either if it was or not
        if (databaseHandler.executeAction(insertQuery)) {
            Alert alertMessage = new Alert(Alert.AlertType.INFORMATION);
            alertMessage.setHeaderText(null);
            alertMessage.setContentText("Event was added successfully");
            alertMessage.showAndWait();
        } else //if there is an error
        {
            Alert alertMessage = new Alert(Alert.AlertType.ERROR);
            alertMessage.setHeaderText(null);
            alertMessage.setContentText("Adding Event Failed!\nThere is already an event with the same information");
            alertMessage.showAndWait();
        }

        //Show the new event on the calendar according to the selected filters
        mainController.repaintView();

        // Close the window
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }


    //Function that fills the startDate picker based on the clicked startDate
    void autofillDatePicker() {
        // Get selected day, month, and year and autofill startDate selection
   /*   int day = LocalDate.now().getDayOfMonth();
        int month = LocalDate.now().getMonthValue();
        int year = LocalDate.now().getYear();*/

        int hours = LocalTime.now().getHour();
        int minutes = LocalTime.now().getMinute();


        // Set default value for datepickers and timepickers
        startDate.setValue(LocalDate.now());
        endDate.setValue(LocalDate.now());

        startTime.setValue(LocalTime.of(hours,minutes));
        endTime.setValue(LocalTime.of(hours+1,minutes));
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {


        //*** Instantiate DBHandler object *******************
        databaseHandler = new DBHandler();
        //****************************************************


        //Fill the startDate picker
        autofillDatePicker();

        //Get the list of exisitng terms from the database and show them in the correspondent drop-down menu
        try {
            //Get terms from database and store them in the ObservableList variable "terms"
            ObservableList<String> terms = databaseHandler.getListOfCategories();
            //Show list of terms in the drop-down menu
            categorieSelect.setItems(terms);
        } catch (SQLException ex) {
            Logger.getLogger(AddEventController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //**********************************************************************
        // ************* Everything below is for Draggable Window ********

        // Set up Mouse Dragging for the Event pop up window
        topLabel.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage stage = (Stage) rootPane.getScene().getWindow();
                xOffset = stage.getX() - event.getScreenX();
                yOffset = stage.getY() - event.getScreenY();
            }
        });
        // Set up Mouse Dragging for the Event pop up window
        topLabel.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage stage = (Stage) rootPane.getScene().getWindow();
                stage.setX(event.getScreenX() + xOffset);
                stage.setY(event.getScreenY() + yOffset);
            }
        });
        // Change cursor when hover over draggable area
        topLabel.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage stage = (Stage) rootPane.getScene().getWindow();
                Scene scene = stage.getScene();
                scene.setCursor(Cursor.HAND); //Change cursor to hand
            }
        });

        // Change cursor when hover over draggable area
        topLabel.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage stage = (Stage) rootPane.getScene().getWindow();
                Scene scene = stage.getScene();
                scene.setCursor(Cursor.DEFAULT); //Change cursor to hand
            }
        });
    }

}
