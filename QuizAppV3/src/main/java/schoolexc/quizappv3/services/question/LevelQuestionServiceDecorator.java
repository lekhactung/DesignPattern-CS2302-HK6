/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizappv3.services.question;

import java.util.List;
import schoolexc.quizappv3.pojo.Category;
import schoolexc.quizappv3.pojo.Level;

/**
 *
 * @author LE TUNG
 */
public class LevelQuestionServiceDecorator extends QuestionServiceDecorator{
    private Level lvl;

    public LevelQuestionServiceDecorator( BaseQuestionService decorator,Level lvl) {
        super(decorator);
        this.lvl = lvl;
    }

    @Override
    public String getSql(List<Object> params) {
        String sql = this.decorator.getSql(params) + "AND level_id = ?";
        params.add(this.lvl.getId());
        return sql;
    }
}
