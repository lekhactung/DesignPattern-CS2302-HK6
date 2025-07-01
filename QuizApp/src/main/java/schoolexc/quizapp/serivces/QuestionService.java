/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizapp.serivces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import schoolexc.quizapp.pojo.Choice;
import schoolexc.quizapp.pojo.Level;
import schoolexc.quizapp.pojo.Question;
import schoolexc.quizapp.ultils.JdbcConnector;

/**
 *
 * @author LE TUNG
 */
public class QuestionService {

    public void addQuestion(Question q) throws SQLException {
        Connection conn = JdbcConnector.getInstance().connect();
//        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/quizdb", "root", "root");

        conn.setAutoCommit(false);

        String sql = "INSERT INTO question(content, hint, image, category_id, level_id) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement stm = conn.prepareCall(sql);
        stm.setString(1, q.getContent());
        stm.setString(2, q.getHint());
        stm.setString(3, q.getImg());
        stm.setInt(4, q.getCategory().getId());
        stm.setInt(5, q.getLevel().getId());

        if (stm.executeUpdate() > 0) {
            int questionId = -1;
            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                questionId = rs.getInt(1);
            }

            sql = "INSERT INTO choice(content, is_correct, question_id) VALUES(?, ?, ?)";
            stm = conn.prepareCall(sql);
            for (var c : q.getChoices()) {
                stm.setString(1, c.getContent());
                stm.setBoolean(2, c.isCorrect());
                stm.setInt(3, questionId);
                stm.executeUpdate();
            }

            conn.commit();
        } else {
            conn.rollback();
        };
    }

    public boolean deleteQuestion(int id) throws SQLException{
        Connection conn = JdbcConnector.getInstance().connect();
        PreparedStatement stm  = conn.prepareCall("DELETE FROM question WHERE id = ?");
        stm.setInt(1, id);
        
        return stm.executeUpdate() > 0;
    }
    
    public List<Question> getQuestion() throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/quizdb", "root", "root");

        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM question ORDER BY id DESC");

        List<Question> question  = new ArrayList<>();
        while (rs.next()) {
            Question q = new Question.Builder(rs.getInt("id"), rs.getString("content")).build();
            question.add(q);
        }
        return  question;
    }
    
    
    public List<Question> getQuestion(String kw) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/quizdb", "root", "root");

        PreparedStatement stm = conn.prepareCall("SELECT * FROM question WHERE content like concat ('%' , ? , '%') ORDER BY id DESC");
        stm.setString(1, kw);
        ResultSet rs = stm.executeQuery();

        List<Question> question = new ArrayList<>();
        while (rs.next()) {
            Question q = new Question.Builder(rs.getInt("id"), rs.getString("content")).build();
            question.add(q);

        }
        return question;
    }
    
     public List<Question> getQuestion(int num) throws SQLException {
        Connection conn = JdbcConnector.getInstance().connect();

        PreparedStatement stm = conn.prepareCall("SELECT * FROM question LIMIT ? ORDER BY rand()");
        stm.setInt(1, num);
        ResultSet rs = stm.executeQuery();

        List<Question> question = new ArrayList<>();
        while (rs.next()) {
            Question q = new Question.Builder(rs.getInt("id"),
                    rs.getString("content"))
                    .addAllChoice(this.getChoicesByQuestion(rs.getInt("id"))).build();
        
            
            
            question.add(q);
     
        
        }
        return question;
    }
     
     public List<Choice> getChoicesByQuestion(int questionId) throws SQLException{
         Connection conn = JdbcConnector.getInstance().connect();

        PreparedStatement stm = conn.prepareCall("SELECT * FROM choice WHERE question_id = ?");
        stm.setInt(1, questionId);
        
        ResultSet rs = stm.executeQuery();
        List<Choice> choices = new ArrayList<>();
        while(rs.next()){
            Choice c = new Choice(rs.getInt("id"), rs.getString("content"),rs.getBoolean("is_correct"));
            choices.add(c);
            
        }
        return choices;
     }
}
