package com.controller;

import com.factory.Factory;
import com.service.IUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    IUserService userService;
    public LoginServlet(){
        userService= Factory.getInstance("userService",IUserService.class);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //获取表单数据
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String code = request.getParameter("code");
        //从session中获取验证码
        String scode = (String) request.getSession().getAttribute("scode");
        System.out.println("后台获取的验证码："+scode);
        //处理业务逻辑
        try {
            if(!userService.verifyUser(userName,password)){
                //分发转向
                if(!code.equalsIgnoreCase(scode)){
                    request.setAttribute("msg", "验证码错误");
                    request.getRequestDispatcher("user/login.jsp").forward(request, response);
                }
                out.print("登录成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
