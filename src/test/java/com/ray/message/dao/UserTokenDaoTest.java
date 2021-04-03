package com.ray.message.dao;

import com.ray.message.model.UserToken;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserTokenDaoTest {

    @Insert("INSERT INTO user_token (user_id, token, expire_time) values (#{userId}, #{token}, #{expireTime})")
    int insert(UserToken userToken);

    @Select("SELECT * FROM user_token WHERE token = #{token}")
    UserToken findByToken(String token);

    @Delete("DELETE FROM user_token WHERE token = #{token}")
    int deleteUserToken(String token);

    @Delete("Delete from `user_Token`")
    void deleteAll();
}
