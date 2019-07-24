package com.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class C3P0Util {
    private static DataSource dataSource;
    private static Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    static {
        dataSource=new ComboPooledDataSource("mysql");
    }
    /**
     * 返回连接池对象
     * @return
     */
    public static DataSource getDataSource(){
        return dataSource;
    }

    /**
     * 使用连接池返回一个连接对象
     * @return
     */
    public static Connection getConnection(){
        try {
            conn=dataSource.getConnection();
            return conn ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void closeAll() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
