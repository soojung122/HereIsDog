package com.software.hereisdog.dao.mybatis.mapper;

import com.software.hereisdog.domain.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO users (username, password, email, role_id) VALUES (#{username}, #{password}, #{email}, #{role.id})")
    void insertUser(User user);

    @Select("SELECT * FROM users WHERE username = #{username}")
    User findByUsername(String username);
}
