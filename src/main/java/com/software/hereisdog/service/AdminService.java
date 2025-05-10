package com.software.hereisdog.service;

import java.util.List;

/**
 * 관리자(Admin) 관련 기능 비즈니스 로직을 처리하는 서비스 인터페이스
 */
public interface AdminService {
    List<Object> findAllUsers();
    void deleteUser(Long userId);
}
