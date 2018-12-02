package com.ycy.dao;

import com.ycy.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName: DaoImpl
 * @Description:
 * @Author:
 * @Date: 2018/12/2 18:43
 * @Version: V1.0
 **/
public class DaoImpl {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public DaoImpl() {
        conn = JdbcUtil.getConnection();
    }

    public boolean register(String username, String password, String nike) {
        boolean success = selectRepe(username, password, nike);
        if (success) {
            try {
                String SQLString = "insert into servlet_test(username,password,nike) values (?,?,?)";
                ps = conn.prepareStatement(SQLString);
                ps.setString(1, username);
                ps.setString(2, password);
                ps.setString(3, nike);
                int result = ps.executeUpdate();

                if (result > 0) {
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                JdbcUtil.release(conn, ps, rs);
            }
        }

        return false;
    }

    public boolean login(String username, String password) {
        try {
            String SQLString = "select username,password from servlet_test";
            ps = conn.prepareStatement(SQLString);
            rs = ps.executeQuery();

            while (rs.next()) {
                if (username.equals(rs.getString(1))
                        && password.equals(rs.getString(2))) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(conn, ps, rs);
        }

        return false;
    }

    private boolean selectRepe(String username, String password, String nike) {
        try {
            String SQLString = "select username,password,nike from servlet_test";
            ps = conn.prepareStatement(SQLString);
            rs = ps.executeQuery();

            while (rs.next()) {
                if (username.equals(rs.getString(1))
                        && password.equals(rs.getString(2))
                        && nike.equals(rs.getString(3))) {
                    return false;
                } else {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
}
