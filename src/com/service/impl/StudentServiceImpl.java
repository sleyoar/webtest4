package com.service.impl;

import com.dao.IStudentDao;
import com.dao.impl.SublistStudentDaoImpl;
import com.entity.Pager;
import com.entity.Student;
import com.service.StudentService;

public class StudentServiceImpl implements StudentService {
    private IStudentDao studentDao;
    public StudentServiceImpl(){
        //创建service实现类是，初始化dao对象
        studentDao=new SublistStudentDaoImpl();
    }

    @Override
    public Pager<Student> findStudent(Student searchModel, int pageNum, int pageSize) {
        Pager<Student> result = studentDao.findStudent(searchModel, pageNum, pageSize);
        return result;
    }
}
