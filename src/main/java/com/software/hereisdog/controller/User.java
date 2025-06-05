package com.software.hereisdog.controller;

import com.software.hereisdog.domain.Customer; 
import com.software.hereisdog.service.FavoriteService;
import com.software.hereisdog.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/user")
public class User {

    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private ReviewService reviewService;

    // ★ 마이페이지: 내 정보, 찜, 리뷰 다 조회
    @GetMapping("/mypage")
    public String myPage(Model model, @SessionAttribute("loginUser") Customer customer) {
        Long userId = customer.getId();

        model.addAttribute("user", customer); // 닉네임/이메일 등 마이페이지 표시
        model.addAttribute("favList", favoriteService.findByUserId(userId));
        model.addAttribute("reviewList", reviewService.findByCustomerId(userId));
        return "user/mypage"; // => /WEB-INF/views/user/mypage.jsp
    }
    
    @GetMapping("/mypage-test")
    public String testMyPage(Model model) {
      
        Customer testUser = new Customer();
        testUser.setId(5L); // DB에 있는 사용자 ID
        testUser.setUsername("yun01");
        testUser.setEmail("ab@naver.com");
        
        model.addAttribute("user", testUser);
        model.addAttribute("favList", favoriteService.findByUserId(testUser.getId()));
        model.addAttribute("reviewList", reviewService.findByCustomerId(testUser.getId()));

        return "user";   
    }
}
