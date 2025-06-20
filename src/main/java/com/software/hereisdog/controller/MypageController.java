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
            return "redirect:/auth/login";  // 로그인 안 했으면 로그인 페이지로
        }

        String userType = loginUser.getUserType(); // "ADMIN", "OWNER", "USER" 중 하나
        System.out.println(">> loginUser.getUserType() = " + userType);
        if ("ADMIN".equalsIgnoreCase(userType)) {
            return "redirect:/mypage/admin";
        } else if ("OWNER".equalsIgnoreCase(userType)) {
            return "redirect:/mypage/owner";
        } else {
            return "redirect:/mypage/user";  // 기본은 일반 사용자
        }
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
