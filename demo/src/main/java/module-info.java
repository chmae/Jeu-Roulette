module ihm.umontpellier.iut.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens ihm.umontpellier.iut.demo to javafx.fxml;
    exports ihm.umontpellier.iut.demo;
}