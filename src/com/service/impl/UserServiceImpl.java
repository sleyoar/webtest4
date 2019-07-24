package com.service.impl;
import com.dao.IUserDao;
import com.entity.User;
import com.factory.Factory;
import com.service.IUserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements IUserService {
    IUserDao userDao;
    public UserServiceImpl(){
        userDao= Factory.getInstance("userDao",IUserDao.class);
    }

    @Override
    public int save(User user) throws SQLException {
        return userDao.save(user);
    }

    @Override
    public int update(User user) throws SQLException {
        return userDao.update(user);
    }

    @Override
    public int delete(int id) throws SQLException {
        return userDao.delete(id);
    }

    @Override
    public User getByUserName(String userName) {
        return userDao.getByUserName(userName);
    }

    @Override
    public User getByCode(String code) throws SQLException {
        return userDao.getByCode(code);
    }

    @Override
    public boolean verifyUser(String userName, String password) throws SQLException {
        return userDao.verifyUser(userName, password);
    }

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }
}
