package com.ycy.test;

import com.ycy.dao.DaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName: ${NAME}
 * @Description:
 * @Author:
 * @Date: 2018/12/2 17:10
 * @Version: V1.0
 **/
public class ServletRegister extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        设置编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

//        获取数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String nike = request.getParameter("nike");

//        向客户端输出内容
        PrintWriter printWriter = response.getWriter();

//        校验数据
        DaoImpl dao = new DaoImpl();
        if (dao.register(username, password, nike)) {
//            注册成功，需要跳转到登录页面
//            设置状态码，重新定位要跳转的页面
            response.setStatus(302);
            response.setHeader("Location", "view/login.html");
        } else {
            printWriter.write("注册失败");
        }
    }
}
