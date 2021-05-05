package com.ray.message.sevice;

import com.ray.message.dao.MessageDao;
import com.ray.message.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageDao messageDao;

    public List<Message> getMessagesByTimeRange(Integer thisUserId, Integer otherUserId, Date startTime, Date endTime, int pageSize, int page) {
        int start = (page - 1) * pageSize;
        List<Message> messagesByTimeRange = messageDao.findMessagesByTimeRange(thisUserId, otherUserId, startTime, endTime, pageSize, start);
        return messagesByTimeRange;
    }

    public int countByTimeRange(Integer thisUserId, Integer otherUserId, Date startTime, Date endTime) {
        return messageDao.countMessagesByTimeRange(thisUserId, otherUserId, startTime, endTime);
    }

    public void addMessage(Integer userId, Message message) {
        message.setSenderUserId(userId);
        message.setSendTime(new Date());
        message.setIsRead(0);
        messageDao.insert(message);
    }

    public List<Message> getNewMessages(Integer thisUserId, Integer otherUserId, Integer pageSize, Integer page, Integer lastIndex) {
        int start = (page - 1) * pageSize;
        List<Message> messages = messageDao.findNewMessages(thisUserId, otherUserId, pageSize, start, lastIndex);
        return messages;
    }

    public int countNewMessages(Integer thisUserId, Integer otherUserId, Integer lastIndex) {
        return messageDao.countNewMessages(thisUserId, otherUserId, lastIndex);
    }

    public void updateMessageAsRead(Integer thisUserId, Integer otherUserId, Integer messageId) {
        //no this messageId Exception
        messageDao.updateMessageAsRead(thisUserId, otherUserId, messageId);
    }
}
