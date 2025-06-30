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
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import schoolexc.quizapp.pojo.Category;
import schoolexc.quizapp.pojo.Choice;
import schoolexc.quizapp.pojo.Level;
import schoolexc.quizapp.pojo.Question;
import schoolexc.quizapp.serivces.CategoryServices;
import schoolexc.quizapp.serivces.LevelServices;
import schoolexc.quizapp.serivces.QuestionService;
import schoolexc.quizapp.ultils.MyAlert;

/**
 * FXML Controller class
 *
 * @author LE TUNG
 */
public class QuestionController implements Initializable {

    @FXML
    private ComboBox<Category> cbCates;
    @FXML
    private ComboBox<Level> cbLevel;
    @FXML
    private VBox vboxChoices;
    @FXML
    private TextArea txtContent;
    @FXML
    private ToggleGroup toggleChoice;

    private static final CategoryServices cateService = new CategoryServices();
    private static final LevelServices lvlService = new LevelServices();
    private static final QuestionService questionService = new QuestionService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.cbCates.setItems(FXCollections.observableList(cateService.getCates()));
            this.cbLevel.setItems(FXCollections.observableList(lvlService.getLevel()));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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

            questionService.addQuestion(q);
            MyAlert.getInstance().showMsg("Them cau hoi thanh cong");

        } catch (SQLException ex) {
            MyAlert.getInstance().showMsg("Them cau hoi that bai");
        } catch (Exception ex) {
            MyAlert.getInstance().showMsg("Du lieu khong hop le");
        }
    }

}
