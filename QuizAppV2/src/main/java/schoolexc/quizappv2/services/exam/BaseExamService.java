/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizappv2.services.exam;

import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.List;
import schoolexc.quizappv2.pojo.Question;

/**
 *
 * @author LE TUNG
 */
public abstract class BaseExamService {
    public abstract List<Question> getQuestion() throws SQLException;
    
}
