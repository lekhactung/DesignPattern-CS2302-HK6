/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizappv3.services.question;

import java.util.List;
import java.util.Locale;
import schoolexc.quizappv3.pojo.Category;

/**
 *
 * @author LE TUNG
 */
public class CategoryQuestionServiceDecorator extends QuestionServiceDecorator{
    private Category cate;

    public CategoryQuestionServiceDecorator( BaseQuestionService decorator,Category cate) {
        super(decorator);
        this.cate = cate;
    }

    
    
    @Override
    public String getSql(List<Object> params) {
        String sql = this.decorator.getSql(params) + "AND category_id = ?";
        params.add(this.cate.getId());
        return sql;
    }
    
    
}
