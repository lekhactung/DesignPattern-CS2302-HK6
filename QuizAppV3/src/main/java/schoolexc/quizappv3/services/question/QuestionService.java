/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizappv3.services.question;

import java.util.List;

/**
 *
 * @author LE TUNG
 */
public class QuestionService extends BaseQuestionService{

    @Override
    public String getSql(List<Object> params) {
        return "SELECT * FROM question WHERE 1=1 ";
    }
    
}
