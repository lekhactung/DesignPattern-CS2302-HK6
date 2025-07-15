/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizapp.serivces.question;

import java.util.List;

/**
 *
 * @author LE TUNG
 */
public class LimitQuestionServiceDecorator extends QuestionServiceDecorator{
    private int num; 

    public LimitQuestionServiceDecorator(BaseQuestionService decorator,int num) {
        super(decorator);
        this.num = num;
    }

    
    
    
    @Override
    public String getSQL(List<Object> params) {
        String sql = this.decorator.getSQL(params) + "ORDER BY rand() LIMIT ?";
        params.add(num);
        return sql;
    }
    
}
