package com.software.hereisdog.controller;

import com.software.hereisdog.controller.PlaceForm;
import com.software.hereisdog.service.PlaceService;

import jakarta.servlet.http.HttpSession;

//import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    
    @GetMapping(value = "/detail", params = {"name", "address"})
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