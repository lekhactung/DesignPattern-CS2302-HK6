module schoolexc.quizappv2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires lombok;
    opens schoolexc.quizappv2 to javafx.fxml;
    exports schoolexc.quizappv2;
    exports schoolexc.quizappv2.pojo;
    exports schoolexc.quizappv2.services.user;
}
