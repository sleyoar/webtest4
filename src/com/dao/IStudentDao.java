package com.dao;

import com.entity.Pager;
import com.entity.Student;

public interface IStudentDao {
    /**
     * 更据查询条件，查询学生分页信息
     *
     * @param searchModel 封装查询条件
     * @param pageNum     查询第几页数据
     * @param pageSize    每页显示的记录条数
     * @return 查询结果
     */
    public Pager<Student> findStudent(Student searchModel, int pageNum, int pageSize);
}