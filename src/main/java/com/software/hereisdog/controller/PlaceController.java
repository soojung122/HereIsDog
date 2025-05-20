package com.software.hereisdog.controller;

import com.software.hereisdog.controller.PlaceForm;
import com.software.hereisdog.service.PlaceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 장소 등록, 조회를 담당하는 컨트롤러
 */
@Controller
@RequestMapping("/places")
public class PlaceController {

    private final PlaceService placeService;

    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
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
    public String createPlace(@Valid @ModelAttribute PlaceForm placeForm,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "place/createPlaceForm";
        }
        placeService.registerPlace(placeForm);
        return "redirect:/places";
    }
    
 // JSON 응답용 필터 API
    @GetMapping("/filter")
    @ResponseBody
    public List<PlaceForm> filterPlaces(@RequestParam("type") String type) {
        return placeService.getPlacesByType(type);
    }

}