package com.ray.message.dao;

import com.ray.message.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {

    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(@Param("id") Integer id);

    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByName(@Param("username") String username);

    @Select("SELECT * FROM user WHERE email = #{email}")
    User findByEmail(String email);

    int updateUser(User user);

    @Insert("insert into user (id, username, password, nickname, email, memo, location, create_time) " +
            "values (#{id,jdbcType=INTEGER}, #{username,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}," +
            "#{nickname,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, #{memo,jdbcType=VARCHAR}," +
            "#{location,jdbcType=VARCHAR}, #{createTime,jdbcType=DATE})")
    int insert(User user);

    @Select("select * from user")
    List<User> selectAll();
}
