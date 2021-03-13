package com.ray.message.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String memo;
    private String location;
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private Date createTime;

}
