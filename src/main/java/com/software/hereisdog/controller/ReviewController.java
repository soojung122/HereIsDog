package com.software.hereisdog.controller;

import com.software.hereisdog.controller.ReviewForm;
import com.software.hereisdog.domain.Review;
import com.software.hereisdog.domain.User;
import com.software.hereisdog.service.ReviewService;

import jakarta.servlet.http.HttpSession;

import com.software.hereisdog.service.ReviewFormValidator;

import java.security.Principal;
import java.util.Map;

//import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


/**
 * 리뷰 등록 및 조회를 담당하는 컨트롤러
 */
@Controller
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewFormValidator reviewFormValidator;
    
    @Autowired
    public ReviewController(ReviewService reviewService,
    						ReviewFormValidator reviewFormValidator) {
        this.reviewService = reviewService;
        this.reviewFormValidator = reviewFormValidator;
    }

    /** 특정 장소에 대한 리뷰 목록 조회 */
    @GetMapping("/{placeId}")
    public String listReviews(@PathVariable Long placeId, Model model) {
        model.addAttribute("reviews", reviewService.getReviewsByPlaceId(placeId));
        return "review/reviewList";
    }

    /** 리뷰 작성 폼 요청 */
    @GetMapping("/{placeId}/new")
    public String createReviewForm(@PathVariable Long placeId,
                                   HttpSession session,
                                   Model model) {
        Map<String, Object> placeInfo = (Map<String, Object>) session.getAttribute("placeDetail");
        if (placeInfo == null) {
            return "redirect:/"; // 세션 만료 시 홈
        }

        User loginUser = (User) session.getAttribute("loginUser");
        String userId = (loginUser != null) ? loginUser.getId().toString() : "test-user";
        
        //System.out.println(userId);

        model.addAllAttributes(placeInfo);  
        model.addAttribute("placeId", placeId); // 중복으로 명시해도 OK
        model.addAttribute("userId", userId);
        model.addAttribute("reviewForm", new ReviewForm());

        return "review";
    }


    /** 리뷰 작성 처리 */
    @PostMapping("/{placeId}")
    public String createReview(@PathVariable Long placeId,
                               @ModelAttribute ReviewForm reviewForm,
                               BindingResult bindingResult,
                               HttpSession session,
                               Model model,
                               Principal principal) {
    	
        Map<String, Object> placeInfo = (Map<String, Object>) session.getAttribute("placeDetail");
        if (placeInfo == null) {
            return "redirect:/"; 
        }

        // 1차 검증: 사용자 정의 Validator 적용
        reviewFormValidator.validate(reviewForm, bindingResult);
        
        if (bindingResult.hasErrors()) {
            model.addAllAttributes(placeInfo);
            model.addAttribute("reviewForm", reviewForm);
            model.addAttribute("errors", bindingResult);
            return "review";
        }

        User loginUser = (User) session.getAttribute("loginUser");
        String userId = (loginUser != null) ? loginUser.getUsername() : "test-user";  

        Review review = new Review();
        review.setPlaceId(placeId);
        review.setUserId(userId);
        review.setRating(reviewForm.getRating());
        review.setContent(reviewForm.getContent());

        reviewService.registerReview(review);

        return "redirect:/places/detail?placeId=" + placeId; 
    }


}
