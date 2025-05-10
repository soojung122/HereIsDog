package com.software.hereisdog.controller;

import com.software.hereisdog.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("places", ownerService.findPlacesByOwner(username));
        return "owner/myPlaces";
    }
}
