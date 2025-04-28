package com.software.hereisdog.service;

import org.springframework.stereotype.Service;

/**
 * AuthService 인터페이스 구현 클래스
 */
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
