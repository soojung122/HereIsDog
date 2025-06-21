package com.software.hereisdog.dao.mybatis.mapper;

import com.software.hereisdog.domain.Customer;
import com.software.hereisdog.domain.Owner;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminMapper {

    @Select("SELECT * FROM customer")
    List<Customer> findAllCustomers();

    @Select("SELECT * FROM owner")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "username", column = "username"),
        @Result(property = "password", column = "password"),
        @Result(property = "email", column = "email"),
        @Result(property = "businessNumber", column = "business_number")
    })
    List<Owner> findAllOwners();

    @Delete("DELETE FROM customer WHERE id = #{id}")
    void deleteCustomer(Long id);

    @Delete("DELETE FROM owner WHERE id = #{id}")
    void deleteOwner(Long id);
}

