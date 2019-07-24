package com.dao;

import com.entity.User;
import java.sql.SQLException;
import java.util.List;

public interface IUserDao {
    //添加User信息
    int save(User user) throws SQLException;

    //更新User信息
    int update(User user) throws SQLException;

    //按照id删除对应User
    int delete(int id) throws SQLException;

    //根据id查询对应的User信息
    User getByUserName(String userName);

    //根据code查询对应的User信息
    User getByCode(String code) throws SQLException;

    //根据userName和password验证用户是否存在
    boolean verifyUser(String userName, String password) throws SQLException;

    //取出所有用户
    List<User> getAllUser();
}
