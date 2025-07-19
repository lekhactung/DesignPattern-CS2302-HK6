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
import schoolexc.quizappv2.utils.JdbcConnector;
import schoolexc.quizappv2.pojo.Category;

/**
 *
 * @author LE TUNG
 */
public class CategoryServices extends BaseSerivce<Category>{


    @Override
    public PreparedStatement getStm(Connection conn) throws SQLException {
        return conn.prepareCall("SELECT * FROM category");
    }

    @Override
    public List<Category> getResult(ResultSet rs) throws SQLException {
       List<Category> cates = new ArrayList<>();
        while (rs.next()) {
            Category c = new Category(rs.getInt("id"), rs.getString("name"));
            cates.add(c);
        }

        return cates;
    }
}
