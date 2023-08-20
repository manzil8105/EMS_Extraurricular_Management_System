module com.example.projectmain {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens com.example.projectmain to javafx.fxml;
    exports com.example.projectmain;
}