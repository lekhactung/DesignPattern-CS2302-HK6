/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package schoolexc.quizappv2;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import schoolexc.quizappv2.services.user.AuthService;
import schoolexc.quizappv2.utils.Configs;
import schoolexc.quizappv2.utils.JdbcConnector;
import schoolexc.quizappv2.utils.MyAlert;
import schoolexc.quizappv2.utils.MyStage;

/**
 * FXML Controller class
 *
 * @author LE TUNG
 */
public class LoginController implements Initializable {

    @FXML
    private TextField tfUsername;
    @FXML
    private TextField tfPassword;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(Configs.LOGIN_STATUS);

    }

    public void handleLogin(ActionEvent event) throws SQLException, IOException {
        if (this.tfUsername.getText().isEmpty() || this.tfPassword.getText().isEmpty()) {
            MyAlert.getInstance().showMsg("Hay nhap thong tin dang nhap!");
        }

        AuthService a = new AuthService(JdbcConnector.getInstance().connect());
        if (a.compareUserInfo(this.tfUsername.getText().trim(), this.tfPassword.getText().trim()) != false) {
            Configs.LOGIN_STATUS = true;
             
            MyStage.getInstance().showStage("primary.fxml");
        } else{
            MyAlert.getInstance().showMsg("Thong tin dang nhap khong dung!");
        }
            System.out.println(Configs.LOGIN_STATUS);
    }

    public void handleRegisterButton(ActionEvent event) {

    }

}
