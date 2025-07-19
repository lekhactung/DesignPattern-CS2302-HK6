/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package schoolexc.quizappv2;

import java.net.URL;
import java.nio.channels.AcceptPendingException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import schoolexc.quizappv2.pojo.Category;
import schoolexc.quizappv2.pojo.Level;
import schoolexc.quizappv2.pojo.Question;
import schoolexc.quizappv2.services.FlyweightFactory;
import schoolexc.quizappv2.services.question.BaseQuestionService;
import schoolexc.quizappv2.services.question.CategoryQuestionServiceDecorator;
import schoolexc.quizappv2.services.question.LevelQuestionServiceDecorator;
import schoolexc.quizappv2.services.question.LimitQuestionServiceDecorator;
import schoolexc.quizappv2.utils.Configs;
import schoolexc.quizappv2.utils.MyAlert;

/**
 * FXML Controller class
 *
 * @author LE TUNG
 */
public class PracticeController implements Initializable {

    @FXML
    private Text txtContent;
    @FXML
    private VBox vboxChoice;
    @FXML
    private TextField txtNum;
    @FXML
    private ComboBox<Category> cbCates;
    @FXML
    private ComboBox<Level> cbLevels;

    private List<Question> questions;
    private int currentIndex = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.cbCates.setItems(FXCollections.observableList(FlyweightFactory.getData(Configs.cateServices, "category")));
            this.cbLevels.setItems(FXCollections.observableList(FlyweightFactory.getData(Configs.levelServices, "level")));
        } catch (SQLException ex) {
            Logger.getLogger(PracticeController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }

    public void check(ActionEvent event) {
        Question q = this.questions.get(this.currentIndex);
        for (int i = 0; i < q.getChoices().size(); i++) {
            if (q.getChoices().get(i).isCorrect()) {
                HBox h = (HBox) vboxChoice.getChildren().get(i);
                if (((RadioButton) h.getChildren().get(0)).isSelected()) {
                    MyAlert.getInstance().showMsg("CORRECT");
                } else {
                    MyAlert.getInstance().showMsg("INCORRECT");
                }
                break;
            }
        }
    }

    public void next(ActionEvent event) {
        if (currentIndex < this.questions.size()) {
            this.currentIndex++;
            loadQuestion();
        }
    }

    public void start(ActionEvent event) throws SQLException {
        if (this.txtNum.getText() == null || this.txtNum.getText().isEmpty()) {
            MyAlert.getInstance().showMsg("Vui long nhap so cau hoi", Alert.AlertType.WARNING);
            return;
        }
        try {
            int num = Integer.parseInt(this.txtNum.getText());
            
            BaseQuestionService s = Configs.questionServices;
            Category c = this.cbCates.getSelectionModel().getSelectedItem();
            if (c != null) {
                s = new CategoryQuestionServiceDecorator(s, c);
            }

            Level lvl = this.cbLevels.getSelectionModel().getSelectedItem();
            if (lvl != null) {
                s = new LevelQuestionServiceDecorator(s, lvl);
            }

            s = new LimitQuestionServiceDecorator(s, num);
            questions = s.list();

            if (!questions.isEmpty()) {
                this.currentIndex = 0;
                loadQuestion();
            }
        } catch (NumberFormatException ex) {
            MyAlert.getInstance().showMsg("So cau khong hop le!", Alert.AlertType.WARNING);
        }
    }

    private void loadQuestion() {
        Question q = this.questions.get(currentIndex);
        this.txtContent.setText(q.getContent());
        vboxChoice.getChildren().clear();
        ToggleGroup toggle = new ToggleGroup();
        for (var c : q.getChoices()) {
            HBox h = new HBox();
            RadioButton r = new RadioButton();
            r.setToggleGroup(toggle);

            Text txt = new Text(c.getContent());

            h.getChildren().addAll(r, txt);
            vboxChoice.getChildren().add(h);

        }
    }

}
