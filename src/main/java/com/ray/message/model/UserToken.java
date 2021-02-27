package com.ray.message.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserToken {
     private Integer id;
     private String token;
     private Date expireTime;

}
