package com.windcoder.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 原生事务实例
 */
public class JdbcTransaction {
    public static void main(String[] args) {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/user";
            String user = "root";
            String password = "123456";
            Long sID = 1L;
            con = (Connection) DriverManager.getConnection(url, user, password);
            // 取消自动提交事务
            con.setAutoCommit(false);
            Statement stmt = (Statement) con.createStatement();
            // 执行一系列操作
            stmt.executeUpdate("delete from user where ID=" + sID);
            stmt.executeUpdate("delete from xiao_affix where user_id=" + sID);
            // 提交JDBC事务
            con.commit();
            // 恢复JDBC事务的默认提交方式
            con.setAutoCommit(true);
        } catch (Exception e) {
            e.printStackTrace();
            if (con!=null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            if (con!=null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }
}
