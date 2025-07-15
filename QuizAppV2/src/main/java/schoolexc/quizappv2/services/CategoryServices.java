/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizappv2.services;

import java.sql.Connection;
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
public class CategoryServices {

    public List<Category> getCates() throws SQLException {
        Connection conn = JdbcConnector.getInstance().connect(); //connect sql server
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM category");//stm truy van sql

        List<Category> cates = new ArrayList<>();
        while (rs.next()) {
            Category c = new Category(rs.getInt("id"), rs.getString("name"));
            cates.add(c);
        }

        return cates;
    }
}
