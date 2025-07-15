/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizappv2.utils;

import javafx.scene.control.Alert;

/**
 *
 * @author LE TUNG
 */
public class MyAlert {
    private static MyAlert instance;
    private final Alert alert;
    
    private MyAlert(){
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("QuizzApp");
        
    }
    
    public static MyAlert getInstance(){
        if(instance==null){
            instance = new MyAlert();
        }
        return instance;
    }
    
    public void showMsg(String msg){
        this.alert.setContentText(msg);
        this.alert.showAndWait();
    }
}
