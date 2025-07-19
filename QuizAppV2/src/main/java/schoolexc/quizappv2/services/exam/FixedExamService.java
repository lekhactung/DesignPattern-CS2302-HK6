/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizappv2.services.exam;

import java.io.ObjectInputFilter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import schoolexc.quizappv2.pojo.Level;
import schoolexc.quizappv2.pojo.Question;
import schoolexc.quizappv2.services.question.BaseQuestionService;
import schoolexc.quizappv2.services.question.LevelQuestionServiceDecorator;
import schoolexc.quizappv2.services.question.LimitQuestionServiceDecorator;
import schoolexc.quizappv2.utils.Configs;

/**
 *
 * @author LE TUNG
 */
public class FixedExamService extends BaseExamService {

    @Override
    public List<Question> getQuestion() throws SQLException {
        double[] rates = {0.4, 0.4, 0.2};

        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < rates.length; i++) {
            BaseQuestionService s = new LimitQuestionServiceDecorator(new LevelQuestionServiceDecorator(Configs.questionServices, i + 1)
                    , (int)(rates[i] * Configs.NUM_OF_QUESTION));
            questions.addAll(s.list());
        }
        
        return questions;
    }

}
