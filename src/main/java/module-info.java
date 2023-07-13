module com.example.managestaff {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.managestaff to javafx.fxml;
    exports com.example.managestaff;
}