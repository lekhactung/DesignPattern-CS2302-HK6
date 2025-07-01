module schoolexc.quizapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires lombok;
    opens schoolexc.quizapp to javafx.fxml;
    exports schoolexc.quizapp;
    exports schoolexc.quizapp.pojo;

}
