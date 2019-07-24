package com.dao.impl;

import ajax.util.C3P0Util;
import com.dao.IUserDao;
import com.entity.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements IUserDao {
    QueryRunner queryRunner;

    public UserDaoImpl() {
        queryRunner = new QueryRunner(C3P0Util.getDs());
    }

    @Override
    public int save(User user) throws SQLException {
        int result = queryRunner.update("insert into user values(?,?,?,?,?)", new Object[]{user.getUserName(), user.getEmail(), user.getPassword(), user.getState(), user.getCode()});
        return result;
    }

    @Override
    public int update(User user) throws SQLException {
        String sql = "update user set userName=?,email=?,password=?,state=?,code=? where id=?";
        Object[] params = {user.getUserName(), user.getEmail(), user.getPassword(), user.getState(), user.getCode(), user.getId()};
        int result = queryRunner.update(sql, params);
        return result;
    }

    @Override
    public int delete(int id) throws SQLException {
        String sql = "delete from user where id=?";
        int result = queryRunner.update(sql, id);
        return result;
    }

    @Override
    public User getByUserName(String userName) {
        String sql = "select * from user where userName=?";
        User user;
        try {
            user = queryRunner.query(sql, new BeanHandler<>(User.class), userName);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User getByCode(String code) throws SQLException {
        String select_sql="select *from user where code=?";
        User user=queryRunner.query(select_sql,new BeanHandler<>(User.class),code);
        return user;
    }

    @Override
    public boolean verifyUser(String userName, String password) throws SQLException {
        String sql = "select * from user where userName=? and password=?";
        Object[] params = {userName, password};
        User user = queryRunner.query(sql, new BeanHandler<>(User.class), params);
        if (user != null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<User> getAllUser() {
        try {
            List<User> list = queryRunner.query("select * from user", new BeanListHandler<>(User.class));
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
