package calendar.ui.main;

import javafx.beans.property.SimpleStringProperty;

public class Calendar {
    private final SimpleStringProperty name;
    private final SimpleStringProperty startDate;


    public Calendar(String name, String startingDate) {
        this.name = new SimpleStringProperty(name);
        this.startDate = new SimpleStringProperty(startingDate);
    }

    public String getName() {
        return name.get();
    }

    public String getStartDate() {
        return startDate.get();
    }

}
