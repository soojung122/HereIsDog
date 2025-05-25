package com.software.hereisdog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.software.hereisdog.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);  // 아이디 존재 여부 확인
}
