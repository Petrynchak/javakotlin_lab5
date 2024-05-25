module com.example.library {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.library to javafx.fxml;
    opens com.example.library.data to javafx.base;

    exports com.example.library;
}
