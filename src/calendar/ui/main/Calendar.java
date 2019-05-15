package calendar.ui.main;

import javafx.beans.property.SimpleStringProperty;

public class Calendar {
    private final SimpleStringProperty name;
    private final SimpleStringProperty startYear;

    private final SimpleStringProperty startDate;

    public Calendar(String name, String startYear, String startingDate) {
        this.name = new SimpleStringProperty(name);
        this.startYear = new SimpleStringProperty(startYear);
        this.startDate = new SimpleStringProperty(startingDate);

    }

    public String getName() {
        return name.get();
    }

    public String getStartYear() {
        return startYear.get();
    }

    public String getStartDate() {
        return startDate.get();
    }

}
