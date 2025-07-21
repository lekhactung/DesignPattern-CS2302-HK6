/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package schoolexc.quizappv3;

import java.net.URL;
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
import javafx.scene.control.Cell;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import schoolexc.quizappv3.pojo.Category;
import schoolexc.quizappv3.pojo.Choice;
import schoolexc.quizappv3.pojo.Level;
import schoolexc.quizappv3.pojo.Question;
import schoolexc.quizappv3.services.question.BaseQuestionService;
import schoolexc.quizappv3.services.question.CategoryQuestionServiceDecorator;
import schoolexc.quizappv3.services.question.KeywordQuestionServiceDecorator;
import schoolexc.quizappv3.services.question.LevelQuestionServiceDecorator;
import schoolexc.quizappv3.services.question.QuestionService;
import schoolexc.quizappv3.utils.Configs;
import schoolexc.quizappv3.utils.MyAlert;

/**
 * FXML Controller class
 *
 * @author LE TUNG
 */
public class QuestionController implements Initializable {

    @FXML
    private ComboBox<Category> cbCate;
    @FXML
    private ComboBox<Level> cbLevel;
    @FXML
    private TableView<Question> tbQuestion;
    @FXML
    private TextField txtSearch;
    @FXML
    private ComboBox<Category> cbCateSearch;
    @FXML
    private ComboBox<Level> cbLevelSearch;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.cbCate.setItems(FXCollections.observableList(Configs.cateService.list()));
            this.cbLevel.setItems(FXCollections.observableList(Configs.lvlService.list()));
            this.cbCateSearch.setItems(FXCollections.observableList(Configs.cateService.list()));
            this.cbLevelSearch.setItems(FXCollections.observableList(Configs.lvlService.list()));

            loadColumn();
            this.tbQuestion.setItems(FXCollections.observableList(Configs.questionService.list()));

        } catch (SQLException ex) {
            Logger.getLogger(QuestionController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        this.txtSearch.textProperty().addListener((e) -> {
            try {
                BaseQuestionService s = new KeywordQuestionServiceDecorator(Configs.questionService, this.txtSearch.getText());
                this.tbQuestion.setItems(FXCollections.observableList(s.list()));

            } catch (SQLException ex) {
                Logger.getLogger(QuestionController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        });
        this.cbCateSearch.getSelectionModel().selectedItemProperty().addListener((e) -> {
            try {
                BaseQuestionService s = new CategoryQuestionServiceDecorator(Configs.questionService, this.cbCateSearch.getSelectionModel().getSelectedItem());
                this.tbQuestion.setItems(FXCollections.observableList(s.list()));

            } catch (SQLException ex) {
                Logger.getLogger(QuestionController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        });
        this.cbLevelSearch.getSelectionModel().selectedItemProperty().addListener((e) -> {
            try {
                BaseQuestionService s = new LevelQuestionServiceDecorator(Configs.questionService, this.cbLevelSearch.getSelectionModel().getSelectedItem());
                this.tbQuestion.setItems(FXCollections.observableList(s.list()));

            } catch (SQLException ex) {
                Logger.getLogger(QuestionController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        });
    }
    @FXML
    private ToggleGroup toggle = new ToggleGroup();

    public void handleMoreChoice(ActionEvent event) {
        HBox h = new HBox();
        h.getStyleClass().add("Main");

        RadioButton r = new RadioButton();
        r.setToggleGroup(toggle);

        TextField tf = new TextField();

        h.getChildren().addAll(r, tf);
        this.vboxChoices.getChildren().add(h);
    }

    @FXML
    private TextField txtContent;
    @FXML
    private VBox vboxChoices;

    public void handleAddQuestion(ActionEvent event) {
        System.err.println("clicked");
        try {
            Question.Builder b = new Question.Builder(txtContent.getText(),
                    this.cbCate.getSelectionModel().getSelectedItem(),
                    this.cbLevel.getSelectionModel().getSelectedItem());

            for (var c : vboxChoices.getChildren()) {
                HBox h = (HBox) c;
                Choice choice = new Choice(((TextField) h.getChildren().get(1)).getText(),
                        ((RadioButton) h.getChildren().get(0)).isSelected());
                b.addChoice(choice);
            }
            Question q = b.build();
            Configs.updQuestionService.addQuestion(q);
            MyAlert.getInstance().showMsg("Them cau hoi thanh cong!", Alert.AlertType.INFORMATION);
        } catch (SQLException ex) {
            MyAlert.getInstance().showMsg("Them cau hoi that bai!");
        } catch (Exception ex) {
            MyAlert.getInstance().showMsg("Du lieu khong hop le!");

        }
    }

    public void loadColumn() {
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
                Optional<ButtonType> type
                        = MyAlert.getInstance().showMsg("Cac lua chon cung se bi xoa, ban chac chan xoa?", Alert.AlertType.CONFIRMATION);
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
