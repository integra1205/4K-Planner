package calendar.ui.main;

import calendar.data.model.MyCalendar;
import calendar.database.DBHandler;
import calendar.ui.addcalendar.AddCalendarController;
import calendar.ui.addevent.AddEventController;
import calendar.ui.editevent.EditEventController;
import calendar.ui.listcalendars.ListCalendarsController;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.effects.JFXDepthManager;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FXMLDocumentController implements Initializable {

    //Date and time fields
    @FXML
    private JFXDatePicker selectedDate;
    @FXML
    private Label thisTime;
    @FXML
    private Label thisDate;
    @FXML
    private Label monthLbl;

    @FXML
    private HBox weekdayHeader;

    @FXML
    private VBox vBoxMonth;


    @FXML
    private Pane dayView;
    @FXML
    private Tab dayPan;


    // WEEK
    @FXML
    private Tab weekPan;

    @FXML
    private VBox vBoxWeek;

    @FXML
    private HBox weekInfo;

    @FXML
    private Label weekLbl;

    @FXML
    private ScrollPane scrollPaneWeek;

    @FXML
    private HBox weekdayHeaderForWeek;

    @FXML
    private GridPane weekView;


    @FXML
    private GridPane monthView;

    @FXML
    private GridPane yearView;
    @FXML
    private Tab yearPan;

    @FXML
    private ScrollPane scrollPaneMonth;





    //--------- Database Handler -----------------------------------------
    DBHandler databaseHandler;
    //--------------------------------------------------------------------

    // Color pickers
    @FXML
    private JFXColorPicker workCP;
    @FXML
    private JFXColorPicker studyCP;
    @FXML
    private JFXColorPicker sportCP;
    @FXML
    private JFXColorPicker vacationCP;
    @FXML
    private JFXColorPicker birthdaysCP;
    @FXML
    private JFXColorPicker holidaysCP;
    @FXML
    private JFXColorPicker otherCP;


    // Check Boxes for filtering
    @FXML
    private JFXCheckBox workBox;
    @FXML
    private JFXCheckBox studyBox;
    @FXML
    private JFXCheckBox sportBox;
    @FXML
    private JFXCheckBox vacationBox;
    @FXML
    private JFXCheckBox birthdaysBox;
    @FXML
    private JFXCheckBox holidaysBox;
    @FXML
    private JFXCheckBox otherBox;
    @FXML
    private JFXCheckBox selectAllCheckBox;

    @FXML
    private Label calendarNameLbl;

    // Other global variables for the class
    public static boolean workingOnCalendar = false;
    public static boolean checkBoxesHaveBeenClicked = false;


    //**************************************************************************
    //**************************************************************************
    //**************************************************************************

    // Events
    private void addEvent(VBox day) {

        // Purpose - Add event to a day

        // Do not add events to days without labels
        if (!day.getChildren().isEmpty()) {

            // Get the day number
            Label lbl = (Label) day.getChildren().get(0);
            System.out.println(lbl.getText());

            // Store event day and month in data singleton
            MyCalendar.getInstance().event_day = Integer.parseInt(lbl.getText());
            //MyCalendar.getInstance().event_month = MyCalendar.getInstance().getMonthIndex(monthSelect.getSelectionModel().getSelectedItem());
            //MyCalendar.getInstance().event_year = Integer.parseInt(selectedYear.getValue());

            // Open add event view
            try {
                // Load root layout from fxml file.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/calendar/ui/addevent/add_event.fxml"));
                AnchorPane rootLayout = (AnchorPane) loader.load();
                Stage stage = new Stage(StageStyle.UNDECORATED);
                stage.initModality(Modality.APPLICATION_MODAL);

                // Pass main controller reference to view
                AddEventController eventController = loader.getController();
                eventController.setMainController(this);

                // Show the scene containing the root layout.
                Scene scene = new Scene(rootLayout);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void editEvent(VBox day, String descript, String categorieID) {

        // Store event fields in data singleton
        Label dayLbl = (Label) day.getChildren().get(0);
        MyCalendar.getInstance().event_day = Integer.parseInt(dayLbl.getText());
        //MyCalendar.getInstance().event_month = MyCalendar.getInstance().getMonthIndex(monthSelect.getSelectionModel().getSelectedItem());
        //MyCalendar.getInstance().event_year = Integer.parseInt(selectedYear.getValue());
        MyCalendar.getInstance().event_subject = descript;
        MyCalendar.getInstance().event_categorie_id = Integer.parseInt(categorieID);

        // When user clicks on any date in the calendar, event editor window opens
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/calendar/ui/editevent/edit_event.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);

            // Pass main controller reference to view
            EditEventController eventController = loader.getController();
            eventController.setMainController(this);

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void newCalendarEvent() {
        // New Calendar view
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/calendar/ui/addcalendar/add_calendar.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);

            // Pass main controller reference to view
            AddCalendarController calendarController = loader.getController();
            calendarController.setMainController(this);

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listCalendarsEvent() {
        // List Calendar view
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/calendar/ui/listcalendars/list_calendars.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);

            // Pass main controller reference to view
            ListCalendarsController listController = loader.getController();
            listController.setMainController(this);

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void manageCategoriesEvent() {
        // Manage Categorie's view
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/calendar/ui/listcategories/list_categories.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initializeDateSelector() {

        // Add event listener to each month list item, allowing user to change date
        selectedDate.valueProperty().addListener((observable, oldValue, newValue) -> {

                    // Necessary to check for null because change listener will
                    // also detect clear() calls
                    if (newValue != null) {

                        // Update the VIEWING MONTH
                        MyCalendar.getInstance().viewing_month = MyCalendar.getInstance().getMonthIndex(newValue.getMonth().toString());
                        MyCalendar.getInstance().viewing_year = newValue.getYear();
                        MyCalendar.getInstance().viewing_day = newValue.getDayOfYear();
                        MyCalendar.getInstance().viewing_week = newValue.getDayOfWeek().getValue();
                        MyCalendar.getInstance().viewing_day_of_month = newValue.getDayOfMonth();
                        // Update view
                        repaintView();

                    }
                }
        );



/*        // Add event listener to each year item, allowing user to change years
        selectedYear.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (newValue != null) {

                    // Update the VIEWING YEAR
                    MyCalendar.getInstance().viewing_year = Integer.parseInt(newValue);

                    // Update view
                    repaintView();
                }
            }
        });*/
    }

    private void loadCalendarLabels() {
        loadMonthLabels();
        loadWeekLabels();
    }

    private void loadMonthLabels() {

        // Get the current VIEW
        int year = MyCalendar.getInstance().viewing_year;
        int month = MyCalendar.getInstance().viewing_month+1;

        monthLbl.setText(MyCalendar.getInstance().getMonth(month) + "  " + Integer.toString(year));

        // Note: Java's Gregorian Calendar class gives us the right
        // "first day of the month" for a given calendar & month
        // This accounts for Leap Year
        GregorianCalendar gc = new GregorianCalendar(year, month, 1);
        int firstDay = gc.get(java.util.Calendar.DAY_OF_WEEK);
        int daysInMonth = gc.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);

        // We are "offsetting" our start depending on what the
        // first day of the month is.
        // For example: Sunday start, Monday start, Wednesday start.. etc
        int offset = firstDay;
        int gridCount = 1;
        int lblCount = 1;

        // Go through calendar grid
        for (Node node : monthView.getChildren()) {

            VBox dayOfMonth = (VBox) node;

            dayOfMonth.getChildren().clear();
            dayOfMonth.setStyle("-fx-backgroud-color: white");
            dayOfMonth.setStyle("-fx-font: 14px \"System\" ");

            // Start placing labels on the first dayOfMonth for the month
            if (gridCount < offset) {
                gridCount++;
                // Darken color of the offset days
                dayOfMonth.setStyle("-fx-background-color: #E9F2F5");
            } else {

                // Don't place a label if we've reached maximum label for the month
                if (lblCount > daysInMonth) {
                    // Instead, darken dayOfMonth color
                    dayOfMonth.setStyle("-fx-background-color: #E9F2F5");
                } else {

                    // Make a new dayOfMonth label
                    Label lbl = new Label(Integer.toString(lblCount));
                    lbl.setPadding(new Insets(5));
                    lbl.setStyle("-fx-text-fill:darkslategray");

                    dayOfMonth.getChildren().add(lbl);
                }
                lblCount++;
            }
        }
    }

    // WEEK
    private void loadWeekLabels() {

        // Get the current VIEW
        int year = MyCalendar.getInstance().viewing_year;
        int month = MyCalendar.getInstance().viewing_month + 1;
        LocalDate[] allDaysOfSelectedWeek = MyCalendar.getInstance().getAllDaysOfSelectedWeek();
        int dayCount = 0;

        weekLbl.setText("from " + allDaysOfSelectedWeek[0] + " to " + allDaysOfSelectedWeek[6]);

        // Go through calendar grid
        for (Node node : weekView.getChildren()) {

            VBox dayOfWeek = (VBox) node;
            dayOfWeek.getChildren().clear();
            dayOfWeek.setStyle("-fx-backgroud-color: white");
            dayOfWeek.setStyle("-fx-font: 14px \"System\" ");

            Label lbl = new Label(Integer.toString(allDaysOfSelectedWeek[dayCount].getDayOfMonth()));
            lbl.setPadding(new Insets(5));
            lbl.setStyle("-fx-text-fill:darkslategray");
            dayOfWeek.getChildren().add(lbl);
            dayCount++;
        }
    }

    public void calendarGenerate() {

        // Set calendar name label
        calendarNameLbl.setText(MyCalendar.getInstance().calendar_name);
        selectedDate.setValue(LocalDate.now());

        // Load year and month selection
        MyCalendar.getInstance().viewing_year = selectedDate.getValue().getYear();

        MyCalendar.getInstance().viewing_month =
                MyCalendar.getInstance().getMonthIndex(selectedDate.getValue().getMonth().toString());
        // Update view
        repaintView();
    }

    public void repaintView() {
        // Purpose - To be usable anywhere to update view
        // 1. Correct calendar labels based on Gregorian Calendar 
        // 2. Display events known to database

        loadCalendarLabels();
        if (checkBoxesHaveBeenClicked) {
            populateDayWithEvents();
            populateWeekWithEvents();
            populateMonthWithEvents();
            populateYearWithEvents();
        } else {
            ActionEvent actionEvent = new ActionEvent();
            handleCheckBoxAction(actionEvent);
        }
    }

    private void populateYearWithEvents() {
        //TODO
    }

    //WEEK
    private void populateWeekWithEvents() {
        //TODO
        // Get viewing calendar

        String calendarName = MyCalendar.getInstance().calendar_name;
        String currentMonth = MyCalendar.getInstance().getMonth(MyCalendar.getInstance().viewing_month + 1);
        int currentMonthIndex = MyCalendar.getInstance().viewing_month + 1;
        int currentYear = MyCalendar.getInstance().viewing_year;
        LocalDate[] currentWeek = MyCalendar.getInstance().getAllDaysOfSelectedWeek();

        // Query to get ALL Events from the selected calendar!!
        String getAllEventsQuery = "SELECT * From EVENTS WHERE CalendarName='" + calendarName + "'";

        // Store the results here
        ResultSet result = databaseHandler.executeQuery(getAllEventsQuery);

        try {
            while (result.next()) {

                // Get date for the event
                Date startDate = result.getDate("EventStartDate");
                Date endDate = result.getDate("EventEndDate");
                Time startTime = result.getTime("EventStartTime");
                Time endTime = result.getTime("EventEndTime");
                String eventDescript = result.getString("EventDescription");
                int eventCategorieID = result.getInt("CategorieID");

                // Check for year we have selected
                if (currentYear == startDate.toLocalDate().getYear()) {
                    if  (startDate.toLocalDate().isEqual(currentWeek[0]) ||
                         startDate.toLocalDate().isEqual(currentWeek[6]) ||
                        (startDate.toLocalDate().isAfter(currentWeek[0]) && startDate.toLocalDate().isBefore(currentWeek[6])) ||
                        (startDate.toLocalDate().isBefore(currentWeek[0]) && endDate.toLocalDate().isAfter(currentWeek[0])) ||
                         endDate.toLocalDate().isEqual(currentWeek[0])) {

                        // Get day for the month
                        int startDay = startDate.toLocalDate().getDayOfMonth();
                        int finishDay = endDate.toLocalDate().getDayOfMonth();

                        // Display decription of the event given it's day
                        showDateWeek(startDay, eventDescript, eventCategorieID);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showDateWeek(int dayNumber, String descript, int categorieID) {

        Image img = new Image(getClass().getClassLoader().getResourceAsStream("calendar/ui/icons/icon2.png"));
        ImageView imgView = new ImageView();
        imgView.setImage(img);

        for (Node node : weekView.getChildren()) {

            // Get the current day
            VBox day = (VBox) node;

            // Don't look at any empty days (they at least must have a day label!)
            if (!day.getChildren().isEmpty()) {

                // Get the day label for that day
                Label lbl = (Label) day.getChildren().get(0);

                // Get the number
                int currentNumber = Integer.parseInt(lbl.getText());

                // Did we find a match?
                if (currentNumber == dayNumber) {

                    // Add an event label with the given description
                    Label eventLbl = new Label(descript);
                    eventLbl.setGraphic(imgView);
                    eventLbl.getStyleClass().add("event-label");

                    // Save the categorie ID in accessible text
                    eventLbl.setAccessibleText(Integer.toString(categorieID));
                    System.out.println(eventLbl.getAccessibleText());

                    eventLbl.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                        editEvent((VBox) eventLbl.getParent(), eventLbl.getText(), eventLbl.getAccessibleText());

                    });

                    // Get categorie color from categorie's table
                    String eventRGB = databaseHandler.getCategorieColor(categorieID);

                    // Parse for rgb values
                    String[] colors = eventRGB.split("-");
                    String red = colors[0];
                    String green = colors[1];
                    String blue = colors[2];

                    System.out.println("Color; " + red + green + blue);

                    eventLbl.setStyle("-fx-background-color: rgb(" + red +
                            ", " + green + ", " + blue + ", " + 1 + ");");

                    // Stretch to fill box
                    eventLbl.setMaxWidth(Double.MAX_VALUE);

                    // Mouse effects
                    eventLbl.addEventHandler(MouseEvent.MOUSE_ENTERED, (e) -> {
                        eventLbl.getScene().setCursor(Cursor.HAND);
                    });

                    eventLbl.addEventHandler(MouseEvent.MOUSE_EXITED, (e) -> {
                        eventLbl.getScene().setCursor(Cursor.DEFAULT);
                    });

                    // Add label to calendar
                    day.getChildren().add(eventLbl);
                }
            }
        }
    }


    private void populateDayWithEvents() {
        //TODO
    }

    private void populateMonthWithEvents() {

        // Get viewing calendar
        String calendarName = MyCalendar.getInstance().calendar_name;
        String currentMonth = selectedDate.getValue().getMonth().toString();

        int currentMonthIndex = MyCalendar.getInstance().getMonthIndex(currentMonth) + 1;
        int currentYear = selectedDate.getValue().getYear();

        // Query to get ALL Events from the selected calendar!!
        String getMonthEventsQuery = "SELECT * From EVENTS WHERE CalendarName='" + calendarName + "'";

        // Store the results here
        ResultSet result = databaseHandler.executeQuery(getMonthEventsQuery);

        try {
            while (result.next()) {

                // Get date for the event
                Date startDate = result.getDate("EventStartDate");
                String eventDescript = result.getString("EventDescription");
                int eventCategorieID = result.getInt("CategorieID");

                // Check for year we have selected
                if (currentYear == startDate.toLocalDate().getYear()) {
                    // Check for the month we already have selected (we are viewing)
                    if (currentMonthIndex == startDate.toLocalDate().getMonthValue()) {

                        // Get day for the month
                        int day = startDate.toLocalDate().getDayOfMonth();

                        // Display decription of the event given it's day
                        showDate(day, eventDescript, eventCategorieID);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showDate(int dayNumber, String descript, int categorieID) {

        Image img = new Image(getClass().getClassLoader().getResourceAsStream("calendar/ui/icons/icon2.png"));
        ImageView imgView = new ImageView();
        imgView.setImage(img);

        for (Node node : monthView.getChildren()) {

            // Get the current day    
            VBox day = (VBox) node;

            // Don't look at any empty days (they at least must have a day label!)
            if (!day.getChildren().isEmpty()) {

                // Get the day label for that day
                Label lbl = (Label) day.getChildren().get(0);

                // Get the number
                int currentNumber = Integer.parseInt(lbl.getText());

                // Did we find a match?
                if (currentNumber == dayNumber) {

                    // Add an event label with the given description
                    Label eventLbl = new Label(descript);
                    eventLbl.setGraphic(imgView);
                    eventLbl.getStyleClass().add("event-label");

                    // Save the categorie ID in accessible text
                    eventLbl.setAccessibleText(Integer.toString(categorieID));
                    System.out.println(eventLbl.getAccessibleText());

                    eventLbl.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                        editEvent((VBox) eventLbl.getParent(), eventLbl.getText(), eventLbl.getAccessibleText());

                    });

                    // Get categorie color from categorie's table
                    String eventRGB = databaseHandler.getCategorieColor(categorieID);

                    // Parse for rgb values
                    String[] colors = eventRGB.split("-");
                    String red = colors[0];
                    String green = colors[1];
                    String blue = colors[2];

                    System.out.println("Color; " + red + green + blue);

                    eventLbl.setStyle("-fx-background-color: rgb(" + red +
                            ", " + green + ", " + blue + ", " + 1 + ");");

                    // Stretch to fill box
                    eventLbl.setMaxWidth(Double.MAX_VALUE);

                    // Mouse effects
                    eventLbl.addEventHandler(MouseEvent.MOUSE_ENTERED, (e) -> {
                        eventLbl.getScene().setCursor(Cursor.HAND);
                    });

                    eventLbl.addEventHandler(MouseEvent.MOUSE_EXITED, (e) -> {
                        eventLbl.getScene().setCursor(Cursor.DEFAULT);
                    });

                    // Add label to calendar
                    day.getChildren().add(eventLbl);
                }
            }
        }
    }


/*    public void exportCalendarPDF() {
        TableView<MyEvent> table = new TableView<MyEvent>();
        ObservableList<MyEvent> data = FXCollections.observableArrayList();


        double w = 500.00;
        // set width of table view
        table.setPrefWidth(w);
        // set resize policy
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // intialize columns
        TableColumn<MyEvent, String> categorie = new TableColumn<MyEvent, String>("Categorie");
        TableColumn<MyEvent, String> subject = new TableColumn<MyEvent, String>("Subject");
        TableColumn<MyEvent, String> date = new TableColumn<MyEvent, String>("Date");
        // set width of columns
        categorie.setMaxWidth(1f * Integer.MAX_VALUE * 20); // 50% width
        subject.setMaxWidth(1f * Integer.MAX_VALUE * 60); // 50% width
        date.setMaxWidth(1f * Integer.MAX_VALUE * 20); // 50% width
        // 
        categorie.setCellValueFactory(new PropertyValueFactory<MyEvent, String>("categorie"));
        subject.setCellValueFactory(new PropertyValueFactory<MyEvent, String>("subject"));
        date.setCellValueFactory(new PropertyValueFactory<MyEvent, String>("date"));

        // Add columns to the table
        table.getColumns().add(categorie);
        table.getColumns().add(subject);
        table.getColumns().add(date);

        String calendarName = MyCalendar.getInstance().calendar_name;

        // Query to get ALL Events from the selected calendar!!
        String getMonthEventsQuery = "SELECT * From EVENTS WHERE CalendarName='" + calendarName + "' ORDER BY EventDate";

        // Store the results here
        ResultSet result = databaseHandler.executeQuery(getMonthEventsQuery);

        try {

            while (result.next()) {
                //initalize temporarily strings
                String evCategorie = "";


                //***** Get Categorie, Event Description and Date *****

                //Get Event Description
                String eventDescript = result.getString("EventDescription");
                //Get Categorie ID for an event
                int categorieID = result.getInt("CategorieID");

                //Get Event starting and ending date and format it as day-month-year
                Date stDate = result.getDate("EventStartDate");
                Date eDate = result.getDate("EventEndDate");
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String startDate = df.format(stDate);
                String endDate = df.format(stDate);

                Time stTime = result.getTime("EventStartTime");

                //Query that will get the categorie name based on a categorie ID
                String getCategorieQuery = "SELECT CategorieName FROM CATEGORIES WHERE CategorieID=" + categorieID + "";
                //Execute query to get CategorieName and store it in a ResultSet variable
                ResultSet termResult = databaseHandler.executeQuery(getCategorieQuery);


                while (termResult.next()) {
                    evCategorie = termResult.getString(1);
                     *//*
                      while(programResult.next())
                        {
                           tempProgram = programResult.getString(1);
                        }
                      tempTerm+=" "+tempProgram;
                    *//*
                }


                //Add event information in a row
                data.add(new MyEvent(eventDescript, evCategorie, startDate, endDate));

            }
        } catch (SQLException ex) {
            Logger.getLogger(AddEventController.class.getName()).log(Level.SEVERE, null, ex);
        }


        table.getItems().setAll(data);
        // open dialog window and export table as pdf
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            job.printPage(table);
            job.endJob();
        }
    }*/

    private String getRGB(Color c) {

        String rgb = Integer.toString((int) (c.getRed() * 255)) + "-"
                + Integer.toString((int) (c.getGreen() * 255)) + "-"
                + Integer.toString((int) (c.getBlue() * 255));

        return rgb;
    }

    private void changeColors() {

        // Purpose - Take all chosen colors in the Colors menu and
        // update each categorie's color in the database

        Color workColor = workCP.getValue();
        String workRGB = getRGB(workColor);
        databaseHandler.setCategorieColor("Work", workRGB);

        Color studyColor = studyCP.getValue();
        String studyRGB = getRGB(studyColor);
        databaseHandler.setCategorieColor("Study", studyRGB);

        Color sportColor = sportCP.getValue();
        String sportRGB = getRGB(sportColor);
        databaseHandler.setCategorieColor("Sport", sportRGB);

        Color vacationColor = vacationCP.getValue();
        String vacationRGB = getRGB(vacationColor);
        databaseHandler.setCategorieColor("Vacation", vacationRGB);

        Color birthdaysColor = birthdaysCP.getValue();
        String birthdaysRGB = getRGB(birthdaysColor);
        databaseHandler.setCategorieColor("Birthdays", birthdaysRGB);

        Color holidayColor = holidaysCP.getValue();
        String holidayRGB = getRGB(holidayColor);
        databaseHandler.setCategorieColor("Holiday", holidayRGB);

        Color otherColor = otherCP.getValue();
        String otherRGB = getRGB(otherColor);
        databaseHandler.setCategorieColor("Other", otherRGB);

    }

    private void initalizeColorPicker() {

        String workRGB = databaseHandler.getCategorieColor(databaseHandler.getCategorieID("Work"));
        String studyRGB = databaseHandler.getCategorieColor(databaseHandler.getCategorieID("Study"));
        String sportRGB = databaseHandler.getCategorieColor(databaseHandler.getCategorieID("Sport"));
        String vacationRGB = databaseHandler.getCategorieColor(databaseHandler.getCategorieID("Vacation"));
        String birthdayRGB = databaseHandler.getCategorieColor(databaseHandler.getCategorieID("Birthdays"));
        String holidayRGB = databaseHandler.getCategorieColor(databaseHandler.getCategorieID("Holidays"));
        String otherRGB = databaseHandler.getCategorieColor(databaseHandler.getCategorieID("Other"));

        // Parse for rgb values for work
        String[] colors = workRGB.split("-");
        String red = colors[0];
        String green = colors[1];
        String blue = colors[2];
        Color c = Color.rgb(Integer.parseInt(red), Integer.parseInt(green), Integer.parseInt(blue));
        workCP.setValue(c);

        // Parse for rgb values for study
        colors = sportRGB.split("-");
        red = colors[0];
        green = colors[1];
        blue = colors[2];
        c = Color.rgb(Integer.parseInt(red), Integer.parseInt(green), Integer.parseInt(blue));
        studyCP.setValue(c);

        // Parse for rgb values for sport
        colors = studyRGB.split("-");
        red = colors[0];
        green = colors[1];
        blue = colors[2];
        c = Color.rgb(Integer.parseInt(red), Integer.parseInt(green), Integer.parseInt(blue));
        sportCP.setValue(c);

        // Parse for rgb values for vacation
        colors = vacationRGB.split("-");
        red = colors[0];
        green = colors[1];
        blue = colors[2];
        c = Color.rgb(Integer.parseInt(red), Integer.parseInt(green), Integer.parseInt(blue));
        vacationCP.setValue(c);

        // Parse for rgb values for birthdays
        colors = birthdayRGB.split("-");
        red = colors[0];
        green = colors[1];
        blue = colors[2];
        c = Color.rgb(Integer.parseInt(red), Integer.parseInt(green), Integer.parseInt(blue));
        birthdaysCP.setValue(c);

        // Parse for rgb values for Holiday
        colors = holidayRGB.split("-");
        red = colors[0];
        green = colors[1];
        blue = colors[2];
        c = Color.rgb(Integer.parseInt(red), Integer.parseInt(green), Integer.parseInt(blue));
        holidaysCP.setValue(c);

        // Parse for rgb values for other
        colors = otherRGB.split("-");
        red = colors[0];
        green = colors[1];
        blue = colors[2];
        c = Color.rgb(Integer.parseInt(red), Integer.parseInt(green), Integer.parseInt(blue));
        otherCP.setValue(c);

    }

    public void initializeMonthView() {

        // Go through each calendar grid location, or each "day" (7x6)
        int rows = 6;
        int cols = 7;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                // Add VBox and style it
                // Add VBox and style it
                VBox vPane = new VBox();
                vPane.getStyleClass().add("calendar_pane");
                vPane.setMinWidth(weekdayHeader.getPrefWidth() / 7);

                vPane.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                    addEvent(vPane);
                });

                GridPane.setVgrow(vPane, Priority.ALWAYS);

                // Add it to the grid
                monthView.add(vPane, j, i);
            }
        }

        // Set up Row Constraints
        for (int i = 0; i < 7; i++) {
            RowConstraints row = new RowConstraints();
            row.setMinHeight(scrollPaneMonth.getHeight() / 7);
            monthView.getRowConstraints().add(row);
        }
    }


    public void initializeCalendarWeekdayHeader() {

        // 7 days in a week
        int weekdays = 7;

        // Weekday names
        String[] weekAbbr = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

        for (int i = 0; i < weekdays; i++) {

            // Make new pane and label of weekday
            StackPane pane = new StackPane();
            pane.getStyleClass().add("weekday-header");

            // Make panes take up equal space
            HBox.setHgrow(pane, Priority.ALWAYS);
            pane.setMaxWidth(Double.MAX_VALUE);

            // Note: After adding a label to this, it tries to resize itself..
            // So I'm setting a minimum width.
            pane.setMinWidth(weekdayHeader.getPrefWidth() / 7);

            // Add it to the header
            weekdayHeader.getChildren().add(pane);

            // Add weekday name
            pane.getChildren().add(new Label(weekAbbr[i]));
        }
    }

    //WEEK
    public void initializeCalendarWeekdayHeaderWeek() {

        // Weekday names
        String[] weekAbbr = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

        for (int i = 0; i < weekAbbr.length; i++) {

            // Make new pane and label of weekday
            StackPane pane = new StackPane();
            pane.getStyleClass().add("weekday-header");

            // Make panes take up equal space
            HBox.setHgrow(pane, Priority.ALWAYS);
            pane.setMaxWidth(Double.MAX_VALUE);

            // Note: After adding a label to this, it tries to resize itself..
            // So I'm setting a minimum width.
            pane.setMinWidth(weekdayHeaderForWeek.getPrefWidth() / 7);

            // Add it to the header
            weekdayHeaderForWeek.getChildren().add(pane);

            // Add weekday name
            pane.getChildren().add(new Label(weekAbbr[i]));
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Make empty calendar in every view
        initializeDateSelector();

        initDateClock();

        initializeDayView();

        initializeWeekView();
        initializeCalendarWeekdayHeaderWeek();

        initializeMonthView();
        initializeCalendarWeekdayHeader();

        initializeYearView();


        // Set Depths
        JFXDepthManager.setDepth(scrollPaneMonth, 1);

        //*** Instantiate DBHandler object *******************
        databaseHandler = new DBHandler();
        //****************************************************

        //Initialize all Color Pickers. Show saved colors for specific categories
        initalizeColorPicker();

        //If the user is not working on any new or existing calendar, disable the filtering check boxes and rules buttons
        disableCheckBoxes();
        // I am still working on this function and issue
        //disableButtons();  

    }

    private void initDateClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            thisTime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

        String dayOfWeek = LocalDateTime.now().getDayOfWeek().toString();
        String month = LocalDateTime.now().getMonth().toString();
        int day = LocalDateTime.now().getDayOfMonth();
        int year = LocalDateTime.now().getYear();

        thisDate.setText(dayOfWeek + ", " + month + ", " + day + ", " + year);
    }

    private void initializeYearView() {
        //TODO: inizialize year view
    }

    //WEEK
    private void initializeWeekView() {
        //TODO: initialize week view

        // Go through each calendar grid location, or each "day" (7)
        int cols = 7;
        for (int i = 0; i < cols; i++) {

            // Add VBox and style it
            VBox vPane = new VBox();
            vPane.getStyleClass().add("calendar_pane");
            vPane.setMinWidth(weekdayHeaderForWeek.getPrefWidth() / cols);

            vPane.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                addEvent(vPane);
            });

            GridPane.setVgrow(vPane, Priority.ALWAYS);

            // Add it to the grid
            weekView.add(vPane, i, 0);
        }
    }

    private void initializeDayView() {
        //TODO: initialize day view
    }

    //**********************************************************************************
    //**********************************************************************************
    //**********************************************************************************

    // Side - menu buttons 
    @FXML
    private void newCalendar(MouseEvent event) {
        newCalendarEvent();
    }

    @FXML
    private void manageCalendars(MouseEvent event) {
        listCalendarsEvent();
    }

    //@FXML
   /* //private void manageRules(MouseEvent event) {
        listRulesEvent();
    }*/
    
  /*  @FXML
    private void newRule(MouseEvent event) {
        newRuleEvent();
    }*/

    @FXML
    private void pdfBtn(MouseEvent event) {
        //exportCalendarPDF();
    }

    @FXML
    private void excelBtn(MouseEvent event) {
        //exportCalendarExcel();
    }

    @FXML
    private void updateColors(MouseEvent event) {
        changeColors();

        if (MyCalendar.getInstance().calendar_name != null)
            repaintView();
    }

    @FXML
    private void manageCategoriesDates(MouseEvent event) {
        manageCategoriesEvent();
    }

    //******************************************************************************************
    //******************************************************************************************
    //******************************************************************************************

    ///******* I am still working on these functions and issues  ********
    /*
    public void disableButtons(){
        
        manageRulesButton.setDisable(true);
    }
    
    public void enableButtons(){
        
        manageRulesButton.setDisable(false);
    }
    */

    public void disableCheckBoxes() {

        //Disable all check boxes for filtering events
        workBox.setDisable(true);
        studyBox.setDisable(true);
        sportBox.setDisable(true);
        vacationBox.setDisable(true);
        birthdaysBox.setDisable(true);
        holidaysBox.setDisable(true);
        otherBox.setDisable(true);
        workBox.setDisable(true);
        selectAllCheckBox.setDisable(true);
    }

    public void enableCheckBoxes() {

        //Enable all check boxes for filtering events
        workBox.setDisable(false);
        studyBox.setDisable(false);
        sportBox.setDisable(false);
        vacationBox.setDisable(false);
        birthdaysBox.setDisable(false);
        holidaysBox.setDisable(false);
        otherBox.setDisable(false);
        workBox.setDisable(false);
        selectAllCheckBox.setDisable(false);
        //Set selection for select all check box to true
        selectAllCheckBox.setSelected(true);
    }

    public void selectCheckBoxes() {

        //Set ALL check boxes for filtering events to be selected
        workBox.setSelected(true);
        studyBox.setSelected(true);
        sportBox.setSelected(true);
        vacationBox.setSelected(true);
        birthdaysBox.setSelected(true);
        holidaysBox.setSelected(true);
        otherBox.setSelected(true);
        workBox.setSelected(true);
    }

    public void unSelectCheckBoxes() {

        //Set ALL check boxes for filtering events to be selected
        workBox.setSelected(false);
        studyBox.setSelected(false);
        sportBox.setSelected(false);
        vacationBox.setSelected(false);
        birthdaysBox.setSelected(false);
        holidaysBox.setSelected(false);
        otherBox.setSelected(false);
        workBox.setSelected(false);
    }

    //******************************************************************************************
    //******************************************************************************************
    //******************************************************************************************

    //Function filters all events. Make them all show up or disappear from the calendar
    @FXML
    private void selectAllCheckBoxes(ActionEvent e) {
        if (selectAllCheckBox.isSelected()) {
            selectCheckBoxes();
        } else {
            unSelectCheckBoxes();
        }

        handleCheckBoxAction(new ActionEvent());
    }


    //This function is constantly checking if any of the checkboxes is selected or deselected
    //and therefore, populate the calendar with the events of the terms that are selected

    @FXML
    private void handleCheckBoxAction(ActionEvent e) {
        System.out.println("have check boxes been cliked: " + checkBoxesHaveBeenClicked);
        if (!checkBoxesHaveBeenClicked) {
            checkBoxesHaveBeenClicked = true;
            System.out.println("have check boxes been cliked: " + checkBoxesHaveBeenClicked);
        }

        //ArrayList that will hold all the filtered events based on the selection of what terms are visible
        ArrayList<String> categoriesToFilter = new ArrayList();

        //Check each of the checkboxes and call the appropiate queries to
        //show only the events that belong to the term(s) the user selects

        //Work
        if (workBox.isSelected()) {
            System.out.println("Work checkbox is selected");
            categoriesToFilter.add("Work");
        }

        if (!workBox.isSelected()) {
            System.out.println("Work checkbox is now deselected");
            int auxIndex = categoriesToFilter.indexOf("Work");
            if (auxIndex != -1) {
                categoriesToFilter.remove(auxIndex);
            }
        }


        //Study
        if (studyBox.isSelected()) {
            System.out.println("Study checkbox is selected");
            categoriesToFilter.add("Study");
        }
        if (!studyBox.isSelected()) {
            System.out.println("Study checkbox is now deselected");
            int auxIndex = categoriesToFilter.indexOf("Study");
            if (auxIndex != -1) {
                categoriesToFilter.remove(auxIndex);
            }
        }

        //Sport
        if (sportBox.isSelected()) {
            System.out.println("Sport checkbox is selected");
            categoriesToFilter.add("Sport");
        }
        if (!sportBox.isSelected()) {
            System.out.println("Sport checkbox is now deselected");
            int auxIndex = categoriesToFilter.indexOf("Sport");
            if (auxIndex != -1) {
                categoriesToFilter.remove(auxIndex);
            }
        }

        //Vacation
        if (vacationBox.isSelected()) {
            System.out.println("Vacation checkbox is selected");
            categoriesToFilter.add("Vacation");
        }
        if (!vacationBox.isSelected()) {
            System.out.println("Vacation checkbox is now deselected");
            int auxIndex = categoriesToFilter.indexOf("Vacation");
            if (auxIndex != -1) {
                categoriesToFilter.remove(auxIndex);
            }
        }

        //Birthdays
        if (birthdaysBox.isSelected()) {
            System.out.println("Birthdays checkbox is selected");
            categoriesToFilter.add("Birthdays");
        }
        if (!birthdaysBox.isSelected()) {
            System.out.println("Birthdays checkbox is now deselected");
            int auxIndex = categoriesToFilter.indexOf("Birthdays");
            if (auxIndex != -1) {
                categoriesToFilter.remove(auxIndex);
            }
        }

        //Holidays
        if (holidaysBox.isSelected()) {
            System.out.println("Holidays checkbox is selected");
            categoriesToFilter.add("Holidays");
        }
        if (!holidaysBox.isSelected()) {
            System.out.println("Holidays checkbox is now deselected");
            int auxIndex = categoriesToFilter.indexOf("Holidays");
            if (auxIndex != -1) {
                categoriesToFilter.remove(auxIndex);
            }
        }


        //Other
        if (otherBox.isSelected()) {
            System.out.println("Other checkbox is selected");
            categoriesToFilter.add("Other");
        }
        if (!otherBox.isSelected()) {
            System.out.println("Other checkbox is now deselected");
            int auxIndex = categoriesToFilter.indexOf("Other");
            if (auxIndex != -1) {
                categoriesToFilter.remove(auxIndex);
            }
        }


        System.out.println("categories to filter list: " + categoriesToFilter);

        //Get name of the current calendar that the user is working on
        String calName = MyCalendar.getInstance().calendar_name;

        System.out.println("and calendarName is: " + calName);

        if (categoriesToFilter.isEmpty()) {
            System.out.println("categories are not selected. No events have to appear on calendar. " +
                    "Just call loadCalendarLabels method in the RepaintView method");
            selectAllCheckBox.setSelected(false);
            loadCalendarLabels();
        } else {
            System.out.println("Call the appropiate function to populate the month with the filtered events");
            //Get List of Filtered Events and store all events in an ArrayList variable
            ArrayList<String> filteredEventsList = databaseHandler.getFilteredEvents(categoriesToFilter, calName);

            System.out.println("List of Filtered events is: " + filteredEventsList);

            //Repaint or reload the events based on the selected terms
            showFilteredEventsInMonth(filteredEventsList);
        }
    }


    public void showFilteredEventsInMonth(ArrayList<String> filteredEventsList) {

        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println("I am in the show filtered events in month function");
        System.out.println("The list of filted events is: " + filteredEventsList);
        System.out.println("****------******-------******--------");


        String calendarName = MyCalendar.getInstance().calendar_name;

        //String currentMonth = monthLbl.getText();
        //System.out.println("currentMonth is: " + currentMonth);
        //int currentMonthIndex = MyCalendar.getInstance().getMonthIndex(currentMonth) + 1;
        //System.out.println("currentMonthIndex is: " + currentMonthIndex);

        //int currentYear = Integer.parseInt(yearLbl.getText());
        //System.out.println("CurrentYear is: " + currentYear);

        System.out.println("Now, the events/labels will be shown/put on the calendar");
        System.out.println("****------******-------******--------");

        for (int i = 0; i < filteredEventsList.size(); i++) {
            String[] eventInfo = filteredEventsList.get(i).split("~");
            String eventDescript = eventInfo[1];
            int eventCategorieID = Integer.parseInt(eventInfo[2]);
            String eventCalName = eventInfo[3];
            String eventDate = eventInfo[4];
            System.out.println(eventDescript);
            System.out.println(eventCategorieID);
            System.out.println(eventCalName);
            System.out.println(eventDate);

            String[] dateParts = eventDate.split("-");
            int eventYear = Integer.parseInt(dateParts[0]);
            int eventMonth = Integer.parseInt(dateParts[1]);
            int eventDay = Integer.parseInt(dateParts[2]);


            System.out.println("****------******-------******--------");
            System.out.println("Now I will check if currentYear equals eventYear. Result is:");
            if (true /*currentYear == eventYear*/) {
                System.out.println("Yes, both year match.");
                System.out.println("Now I will check if the month index equals the event's month. REsult is");
                // if (currentMonthIndex == eventMonth)
                {
                    System.out.println("Yes they are the same. Now I will call showDate function");
                    showDate(eventDay, eventDescript, eventCategorieID);

                }
            }
        }
    }


    @FXML
    private void deleteAllEvents(MouseEvent event) {

        //Show confirmation dialog to make sure the user want to delete the selected rule
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setHeaderText("All Events Deletion");
        alert.setContentText("Are you sure you want to delete all events in this calendar?");
        //Customize the buttons in the confirmation dialog
        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");
        //Set buttons onto the confirmation dialog
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        //Get the user's answer on whether deleting or not
        Optional<ButtonType> result = alert.showAndWait();

        //If the user wants to delete the calendar, call the function that deletes the calendar. Otherwise, close the window
        if (result.get() == buttonTypeYes) {
            deleteAllEventsInCalendar();
        }


    }

    // Function that deletes all the events in the current calendar
    public void deleteAllEventsInCalendar() {

        //Variable that holds the name of the current calendar
        String calName = MyCalendar.getInstance().calendar_name;

        //Query that will delete all events that belong to the selected calendar
        String deleteAllEventsQuery = "DELETE FROM EVENTS "
                + "WHERE EVENTS.CalendarName='" + calName + "'";

        //Execute query that deletes all events associated to the selected calendar
        boolean eventsWereDeleted = databaseHandler.executeAction(deleteAllEventsQuery);

        //Check if events were successfully deleted and indicate the user if so
        if (eventsWereDeleted) {
            //Update the calendar. Show that events were actually deleted
            repaintView();

            //Show message indicating that the selected calendar was deleted
            Alert alertMessage = new Alert(Alert.AlertType.INFORMATION);
            alertMessage.setHeaderText(null);
            alertMessage.setContentText("All events were successfully deleted");
            alertMessage.showAndWait();
        } else {
            //Show message indicating that the calendar could not be deleted
            Alert alertMessage = new Alert(Alert.AlertType.ERROR);
            alertMessage.setHeaderText(null);
            alertMessage.setContentText("Deleting Events Failed!");
            alertMessage.showAndWait();
        }
    }

}
