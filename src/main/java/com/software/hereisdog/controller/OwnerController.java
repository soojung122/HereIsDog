package com.software.hereisdog.controller;

import com.software.hereisdog.service.OwnerService;
import com.software.hereisdog.service.PlaceService;

import jakarta.servlet.http.HttpSession;

import com.software.hereisdog.domain.Place;
import com.software.hereisdog.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 사업자(Owner) 관련 기능을 담당하는 컨트롤러
 */
@Controller
@RequestMapping("/mypage/owner")
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    /** 내 장소 단일 조회 */
    @GetMapping("")
    public String getMyPlace(HttpSession session, Model model) {
        Object loginUser = session.getAttribute("loginUser");

        if (!(loginUser instanceof User user)) {
            return "redirect:/auth/login"; // 세션에 사용자 없으면 로그인으로
        }

        String username = user.getUsername(); // ✅ 이렇게 안전하게 꺼냄
        Place place = ownerService.getPlaceByOwner(username);
        System.out.println(place);
        model.addAttribute("shop", place);
        model.addAttribute("owner", user); // 필요 시 사용자 정보도 전달

        return "owner"; // /WEB-INF/jsp/owner.jsp
    }
    
    @PostMapping("/update")
    @ResponseBody
    public String updatePlace(@ModelAttribute Place place, HttpSession session) {
        System.out.println("진입");
    	String username = ((User) session.getAttribute("loginUser")).getUsername();
        if (username == null) {
        	System.out.println("세션에 loginUser 없음");
            return "fail";  // 로그인 안 되어 있으면 실패
        }
        
        place.setOwnerUsername(username);  
        ownerService.updatePlace(place);   

        return "success";  
    }

}
