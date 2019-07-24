package ajax.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        work(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        out.write("No get");
        out.flush();
        out.close();
    }
    private void work(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        request.setCharacterEncoding("UTF-8");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String enPassword = password; //User.encode(password);  //md5加密!
        String ret = "No " + userName;
        if ("admin".equals(userName)&&"123456".equals(password)) {
            request.getSession().setAttribute("user","admin");
            request.getSession().setMaxInactiveInterval(60 * 30);
            ret = "Yes"; //登录成功
        }
        PrintWriter out = response.getWriter();
        out.write(ret);
        out.flush();
        out.close();
    }
}
