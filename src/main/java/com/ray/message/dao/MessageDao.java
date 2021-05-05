package com.ray.message.dao;

import com.ray.message.model.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface MessageDao {

    @Insert("insert into `message` (id, text, sender_user_id, receiver_user_id, send_time, is_read) " +
            "values (#{id,jdbcType=INTEGER},#{text,jdbcType=VARCHAR},#{senderUserId,jdbcType=INTEGER}, #{receiverUserId,jdbcType=INTEGER}," +
            "#{sendTime,jdbcType=DATE}, #{isRead,jdbcType=INTEGER})")
    int insert(Message message);

    @Select("select * from `message` where " +
            "(sender_user_id = #{thisUserId} AND receiver_user_id = #{otherUserId}) " +
            "OR (sender_user_id = #{otherUserId} AND receiver_user_id = #{thisUserId}) " +
            "AND send_time >= #{startTime} AND send_time <= #{endTime} ORDER BY send_time DESC LIMIT #{start}, #{pageSize}")
    List<Message> findMessagesByTimeRange(Integer thisUserId, Integer otherUserId, Date startTime, Date endTime, int pageSize, int start);

    @Select("select COUNT(*) from `message` where " +
            "(sender_user_id = #{thisUserId} AND receiver_user_id = #{otherUserId}) " +
            "OR (sender_user_id = #{otherUserId} AND receiver_user_id = #{thisUserId}) " +
            "AND send_time >= #{startTime} AND send_time <= #{endTime}")
    int countMessagesByTimeRange(Integer thisUserId, Integer otherUserId, Date startTime, Date endTime);

    @Select("select * from `message` where " + "id > #{lastIndex} AND" +
            "((sender_user_id = #{thisUserId} AND receiver_user_id = #{otherUserId}) " +
            "OR (sender_user_id = #{otherUserId} AND receiver_user_id = #{thisUserId})) " +
            "ORDER BY send_time DESC LIMIT #{start}, #{pageSize}")
    List<Message> findNewMessages(Integer thisUserId, Integer otherUserId, Integer pageSize, int start, Integer lastIndex);

    @Select("select COUNT(*) from `message` where " +  "id > #{lastIndex} AND" +
            "((sender_user_id = #{thisUserId} AND receiver_user_id = #{otherUserId}) " +
            "OR (sender_user_id = #{otherUserId} AND receiver_user_id = #{thisUserId}))")
    int countNewMessages(Integer thisUserId, Integer otherUserId, Integer lastIndex);

    @Update("update `message` set is_read=true where id = #{messageId} " +
            "AND (sender_user_id = #{otherUserId} AND receiver_user_id = #{thisUserId})")
    void updateMessageAsRead(Integer thisUserId, Integer otherUserId, Integer messageId);
}
