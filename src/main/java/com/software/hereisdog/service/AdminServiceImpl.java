package com.software.hereisdog.service;
import com.software.hereisdog.dao.AdminDAO;
import com.software.hereisdog.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminDAO adminDAO;

    @Autowired
    public AdminServiceImpl(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    @Override
    public List<User> findAllUsers() {
        return adminDAO.findAllUsers();
    }

    @Override
    public void deleteUser(Long userId) {
        adminDAO.deleteUser(userId);
    }
}
