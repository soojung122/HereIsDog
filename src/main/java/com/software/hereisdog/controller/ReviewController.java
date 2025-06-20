package com.software.hereisdog.controller;

import com.software.hereisdog.controller.ReviewForm;
import com.software.hereisdog.service.ReviewService;
import com.software.hereisdog.service.ReviewFormValidator;
<<<<<<< Updated upstream
=======

import java.security.Principal;
import java.util.List;
import java.util.Map;

>>>>>>> Stashed changes
//import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
<<<<<<< Updated upstream
=======
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

>>>>>>> Stashed changes

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
<<<<<<< Updated upstream
    public String listReviews(@PathVariable Long placeId, Model model) {
        model.addAttribute("reviews", reviewService.findByPlaceId(placeId));
=======
    public String listReviews(@PathVariable String placeId, Model model) {
        model.addAttribute("reviews", reviewService.getReviewsByPlaceId(placeId));
>>>>>>> Stashed changes
        return "review/reviewList";
    }

    /** 리뷰 작성 폼 요청 */
<<<<<<< Updated upstream
    @GetMapping("/{placeId}/new")
    public String createReviewForm(@PathVariable Long placeId, Model model) {
=======
   /* @GetMapping("/{placeId}/new")
    public String createReviewForm(@PathVariable Long placeId,
                                   HttpSession session,
                                   Model model) {
        Map<String, Object> placeInfo = (Map<String, Object>) session.getAttribute("placeDetail");
        if (placeInfo == null) {
            return "redirect:/"; // 세션 만료 시 홈
        }

        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/loginForm"; // 로그인 페이지로 이동
        }
        
        String userId = loginUser.getUsername();    
        //System.out.println(userId);

        model.addAllAttributes(placeInfo);  
        model.addAttribute("placeId", placeId); // 중복으로 명시해도 OK
        model.addAttribute("userId", userId);
>>>>>>> Stashed changes
        model.addAttribute("reviewForm", new ReviewForm());
        model.addAttribute("placeId", placeId);
        return "review/createReviewForm";
    }

<<<<<<< Updated upstream
    /** 리뷰 작성 처리 */
    @PostMapping("/{placeId}/new")
=======

*/


    /** 리뷰 작성 처리 */
   /* @PostMapping("/{placeId}")
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
}
=======
  
    */
    /** 리뷰 작성 폼 요청 */
     @GetMapping("/new")
     public String createReviewForm(@RequestParam String placeId,
             						@RequestParam String name,
						             @RequestParam String address,
						             @RequestParam(required = false) String phone,
						             @RequestParam(required = false) String image,
						             @RequestParam(required = false, name = "place_url") String placeUrl,
						             HttpSession session,
						             Model model) {
    	 System.out.println(">> /reviews/new 진입 시도됨");
    	    System.out.println(">> placeDetail 세션 값: " + session.getAttribute("placeDetail"));
    	    System.out.println(">> loginUser 세션 값: " + session.getAttribute("loginUser"));
    	    
         Map<String, Object> placeInfo = (Map<String, Object>) session.getAttribute("placeDetail");
         if (placeInfo == null) {
             return "redirect:/"; // 세션 만료 시 홈
         }

         User loginUser = (User) session.getAttribute("loginUser");
         if (loginUser == null) {
             return "redirect:/auth/login"; // 로그인 페이지로 이동
         }
         
         String userId = loginUser.getUsername();    
         //System.out.println(userId);

         model.addAllAttributes(placeInfo);  
         model.addAttribute("placeId", placeId); // 중복으로 명시해도 OK
         model.addAttribute("userId", userId);
         model.addAttribute("reviewForm", new ReviewForm());

         return "review";
     }
     
    /* @GetMapping("/places/detail")
     public String showPlaceDetail(@RequestParam String placeId,
                                   @RequestParam String name,
                                   @RequestParam String address,
                                   @RequestParam(required = false) String phone,
                                   @RequestParam(required = false) String image,
                                   @RequestParam(required = false, name = "place_url") String placeUrl,
                                   Model model) {
    	 //System.out.println("전달된 placeId 확인: " + placeId);
    	 
    	 
         model.addAttribute("placeId", placeId);
         model.addAttribute("name", name);
         model.addAttribute("address", address);
         model.addAttribute("phone", phone);
         model.addAttribute("image", image);
         model.addAttribute("place_url", placeUrl);
         
         List<Review> reviews = reviewService.getReviewsByPlaceId(placeId);
         Double avgRating = reviewService.getAverageRatingByPlaceId(placeId);

         model.addAttribute("reviews", reviews);
         model.addAttribute("avgRating", avgRating);


         return "detail"; // detail.jsp
     }*/
 
     
    @PostMapping("/{placeId}")
    public String createReview(@PathVariable String placeId,
                               @ModelAttribute ReviewForm reviewForm,
                               BindingResult bindingResult,
                               HttpSession session,
                               @RequestParam String name,
                               @RequestParam String address,
                               @RequestParam(required = false) String phone,
                               @RequestParam(required = false) String image,
                               @RequestParam(required = false, name = "place_url") String placeUrl,
                               Model model,
                               RedirectAttributes redirectAttributes) {
    	System.out.println("세션 loginUser: " + session.getAttribute("loginUser"));
    	System.out.println("reviewForm.rating: " + reviewForm.getRating());

        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/auth/login";
        }
        
        reviewFormValidator.validate(reviewForm, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("placeId", placeId);
            model.addAttribute("userId", loginUser.getUsername());
            model.addAttribute("name", name);
            model.addAttribute("address", address);
            model.addAttribute("phone", phone);
            model.addAttribute("image", image);
            model.addAttribute("place_url", placeUrl);
            model.addAttribute("errors", bindingResult);
            
            return "review";  // 유효성 에러 시 다시 작성 페이지로
        }
        
        Review review = new Review();
        review.setPlaceId(placeId);
        review.setUserId(loginUser.getUsername());
        review.setRating(reviewForm.getRating());
        review.setContent(reviewForm.getContent());

        reviewService.registerReview(review);
        
        /*redirectAttributes.addAttribute("placeId", placeId);
        redirectAttributes.addAttribute("name", name);
        redirectAttributes.addAttribute("address", address);
        if (phone != null) redirectAttributes.addAttribute("phone", phone);
        if (image != null) redirectAttributes.addAttribute("image", image);
        if (placeUrl != null) redirectAttributes.addAttribute("place_url", placeUrl);

        return "redirect:/places/detail";*/
        return "redirect:/places/detail" +
        "?placeId=" + URLEncoder.encode(placeId, StandardCharsets.UTF_8) +
        "&name=" + URLEncoder.encode(name, StandardCharsets.UTF_8) +
        "&address=" + URLEncoder.encode(address, StandardCharsets.UTF_8) +
        (phone != null ? "&phone=" + URLEncoder.encode(phone, StandardCharsets.UTF_8) : "") +
        (image != null ? "&image=" + URLEncoder.encode(image, StandardCharsets.UTF_8) : "") +
        (placeUrl != null ? "&place_url=" + URLEncoder.encode(placeUrl, StandardCharsets.UTF_8) : "");
    }


}
>>>>>>> Stashed changes
