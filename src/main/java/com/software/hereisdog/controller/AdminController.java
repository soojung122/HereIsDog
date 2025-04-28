package com.software.hereisdog.controller;

import com.software.hereisdog.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 관리자(Admin) 관련 기능을 담당하는 컨트롤러
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    /** 모든 회원 목록 조회 */
    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", adminService.findAllUsers());
        return "admin/userList";
    }

    /** 사용자 삭제 */
    @PostMapping("/users/{userId}/delete")
    public String deleteUser(@PathVariable Long userId) {
        adminService.deleteUser(userId);
        return "redirect:/admin/users";
    }
}
