/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizapp.serivces.question;

import java.util.List;
import schoolexc.quizapp.pojo.Category;
import schoolexc.quizapp.serivces.question.BaseQuestionService;
import schoolexc.quizapp.serivces.question.QuestionServiceDecorator;

/**
 *
 * @author admin
 */
public class CategoryQuestionServiceDecorator extends QuestionServiceDecorator{
    private Category category ;
    
    public CategoryQuestionServiceDecorator(BaseQuestionService decorator, Category c ){
        super(decorator);
        this.category = c;
    }
    
    public CategoryQuestionServiceDecorator(BaseQuestionService decorator, int id){
        super(decorator);
        this.category = new Category(id);
    }

    @Override
    public String getSQL(List<Object> params) {
        String sql = this.decorator.getSQL(params) + "AND category_id =? ";
        params.add(this.category.getId());
        return sql;
    }
    
}
