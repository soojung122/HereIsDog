package com.software.hereisdog.service;

import com.software.hereisdog.controller.SignupForm;

/**
 * 로그인, 로그아웃, 회원가입 로직을 처리하는 서비스 인터페이스
 */
public interface AuthService {
    boolean login(String username, String password, String role);
    void signup(SignupForm signupForm);
    // 아이디 찾기 DB연동
    String findUsernameByEmailAndRole(String email, String role);
    // 비밀번호 재설정 DB연동
    void updatePassword(String username, String newPassword);
}
