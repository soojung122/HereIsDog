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

    /** 즐겨찾기 추가 */
    @PostMapping("/{placeId}")
    public String addFavorite(@PathVariable Long placeId, @SessionAttribute(name = "username") String username) {
        favoriteService.addFavorite(username, placeId);
        return "redirect:/places";
    }

    /** 즐겨찾기 삭제 */
    @DeleteMapping("/{placeId}")
    public String removeFavorite(@PathVariable Long placeId, @SessionAttribute(name = "username") String username) {
        favoriteService.removeFavorite(username, placeId);
        return "redirect:/favorites";
    }
}
