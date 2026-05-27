module com.mycompany.algoritmoordenamientokevin {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

   opens com.mycompany.algoritmoordenamientokevin to javafx.fxml;
    opens com.mycompany.algoritmoordenamientokevin.Controlador to javafx.fxml, javafx.base;
    opens com.mycompany.algoritmoordenamientokevin.ordenamiento to javafx.fxml;

    exports com.mycompany.algoritmoordenamientokevin;
}
