/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizapp.serivces.question;

import java.util.List;
import schoolexc.quizapp.pojo.Level;

/**
 *
 * @author admin
 */
public class LevelQuestionServiceDecorator extends QuestionServiceDecorator {

    private Level level;

    public LevelQuestionServiceDecorator(BaseQuestionService decorator, Level level) {
        super(decorator);
        this.level = level;
    }

    public LevelQuestionServiceDecorator(BaseQuestionService decorator, int levelId) {
        super(decorator);
        this.level = new Level(levelId);
    }

    @Override
    public String getSQL(List<Object> params) {
        String sql = this.decorator.getSQL(params) + "AND level_id =? ";
        params.add(this.level.getId());
        return sql;
    }

}
