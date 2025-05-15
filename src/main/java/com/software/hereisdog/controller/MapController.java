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
public class MapController {
    
    private final MapService mapService;

    @Autowired
    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @GetMapping("/map")
    public String showMap(@RequestParam(required = false) String type, Model model) {
        model.addAttribute("type", type);
        model.addAttribute("places", mapService.getAllPlaces());
        return "map";  // → /WEB-INF/jsp/map.jsp 로 렌더링됨
    }

}
