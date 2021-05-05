package com.ray.message.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private Integer id;
    private String text;
    private Integer senderUserId;
    private Integer receiverUserId;
    private Date sendTime;
    private Integer isRead;

}
