package ajax.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class C3P0Util {
    private static DataSource ds=new ComboPooledDataSource();

    public static Connection getConn()  {
        try {
            return  ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public static DataSource getDs(){
        return  ds;
    }

    public static void closeAll(PreparedStatement ps, ResultSet rs,Connection conn){
        // 鍏抽棴璧勬簮
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = null;
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            ps = null;
        }
        if (conn != null) {
            try {
                conn.close();// 鍏抽棴
            } catch (Exception e) {
                e.printStackTrace();
            }
            conn = null;
        }

    }
}
