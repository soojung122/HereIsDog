package com.software.hereisdog.controller; 

import com.software.hereisdog.service.OwnerService;
import com.software.hereisdog.domain.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/owner")
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    /** 내 장소 목록 전체 보기 (기존) */
//    @GetMapping("/places")
//    public String listMyPlaces(@SessionAttribute(name = "username") String username, Model model) {
//        List<Place> places = ownerService.findPlacesByOwner(username);
//        model.addAttribute("places", places);
//        return "owner/myPlaces";
//    }

    /** 내 마이페이지(한 가게만 shop 변수로 전달) */
    @GetMapping("")
    public String ownerMypage(@SessionAttribute(name = "username") String username, Model model) {
        List<Place> places = ownerService.findPlacesByOwner(username);

        // 한 가게만 보여주고 싶으면 (첫 번째 가게만)
        Place shop = places.isEmpty() ? null : places.get(0);
        model.addAttribute("shop", shop);

        return "owner"; // owner.jsp
    }
}
