/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizappv2.utils;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author LE TUNG
 */
public class MyAlert {

    private static MyAlert instance;
    private final Alert alert;

    private MyAlert() {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("QuizzApp");

    }

    public static MyAlert getInstance() {
        if (instance == null) {
            instance = new MyAlert();
        }
        return instance;
    }

    public void showMsg(String msg) {
        this.alert.setContentText(msg);
        this.alert.showAndWait();
    }

    public Optional<ButtonType> showMsg(String msg, Alert.AlertType type) {
        this.alert.setContentText(msg);
        this.alert.setAlertType(type);
        return this.alert.showAndWait();
    }
}
