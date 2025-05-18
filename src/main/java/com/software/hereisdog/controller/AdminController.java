package com.software.hereisdog.controller;
import com.software.hereisdog.service.AdminService;
import com.software.hereisdog.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
        List<User> users = adminService.findAllUsers();
        model.addAttribute("users", users);
        return "admin/userList";
    }

    /** 사용자 삭제 */
    @PostMapping("/users/{userId}/delete")
    public String deleteUser(@PathVariable Long userId) {
        adminService.deleteUser(userId);
        return "redirect:/admin/users";
    }
}
