package com.entity;

public class User {
    private Integer id;//用户id
    private String userName;//用户名
    private String email;//用户邮箱
    private String password;//用户密码
    private Integer state;//账号状态，0未激活，1激活
    private String code;//激活码

    public User() {
    }

    public User(Integer id, String userName, String email, String password, Integer state, String code) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.state = state;
        this.code = code;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", state=" + state +
                ", code='" + code + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
