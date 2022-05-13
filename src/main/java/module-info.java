module com.example.employeesys {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.employeesys to javafx.fxml;
    exports com.example.employeesys;
}