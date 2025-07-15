/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizapp.serivces.question;

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
public class QuestionService implements BaseQuestionService{

   
    
//    public List<Question> getQuestions() throws SQLException {
//        Connection conn = JdbcConnector.getInstance().connect();
//
//        Statement stm = conn.createStatement(); 
//        
//        ResultSet rs = stm.executeQuery("SELECT * FROM question ORDER BY id DESC");
//
//        List<Question> questions = new ArrayList<>();
//        while (rs.next()) {
//            Question q = new Question.Builder(rs.getInt("id"), 
//                    rs.getString("content")).build();
//            questions.add(q);
//        }
//        
//        return questions;
//    }
    
//    public List<Question> getQuestions(String kw) throws SQLException {
//        Connection conn = JdbcConnector.getInstance().connect();
//
//        PreparedStatement stm = conn.prepareCall("SELECT * FROM question WHERE content like concat('%', ?, '%') ORDER BY id DESC"); 
//        stm.setString(1, kw);
//        
//        ResultSet rs = stm.executeQuery();
//
//        List<Question> questions = new ArrayList<>();
//        while (rs.next()) {
//            Question q = new Question.Builder(rs.getInt("id"), 
//                    rs.getString("content")).build();
//            questions.add(q);
//        }
//        
//        return questions;
//    }
    
//    public List<Question> getQuestions(int num) throws SQLException {
//        Connection conn = JdbcConnector.getInstance().connect();
//
//        PreparedStatement stm = conn.prepareCall("SELECT * FROM question "); 
//        stm.setInt(1, num);
//        
//        ResultSet rs = stm.executeQuery();
//
//        List<Question> questions = new ArrayList<>();
//        while (rs.next()) {
//            Question q = new Question.Builder(rs.getInt("id"), 
//                    rs.getString("content"))
//                    .addAllChoice(this.getChoicesByQuestion(rs.getInt("id"))).build();
//            
//            questions.add(q);
//        }
//        
//        return questions;
//    }
    
//    public List<Choice> getChoicesByQuestion(int questionId) throws SQLException {
//        Connection conn = JdbcConnector.getInstance().connect();
//        PreparedStatement stm = conn.prepareCall("SELECT * FROM choice WHERE question_id=?"); 
//        stm.setInt(1, questionId);
//        
//        ResultSet rs = stm.executeQuery();
//        
//        List<Choice> choices = new ArrayList<>();
//        while (rs.next()) {
//            Choice c = new Choice(rs.getInt("id"), rs.getString("content"), rs.getBoolean("is_correct"));
//            choices.add(c);
//        }
//        
//        return choices;
//    }

    @Override
    public String getSQL(List<Object> params) {
        return "SELECT * FROM question WHERE 1=1";
    }
}
