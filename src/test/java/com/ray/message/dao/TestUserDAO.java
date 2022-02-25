package com.ray.message.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TestUserDAO {

    @Delete("DELETE FROM user WHERE username=#{username}")
    void deleteUser(@Param("username") String username);
}
