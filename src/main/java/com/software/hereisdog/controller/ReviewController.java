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
    public String listReviews(@RequestParam String placeName, @RequestParam String placeAddress, Model model) {
        model.addAttribute("reviews", reviewService.getReviewsByPlace(placeName, placeAddress));
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
        
        System.out.println(userId);

        model.addAllAttributes(placeInfo);  
        model.addAttribute("placeId", placeId); 
        model.addAttribute("userId", userId);
        model.addAttribute("reviewForm", new ReviewForm());
        model.addAttribute("type", placeInfo.get("type")); //ㅊ코드 추가
        
        //String type = (String) placeInfo.get("type");
        
        return "review";
    }


    /** 리뷰 작성 처리: review->detail */
    @PostMapping("/{placeId}")
    public String saveReview(@PathVariable Long placeId,
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
        
     // 세션에서 로그인 유저 정보 가져오기
        User loginUser = (User) session.getAttribute("loginUser");
        String userId = (loginUser != null) ? loginUser.getUsername() : "test-user";

        // 장소 정보
        String name = (String) placeInfo.get("name");
        String address = (String) placeInfo.get("address");
        String phone = (String) placeInfo.getOrDefault("phone", "");
        String image = (String) placeInfo.getOrDefault("image", "");
        String placeUrl = (String) placeInfo.getOrDefault("place_url", "");
        String type = (String) placeInfo.getOrDefault("type", "");

        // 필수 필드 null 방어
        if (name == null || address == null) {
            model.addAttribute("errorMessage", "장소 정보가 누락되었습니다.");
            return "review";
        }

        // Review 객체 생성 및 값 세팅
        Review review = new Review();
        review.setUserId(userId);
        review.setPlaceName(name);
        review.setPlaceAddress(address);
        review.setContent(reviewForm.getContent());
        review.setRating(reviewForm.getRating());
        // 필요한 필드 더 있으면 여기서 추가

        // 저장
        reviewService.registerReview(review);

        // 리다이렉트 URL (값 인코딩 처리)
        String redirectUrl = String.format("redirect:/places/detail?name=%s&address=%s&phone=%s&image=%s&place_url=%s&type=%s",
                URLEncoder.encode(name, StandardCharsets.UTF_8),
                URLEncoder.encode(address, StandardCharsets.UTF_8),
                URLEncoder.encode(phone, StandardCharsets.UTF_8),
                URLEncoder.encode(image, StandardCharsets.UTF_8),
                URLEncoder.encode(placeUrl, StandardCharsets.UTF_8),
                URLEncoder.encode(type, StandardCharsets.UTF_8));

        return redirectUrl;

    }

   
}
