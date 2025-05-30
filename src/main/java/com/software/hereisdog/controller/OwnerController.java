package com.software.hereisdog.controller;

import com.software.hereisdog.service.OwnerService;
import com.software.hereisdog.domain.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 사업자(Owner) 관련 기능을 담당하는 컨트롤러
 */
@Controller
@RequestMapping("/owner")
public class OwnerController {

	private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    /** 내 장소 목록 조회 */
    @GetMapping("/places")
    public String listMyPlaces(@SessionAttribute(name = "username") String username, Model model) {
        List<Place> places = ownerService.findPlacesByOwner(username);
        model.addAttribute("places", places);
        return "owner/myPlaces";
    }
}
