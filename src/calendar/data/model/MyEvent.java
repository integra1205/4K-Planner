package calendar.data.model;

import javafx.beans.property.SimpleStringProperty;

public class MyEvent {
    private final SimpleStringProperty subject;
    private final SimpleStringProperty categorie;
    private final SimpleStringProperty calendar;
    private final SimpleStringProperty startdate;
    private final SimpleStringProperty starttime;
    private final SimpleStringProperty enddate;
    private final SimpleStringProperty endtime;

    public MyEvent(String subject, String categorie, String calendar, String startdate,
                   String starttime, String enddate, String endtime) {
        this.subject = new SimpleStringProperty(subject);
        this.categorie = new SimpleStringProperty(categorie);
        this.calendar = new SimpleStringProperty(calendar);
        this.startdate = new SimpleStringProperty(startdate);
        this.starttime = new SimpleStringProperty(starttime);
        this.enddate = new SimpleStringProperty(enddate);
        this.endtime = new SimpleStringProperty(endtime);
    }
    
    // Note: We need these accessors.

    public String getSubject() {
        return subject.get();
    }

    public String getCategorie() {
        return categorie.get();
    }

    public String getCalendar() {
        return calendar.get();
    }

    public String getStartdate() {
        return startdate.get();
    }

    public String getStarttime() {
        return starttime.get();
    }

    public String getEnddate() {
        return enddate.get();
    }

    public String getEndtime() {
        return endtime.get();
    }

}
