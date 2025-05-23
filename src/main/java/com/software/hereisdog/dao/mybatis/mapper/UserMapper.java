package com.software.hereisdog.dao.mybatis.mapper;

import com.software.hereisdog.domain.User;
import com.software.hereisdog.domain.Customer;
import com.software.hereisdog.domain.Owner;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO users (username, password, email, role_id) VALUES (#{username}, #{password}, #{email}, #{role.id})")
    void insertUser(User user);

    @Select("SELECT * FROM users WHERE username = #{username}")
    User findByUsername(String username);
    
    @Insert("INSERT INTO customer (username, password, email) VALUES (#{username}, #{password}, #{email})")
    void insertCustomer(Customer customer);

    @Insert("INSERT INTO owner (username, password, email, business_number) VALUES (#{username}, #{password}, #{email}, #{businessNumber})")
    void insertOwner(Owner owner);
    
    @Select("SELECT * FROM customer WHERE username = #{username}")
    Customer findCustomerByUsername(String username);

    @Select("SELECT * FROM owner WHERE username = #{username}")
    Owner findOwnerByUsername(String username);
}
