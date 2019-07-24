package ajax.dao.impl;

import ajax.dao.IStuDao;
import ajax.entity.Stu;
import ajax.util.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StuDaoImpl implements IStuDao {
    QueryRunner qr;
    public StuDaoImpl(){
        qr=new QueryRunner(C3P0Util.getDs());
    }

    @Override
    public  int saveStu(Stu stu) {

        return 0;
    }

    @Override
    public   int deleteStu(int id) {
       /* String delete_sql="delete from users where id=?";
        int result=0;
        try {
           result= qr.update(delete_sql,new Object[]{id});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;*/
        return  0;
    }

    @Override
    public   int updateStu(Stu stu) {
      /*  String update_sql="update users set username=?,password=? where id=?";
        int result=0;
        try {
            result=qr.update(update_sql,new Object[]{user.getUsername(),user.getPassword(),user.getId()});
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        return 0;
    }

    @Override
    public  List<Stu> getAllStus() {
        String select_sql="select * from stu";
        List<Stu>stus=new ArrayList<>();
        try {
            stus=qr.query(select_sql,new BeanListHandler<Stu>(Stu.class));
            //System.out.println(users);
            for(Stu stu:stus){
                System.out.println(stu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stus;

    }

    @Override
    public  Stu findbyId(int id) {
        return  null;
    }


}