package schoolexc.quizappv2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import schoolexc.quizappv2.utils.MyStage;
import schoolexc.quizappv2.utils.theme.Theme;
import schoolexc.quizappv2.utils.theme.ThemeManager;

public class PrimaryController implements Initializable {

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
    }

    public void handleTheme(ActionEvent event) {
        this.cbTheme.getSelectionModel().getSelectedItem().updateTheme();
        ThemeManager.applyTheme(this.cbTheme.getScene());
    }

}
