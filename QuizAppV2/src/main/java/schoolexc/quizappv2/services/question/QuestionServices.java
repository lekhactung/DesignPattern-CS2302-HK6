/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizappv2.services.question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.temporal.TemporalQueries;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.jbosslog.JBossLog;
import schoolexc.quizappv2.pojo.Choice;
import schoolexc.quizappv2.pojo.Level;
import schoolexc.quizappv2.pojo.Question;
import schoolexc.quizappv2.utils.JdbcConnector;

/**
 *
 * @author LE TUNG
 */
public class QuestionServices extends BaseQuestionService {

    @Override
    public String getSql(List<Object> params) {
        return "SELECT * FROM question WHERE 1=1 ";
    }

//    public List<Question> getQuestion() throws SQLException {
//        Connection conn = JdbcConnector.getInstance().connect();
//        Statement stm = conn.createStatement();
//
//        ResultSet rs = stm.executeQuery("SELECT * FROM question");
//        List<Question> questions = new ArrayList<>();
//        while (rs.next()) {
//            Question q = new Question.Builder(rs.getInt("id"), rs.getString("content")).build();
//            questions.add(q);
//        }
//        return questions;
//    }

//    public List<Question> getQuestion(String kw) throws SQLException {
//        Connection conn = JdbcConnector.getInstance().connect();
////        Statement stm = conn.createStatement();
//        PreparedStatement stm = conn.prepareCall("SELECT * FROM question WHERE content like concat ('%',?,'%') ORDER BY id DESC");
//        stm.setString(1, kw);
//        ResultSet rs = stm.executeQuery();
//
//        List<Question> questions = new ArrayList<>();
//        while (rs.next()) {
//            Question q = new Question.Builder(rs.getInt("id"), rs.getString("content")).build();
//            questions.add(q);
//        }
//        return questions;
//    }
//
//    public List<Question> getQuestion(int num) throws SQLException {
//        Connection conn = JdbcConnector.getInstance().connect();
//        PreparedStatement stm = conn.prepareCall("SELECT * FROM question ORDER BY rand() LIMIT ?");
//        stm.setInt(1, num);
//        ResultSet rs = stm.executeQuery();
//
//        List<Question> questions = new ArrayList<>();
//        while (rs.next()) {
//            Question q = new Question.Builder(rs.getInt("id"), rs.getString("content"))
//                    .addAllChoice(this.getChoiceByQuestion(rs.getInt("id")))
//                    .build();
//
//            questions.add(q);
//        }
//        return questions;
//    }

//    public List<Choice> getChoiceByQuestion(int questionId) throws SQLException {
//        Connection conn = JdbcConnector.getInstance().connect();
//
//        PreparedStatement stm = conn.prepareCall("SELECT * FROM choice WHERE question_id = ?");
//        stm.setInt(1, questionId);
//        ResultSet rs = stm.executeQuery();
//        List<Choice> choices = new ArrayList<>();
//        while (rs.next()) {
//            Choice c = new Choice(rs.getInt("id"), rs.getString("content"), rs.getBoolean("is_correct"));
//            choices.add(c);
//        }
//        return choices;
//    }

}
