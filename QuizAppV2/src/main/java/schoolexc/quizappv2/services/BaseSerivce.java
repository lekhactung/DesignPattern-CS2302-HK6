/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizappv2.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import schoolexc.quizappv2.pojo.Question;
import schoolexc.quizappv2.utils.JdbcConnector;

/**
 *
 * @author LE TUNG
 */
public abstract class BaseSerivce <T> {
    public abstract PreparedStatement getStm(Connection conn) throws SQLException;
    public abstract List<T> getResult(ResultSet rs) throws SQLException;
    public List<T> list() throws SQLException{
        Connection conn = JdbcConnector.getInstance().connect();
        
        PreparedStatement stm = this.getStm(conn);
        
        return this.getResult(stm.executeQuery());
    }
}
