package com.controller;

import com.entity.User;
import com.factory.Factory;
import com.service.IUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yyh
 * @date 2019/7/22-11:23
 */
@WebServlet("/active")
public class ActiveServlet extends HttpServlet {
    IUserService userService;

    public ActiveServlet() {
        userService = Factory.getInstance("userService", IUserService.class);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       try{
           String code=request.getParameter("code");
           //根据激活码查询用户
           User user=userService.getByCode(code);
           //已经查询到，修改用户的状态
           if(user!=null) {
               //已经查询到了,修改用户的状态
               user.setState(1);
               user.setCode(null);
               userService.update(user);
               request.setAttribute("msg", "你已经激活成功，请去登录！");
               request.getRequestDispatcher("user/msg.jsp").forward(request, response);

           }else {
               //根据激活码没有查询到该用户
               request.setAttribute("msg", "你的激活码有误，请重新激活");
               request.getRequestDispatcher("user/msg.jsp").forward(request, response);
           }
       }catch (Exception e){
           e.printStackTrace();
       }
       //接受激活码

    }
}
