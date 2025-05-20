package com.software.hereisdog.controller;

import com.software.hereisdog.controller.ReviewForm;
import com.software.hereisdog.service.ReviewService;
import com.software.hereisdog.service.ReviewFormValidator;
//import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("reviews", reviewService.findByPlaceId(placeId));
        return "review/reviewList";
    }

    /** 리뷰 작성 폼 요청 */
    @GetMapping("/{placeId}/new")
    public String createReviewForm(@PathVariable Long placeId, Model model) {
        model.addAttribute("reviewForm", new ReviewForm());
        model.addAttribute("placeId", placeId);
        return "review/createReviewForm";
    }

    /** 리뷰 작성 처리 */
    @PostMapping("/{placeId}/new")
    public String createReview(@PathVariable Long placeId,
                                @ModelAttribute ReviewForm reviewForm,
                                BindingResult bindingResult,
                                Model model) {
    	reviewFormValidator.validate(reviewForm, bindingResult);
    	
    	if (bindingResult.hasErrors()) {
            model.addAttribute("placeId", placeId); // 다시 전달 필요
            model.addAttribute("reviewForm", reviewForm);
            return "review/createReviewForm";
        }

        reviewService.registerReview(placeId, reviewForm);
        return "redirect:/reviews/" + placeId;
    }
}
