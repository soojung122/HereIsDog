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
    public String addFavorite(@RequestParam Long placeId,
                              @RequestParam String name,
                              @RequestParam String address,
                              @SessionAttribute("userId") Long userId) {
        return favoriteService.addFavorite(userId, placeId, name, address); // ✅ 그대로 반환
    }


    @PostMapping("/removeById")
    @ResponseBody
    public String removeFavorite(@RequestParam Long placeId,
                                 @SessionAttribute(name = "userId") Long userId) {
        favoriteService.removeFavorite(userId, placeId);
        return "success";
    }

}
