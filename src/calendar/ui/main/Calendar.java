package calendar.ui.main;

import javafx.beans.property.SimpleStringProperty;

public class Calendar {
    private final SimpleStringProperty name;
    private final SimpleStringProperty startDate;
    private final SimpleStringProperty updateDate;


    public Calendar(String name, String startingDate, String updatingDate) {
        this.name = new SimpleStringProperty(name);
        this.startDate = new SimpleStringProperty(startingDate);
        this.updateDate = new SimpleStringProperty(updatingDate);

    }

    public String getName() {
        return name.get();
    }

    public String getStartDate() {
        return startDate.get();
    }

    public String getUpdateDate() {
        return updateDate.get();
    }
}
