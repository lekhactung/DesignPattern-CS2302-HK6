/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizapp.ultils;

import javafx.scene.control.Alert;

/**
 *
 * @author LE TUNG
 */
public class MyAlert {
    private static MyAlert instance;
    private final Alert alert;
    
    private MyAlert(){
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("QuizApp");
    }
    
    public void showMsg(String msg){
        this.alert.setContentText(msg);
        this.alert.showAndWait();
    }
    
    public static MyAlert getInstance(){
        if(instance==null){
            instance = new MyAlert();
        }
        
        return instance;
    }
}
