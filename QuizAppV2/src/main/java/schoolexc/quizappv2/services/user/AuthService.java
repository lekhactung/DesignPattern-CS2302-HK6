/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizappv2.services.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import schoolexc.quizappv2.pojo.User;
import schoolexc.quizappv2.utils.Configs;
import schoolexc.quizappv2.utils.JdbcConnector;

/**
 *
 * @author LE TUNG
 */
public class AuthService {
    private Connection conn;

    public AuthService(Connection conn) {
        this.conn = conn;
    }
    
    public boolean compareUserInfo(String username,String userPassword) throws SQLException{
//        Connection conn = JdbcConnector.getInstance().connect();
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
        PreparedStatement stm = conn.prepareCall(sql);
        stm.setString(1, username);
        stm.setString(2, userPassword);
        ResultSet rs = stm.executeQuery();
        if(rs.next()){
            UserLoginService.getInstance().login(new User(rs.getString("username"),rs.getString("password")));
            return true;
        }
//        stm.execute();
//        return true;
        Configs.LOGIN_STATUS = false;
        return false;
    }
    
    
}
