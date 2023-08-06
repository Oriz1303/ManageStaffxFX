module com.example.managestaff {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.sql;
    opens com.example.managestaff to javafx.fxml;
    exports com.example.managestaff;
    exports com.example.managestaff.controller;
    opens com.example.managestaff.controller to javafx.fxml;
    opens com.example.managestaff.model.entity to javafx.base;

}