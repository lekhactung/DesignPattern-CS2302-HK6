/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizappv2.services.question;

import java.util.List;
import schoolexc.quizappv2.pojo.Category;
import schoolexc.quizappv2.pojo.Level;

/**
 *
 * @author LE TUNG
 */
public class LevelQuestionServiceDecorator extends QuestionServiceDecorator{
    private Level lvl;

    public LevelQuestionServiceDecorator(BaseQuestionService decorator, Level l) {
        super(decorator);
        this.lvl = l;
    }

    public LevelQuestionServiceDecorator(BaseQuestionService decorator, int lvlId) {
        super(decorator);
        this.lvl = new Level(lvlId);
    }

    @Override
    public String getSql(List<Object> params) {
        String sql = this.decorator.getSql(params) + " AND level_id = ?";
        params.add(this.lvl.getId());
        return sql;
    }
}
