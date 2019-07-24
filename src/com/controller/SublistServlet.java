package com.controller;

import com.Constant;
import com.entity.Pager;
import com.entity.Student;
import com.service.StudentService;
import com.service.impl.StudentServiceImpl;
import com.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SublistServlet")
public class SublistServlet extends HttpServlet {
    private static final long serialVersionUID = -326362240626848942L;
    private StudentService studentService = new StudentServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收request参数
        String stuName = request.getParameter("stuName");

        int gender = Constant.DEFAULT_GENDER;
        String genderStr = request.getParameter("gender");
        if (genderStr != null&&!"".equals(genderStr)) {
            gender = Integer.parseInt(genderStr);
        }
        //校验pageNum
        String pageNumStr = request.getParameter("pageNum");
        if (pageNumStr != null && !StringUtil.isNum(pageNumStr)) {
            request.setAttribute("errorMsg","参数传输错误");
            request.getRequestDispatcher("student/sublistStudent.jsp").forward(request, response);
            return;
        }
        int pageNum = Constant.DEFAULT_PAGE_NUM;
        if (pageNumStr != null&&!"".equals(pageNumStr)) {
            pageNum = Integer.parseInt(pageNumStr);
        }

        int pageSize = Constant.DEFAULT_PAGE_SIZE;
        String pageSizeStr = request.getParameter("pageSize");
        if (pageSizeStr != null&&!"".equals(pageSizeStr)) {
            pageSize = Integer.parseInt(pageSizeStr);
        }
        System.out.println(pageNum+","+pageSize);
        //组装查询条件
        Student searchModel = new Student();
        searchModel.setStuName(stuName);
        searchModel.setGender(gender);

        //调用service获取查询结果
        Pager<Student> result = studentService.findStudent(searchModel, pageNum, pageSize);

        //返回结果到页面
        request.setAttribute("result", result);
        request.setAttribute("stuName",stuName);
        request.setAttribute("gender",gender);
        request.getRequestDispatcher("student/sublistStudent.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
