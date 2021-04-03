package com.ray.message.dao;

import com.ray.message.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDaoTest {

    @Delete("Delete from `user`")
    void deleteAll();

    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByName(String name);

    @Insert("insert into user (id, username, password, nickname, email, memo, location, create_time) " +
            "values (#{id,jdbcType=INTEGER}, #{username,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}," +
            "#{nickname,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, #{memo,jdbcType=VARCHAR}," +
            "#{location,jdbcType=VARCHAR}, #{createTime,jdbcType=DATE})")
    int insert(User user);
}
