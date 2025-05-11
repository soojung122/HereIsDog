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
    public boolean login(String username, String password) {
        return false; // 로그인 생략
    }

    @Override
    public void signup(SignupForm form) {
        //SignupForm form = (SignupForm) signupForm;

        if (form.getBusinessNumber() != null && !form.getBusinessNumber().isBlank()) {
            // Owner
            Owner owner = new Owner();
            owner.setUsername(form.getUsername());
            owner.setPassword(form.getPassword());
            owner.setNickname(form.getNickname());
            owner.setEmail(form.getEmail());
            owner.setBusinessNumber(form.getBusinessNumber());

            userMapper.insertOwner(owner);

        } else {
            // Customer
            Customer customer = new Customer();
            customer.setUsername(form.getUsername());
            customer.setPassword(form.getPassword());
            customer.setNickname(form.getNickname());
            customer.setEmail(form.getEmail());

            userMapper.insertCustomer(customer);
        }
        System.out.println("회원가입 요청 - " + form.getUsername());

    }
}