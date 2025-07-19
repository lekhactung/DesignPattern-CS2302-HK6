/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizappv2.services.question;

import java.util.List;

/**
 *
 * @author LE TUNG
 */
public class KeywordQuestionServiceDecorator extends QuestionServiceDecorator{
    private String kw;
    public KeywordQuestionServiceDecorator(BaseQuestionService decorator,String kw) {
        super(decorator);
        this.kw = kw;
    }
    
    @Override
    public String getSql(List<Object> params) {
        String sql = this.decorator.getSql(params) + "AND content like concat ('%',?,'%') ORDER BY id DESC";
        params.add(kw);
        return sql;
    }
    
}
