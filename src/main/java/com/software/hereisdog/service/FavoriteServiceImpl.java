package com.software.hereisdog.service;

import org.springframework.stereotype.Service;
import com.software.hereisdog.dao.mybatis.mapper.FavoritePlaceMapper;
import com.software.hereisdog.domain.FavoritePlace;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoritePlaceMapper favoritePlaceMapper;

    @Override
    public String addFavorite(Long userId, String name, String address) {
        FavoritePlace existing = favoritePlaceMapper.findByUserIdAndPlace(userId, name, address);

        if (existing == null) {
            FavoritePlace favorite = new FavoritePlace(null, userId, name, address, "Y");
            favoritePlaceMapper.insertFavorite(favorite);
            return "찜 완료!";
        } else if (!"Y".equals(existing.getLiked())) {
            favoritePlaceMapper.setLikedById(existing.getId(), "Y");
            return "찜 완료!";
        } else {
            favoritePlaceMapper.setLikedById(existing.getId(), "N");
            return "찜 취소되었습니다";
        }
    }

    @Override
    public void removeFavorite(Long userId, String name, String address) {
        favoritePlaceMapper.deleteFavorite(userId, name, address);
    }
    
    @Override
    public List<FavoritePlace> findByUserId(Long userId) {
        return favoritePlaceMapper.findByUserId(userId);
    }

}
