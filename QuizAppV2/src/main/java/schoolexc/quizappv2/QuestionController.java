/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package schoolexc.quizappv2;

import java.net.URL;
import java.nio.Buffer;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import schoolexc.quizappv2.pojo.Category;
import schoolexc.quizappv2.pojo.Choice;
import schoolexc.quizappv2.pojo.Level;
import schoolexc.quizappv2.pojo.Question;

import schoolexc.quizappv2.services.CategoryServices;
import schoolexc.quizappv2.services.FlyweightFactory;
import schoolexc.quizappv2.services.LevelServices;
import schoolexc.quizappv2.services.question.BaseQuestionService;
import schoolexc.quizappv2.services.question.CategoryQuestionServiceDecorator;
import schoolexc.quizappv2.services.question.KeywordQuestionServiceDecorator;
import schoolexc.quizappv2.services.question.LevelQuestionServiceDecorator;
import schoolexc.quizappv2.services.question.QuestionServiceDecorator;
import schoolexc.quizappv2.services.question.QuestionServices;
import schoolexc.quizappv2.utils.Configs;
import schoolexc.quizappv2.utils.MyAlert;

/**
 * FXML Controller class
 *
 * @author LE TUNG
 */
public class QuestionController implements Initializable {

    @FXML
    private ComboBox<Category> cbCates;
    @FXML
    private ComboBox<Category> cbSearchCates;
    @FXML
    private ComboBox<Level> cbLevels;
    @FXML
    private ComboBox<Level> cbSearchLevels;
    @FXML
    private TableView<Question> tbQuestion;
    @FXML
    private TextField txtSearch;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.cbCates.setItems(FXCollections.observableList(FlyweightFactory.getData(Configs.cateServices, "category")));
            this.cbLevels.setItems(FXCollections.observableList(FlyweightFactory.getData(Configs.levelServices, "level")));
            this.cbSearchCates.setItems(FXCollections.observableList(FlyweightFactory.getData(Configs.cateServices, "category")));
            this.cbSearchLevels.setItems(FXCollections.observableList(FlyweightFactory.getData(Configs.levelServices, "level")));

            this.loadCollumn();
            this.tbQuestion.setItems(FXCollections.observableList(Configs.questionServices.list()));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        this.txtSearch.textProperty().addListener((e) -> {
            try {
                BaseQuestionService s = new KeywordQuestionServiceDecorator(Configs.questionServices, this.txtSearch.getText());
                this.tbQuestion.setItems(FXCollections.observableList(s.list()));
            } catch (SQLException ex) {
                Logger.getLogger(QuestionController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        });

        this.cbSearchCates.getSelectionModel().selectedItemProperty().addListener((e) -> {
            try {
                BaseQuestionService s = new CategoryQuestionServiceDecorator(Configs.questionServices, this.cbSearchCates.getSelectionModel().getSelectedItem());
                this.tbQuestion.setItems(FXCollections.observableList(s.list()));
            } catch (SQLException ex) {
                Logger.getLogger(QuestionController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        });
        this.cbSearchLevels.getSelectionModel().selectedItemProperty().addListener((e) -> {
            try {
                BaseQuestionService s = new LevelQuestionServiceDecorator(Configs.questionServices, this.cbSearchLevels.getSelectionModel().getSelectedItem());
                this.tbQuestion.setItems(FXCollections.observableList(s.list()));
            } catch (SQLException ex) {
                Logger.getLogger(QuestionController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        });
    }

    @FXML
    private VBox vboxChoice;
    @FXML
    private ToggleGroup toggleChoice = new ToggleGroup();

    public void moreChoice(ActionEvent event) {
        HBox h = new HBox();
        h.getStyleClass().add("Main");

        RadioButton r = new RadioButton();
        r.setToggleGroup(toggleChoice);
        TextField tf = new TextField();

        h.getChildren().addAll(r, tf);

        this.vboxChoice.getChildren().add(h);
    }

    @FXML
    private TextArea txtContent;

    public void handleQuestion(ActionEvent event) {
        try {
            Question.Builder b = new Question.Builder(this.txtContent.getText(),
                    this.cbCates.getSelectionModel().getSelectedItem(),
                    this.cbLevels.getSelectionModel().getSelectedItem());
            for (var c : vboxChoice.getChildren()) {
                HBox h = (HBox) c;
                Choice choice = new Choice(((TextField) h.getChildren().get(1)).getText(), //vi can lay textfield -> ep kieu textfield -> get 1 la get phan tu sau radio button
                        ((RadioButton) h.getChildren().get(0)).isSelected()); // ep kieu radio button -> get 0 la get radiobutton -> check xem co tick vao hay khong tra ve boolean
                b.addChoice(choice);
            }

            Question q = b.build();
            Configs.updQuestionService.addQuestion(q);
            MyAlert.getInstance().showMsg("Them cau hoi thanh cong");

        } catch (SQLException ex) {
            MyAlert.getInstance().showMsg("Them cau hoi that bai");
        } catch (Exception ex) {
            Logger.getLogger(QuestionController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    private void loadCollumn() {
        TableColumn colId = new TableColumn("Id");
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colId.setPrefWidth(150);
        TableColumn colContent = new TableColumn("Content");
        colContent.setCellValueFactory(new PropertyValueFactory("content"));
        colContent.setPrefWidth(300);
        TableColumn colAction = new TableColumn();
        colAction.setCellFactory((e) -> {
            TableCell cell = new TableCell();
            Button btn = new Button("XOA");
            btn.setOnAction((event) -> {
                Optional<ButtonType> type = MyAlert.getInstance().showMsg("Cac lua chon cung se bi xoa theo cau hoi, ban chac chan khong?", Alert.AlertType.CONFIRMATION);
                if (type.isPresent() && type.get().equals(ButtonType.OK)) {
                    Question q = (Question) cell.getTableRow().getItem();
                    try {
                        Configs.updQuestionService.deleteQuestion(q.getId());
                    } catch (SQLException ex) {
                        Logger.getLogger(QuestionController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
            });

            cell.setGraphic(btn);
            return cell;
        });

        this.tbQuestion.getColumns().addAll(colId, colContent, colAction);
    }

}
