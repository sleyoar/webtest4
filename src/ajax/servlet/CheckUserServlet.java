package ajax.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/check")
public class CheckUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pw=response.getWriter();
        String name=request.getParameter("username");
        String result="";
        if ("tom".equalsIgnoreCase(name)) {
            result="<font color='red'>该用户名已被注册，请重新注册</font>";
        }else {
            result="<font color='green'>用户名可用</font>";
        }
        pw.print(result);
    }
}
