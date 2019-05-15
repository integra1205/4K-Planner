package calendar.ui.main;

import javafx.beans.property.SimpleStringProperty;

public class Event {
    private final SimpleStringProperty categorie;
    private final SimpleStringProperty subject;
    private final SimpleStringProperty date;
 
    public Event(String categorie, String subject, String date) {
        this.categorie = new SimpleStringProperty(categorie);
        this.subject = new SimpleStringProperty(subject);
        this.date = new SimpleStringProperty(date);
    }
    
    // Note: We need these accessors.
    public String getCategorie() {
        return categorie.get();
    }

    public String getSubject() {
        return subject.get();
    }

    public String getDate() {
        return date.get();
    }
}
