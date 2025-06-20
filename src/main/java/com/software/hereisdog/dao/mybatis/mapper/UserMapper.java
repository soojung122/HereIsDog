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
<<<<<<< Updated upstream
}
=======
    
    @Select("SELECT * FROM customer WHERE email = #{email}")
    Customer findCustomerByEmail(String email);

    @Select("SELECT * FROM owner WHERE email = #{email}")
    Owner findOwnerByEmail(String email);
    
    // 비밀번호 재설정 - Customer용
    @Update("UPDATE customer SET password = #{newPassword} WHERE username = #{username}")
    void updateCustomerPassword(@Param("username") String username, @Param("newPassword") String newPassword);
    
    // 비밀번호 재설정 - Owner용
    @Update("UPDATE owner SET password = #{newPassword} WHERE username = #{username}")
    void updateOwnerPassword(@Param("username") String username, @Param("newPassword") String newPassword);

}
>>>>>>> Stashed changes
