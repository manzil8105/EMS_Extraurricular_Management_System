module com.example.projectmain {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.projectmain to javafx.fxml;
    exports com.example.projectmain;
}