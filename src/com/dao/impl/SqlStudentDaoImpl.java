package com.dao.impl;

import com.Constant;
import com.dao.IStudentDao;
import com.entity.Pager;
import com.entity.Student;
import com.util.JDBCUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 使用mysql实现分页 limit
 */
public class SqlStudentDaoImpl implements IStudentDao {
    @Override
    public Pager<Student> findStudent(Student searchModel, int pageNum, int pageSize) {
        Pager<Student> result = null;
        //存放查询参数
        List<Object> paramList = new ArrayList<Object>();

        String stuName = searchModel.getStuName();
        int gender = searchModel.getGender();

        StringBuilder sql = new StringBuilder("select * from student where 1=1");
        StringBuilder conutsql = new StringBuilder("select count(id) as totalRecord from student where 1=1");
        if (stuName != null && !stuName.equals("")) {
            sql.append(" and stu_name like ? ");
            conutsql.append(" and stu_name like ? ");
            paramList.add("%" + stuName + "%");
        }
        if (gender == Constant.GENDER_FEMALE || gender == Constant.GENDER_MALE) {
            sql.append(" and gender=?");
            conutsql.append(" and gender= ? ");
            paramList.add(gender);
        }
        //起始索引
        int fromIndex = pageSize * (pageNum - 1);
        //使用limit关键字，实现分页
        sql.append(" limit " + fromIndex + ", " + pageSize);

        //存放所有查询出的学生对象
        List<Student> studentList = new ArrayList<>();
        JDBCUtil jdbcUtil = null;
        try {
            jdbcUtil = new JDBCUtil();
            jdbcUtil.getConnection();//获取数据库连接
            //获取总记录数
            List<Map<String, Object>> countResult = jdbcUtil.findResult(conutsql.toString(), paramList);
            Map<String, Object> countMap = countResult.get(0);
            int totalRecord =((Number)countMap.get("totalRecord")).intValue();
            //获取总学生对象
            List<Map<String, Object>> studentResult = jdbcUtil.findResult(sql.toString(), paramList);
            if (studentResult != null) {
                for (Map<String, Object> map : studentResult) {
                    Student s = new Student(map);
                    studentList.add(s);
                }
            }
            //获取总页数
            int totalPage = totalRecord / pageSize;
            if (totalRecord % pageSize != 0) {
                totalPage++;
            }
            //组装page对象
            result=new Pager<Student>(pageSize,pageNum,
                    totalRecord,totalPage,studentList);

        } catch (SQLException e) {
            throw new RuntimeException("查询所有数据出错", e);
        } finally {
            if (jdbcUtil != null) {
                jdbcUtil.closeAll();
            }
        }
        return result;
    }
}
