package com.ray.message.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserToken {
     private Integer userId;
     private String token;
     @DateTimeFormat(pattern = "YYYY-MM-DD HH:MM:SS")
     private Date expireTime;

}
