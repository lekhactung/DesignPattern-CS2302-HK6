package schoolexc.quizapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import schoolexc.quizapp.ultils.MyAlert;
import schoolexc.quizapp.ultils.MyStage;
import schoolexc.quizapp.ultils.Theme.DarkThemeFactory;
import schoolexc.quizapp.ultils.Theme.DefaulThemeFactory;
import schoolexc.quizapp.ultils.Theme.LightThemeFactory;
import schoolexc.quizapp.ultils.Theme.Theme;
import schoolexc.quizapp.ultils.Theme.ThemeManager;

public class PrimaryController implements Initializable {

    @FXML
    private ComboBox<Theme> cbTheme;

    public void handleTheme(ActionEvent event) {
        this.cbTheme.getSelectionModel().getSelectedItem().updTheme();
//        switch (this.cbTheme.getSelectionModel().getSelectedItem()) {
//            case DARK:
//                ThemeManager.setThemeFac(new DarkThemeFactory());
//                break;
//            case LIGHT:
//                ThemeManager.setThemeFac(new LightThemeFactory());
//                break;
//            case DEFAULT:
//                ThemeManager.setThemeFac(new DefaulThemeFactory());
//                break;
//        }
        ThemeManager.applyTheme(this.cbTheme.getScene());
    }

    public void handleQuestionManagement(ActionEvent event) throws IOException {
//        MyAlert.getInstance().showMsg("Comming soon...");
//        Scene scene = new Scene(new FXMLLoader(App.class.getResource("Question.fxml")).load()); 
//        Stage stage = new  Stage();
//        stage.setScene(scene);
//        stage.setTitle("QuizApp");
//        stage.show();

        MyStage.getInstance().showStage("Question.fxml");
    }

    public void handlePractice(ActionEvent event) throws IOException {
//        MyAlert.getInstance().showMsg("Comming soon...");
        MyStage.getInstance().showStage("practice.fxml");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbTheme.setItems(FXCollections.observableArrayList(Theme.values()));
    }
}
