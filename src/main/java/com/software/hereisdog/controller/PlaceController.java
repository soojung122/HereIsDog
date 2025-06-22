package com.software.hereisdog.controller;

import com.software.hereisdog.controller.PlaceForm;
import com.software.hereisdog.service.PlaceService;
import com.software.hereisdog.service.ReviewService;

import jakarta.servlet.http.HttpSession;

//import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.software.hereisdog.domain.Review;
import com.software.hereisdog.domain.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.software.hereisdog.service.PlaceFormValidator;

/**
 * 장소 등록, 조회를 담당하는 컨트롤러
 */
@Controller
@RequestMapping("/places")
public class PlaceController {

    private final PlaceService placeService;
    private final PlaceFormValidator placeFormValidator;
    private ReviewService reviewService;

    @Autowired
    public PlaceController(PlaceService placeService,
    		ReviewService reviewService,
                           PlaceFormValidator placeFormValidator) {
        this.placeService = placeService;
        this.placeFormValidator = placeFormValidator;
        this.reviewService = reviewService;
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
                              Model model,
                              @SessionAttribute(value = "loginUser", required = false) User loginUser) {
        // 수동 검증기 실행
        placeFormValidator.validate(placeForm, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("placeForm", placeForm);
            return "place/createPlaceForm";
        }
        
        if (loginUser == null) {
            return "redirect:/login";
        }

        try {
            placeService.registerMyPlace(placeForm, loginUser.getUsername());
        } catch (IllegalStateException e) {
            bindingResult.reject("place.register.duplicate", e.getMessage());
            model.addAttribute("placeForm", placeForm);
            return "place/createPlaceForm";
        }

        return "redirect:/places";
    }
    
    @GetMapping(value = "/detail", params = {"name", "address"})
    public String showPlaceDetailFromMap(@RequestParam String name,
                                         @RequestParam String address,
                                         @RequestParam(required = false) String phone,
                                         @RequestParam(required = false, name = "place_url") String placeUrl,
                                         @RequestParam(required = false) String image,
                                         @RequestParam(required = false) String type,
                                         HttpSession session,
                                         Model model) {

        
    	PlaceForm place = placeService.getPlaceByNameAndAddress(name, address);
    	System.out.println(">>DB에서 가져온 값 = " + place);

    	Map<String, Object> placeInfo = new HashMap<>();
    	
    	if (phone == null || phone.isBlank()) {
    	    phone = "010-0000-0000";
    	}
    	
        if (place != null) {
            // DB에서 조회된 경우: DB 값 넘김
            model.addAttribute("place", place);
            model.addAttribute("fromDb", true);
            
            placeInfo.put("name", place.getName());
            placeInfo.put("address", place.getAddress());
            placeInfo.put("phone", place.getPhoneNumber());
            // ↓ 여기서 request 파라미터 image, placeUrl 그대로 사용
            placeInfo.put("image", image);
            placeInfo.put("place_url", placeUrl);
            placeInfo.put("hours", place.getOpeningHours());
            placeInfo.put("type", type);
            model.addAttribute("fromDb", true);
            //System.out.println(">> phone: " + phone);
            
        } else {
        	model.addAttribute("fromDb", false);
            model.addAttribute("name", name);
            model.addAttribute("hours", "09:00 ~ 18:00"); 
            model.addAttribute("address", address);
            model.addAttribute("phone", phone);
            model.addAttribute("place_url", placeUrl);
            model.addAttribute("image", image);
            model.addAttribute("type", type);
            
            placeInfo.put("name", name);
            placeInfo.put("address", address);
            placeInfo.put("phone", phone);
            placeInfo.put("image", image);
            placeInfo.put("place_url", placeUrl);
            placeInfo.put("hours", "09:00 ~ 18:00");
            placeInfo.put("type", type);
            model.addAttribute("fromDb", false);

        }
        
        
        // 세션에 저장
        session.setAttribute("placeDetail", placeInfo);
        model.addAllAttributes(placeInfo);
        
        // 5) placeId, reviews 넣기 (기존대로)
        Long pid = (place != null ? place.getId() : null);
        model.addAttribute("placeId", pid);

        String placeName = (place != null) ? place.getName() : name;
        String placeAddress = (place != null) ? place.getAddress() : address;

        List<Review> reviews = (placeName != null && placeAddress != null)
                ? reviewService.getReviewsByPlace(placeName, placeAddress)
                : List.of();
        model.addAttribute("reviews", reviews);

     // 평균 평점 계산
        double averageRating = 0.0;
        if (!reviews.isEmpty()) {
            double sum = 0.0;
            for (Review review : reviews) {
                sum += review.getRating();
            }
            averageRating = Math.round((sum / reviews.size()) * 10.0) / 10.0;  // 소수점 1자리 반올림
        }

        model.addAttribute("averageRating", averageRating);

        return "detail";
    }
    
    @PostMapping("/my")
    @ResponseBody
    public ResponseEntity<String> registerMyPlace(@ModelAttribute PlaceForm placeForm,
                                  @SessionAttribute("loginUser") User user) {
    	try {
            placeForm.setOwnerUsername(user.getUsername());
            placeService.registerMyPlace(placeForm, user.getUsername());
            return ResponseEntity.ok("가게가 등록되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.ok("가게 등록 조건을 만족하지 않습니다.");
        }
    }


    /** detail.jsp → placeId 파라미터로 넘어왔을 때 (세션에서 placeInfo 꺼내 사용) */
   /* @GetMapping("/detail")
    public String showPlaceDetail(@RequestParam Long placeId,
                                  HttpSession session,
                                  Model model) {

        Map<String, Object> placeInfo = (Map<String, Object>) session.getAttribute("placeDetail");
        if (placeInfo == null) {
            return "redirect:/"; // 세션 만료
        }

        // 장소 기본 정보를 모델에 추가
        model.addAllAttributes(placeInfo);
        model.addAttribute("placeId", placeId);
        model.addAttribute("hours", "9:00 ~ 18:00");

        // 리뷰 조회: name, address를 placeInfo에서 추출
        String placeName = (String) placeInfo.get("name");
        String placeAddress = (String) placeInfo.get("address");

        List<Review> reviews = (placeName != null && placeAddress != null)
                ? reviewService.getReviewsByPlace(placeName, placeAddress)
                : List.of();
        model.addAttribute("reviews", reviews);

        return "detail";
    }
*/


    
    // JSON 응답용 필터 API
    @GetMapping("/filter")
    @ResponseBody
    public List<PlaceForm> filterPlaces(@RequestParam("type") String type) {
        return placeService.getPlacesByType(type);
    }


}