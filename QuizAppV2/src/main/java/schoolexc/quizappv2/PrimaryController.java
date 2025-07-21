package schoolexc.quizappv2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import schoolexc.quizappv2.utils.Configs;
import schoolexc.quizappv2.utils.MyStage;
import schoolexc.quizappv2.utils.theme.Theme;
import schoolexc.quizappv2.utils.theme.ThemeManager;

public class PrimaryController implements Initializable {

    @FXML
    private Button btnQstion;
    @FXML
    private Button btnPractice;
    @FXML
    private Button btnExam;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnRegister;
    @FXML
    private ComboBox<Theme> cbTheme;

    public void handleQuestionManagement(ActionEvent event) throws IOException {
        MyStage.getInstance().showStage("question.fxml");
    }

    public void handlePracticeManagement(ActionEvent event) throws IOException {
        MyStage.getInstance().showStage("practice.fxml");

    }

    public void handleExam(ActionEvent event) throws IOException {
        MyStage.getInstance().showStage("exam.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbTheme.setItems(FXCollections.observableArrayList(Theme.values()));
        if (Configs.LOGIN_STATUS != true) {
            this.btnQstion.setVisible(false);
            this.btnPractice.setVisible(false);
            this.btnExam.setVisible(false);
        } else {
            this.btnQstion.setVisible(true);
            this.btnPractice.setVisible(true);
            this.btnExam.setVisible(true);
            this.btnLogin.setVisible(false);
            this.btnRegister.setVisible(false);
        }
    }

    public void handleTheme(ActionEvent event) {
        this.cbTheme.getSelectionModel().getSelectedItem().updateTheme();
        ThemeManager.applyTheme(this.cbTheme.getScene());
    }

    public void handleLogin(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) this.btnLogin.getScene().getWindow();
        primaryStage.close();
        MyStage.getInstance().showStage("login.fxml");  
    }

}
