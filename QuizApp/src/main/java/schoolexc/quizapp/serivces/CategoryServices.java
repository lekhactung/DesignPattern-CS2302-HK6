/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizapp.serivces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import schoolexc.quizapp.pojo.Category;

/**
 *
 * @author LE TUNG
 */
public class CategoryServices {

    public List<Category> getCates() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/quizdb", "root", "root");
        
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM category");

        List<Category> cates = new ArrayList<>();
        while (rs.next()) {
            Category c = new Category(rs.getInt("id"), rs.getString("ten"));
            cates.add(c);

//                System.out.printf("id : %d \t ten : %s\n",rs.getInt("id"),rs.getString("ten"));
        }
        return cates;
    }
}
