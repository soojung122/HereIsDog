package com.software.hereisdog.controller;

import com.software.hereisdog.controller.PlaceForm;
import com.software.hereisdog.domain.Review;
import com.software.hereisdog.domain.User;
import com.software.hereisdog.service.PlaceFormValidator;
import com.software.hereisdog.service.PlaceService;
import com.software.hereisdog.service.ReviewService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/places")
public class PlaceController {

    private final PlaceService placeService;
    private final PlaceFormValidator placeFormValidator;
    private final ReviewService reviewService;

    @Autowired
    public PlaceController(PlaceService placeService,
                           PlaceFormValidator placeFormValidator,
                           ReviewService reviewService) {
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

    /**
     * 카카오맵(이름+주소)에서 넘어올 때,
     * 혹은 DB에 이미 저장된 장소를 불러올 때 모두 이 메서드가 사용됩니다.
     */
    @GetMapping(value = "/detail", params = {"name", "address"})
    public String showPlaceDetailFromMap(
            @RequestParam String name,
            @RequestParam String address,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false, name = "place_url") String placeUrl,
            @RequestParam(required = false) String image,
            @RequestParam(required = false) String type,
            HttpSession session,
            Model model) {

        // 1) DB에 저장된 place인지 조회
        PlaceForm place = placeService.getPlaceByNameAndAddress(name, address);

        // 2) placeInfo 맵 구성
        Map<String, Object> placeInfo = new HashMap<>();
        if (place != null) {
            placeInfo.put("name", place.getName());
            placeInfo.put("address", place.getAddress());
            placeInfo.put("phone", place.getPhoneNumber());
            // ↓ 여기서 request 파라미터 image, placeUrl 그대로 사용
            placeInfo.put("image", image);
            placeInfo.put("place_url", placeUrl);
            placeInfo.put("hours", place.getOpeningHours());
            model.addAttribute("fromDb", true);
        } else {
            placeInfo.put("name", name);
            placeInfo.put("address", address);
            placeInfo.put("phone", phone);
            placeInfo.put("image", image);
            placeInfo.put("place_url", placeUrl);
            placeInfo.put("hours", "9:00 ~ 18:00");
            placeInfo.put("type", type);
            model.addAttribute("fromDb", false);
        }

        // 3) 세션에 저장
        session.setAttribute("placeDetail", placeInfo);

        // 4) 모델에 일괄 추가
        model.addAllAttributes(placeInfo);

        // 5) placeId, reviews 넣기 (기존대로)
        Long pid = (place != null ? place.getId() : null);
        model.addAttribute("placeId", pid);
        List<Review> reviews = (pid != null)
                ? reviewService.getReviewsByPlaceId(pid)
                : List.of();
        model.addAttribute("reviews", reviews);

        return "detail";
    }


    /** detail.jsp → placeId 파라미터로 넘어왔을 때 (세션에서 placeInfo 꺼내 사용) */
    @GetMapping("/detail")
    public String showPlaceDetail(@RequestParam Long placeId,
                                  HttpSession session,
                                  Model model) {

        Map<String, Object> placeInfo = (Map<String, Object>) session.getAttribute("placeDetail");
        if (placeInfo == null) {
            return "redirect:/"; // 세션 만료 시 홈으로
        }

        model.addAllAttributes(placeInfo);
        model.addAttribute("placeId", placeId);
        model.addAttribute("hours", "24시간 운영");

        // 리뷰 조회 후 모델에 삽입
        List<Review> reviews = reviewService.getReviewsByPlaceId(placeId);
        model.addAttribute("reviews", reviews);

        return "detail";
    }

    /** 소유자용 장소 등록 (AJAX) */
    @PostMapping("/my")
    @ResponseBody
    public ResponseEntity<String> registerMyPlace(@ModelAttribute PlaceForm placeForm,
                                                  @SessionAttribute("loginUser") User user) {
        try {
            placeForm.setOwnerUsername(user.getUsername());
            placeService.registerMyPlace(placeForm, user.getUsername());
            return ResponseEntity.ok("가게가 등록되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("가게 등록 조건을 만족하지 않습니다.");
        }
    }

    /** JSON 응답용 필터 API */
    @GetMapping("/filter")
    @ResponseBody
    public List<PlaceForm> filterPlaces(@RequestParam("type") String type) {
        return placeService.getPlacesByType(type);
    }
}
