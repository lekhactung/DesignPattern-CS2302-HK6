/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package schoolexc.quizappv2;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import schoolexc.quizappv2.pojo.Choice;
import schoolexc.quizappv2.pojo.Question;
import schoolexc.quizappv2.services.exam.BaseExamService;
import schoolexc.quizappv2.services.exam.ExamType;
import schoolexc.quizappv2.services.exam.FixedExamService;
import schoolexc.quizappv2.services.exam.SpecificExamService;
import schoolexc.quizappv2.utils.Configs;
import schoolexc.quizappv2.utils.MyAlert;

/**
 * FXML Controller class
 *
 * @author LE TUNG
 */
public class ExamController implements Initializable {

    @FXML
    private ComboBox<ExamType> cbType;
    @FXML
    private TextField txtNum;
    @FXML
    private ListView<Question> lvQuestion;
    private BaseExamService exService;
    private List<Question> questions;

    private Map<Integer, Choice> results;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.txtNum.setVisible(false);
        this.cbType.getSelectionModel().selectedItemProperty().addListener((e) -> {
            if (cbType.getSelectionModel().getSelectedItem() == ExamType.SPECIFIC) {
                this.txtNum.setVisible(true);
            } else {
                this.txtNum.setVisible(false);
            }
        });

        this.cbType.setItems(FXCollections.observableArrayList(ExamType.values()));
    }

    public void handleStart(ActionEvent event) throws SQLException {
        if (cbType.getSelectionModel().getSelectedItem() == ExamType.SPECIFIC) {
            exService = new SpecificExamService(Integer.parseInt(this.txtNum.getText()));
        } else {
            exService = new FixedExamService();
        }

        this.results = new HashMap<>();
        this.questions = exService.getQuestion();
        this.lvQuestion.setItems(FXCollections.observableList(questions));

        this.lvQuestion.setCellFactory(params -> new ListCell<Question>() {
            @Override
            protected void updateItem(Question question, boolean empty) {
                super.updateItem(question, empty);

                if (question == null || empty == true) {
                    this.setGraphic(null);
                } else {
                    VBox v = new VBox(5);
                    v.setStyle("-fx-padding:10;-fx-border-color:gray;-fx-color-width:3;");
                    Text t = new Text(question.getContent());
                    v.getChildren().add(t);
                    ToggleGroup tg = new ToggleGroup();
                    for (var c : question.getChoices()) {
                        RadioButton r = new RadioButton(c.getContent());
                        r.setToggleGroup(tg);
                        //BO SUNG
                        if (results.get(question.getId()) == c) {
                            r.setSelected(true);
                        }
                        //                        
                        r.setOnAction((e) -> {
                            if (r.isSelected()) {
                                results.put(question.getId(), c);
                            }
                        });

                        v.getChildren().add(r);
                    }
                    this.setGraphic(v);
                }
            }
        });
    }

    public void handleFinish(ActionEvent event) {
        int count = 0;

        for (var c : this.results.values()) {
            if (c.isCorrect()) {
                count++;
            }
        }
        MyAlert.getInstance()
                .showMsg(String.format("Ban lam dung %d/%d", count, questions.size()),
                         Alert.AlertType.INFORMATION);
    }

    public void handleSave(ActionEvent event) {
        if (questions == null || questions.isEmpty()) {
            MyAlert.getInstance().showMsg("De thi chua duoc tao", Alert.AlertType.WARNING);
        } else {
            Optional<ButtonType> type = MyAlert.getInstance().showMsg("Ban chac chan luu de thi?", Alert.AlertType.CONFIRMATION);
            if (type.isPresent() || type.get().equals(ButtonType.OK)) {
                try {
                    exService.addExam(questions);
                    MyAlert.getInstance().showMsg("Luu thanh cong");
                } catch (SQLException ex) {
                    MyAlert.getInstance().showMsg("He thong da xay ra loi, ly do : " + ex.getMessage(),Alert.AlertType.ERROR);
                }
            }
        }
    }
}
