package com.lsh.mustardtest.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 刘森华
 * 2019/04/10
 */

public class TestMustard {
    public static void main(String args[]) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("加载驱动成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mustard?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8", "scott", "tiger");
             Statement statement = connection.createStatement();) {
            for (int i = 1; i <= 10; i++) {
                String sqlFormat = "insert into category values(null, '测试分类%d')";
                String sql = String.format(sqlFormat, i);
                statement.execute(sql);
            }
            System.out.println("已经成功创建10条分类测试数据");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
