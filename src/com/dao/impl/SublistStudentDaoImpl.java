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

public class SublistStudentDaoImpl implements IStudentDao {
    @Override
    public Pager<Student> findStudent(Student searchModel, int pageNum, int pageSize) {
        List<Student> allStudent = getAllStudent(searchModel);
        Pager<Student> pager = new Pager<Student>(pageNum, pageSize, allStudent);
        return pager;
    }

    /**
     * 模仿获取所有数据
     *
     * @param searchModel 查询参数
     * @return 查询结果
     */
    private List<Student> getAllStudent(Student searchModel) {
        List<Student> result = new ArrayList<Student>();
        List<Object> paramList = new ArrayList<Object>();

        String stuName = searchModel.getStuName();
        int gender = searchModel.getGender();

        StringBuilder sql = new StringBuilder("select * from student where 1=1");
        if (stuName != null && !stuName.equals("")) {
            sql.append(" and stu_name like ? ");
            paramList.add("%" + stuName + "%");
        }
        if (gender == Constant.GENDER_FEMALE || gender == Constant.GENDER_MALE) {
            sql.append(" and gender=?");
            paramList.add(gender);
        }
        JDBCUtil jdbcUtil=null;
        try {
            jdbcUtil= new JDBCUtil();
            jdbcUtil.getConnection();
            List<Map<String, Object>> mapList = jdbcUtil.findResult(sql.toString(), paramList);
            if (mapList != null) {
                for (Map<String, Object> map : mapList) {
                    Student s = new Student(map);
                    result.add(s);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("查询所有数据出错",e);
        } finally {
            if(jdbcUtil!=null) {
                jdbcUtil.closeAll();
            }
        }
        return result;
    }
}
