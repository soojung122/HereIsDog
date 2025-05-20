package com.software.hereisdog.service;

import org.springframework.stereotype.Service;
import com.software.hereisdog.dao.FavoritePlaceDAO;
import com.software.hereisdog.domain.FavoritePlace;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * FavoriteService 인터페이스 구현 클래스
 */
@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoritePlaceDAO favoritePlaceDAO;

    @Override
    public void addFavorite(Long userId, Long placeId) {
        FavoritePlace favorite = new FavoritePlace(null, userId, placeId);
        favoritePlaceDAO.insertFavorite(favorite);
    }

    @Override
    public void removeFavorite(Long userId, Long placeId) {
        favoritePlaceDAO.deleteFavorite(userId, placeId);
    }
}
