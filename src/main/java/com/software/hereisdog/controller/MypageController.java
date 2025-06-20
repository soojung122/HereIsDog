package com.software.hereisdog.controller;

import com.software.hereisdog.domain.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MypageController {

    private static final String ADMIN_USERNAME = "adminUsername";  // 하드코딩 관리자 ID

    @GetMapping("/mypage")
    public String redirectMypage(HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/auth/login";  // 로그인 안했으면 로그인 페이지로
        }

        // 1) 관리자 하드코딩 판단 (username으로)
        if (ADMIN_USERNAME.equals(loginUser.getUsername())) {
            return "redirect:/admin";
        }

        // 2) 오너/유저 구분 : businessNumber 필드로 판단 (사업자 번호가 있으면 오너)
        if (loginUser.getBusinessNumber() != null && !loginUser.getBusinessNumber().isEmpty()) {
            return "redirect:/owner";
        }

        // 3) 그 외는 일반 유저 페이지로
        return "redirect:/user";
    }
    
     // 명시적 페이지 매핑 추가
        @GetMapping("/mypage/user")
        public String userPage() {
            return "user";
        }
    
        @GetMapping("/mypage/owner")
        public String ownerPage() {
            return "owner";
        }
    
        @GetMapping("/mypage/admin")
        public String adminPage() {
            return "admin";
        }
}
