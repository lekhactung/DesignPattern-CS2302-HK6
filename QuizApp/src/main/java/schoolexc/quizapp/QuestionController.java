/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package schoolexc.quizapp;

import java.net.URL;
import java.nio.channels.ConnectionPendingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.ListCell;
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
import schoolexc.quizapp.pojo.Category;
import schoolexc.quizapp.pojo.Choice;
import schoolexc.quizapp.pojo.Level;
import schoolexc.quizapp.pojo.Question;
import schoolexc.quizapp.serivces.CategoryServices;
import schoolexc.quizapp.serivces.LevelServices;
import schoolexc.quizapp.serivces.question.BaseQuestionService;
import schoolexc.quizapp.serivces.question.KeywordQuestionServiceDecorator;
import schoolexc.quizapp.serivces.question.QuestionService;
import schoolexc.quizapp.serivces.question.CategoryQuestionServiceDecorator;
import schoolexc.quizapp.serivces.question.LevelQuestionServiceDecorator;
import schoolexc.quizapp.ultils.MyAlert;
import schoolexc.quizapp.ultils.Configs;

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
    private ComboBox<Level> cbLevel;
    @FXML
    private ComboBox<Level> cbSearchLevel;
    @FXML
    private VBox vboxChoices;
    @FXML
    private TextArea txtContent;
    @FXML
    private ToggleGroup toggleChoice;
    @FXML
    private TableView<Question> tbQuestion;
    @FXML
    private TextField txtSearch;

    private static final CategoryServices cateService = new CategoryServices();
    private static final LevelServices lvlService = new LevelServices();
    private static final QuestionService questionService = new QuestionService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.cbCates.setItems(FXCollections.observableList(Configs.cateService.getCates()));
            this.cbLevel.setItems(FXCollections.observableList(Configs.levelService.getLevel()));
            this.cbSearchCates.setItems(FXCollections.observableList(Configs.cateService.getCates()));
            this.cbSearchLevel.setItems(FXCollections.observableList(Configs.levelService.getLevel()));

            this.loadColumn();
            this.tbQuestion.setItems(FXCollections.observableList(Configs.questionService.list()));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        this.txtSearch.textProperty().addListener((e) -> {
            try {
                BaseQuestionService s = new KeywordQuestionServiceDecorator(Configs.questionService, this.txtSearch.getText());
                this.tbQuestion.setItems(FXCollections.observableList(s.list()));

            } catch (SQLException ex) {
                Logger.getLogger(QuestionController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        });

        this.cbSearchCates.getSelectionModel().selectedIndexProperty().addListener(e -> {
            try {
                BaseQuestionService s = new CategoryQuestionServiceDecorator(Configs.questionService, this.cbSearchCates.getSelectionModel().getSelectedItem());
                this.tbQuestion.setItems(FXCollections.observableList(s.list()));

            } catch (SQLException ex) {
                Logger.getLogger(QuestionController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        });
        
        this.cbSearchLevel.getSelectionModel().selectedIndexProperty().addListener(e -> {
            try {
                BaseQuestionService s = new LevelQuestionServiceDecorator(Configs.questionService, this.cbSearchLevel.getSelectionModel().getSelectedItem());
                this.tbQuestion.setItems(FXCollections.observableList(s.list()));

            } catch (SQLException ex) {
                Logger.getLogger(QuestionController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        });

    }

    public void handleMoreChoice(ActionEvent event) {
        HBox h = new HBox();
        h.getStyleClass().add("Main");

        RadioButton r = new RadioButton();
        r.setToggleGroup(toggleChoice);
        TextField tf = new TextField();

        h.getChildren().addAll(r, tf);

        this.vboxChoices.getChildren().add(h);
    }

    public void handleQuestion(ActionEvent event) {
        try {
            Question.Builder b = new Question.Builder(this.txtContent.getText(),
                    this.cbCates.getSelectionModel().getSelectedItem(),
                    this.cbLevel.getSelectionModel().getSelectedItem());

            for (var c : vboxChoices.getChildren()) {
                HBox h = (HBox) c;
                Choice choice = new Choice(((TextField) h.getChildren().get(1)).getText(),
                        ((RadioButton) h.getChildren().get(0)).isSelected());

                b.addChoice(choice);
            }
            Question q = b.build();

            Configs.uQuestionService.addQuestion(q);
            MyAlert.getInstance().showMsg("Them cau hoi thanh cong");

        } catch (SQLException ex) {
            MyAlert.getInstance().showMsg("Them cau hoi that bai");
        } catch (Exception ex) {
            MyAlert.getInstance().showMsg("Du lieu khong hop le");
        }
    }

    private void loadColumn() {
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
            btn.setOnAction(event -> {
                Optional<ButtonType> type = MyAlert.getInstance().showMsg("Chac chan xoa?", Alert.AlertType.CONFIRMATION);

                if (type.isPresent() && type.get().equals(ButtonType.OK)) {
                    Question q = (Question) cell.getTableRow().getItem();
                    try {
                        Configs.uQuestionService.deleteQuestion(q.getId());
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
