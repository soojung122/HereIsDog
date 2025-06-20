/*package com.software.hereisdog.service;

import org.springframework.stereotype.Service;

/**
 * AuthService 인터페이스 구현 클래스
 */
/*
@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public boolean login(String username, String password) {
        // TODO: MyBatis DAO를 이용하여 로그인 검증
        return true;
    }

    @Override
    public void signup(Object signupForm) {
        // TODO: MyBatis DAO를 이용하여 회원 가입 처리
    }
}
*/

package com.software.hereisdog.service;

import com.software.hereisdog.controller.SignupForm;
import com.software.hereisdog.dao.mybatis.mapper.UserMapper;
import com.software.hereisdog.domain.Customer;
import com.software.hereisdog.domain.Owner;
import com.software.hereisdog.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 회원가입 처리 서비스 (Customer / Owner 분기 저장)
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final UserMapper userMapper;

    @Autowired
    public AuthServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean login(String username, String password, String role) {
        if ("owner".equals(role)) {
            Owner owner = userMapper.findOwnerByUsername(username);
            return owner != null && owner.getPassword().equals(password);
        } else {
            Customer customer = userMapper.findCustomerByUsername(username);
            return customer != null && customer.getPassword().equals(password);
        }
    }
    
    @Override
    public User getUserByUsernameAndRole(String username, String role) {
        if ("owner".equals(role)) {
            Owner owner = userMapper.findOwnerByUsername(username);
            if (owner != null) {
                owner.setUserType("OWNER"); // 🔥 여기가 핵심!
            }
            return owner;
        } else {
            Customer customer = userMapper.findCustomerByUsername(username);
            if (customer != null) {
                customer.setUserType("USER"); // 🔥 여기도!
            }
            return customer;
        }
    }
    
    @Override
    public void signup(SignupForm form) {
        //SignupForm form = (SignupForm) signupForm;

        if (form.getBusinessNumber() != null && !form.getBusinessNumber().isBlank()) {
            // Owner
            Owner owner = new Owner();
            owner.setUsername(form.getUsername());
            owner.setPassword(form.getPassword());
            //owner.setNickname(form.getNickname());
            owner.setEmail(form.getEmail());
            owner.setBusinessNumber(form.getBusinessNumber());

            userMapper.insertOwner(owner);

        } else {
            // Customer
            Customer customer = new Customer();
            customer.setUsername(form.getUsername());
            customer.setPassword(form.getPassword());
            //customer.setNickname(form.getNickname());
            customer.setEmail(form.getEmail());

            userMapper.insertCustomer(customer);
        }
        System.out.println("회원가입 요청 - " + form.getUsername());

    }
    
    // 아이디 찾기 관련
    @Override
    public String findUsernameByEmailAndRole(String email, String role) {
        if ("owner".equals(role)) {
            Owner owner = userMapper.findOwnerByEmail(email);
            return owner != null ? owner.getUsername() : null;
        } else {
            Customer customer = userMapper.findCustomerByEmail(email);
            return customer != null ? customer.getUsername() : null;
        }
    }
    
    // 비밀번호 재설정 관련
    @Override
    public void updatePassword(String username, String newPassword) {
        // 먼저 owner 테이블에서 찾기
        Owner owner = userMapper.findOwnerByUsername(username);
        if (owner != null) {
            userMapper.updateOwnerPassword(username, newPassword);
            return;
        }

        // 없으면 customer 테이블에서 찾기
        Customer customer = userMapper.findCustomerByUsername(username);
        if (customer != null) {
            userMapper.updateCustomerPassword(username, newPassword);
        }
    }
}