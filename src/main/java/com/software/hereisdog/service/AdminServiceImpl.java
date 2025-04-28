package com.software.hereisdog.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Collections;

/**
 * AdminService 인터페이스 구현 클래스
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Override
    public List<Object> findAllUsers() {
        // TODO: MyBatis DAO를 이용하여 전체 사용자 목록 조회
        return Collections.emptyList();
    }

    @Override
    public void deleteUser(Long userId) {
        // TODO: MyBatis DAO를 이용하여 사용자 삭제
    }
}
