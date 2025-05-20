package com.software.hereisdog.dao;
import com.software.hereisdog.domain.FavoritePlace;
import java.util.List;

public interface FavoritePlaceDAO {
    List<FavoritePlace> findByUserId(Long userId);

    void insertFavorite(FavoritePlace favoritePlace);
    void deleteFavorite(Long userId, Long placeId);
}