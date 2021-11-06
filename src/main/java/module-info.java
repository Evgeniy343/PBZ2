module com.example.test {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    exports com.example.test;
    exports com.example.test.controller;
    exports com.example.test.controller.watch;
    exports com.example.test.controller.create;
    exports com.example.test.controller.edit;
    exports com.example.test.controller.delete;
    exports com.example.test.controller.select;
    opens com.example.test to javafx.fxml;
    opens com.example.test.controller to javafx.fxml;
    opens com.example.test.controller.watch to javafx.fxml;
    opens com.example.test.controller.create to javafx.fxml;
    opens com.example.test.controller.edit to javafx.fxml;
    opens com.example.test.controller.delete to javafx.fxml;
    opens com.example.test.controller.select to javafx.fxml;
}