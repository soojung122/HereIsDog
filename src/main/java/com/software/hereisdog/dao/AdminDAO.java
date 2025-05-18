package com.software.hereisdog.dao;
import com.software.hereisdog.domain.User;
import java.util.List;

public interface AdminDAO {
    List<User> findAllUsers();           // 회원 전체 조회
    void deleteUser(Long userId);        // 회원 삭제
    // 필요하다면 관리자 조회도
    // Admin findAdminById(Long adminId);
}
