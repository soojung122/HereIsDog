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
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/mypage")
    public String myPage(HttpSession session, Model model) {
        Object loginUser = session.getAttribute("loginUser");
        if (loginUser == null || !(loginUser instanceof Customer)) {
            return "redirect:/auth/login";
        }

        Customer customer = (Customer) session.getAttribute("loginUser");

        Long userId = customer.getId();               // 찜용
        String username = customer.getUsername();     // 리뷰용

        
        // 닉네임 username 중 실제 있는 것만!
        model.addAttribute("user", customer); // nickname/username 출력
        model.addAttribute("favList", favoriteService.findByUserId(userId));
        model.addAttribute("reviewList", reviewService.getReviewsByUserId(username));
        return "user"; // user.jsp
    }

    @GetMapping("")
    public String userMain() {
        return "user";
    }
}
