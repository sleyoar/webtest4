package com.service.impl;

import com.dao.IStudentDao;
import com.dao.impl.SqlStudentDaoImpl;
import com.entity.Pager;
import com.entity.Student;
import com.service.StudentService;

public class SqlStudentServiceImpl implements StudentService {
    private IStudentDao studentDao;
    public SqlStudentServiceImpl(){
        //创建service实现类是，初始化dao对象
        studentDao=new SqlStudentDaoImpl();
    }
    @Override
    public Pager<Student> findStudent(Student searchModel, int pageNum, int pageSize) {
        Pager<Student> result = studentDao.findStudent(searchModel, pageNum, pageSize);
        return result;
    }
}
