/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizappv2.services.exam;

import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.List;
import schoolexc.quizappv2.pojo.Exam;
import schoolexc.quizappv2.pojo.Question;
import schoolexc.quizappv2.utils.JdbcConnector;

/**
 *
 * @author LE TUNG
 */
public abstract class BaseExamService {

    public abstract List<Question> getQuestion() throws SQLException;

    public void addExam(List<Question> questions) throws SQLException {
        Connection conn = JdbcConnector.getInstance().connect();
        conn.setAutoCommit(false);
        
        
        String sql = "INSERT INTO exam(title,created_date) VALUES(?,?)";

        Exam exam = new Exam(questions);
        PreparedStatement stm = conn.prepareCall(sql);
        stm.setString(1, exam.getTitle());
        stm.setString(2, exam.getCreatedDate().toString());

        if (stm.executeUpdate() > 0) {
            int exam_id = -1;
            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                exam_id = rs.getInt(1);
            }

            sql = "INSERT INTO exam_question(exam_id,question_id) VALUES (?,?)";
            stm = conn.prepareCall(sql);
            for(var q : questions){
                stm.setInt(1,exam_id);
                stm.setInt(2, q.getId());
                
                stm.executeUpdate();
            }
            conn.commit();
        } else{
            conn.rollback();
        }

    }
}
