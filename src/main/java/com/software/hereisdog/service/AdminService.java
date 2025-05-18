package com.software.hereisdog.service;
import com.software.hereisdog.domain.User;
import java.util.List;

public interface AdminService {
    List<User> findAllUsers();
    void deleteUser(Long userId);
}
