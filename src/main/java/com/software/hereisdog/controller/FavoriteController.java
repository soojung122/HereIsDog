package com.software.hereisdog.controller;

import com.software.hereisdog.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 즐겨찾기 추가 및 삭제를 담당하는 컨트롤러
 */
@Controller
@RequestMapping("/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    @Autowired
    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping("/add")
    @ResponseBody
    public String addFavorite(@RequestParam String name,
                              @RequestParam String address,
                              @SessionAttribute("userId") Long userId) {
        return favoriteService.addFavorite(userId, name, address); // ✅ 그대로 반환
    }


    @PostMapping("/removeById")
    @ResponseBody
    public String removeFavorite(@RequestParam Long id,
                                 @SessionAttribute(name = "userId") Long userId) {
        // userId를 확인하려면 추가 검증 로직 가능 (optional)
        favoriteService.removeFavoriteById(id);
        return "success";
    }
}
