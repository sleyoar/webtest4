package com.controller;

import com.entity.User;
import com.factory.Factory;
import com.service.IUserService;
import com.util.CodeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    IUserService userService;

    public RegisterServlet() {
        userService = Factory.getInstance("userService", IUserService.class);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //接受数据
            request.setCharacterEncoding("UTF-8");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            //封装数据
            User user = new User();
            user.setUserName(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setState(0);//0:未激活  1:已激活
            String code = CodeUtil.generateUniqueCode() + CodeUtil.generateUniqueCode();
            user.setCode(code);
            //调用业务层处理数据
            userService.save(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //页面跳转
        request.setAttribute("msg", "你已经注册成功!请去邮箱激活");
        request.getRequestDispatcher("user/msg.jsp").forward(request, response);

    }
}
