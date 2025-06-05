package com.software.hereisdog.controller;

import com.software.hereisdog.service.MapService;

import jakarta.servlet.http.HttpSession;

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
    public String showMap(@RequestParam(required = false) String type,
                          Model model,
                          HttpSession session) {

        // ✅ 세션에 로그인 정보가 없으면 로그인 페이지로 리다이렉트
        if (session.getAttribute("loginUser") == null) {
            return "redirect:/auth/login";
        }

        model.addAttribute("type", type);
        model.addAttribute("places", mapService.getAllPlaces());
        return "map";  // → /WEB-INF/jsp/map.jsp 로 렌더링됨
    }

}
