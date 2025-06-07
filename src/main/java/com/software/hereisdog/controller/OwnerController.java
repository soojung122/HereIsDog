package com.software.hereisdog.controller;

import com.software.hereisdog.service.OwnerService;
import com.software.hereisdog.domain.Owner;
import com.software.hereisdog.domain.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import com.software.hereisdog.domain.User;


@Controller
@RequestMapping("/owner")
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    /** 오너 마이페이지(한 가게만 shop 변수로 전달) */
    @GetMapping("")
    public String ownerMypage(HttpSession session, Model model) {
        // 세션에서 User 객체 꺼내오기 (Owner 아니어도 OK)
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/auth/login";
        }

        // businessNumber로 오너임을 확인
        String businessNumber = loginUser.getBusinessNumber();
        if (businessNumber == null || businessNumber.isEmpty()) {
            // 사업자 번호 없으면 일반 사용자로 리디렉트
            return "redirect:/user";
        }

        // 오너 username으로 가게 조회
        String username = loginUser.getUsername();
        List<Place> places = ownerService.findPlacesByOwner(username);

        // 한 가게만 보여주고 싶으면 (첫 번째 가게만)
        Place shop = places.isEmpty() ? null : places.get(0);
        model.addAttribute("shop", shop);

        // 오너 정보도 같이 전달 (User 객체지만 JSP에서 owner.nickname 등 사용 가능)
        model.addAttribute("owner", loginUser);

        return "owner";
    }
   

}
