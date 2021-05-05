package com.ray.message.controller;

import com.ray.message.annotations.NeedAuth;
import com.ray.message.exception.ServiceException;
import com.ray.message.model.Message;
import com.ray.message.rep.PaginatedResponse;
import com.ray.message.rep.RespResult;
import com.ray.message.sevice.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/messages")
    @NeedAuth
    public RespResult getMessageRange(Integer thisUserId, @RequestParam(value = "otherUserId") Integer otherUserId,
                                      @RequestParam(value = "startTime") Date startTime,
                                      @RequestParam(value = "endTime") Date endTime,
                                      @RequestParam(value = "pageSize") int pageSize,
                                      @RequestParam(value = "page") int page) throws ServiceException {
        List<Message> messagesByTimeRange = messageService.getMessagesByTimeRange(thisUserId, otherUserId, startTime, endTime, pageSize, page);
        boolean hasMore = messageService.countByTimeRange(thisUserId, otherUserId, startTime, endTime) > (page - 1) * pageSize + messagesByTimeRange.size();
        return RespResult.createWithData(new PaginatedResponse<Message>(page, messagesByTimeRange, hasMore));
    }

    @PostMapping("/send")
    @NeedAuth
    public RespResult send(Integer userId, @RequestBody Message message) {
        messageService.addMessage(userId, message);
        return RespResult.createWithData("sent successfully");
    }

    @GetMapping("/new_messages")
    @NeedAuth
    public RespResult getNewMessages(Integer thisUserId, @RequestParam(value = "otherUserId") Integer otherUserId,
                                     @RequestParam(value = "pageSize") Integer pageSize,
                                     @RequestParam(value = "page") Integer page,
                                     @RequestParam(value = "lastIndex") Integer lastIndex) {
        List<Message> messages = messageService.getNewMessages(thisUserId, otherUserId, pageSize, page, lastIndex);
        boolean hasMore = messageService.countNewMessages(thisUserId, otherUserId, lastIndex)
                > (page - 1) * pageSize + messages.size();
        return RespResult.createWithData(new PaginatedResponse<>(page, messages, hasMore));
    }

    @PostMapping("/readMessage")
    @NeedAuth
    public RespResult readMessage(Integer thisUserId,
                                  @RequestParam(value = "otherUserId") Integer otherUserId,
                                  @RequestParam(value = "message_id") Integer messageId) {
        messageService.updateMessageAsRead(thisUserId, otherUserId, messageId);
        return RespResult.createWithData("Read");
    }

}
