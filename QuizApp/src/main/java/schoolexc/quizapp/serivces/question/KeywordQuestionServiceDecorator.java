/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizapp.serivces.question;

import java.util.List;

/**
 *
 * @author admin
 */
public class KeywordQuestionServiceDecorator extends QuestionServiceDecorator{
    protected String kw;
    public KeywordQuestionServiceDecorator(BaseQuestionService decorator, String kw) {
        super(decorator);
        this.kw = kw;
    }
    
    
    @Override
    public String getSQL(List<Object> params) {
        String sql = this.decorator.getSQL(params) + "AND content like concat('%', ?, '%') ";
        params.add(kw);
        return sql;
    }
    
}
