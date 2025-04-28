package com.software.hereisdog.controller;

import com.software.hereisdog.controller.ReviewForm;
import com.software.hereisdog.service.ReviewService;
import jakarta.validation.Valid;
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

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
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
                                @Valid @ModelAttribute ReviewForm reviewForm,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "review/createReviewForm";
        }
        reviewService.registerReview(placeId, reviewForm);
        return "redirect:/reviews/" + placeId;
    }
}
