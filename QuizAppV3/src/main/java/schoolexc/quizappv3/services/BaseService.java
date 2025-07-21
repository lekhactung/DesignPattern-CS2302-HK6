/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizappv3.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.List;
import schoolexc.quizappv3.utils.JdbcConnector;

/**
 *
 * @author LE TUNG
 */
public abstract class BaseService <T>{
    public abstract PreparedStatement getStm(Connection conn) throws  SQLException;
    public abstract List<T> getResults(ResultSet rs) throws SQLException;
    public List<T> list() throws SQLException{
        Connection conn = JdbcConnector.getInstance().connect();
        
        PreparedStatement stm = this.getStm(conn);
        
        return this.getResults(stm.executeQuery());
    }
}
