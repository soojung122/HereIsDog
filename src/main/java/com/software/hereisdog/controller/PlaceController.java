package com.software.hereisdog.controller;

import com.software.hereisdog.controller.PlaceForm;
import com.software.hereisdog.service.PlaceService;
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
    
    @GetMapping("/detail")
    public String showPlaceDetail(@RequestParam String name,
                                  @RequestParam String address,
                                  @RequestParam(required = false) String phone,
                                  @RequestParam(required = false) String image,
                                  @RequestParam(required = false, name = "place_url") String placeUrl,
                                  Model model) {

        model.addAttribute("name", name);
        model.addAttribute("address", address);
        model.addAttribute("phone", phone);

        // 기본값 처리
        if (image == null || image.trim().isEmpty()) {
            image = "https://via.placeholder.com/600x300";
        }
        if (placeUrl == null || placeUrl.trim().isEmpty()) {
            placeUrl = null;
        }

        model.addAttribute("image", image);
        model.addAttribute("place_url", placeUrl);
        model.addAttribute("hours", "24시간 운영"); // 예시값

        return "detail"; // /WEB-INF/jsp/detail.jsp 로 이동
    }
    
    // JSON 응답용 필터 API
    @GetMapping("/filter")
    @ResponseBody
    public List<PlaceForm> filterPlaces(@RequestParam("type") String type) {
        return placeService.getPlacesByType(type);
    }


}