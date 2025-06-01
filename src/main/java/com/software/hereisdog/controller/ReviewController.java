package com.software.hereisdog.controller;

import com.software.hereisdog.controller.ReviewForm;
import com.software.hereisdog.domain.Review;
import com.software.hereisdog.domain.User;
import com.software.hereisdog.service.ReviewService;

import jakarta.servlet.http.HttpSession;

import com.software.hereisdog.service.ReviewFormValidator;

import java.security.Principal;

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
    public String createReviewForm(@PathVariable Long placeId, @RequestParam String name,
    							@RequestParam String image,HttpSession session, Model model) {
    	// 세션에서 로그인 유저 ID 가져오기
        /*User loginUser = (User) session.getAttribute("loginUser");
        String userId;
		if (loginUser != null)
			userId = loginUser.getId().toString();
		else
			userId = null;*/
    	
    	//테스트용 하드코딩
        String userId = "test-user";
        
        model.addAttribute("placeId", placeId);
        model.addAttribute("name", name);
        model.addAttribute("image", image);
        model.addAttribute("userId", userId);
        model.addAttribute("reviewForm", new ReviewForm());

        return "review";

    }

    /** 리뷰 작성 처리 */
    @PostMapping("/{placeId}")
    public String createReview(@PathVariable Long placeId,
    					       @RequestParam String name,
    					       @RequestParam String image,
    						   @RequestParam String address,
                               @ModelAttribute ReviewForm reviewForm,
                               BindingResult bindingResult,
                               Model model,
                               Principal principal) {

        reviewFormValidator.validate(reviewForm, bindingResult);

        if (bindingResult.hasErrors()){
            model.addAttribute("placeId", placeId);
            model.addAttribute("name", name);
            model.addAttribute("image", image);
            model.addAttribute("userId", reviewForm.getUserId()); // 세션에서 꺼낸 경우에도 유지
            model.addAttribute("reviewForm", reviewForm);
            model.addAttribute("errors", bindingResult);
            return "review";  // JSP 내부에서 ${name}, ${image} 그대로 사용 가능
        }

        
        String userId = (principal != null) ? principal.getName() : "abcd";

        Review review = new Review();
        review.setPlaceId(placeId);
        review.setUserId(userId);
        review.setRating(reviewForm.getRating());
        review.setContent(reviewForm.getContent());

        reviewService.registerReview(review);
        return "redirect:/places/detail?placeId=" + placeId
        	     + "&name=" + URLEncoder.encode(name, StandardCharsets.UTF_8)
        	     + "&address=" + URLEncoder.encode(address, StandardCharsets.UTF_8)
        	     + "&image=" + URLEncoder.encode(image, StandardCharsets.UTF_8);

    }

}
