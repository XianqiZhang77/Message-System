package com.ray.message.model;


import lombok.Data;

import java.util.Date;

@Data
public class User {

    private int id;
    private String name;
    private String nickname;
    private String email;
    private String memo;
    private String location;
    private Date createTime;

}
