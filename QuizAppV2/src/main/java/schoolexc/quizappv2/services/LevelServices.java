/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizappv2.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import schoolexc.quizappv2.pojo.Level;
import schoolexc.quizappv2.utils.JdbcConnector;

/**
 *
 * @author LE TUNG
 */
public class LevelServices extends BaseSerivce<Level> {

    @Override
    public PreparedStatement getStm(Connection conn) throws SQLException {
        return conn.prepareCall("SELECT * FROM level");
    }

    @Override
    public List<Level> getResult(ResultSet rs) throws SQLException {
    List<Level> levels = new ArrayList<>();
        while (rs.next()) {
            Level l = new Level(rs.getInt("id"), rs.getString("name"), rs.getString("note"));
            levels.add(l);
        }

        return levels;    
    }
}
