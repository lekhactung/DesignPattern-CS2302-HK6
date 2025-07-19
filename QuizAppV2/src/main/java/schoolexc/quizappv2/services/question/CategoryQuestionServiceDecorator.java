/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizappv2.services.question;

import java.util.List;
import schoolexc.quizappv2.pojo.Category;

/**
 *
 * @author LE TUNG
 */
public class CategoryQuestionServiceDecorator extends QuestionServiceDecorator {

    private Category cate;

    public CategoryQuestionServiceDecorator(BaseQuestionService decorator, Category c) {
        super(decorator);
        this.cate = c;
    }

    public CategoryQuestionServiceDecorator(BaseQuestionService decorator, int cateId) {
        super(decorator);
        this.cate = new Category(cateId);
    }

    @Override
    public String getSql(List<Object> params) {
        String sql = this.decorator.getSql(params) + "AND category_id = ? ";
        params.add(this.cate.getId());
        return sql;
    }

}
