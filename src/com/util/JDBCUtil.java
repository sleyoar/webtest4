package com.util;

import java.sql.*;
import java.util.*;

public class JDBCUtil {
    private static String user;
    private static String password;
    private static String driver;
    private static String url;
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    static {
        ResourceBundle bundle = ResourceBundle.getBundle("db");
        user = bundle.getString("user");
        password = bundle.getString("password");
        driver = bundle.getString("driver");
        url = bundle.getString("url");
    }

    public JDBCUtil() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public List<Map<String, Object>> findResult(String sql,List<?> params) throws SQLException{
        conn=getConnection();
        List<Map<String, Object>> list=new ArrayList<>();
        int index=1;
        ps=conn.prepareStatement(sql);
        if(params!=null&&!params.isEmpty()){
            for(int i=0;i<params.size();i++){
                ps.setObject(index++,params.get(i));
            }
        }
        rs=ps.executeQuery();
        ResultSetMetaData metaData=rs.getMetaData();
        int cols=metaData.getColumnCount();
        while (rs.next()){
            Map<String,Object> map=new HashMap<>();
            for(int i=0;i<cols;i++){
                String cols_name=metaData.getColumnName(i+1);
                Object cols_value=rs.getObject(cols_name);
                if(cols_value==null){
                    cols_value="";
                }
                map.put(cols_name,cols_value);
            }
            list.add(map);
        }
        return list;
    }
    /**
     * @param sql   sql语句
     * @param param 条件
     * @return ResultSet
     */
    /*public List<Map<String, Object>> executeQuery(String sql, Object... param){
        List<Map<String, Object>> list = new ArrayList<>();
        conn = getConnection();
        try {
            ps = conn.prepareStatement(sql);
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    ps.setObject(i, param[i]);
                }
            }
            rs = ps.executeQuery();
            ResultSetMetaData rm = rs.getMetaData();
            int count = rm.getColumnCount();
            while (rs.next()) {
                Map<String, Object> map = new HashMap<>();
                for (int i = 0; i < count; i++) {
                    map.put(rm.getCatalogName(i+1),
                            rs.getObject(rm.getCatalogName(i+1)));
                }
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }*/

    //增删改。。。
    public int executeUpdate(String sql, Object... param) {
        conn = getConnection();
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < param.length; i++) {
                ps.setObject(i + 1, param[i]);
            }
            int affectedRows = ps.executeUpdate();
            return affectedRows;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    //method4:专门用于发送查询语句
    public ResultSet execQuery(String strSQL,Object... param){
        conn=getConnection();
        System.out.println("SQL:>"+strSQL);
        try{
            ps=conn.prepareStatement(strSQL);
            for(int i=0;i<param.length;i++){
                ps.setObject(i+1, param[i]);
            }
            rs=ps.executeQuery();
            return rs;
        } catch(SQLException e){
            e.printStackTrace();
            return null;
        }
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
