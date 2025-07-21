/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizappv3.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import schoolexc.quizappv3.pojo.Question;
import schoolexc.quizappv3.utils.JdbcConnector;

/**
 *
 * @author LE TUNG
 */
public class UpdQuestionServices {

    public void addQuestion(Question question) throws SQLException {
        Connection conn = JdbcConnector.getInstance().connect();

        conn.setAutoCommit(false);

        String sql = "INSERT INTO question(content,hint,image,category_id,level_id) VALUES (?,?,?,?,?)";
        PreparedStatement stm = conn.prepareCall(sql);
        stm.setString(1, question.getContent());
        stm.setString(2, question.getHint());
        stm.setString(3, question.getImage());
        stm.setInt(4, question.getCategory().getId());
        stm.setInt(5, question.getLevel().getId());

        if (stm.executeUpdate() > 0) {
            int question_id = -1;
            ResultSet rs = stm.getGeneratedKeys();

            if (rs.next()) {
                question_id = rs.getInt(1);
            }

            sql = "INSERT INTO choice(content,is_correct,question_id) VALUES (?,?,?)";
            stm = conn.prepareCall(sql);

            for (var c : question.getChoices()) {
                stm.setString(1, c.getContent());
                stm.setBoolean(2, c.isIsCorrect());
                stm.setInt(3, question_id);
                stm.executeUpdate();
            }
            conn.commit();
        } else {
            conn.rollback();
        }
    }

    public boolean deleteQuestion(int questionId) throws SQLException {
        Connection conn = JdbcConnector.getInstance().connect();
        String sql = "DELETE FROM question WHERE id = ?";
        PreparedStatement stm = conn.prepareCall(sql);
        stm.setInt(1, questionId);
        return stm.executeUpdate() > 0;
    }

    public List<Question> getQuestion(String kw) throws SQLException {
        Connection conn = JdbcConnector.getInstance().connect();
        String sql = "SELECT * FROM question WHERE content like concat('%',?,'%') ORDER BY id DESC";
        PreparedStatement stm = conn.prepareCall(sql);
        stm.setString(1, kw);
        ResultSet rs = stm.executeQuery();
        List<Question> questions = new ArrayList<>();
        while (rs.next()) {
            Question q = new Question.Builder(rs.getInt("id"), rs.getString("content")).build();
            questions.add(q);
        }
        return questions;
    }

}
