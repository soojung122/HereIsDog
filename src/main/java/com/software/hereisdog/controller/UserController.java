package com.software.hereisdog.controller;

import com.software.hereisdog.domain.Customer;
import com.software.hereisdog.domain.FavoritePlace;
import com.software.hereisdog.domain.Review;
import com.software.hereisdog.service.FavoriteService;
import com.software.hereisdog.service.ReviewService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/mypage/user")
    public String myPage(Model model, HttpSession session) {
        Object loginUser = session.getAttribute("loginUser");

        // Customer 객체인지 확인 후 캐스팅
        if (loginUser instanceof Customer customer) {
            Long userId = customer.getId();

            model.addAttribute("user", customer);
            model.addAttribute("favList", favoriteService.findByUserId(userId));

            return "user"; // /WEB-INF/jsp/user.jsp
        }

        return "redirect:/auth/login";
    }
}
