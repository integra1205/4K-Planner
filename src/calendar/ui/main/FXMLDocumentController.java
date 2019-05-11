package calendar.ui.main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.IsoFields;
import java.util.Calendar;

import calendar.data.model.Model;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

public class FXMLDocumentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane rootPane;


    //--------- Database Handler -----------------------------------------
   //  DBHandler databaseHandler;     Девчонки! не забыть про БД      DON'T FORGET ABOUT DB!!!
    //--------------------------------------------------------------------


    // ***** elements of RIGHT-PANE: *****

    @FXML
    private Label today_is;

    @FXML
    private Label go_to;

    @FXML
    private JFXDatePicker date_piker_go_to;


    // ***** elements of CENTER-PANE: *****

    // ***** for YEAR: *****

    @FXML
    private VBox centerAreaYear;

    @FXML
    private ScrollPane scrollPaneYear;

    @FXML
    private JFXButton button_prev_year;

    @FXML
    private JFXButton button_next_year;

    @FXML
    private HBox weekdayHeaderYear;

    @FXML
    private GridPane calendarGridYear;

    @FXML
    private Label selected_date_for_year;


    // ***** for MONTH: *****


    @FXML
    private VBox centerArea;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private JFXButton button_prev_month;

    @FXML
    private JFXButton button_next_month;

    @FXML
    private HBox weekdayHeader;

    @FXML
    private GridPane calendarGrid;

    @FXML
    private Label selected_date_for_month;


    // ***** for  WEEK: *****


    @FXML
    private VBox centerAreaWeek;

    @FXML
    private ScrollPane scrollPaneWeek;

    @FXML
    private JFXButton button_prev_week;

    @FXML
    private JFXButton button_next_week;

    @FXML
    private HBox weekdayHeaderWeek;

    @FXML
    private GridPane calendarGridWeek;

    @FXML
    private Label selected_date_for_week;



    // ***** for DAY: *****

    @FXML
    private VBox centerAreaDay;

    @FXML
    private ScrollPane scrollPaneDay;

    @FXML
    private JFXButton button_prev_day;

    @FXML
    private JFXButton button_next_day;

    @FXML
    private HBox dayHeader;

    @FXML
    private GridPane calendarGridDay;

    @FXML
    private Label selected_date_for_day;



    // ***** elements of LEFT-PANE: *****

    @FXML
    private VBox colorRootPane;


    @FXML
    private TextField nameCategory1;

    @FXML
    private JFXColorPicker colorCategory1;

    @FXML
    private JFXCheckBox CheckBoxCategory1;



    @FXML
    private TextField nameCategory2;

    @FXML
    private JFXColorPicker colorCategory2;

    @FXML
    private JFXCheckBox CheckBoxCategory2;



    @FXML
    private TextField nameCategory3;

    @FXML
    private JFXColorPicker colorCategory3;

    @FXML
    private JFXCheckBox CheckBoxCategory3;



    @FXML
    private TextField nameCategory4;

    @FXML
    private JFXColorPicker colorCategory4;

    @FXML
    private JFXCheckBox CheckBoxCategory4;



    @FXML
    private TextField nameCategory5;

    @FXML
    private JFXColorPicker colorCategory5;

    @FXML
    private JFXCheckBox CheckBoxCategory5;



    @FXML
    private JFXCheckBox selectAllCheckBox;



    @FXML
    void deleteAllEvents(MouseEvent event) {

    }

    @FXML
    void handleCheckBoxAction(ActionEvent event) {

    }

    @FXML
    void manageCalendars(MouseEvent event) {

    }

    @FXML
    void newCalendar(MouseEvent event) {

    }

    @FXML
    void selectAllCheckBoxes(ActionEvent event) {

    }

    @FXML
    void updateColors(MouseEvent event) {

    }

    Calendar calendar=Calendar.getInstance();

    DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");

    @FXML
    void initialize() {

        assert rootPane != null : "fx:id=\"rootPane\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert today_is != null : "fx:id=\"today_is\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert go_to != null : "fx:id=\"go_to\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert date_piker_go_to != null : "fx:id=\"date_piker_go_to\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert centerAreaYear != null : "fx:id=\"centerAreaYear\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert scrollPaneYear != null : "fx:id=\"scrollPaneYear\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert button_prev_year != null : "fx:id=\"button_prev_year\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert selected_date_for_year != null : "fx:id=\"selected_date_for_year\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert button_next_year != null : "fx:id=\"button_next_year\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert weekdayHeaderYear != null : "fx:id=\"weekdayHeaderYear\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert calendarGridYear != null : "fx:id=\"calendarGridYear\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert centerArea != null : "fx:id=\"centerArea\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert scrollPane != null : "fx:id=\"scrollPane\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert button_prev_month != null : "fx:id=\"button_prev_month\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert selected_date_for_month != null : "fx:id=\"selected_date_for_month\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert button_next_month != null : "fx:id=\"button_next_month\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert weekdayHeader != null : "fx:id=\"weekdayHeader\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert calendarGrid != null : "fx:id=\"calendarGrid\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert centerAreaWeek != null : "fx:id=\"centerAreaWeek\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert scrollPaneWeek != null : "fx:id=\"scrollPaneWeek\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert button_prev_week != null : "fx:id=\"button_prev_week\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert selected_date_for_week != null : "fx:id=\"selected_date_for_week\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert button_next_week != null : "fx:id=\"button_next_week\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert weekdayHeaderWeek != null : "fx:id=\"weekdayHeaderWeek\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert calendarGridWeek != null : "fx:id=\"calendarGridWeek\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert centerAreaDay != null : "fx:id=\"centerAreaDay\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert scrollPaneDay != null : "fx:id=\"scrollPaneDay\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert button_prev_day != null : "fx:id=\"button_prev_day\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert selected_date_for_day != null : "fx:id=\"selected_date_for_day\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert button_next_day != null : "fx:id=\"button_next_day\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert dayHeader != null : "fx:id=\"dayHeader\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert calendarGridDay != null : "fx:id=\"calendarGridDay\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert colorRootPane != null : "fx:id=\"colorRootPane\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert nameCategory1 != null : "fx:id=\"nameCategory1\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert colorCategory1 != null : "fx:id=\"colorCategory1\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert CheckBoxCategory1 != null : "fx:id=\"CheckBoxCategory1\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert nameCategory2 != null : "fx:id=\"nameCategory2\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert colorCategory2 != null : "fx:id=\"colorCategory2\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert CheckBoxCategory2 != null : "fx:id=\"CheckBoxCategory2\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert nameCategory3 != null : "fx:id=\"nameCategory3\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert colorCategory3 != null : "fx:id=\"colorCategory3\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert CheckBoxCategory3 != null : "fx:id=\"CheckBoxCategory3\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert nameCategory4 != null : "fx:id=\"nameCategory4\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert colorCategory4 != null : "fx:id=\"colorCategory4\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert CheckBoxCategory4 != null : "fx:id=\"CheckBoxCategory4\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert nameCategory5 != null : "fx:id=\"nameCategory5\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert colorCategory5 != null : "fx:id=\"colorCategory5\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert CheckBoxCategory5 != null : "fx:id=\"CheckBoxCategory5\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert selectAllCheckBox != null : "fx:id=\"selectAllCheckBox\" was not injected: check your FXML file 'FXMLDocument.fxml'.";

        // LEFT-PANE TODAY
        today_is.setText(dateFormat.format(calendar.getTime()));

        //  TODAY - for default viewing
        Model.getInstance().viewing_year=calendar.get(Calendar.YEAR);
        Model.getInstance().viewing_month= Model.getInstance().getMonthName(calendar.get(Calendar.MONTH));
        Model.getInstance().viewing_week= calendar.get(Calendar.WEEK_OF_YEAR);
        Model.getInstance().viewing_day= calendar.get(Calendar.DAY_OF_MONTH);

        // Event`s from data picker
        initializeDatePicker();
    }

    @FXML
    private void initializeDatePicker() {

//      Our format for DataPicker
        date_piker_go_to.setConverter(new StringConverter<LocalDate>() {
            String pattern = "dd-MM-YYYY";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            {
                date_piker_go_to.setPromptText(pattern.toLowerCase());
            }

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });

        // Add event listener to dataPicker
        date_piker_go_to.valueProperty().addListener((new ChangeListener<LocalDate>() {

            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {

                if (newValue != null) {

                    Model.getInstance().viewing_year=date_piker_go_to.getValue().getYear();
                    Model.getInstance().viewing_month= date_piker_go_to.getValue().getMonth();
                    Model.getInstance().viewing_week= date_piker_go_to.getValue().get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
                    Model.getInstance().viewing_day=  date_piker_go_to.getValue().getDayOfMonth();

                    selected_date_for_year.setText(String.valueOf(Model.getInstance().viewing_year));
                    selected_date_for_month.setText(String.valueOf(Model.getInstance().viewing_month));
                    selected_date_for_week.setText("Number of Week is "+ Model.getInstance().viewing_week);

                    selected_date_for_day.setText(Model.getInstance().viewing_day +" "
                                                 + Model.getInstance().viewing_month +" "
                                                 + Model.getInstance().viewing_year);

                    // Update view
                    repaintView();
                }
            };
        }));
    }

    public void repaintView(){
        //... repaint...
    }

}
