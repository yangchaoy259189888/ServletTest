package com.ycy.test;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @ClassName: ${NAME}
 * @Description:
 * @Author:
 * @Date: 2018/12/2 17:51
 * @Version: V1.0
 **/
public class ServletDownload extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        根据传过来的数据组成下载的地址路径
        String fileName = request.getParameter("fileName");
        String realPath = getServletContext().getRealPath("download/" + fileName);
        System.out.println(realPath);

//        下载提示框
        response.setHeader("Content-disposition", "attachment; fileName=" + fileName);

//        两个输入输出流，并下载
        FileInputStream fileInputStream = new FileInputStream(realPath);
        System.out.println(fileInputStream);
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = fileInputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }

//        关闭流
        fileInputStream.close();
        outputStream.close();
    }
}
