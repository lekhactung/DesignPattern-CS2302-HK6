/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package schoolexc.quizappv2;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import schoolexc.quizappv2.pojo.Category;
import schoolexc.quizappv2.pojo.Choice;
import schoolexc.quizappv2.pojo.Level;
import schoolexc.quizappv2.pojo.Question;

import schoolexc.quizappv2.services.CategoryServices;
import schoolexc.quizappv2.services.LevelServices;
import schoolexc.quizappv2.services.QuestionServices;
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
    private ComboBox<Level> cbLevels;

    private final CategoryServices cateServices = new CategoryServices();
    private final LevelServices levelServices = new LevelServices();
    private final QuestionServices questionServices = new QuestionServices();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.cbCates.setItems(FXCollections.observableList(cateServices.getCates()));
            this.cbLevels.setItems(FXCollections.observableList(levelServices.getLevels()));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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
            questionServices.addQuestion(q);
            MyAlert.getInstance().showMsg("Them cau hoi thanh cong");

        }
        catch(SQLException ex){
            MyAlert.getInstance().showMsg("Them cau hoi that bai");
        }
        catch (Exception ex) {
            Logger.getLogger(QuestionController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }
}
