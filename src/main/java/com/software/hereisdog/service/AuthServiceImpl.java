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

<<<<<<< HEAD
    /**
     * 사용자 회원가입 처리
     * @param signupForm 가입 폼에서 입력받은 사용자 정보
     */

    @Override
    public void signup(Object signupForm) {
        // SignupForm을 실제 타입으로 변환
        SignupForm form = (SignupForm) signupForm;
        /*
        // User 엔티티 생성
        User user = new User();
        user.setUsername(form.getUsername());
        user.setPassword(form.getPassword());
        user.setEmail(form.getEmail());
=======
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
>>>>>>> d5403291e3d679ff2c3dcca7545b6bc4fbb1817a

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

<<<<<<< HEAD
        // DB에 저장
        userMapper.insertUser(user); */
    } 
=======
    }
>>>>>>> d5403291e3d679ff2c3dcca7545b6bc4fbb1817a
}
