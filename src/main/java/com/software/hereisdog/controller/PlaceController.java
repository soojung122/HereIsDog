package com.software.hereisdog.controller;

import com.software.hereisdog.controller.PlaceForm;
import com.software.hereisdog.service.PlaceService;

import jakarta.servlet.http.HttpSession;

//import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.software.hereisdog.domain.User;
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

    @Autowired
    public PlaceController(PlaceService placeService,
                           PlaceFormValidator placeFormValidator) {
        this.placeService = placeService;
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

        
    	PlaceForm place = placeService.getPlaceByAddress(address);
    	System.out.println(">>DB에서 가져온 값 = " + place);

        if (place != null) {
            // DB에서 조회된 경우: DB 값 넘김
            model.addAttribute("place", place);
            model.addAttribute("fromDb", true);
        } else {
        	model.addAttribute("fromDb", false);
            model.addAttribute("name", name);
            model.addAttribute("hours", "9:00 ~ 18:00"); 
            model.addAttribute("address", address);
            model.addAttribute("phone", phone);
            model.addAttribute("place_url", placeUrl);
            model.addAttribute("image", image);
            model.addAttribute("type", type);
        }


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


    
    @GetMapping("/detail")
    public String showPlaceDetail(@RequestParam Long placeId,
                                  HttpSession session,
                                  Model model) {

        Map<String, Object> placeInfo = (Map<String, Object>) session.getAttribute("placeDetail");
        if (placeInfo == null) {
            return "redirect:/"; // 세션 만료
        }

        model.addAllAttributes(placeInfo);
        model.addAttribute("placeId", placeId);
        model.addAttribute("hours", "24시간 운영");

        return "detail";
    }



    
    // JSON 응답용 필터 API
    @GetMapping("/filter")
    @ResponseBody
    public List<PlaceForm> filterPlaces(@RequestParam("type") String type) {
        return placeService.getPlacesByType(type);
    }


}