package com.software.hereisdog.service;

import com.software.hereisdog.controller.SignupForm;
import com.software.hereisdog.dao.mybatis.mapper.UserMapper;
import com.software.hereisdog.domain.User;
import com.software.hereisdog.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 로그인, 로그아웃, 회원가입 로직을 처리하는 서비스 구현 클래스
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final UserMapper userMapper;

    @Autowired
    public AuthServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 사용자 로그인 처리
     */
    @Override
    public boolean login(String username, String password) {
        User user = userMapper.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    /**
     * 사용자 회원가입 처리
     * @param signupForm 가입 폼에서 입력받은 사용자 정보
     */
    @Override
    public void signup(Object signupForm) {
        // SignupForm을 실제 타입으로 변환
        SignupForm form = (SignupForm) signupForm;

        // User 엔티티 생성
        User user = new User();
        user.setUsername(form.getUsername());
        user.setPassword(form.getPassword());
        user.setEmail(form.getEmail());

        // 기본 권한 부여 (예: 일반 사용자 role_id = 1)
        Role role = new Role();
        role.setId(1L);  // Role 엔티티에 따라 값 조정
        user.setRole(role);

        // DB에 저장
        userMapper.insertUser(user);
    }
}
