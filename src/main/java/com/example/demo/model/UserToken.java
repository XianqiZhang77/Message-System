package com.example.demo.model;

import java.util.Date;

public class UserToken {
     private Integer id;
     private String token;
     private Date expireTime;

    public UserToken(Integer id, String token, Date expireTime) {
        this.id = id;
        this.token = token;
        this.expireTime = expireTime;
    }

    public UserToken() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }
}
