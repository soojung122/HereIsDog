package com.software.hereisdog.controller;

import com.software.hereisdog.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 지도 관련 기능을 처리하는 컨트롤러
 */
@Controller
@RequestMapping("/map")
public class MapController {

    private final MapService mapService;

    @Autowired
    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    /** 지도에 장소 표시 요청 */
    @GetMapping
    public String showMap(Model model) {
        model.addAttribute("places", mapService.getAllPlaces());
        return "map/showMap";
    }
}
