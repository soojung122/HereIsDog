package com.software.hereisdog.service;

import com.software.hereisdog.controller.SignupForm;

/**
 * 로그인, 로그아웃, 회원가입 로직을 처리하는 서비스 인터페이스
 */
public interface AuthService {
    boolean login(String username, String password);
    void signup(SignupForm signupForm);
}
