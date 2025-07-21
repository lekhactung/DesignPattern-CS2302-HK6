module schoolexc.quizappv3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    opens schoolexc.quizappv3 to javafx.fxml;
    exports schoolexc.quizappv3;
    exports schoolexc.quizappv3.pojo;
}
