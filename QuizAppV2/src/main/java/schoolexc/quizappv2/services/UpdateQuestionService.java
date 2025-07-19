/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizappv2.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import schoolexc.quizappv2.pojo.Question;
import schoolexc.quizappv2.utils.JdbcConnector;

/**
 *
 * @author LE TUNG
 */
public class UpdateQuestionService {

    public boolean deleteQuestion(int id) throws SQLException {
        Connection conn = JdbcConnector.getInstance().connect();
        PreparedStatement stm = conn.prepareCall("DELETE FROM question WHERE ID = ?");
        stm.setInt(1, id);
        return stm.executeUpdate() > 0;
    }

    public void addQuestion(Question q) throws SQLException {
        Connection conn = JdbcConnector.getInstance().connect();

        conn.setAutoCommit(false);

        String sql = "INSERT INTO question(content,hint,image,category_id,level_id) VALUES(?,?,?,?,?)";
        PreparedStatement stm = conn.prepareCall(sql);

        stm.setString(1, q.getContent());
        stm.setString(2, q.getHint());
        stm.setString(3, q.getImg());
        stm.setInt(4, q.getCategory().getId());
        stm.setInt(5, q.getLevel().getId());

        if (stm.executeUpdate() > 0) {
            int question_id = -1;
            ResultSet rs = stm.getGeneratedKeys();

            if (rs.next()) {
                question_id = rs.getInt(1);
            }
            sql = "INSERT INTO choice(content,is_correct,question_id) VALUES(?,?,?) ";
            stm = conn.prepareCall(sql);
            for (var c : q.getChoices()) {
                stm.setString(1, c.getContent());
                stm.setBoolean(2, c.isCorrect());
                stm.setInt(3, question_id);
                stm.executeUpdate();
            }

            conn.commit();
        } else {
            conn.rollback();
        }
    }
}
