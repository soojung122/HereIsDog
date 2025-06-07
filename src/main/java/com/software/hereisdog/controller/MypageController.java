package com.software.hereisdog.controller;

import com.software.hereisdog.domain.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MypageController {

    private static final String ADMIN_USERNAME = "adminUsername";  

    @GetMapping("/mypage")
    public String redirectMypage(HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/auth/login";
        }

        // 관리자 분기 (username 하드코딩)
        if (ADMIN_USERNAME.equals(loginUser.getUsername())) {
            return "redirect:/admin";
        }

        // 오너 분기 (사업자 번호 체크)
        if (loginUser.getBusinessNumber() != null && !loginUser.getBusinessNumber().isEmpty()) {
            return "redirect:/owner";
        }

        // 일반 유저
        return "redirect:/user";
    }
}

