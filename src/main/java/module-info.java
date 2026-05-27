module com.mycompany.algoritmoordenamientokevin {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.algoritmoordenamientokevin to javafx.fxml;
    exports com.mycompany.algoritmoordenamientokevin;
}
