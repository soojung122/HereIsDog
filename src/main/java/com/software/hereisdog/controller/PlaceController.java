package com.software.hereisdog.controller;

import com.software.hereisdog.controller.PlaceForm;
import com.software.hereisdog.service.PlaceService;
<<<<<<< Updated upstream
=======
import com.software.hereisdog.service.ReviewService;
import com.software.hereisdog.domain.Review;

import jakarta.servlet.http.HttpSession;

>>>>>>> Stashed changes
//import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.software.hereisdog.service.PlaceFormValidator;

/**
 * 장소 등록, 조회를 담당하는 컨트롤러
 */
@Controller
@RequestMapping("/places")
public class PlaceController {

    private final PlaceService placeService;
    private final PlaceFormValidator placeFormValidator;
    private final ReviewService reviewService;


    @Autowired
    public PlaceController(PlaceService placeService,
    					ReviewService reviewService,
                           PlaceFormValidator placeFormValidator) {
        this.placeService = placeService;
        this.reviewService = reviewService;
        this.placeFormValidator = placeFormValidator;
    }

    /** 장소 목록 조회 */
    @GetMapping
    public String listPlaces(Model model) {
        model.addAttribute("places", placeService.findAll());
        return "place/placeList";
    }

    /** 장소 등록 폼 요청 */
    @GetMapping("/new")
    public String createPlaceForm(Model model) {
        model.addAttribute("placeForm", new PlaceForm());
        return "place/createPlaceForm";
    }

    /** 장소 등록 처리 */
    @PostMapping("/new")
    public String createPlace(@ModelAttribute PlaceForm placeForm,
                              BindingResult bindingResult,
                              Model model) {
        // 수동 검증기 실행
        placeFormValidator.validate(placeForm, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("placeForm", placeForm);
            return "place/createPlaceForm";
        }

        placeService.registerPlace(placeForm);
        return "redirect:/places";
    }
<<<<<<< Updated upstream

=======
    
   /* @GetMapping(value = "/detail", params = {"name", "address"})
    public String showPlaceDetailFromMap(@RequestParam String name,
                                         @RequestParam String address,
                                         @RequestParam(required = false) String phone,
                                         @RequestParam(required = false, name = "place_url") String placeUrl,
                                         @RequestParam(required = false) String image,
                                         HttpSession session,
                                         Model model) {

        Map<String, Object> placeInfo = new HashMap<>();
        placeInfo.put("name", name);
        placeInfo.put("address", address);
        placeInfo.put("phone", phone);
        placeInfo.put("image", (image == null || image.trim().isEmpty()) ? "https://via.placeholder.com/600x300" : image);
        placeInfo.put("place_url", placeUrl);

        session.setAttribute("placeDetail", placeInfo);

        model.addAllAttributes(placeInfo);
        model.addAttribute("hours", "24시간 운영");

        return "detail";
    }

    
   @GetMapping("/detail")
    public String showPlaceDetail(@RequestParam Long placeId,
                                  HttpSession session,
                                  Model model) {
    	System.out.println("loginUser 세션 상태: " + session.getAttribute("loginUser"));
        Map<String, Object> placeInfo = (Map<String, Object>) session.getAttribute("placeDetail");
        if (placeInfo == null) {
            return "redirect:/"; // 세션 만료
        }

        model.addAllAttributes(placeInfo);
        model.addAttribute("placeId", placeId);
        model.addAttribute("hours", "24시간 운영");
        
        //리뷰 content 조회
        List<Review> reviews = reviewService.getReviewsByPlaceId(placeId);
        model.addAttribute("reviews", reviews);

        return "detail";
    }

	*/
    
    @GetMapping(value = "/detail", params = {"name", "address"})
    public String showPlaceDetailFromMap(@RequestParam String name,
                                         @RequestParam String address,
                                         @RequestParam(required = false) String phone,
                                         @RequestParam(required = false, name = "place_url") String placeUrl,
                                         @RequestParam(required = false) String image,
                                         HttpSession session,
                                         Model model) {

        String placeId = name + "_" + address; // ✅ 핵심

        model.addAttribute("placeId", placeId);
        model.addAttribute("name", name);
        model.addAttribute("address", address);
        model.addAttribute("phone", phone);
        model.addAttribute("place_url", placeUrl);
        model.addAttribute("image", (image == null || image.trim().isEmpty()) ? "https://via.placeholder.com/600x300" : image);
        model.addAttribute("hours", "24시간 운영");

        return "detail";
    }

    
    
    @GetMapping("/detail")
    public String showPlaceDetail(@RequestParam String placeId,
    		@RequestParam(required = false) String name,
    		@RequestParam(required = false) String address,
                                  @RequestParam(required = false) String phone,
                                  @RequestParam(required = false) String image,
                                  @RequestParam(required = false, name = "place_url") String placeUrl,
                                  Model model) {
    	// test용 디버그
    	System.out.println("[DEBUG] === showPlaceDetail() 호출됨 ===");
    	
    	// placeId 사용해서 리뷰 가져오기
        List<Review> reviews = reviewService.getReviewsByPlaceId(placeId.trim());
        model.addAttribute("reviews", reviews);
        
        // 테스트용 디버그
        System.out.println("리뷰 리스트 크기: " + reviews.size());
        for (Review r : reviews) {
            System.out.println("리뷰 내용: " + r.getContent());
        }
        
        // 평균 평점
        double avgRating = 0.0;
        if (!reviews.isEmpty()) {
            double sum = 0.0;
            for (Review review : reviews) sum += review.getRating();
            avgRating = Math.round((sum / reviews.size()) * 10.0) / 10.0;
        }
        model.addAttribute("avgRating", avgRating);

        // 기타 필드 유지
        model.addAttribute("placeId", placeId);
        model.addAttribute("name", name);
        model.addAttribute("address", address);
        model.addAttribute("phone", phone);
        model.addAttribute("image", (image == null || image.trim().isEmpty()) ? "https://via.placeholder.com/600x300" : image);
        model.addAttribute("place_url", placeUrl);
        model.addAttribute("hours", "24시간 운영");
        
        
        return "detail";
    }


    
>>>>>>> Stashed changes
    // JSON 응답용 필터 API
    @GetMapping("/filter")
    @ResponseBody
    public List<PlaceForm> filterPlaces(@RequestParam("type") String type) {
        return placeService.getPlacesByType(type);
    }


}