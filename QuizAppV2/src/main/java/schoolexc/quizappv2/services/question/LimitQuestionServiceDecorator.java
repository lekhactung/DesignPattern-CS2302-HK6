/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizappv2.services.question;

import java.sql.SQLException;
import java.util.List;
import schoolexc.quizappv2.pojo.Question;

/**
 *
 * @author LE TUNG
 */
public class LimitQuestionServiceDecorator extends QuestionServiceDecorator{
    
    private int num;
    
    public LimitQuestionServiceDecorator(BaseQuestionService decorator, int num) {
        super(decorator);
        this.num = num;
    }

    @Override
    public List<Question> list() throws SQLException {
        List<Question> questions = super.list();
        for(var q : questions){
            q.setChoices(this.getChoiceByQuestion(q.getId()));
        }
        return questions;
    }

    
    
    @Override
    public String getSql(List<Object> params) {
        String sql = this.decorator.getSql(params) + " ORDER BY rand() LIMIT ?";
        params.add(this.num);
        return sql;
    }
    
}
