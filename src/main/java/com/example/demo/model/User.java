package com.example.demo.model;


import java.util.Date;

public class User {

    private int id;
    private String name;
    private String nickname;
    private String email;
    private String memo;
    private String location;
    private Date createTime;

    public User(int id, String name, String nickname, String email, String memo, String location, Date createTime) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.memo = memo;
        this.location = location;
        this.createTime = createTime;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
