/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizappv2.services.exam;

import java.io.ObjectInputFilter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import schoolexc.quizappv2.pojo.Question;
import schoolexc.quizappv2.services.question.BaseQuestionService;
import schoolexc.quizappv2.services.question.LimitQuestionServiceDecorator;
import schoolexc.quizappv2.utils.Configs;

/**
 *
 * @author LE TUNG
 */
public class SpecificExamService extends BaseExamService {

    private int num;

    public SpecificExamService(int num) {
        this.num = num;
    }

    @Override
    public List<Question> getQuestion() throws SQLException {
        BaseQuestionService s = new LimitQuestionServiceDecorator(Configs.questionServices, num);
        return s.list();
    }

}
