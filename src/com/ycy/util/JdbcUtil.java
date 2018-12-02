package com.ycy.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @ClassName: JdbcUtil
 * @Description:
 * @Author:
 * @Date: 2018/11/19 16:02
 * @Version: V1.0
 **/
public class JdbcUtil {
    static String driverClass = null;
    static String url = null;
    static String user = null;
    static String password = null;

    static {
//        创建一个属性配置对象
        Properties properties = new Properties();
//        使用类加载器，去读取资源文件
        InputStream inputStream = JdbcUtil.class.getClassLoader().
                getResourceAsStream("jdbc.properties");

//        导入输入流
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        读取属性
        driverClass = properties.getProperty("driverClass");
        url = properties.getProperty("url");
        user = properties.getProperty("user");
        password = properties.getProperty("password");
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(driverClass);
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static void release(Connection conn, PreparedStatement ps, ResultSet rs) {
        closeConn(conn);
        closePs(ps);
        closeRs(rs);
    }

    private static void closeConn(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void closePs(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void closeRs(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}