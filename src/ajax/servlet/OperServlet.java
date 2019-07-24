package ajax.servlet;

import ajax.dao.IStuDao;
import ajax.entity.Stu;
import ajax.factory.BeanFactory;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("*.do")
public class OperServlet extends HttpServlet {
    IStuDao stuDao;
    public OperServlet(){
        stuDao= BeanFactory.getInstance("studao",IStuDao.class);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Stu> stus=stuDao.getAllStus();
        //转换为json格式
        String json= JSON.toJSONString(stus);
        //把json格式输出到前端
        response.getWriter().print(json);
        System.out.println("查询到所有学生："+json);

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String serlvetPath=request.getServletPath();
        response.setContentType("text/xml;character=utf-8");
        response.setCharacterEncoding("utf-8");

        if(serlvetPath.contains("list")){
            doList(request,response);
        }

    }
}
