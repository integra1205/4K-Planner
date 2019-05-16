package calendar.ui.main;

import javafx.beans.property.SimpleStringProperty;

public class Categorie {
    private final SimpleStringProperty categorieName;

    public Categorie(String categorieName) {
        this.categorieName = new SimpleStringProperty(categorieName);
    }

    public String getCategorieName() {
        return categorieName.get();
    }

}
