package com.software.hereisdog.service;

import org.springframework.stereotype.Service;

/**
 * FavoriteService 인터페이스 구현 클래스
 */
@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Override
    public void addFavorite(String username, Long placeId) {
        // TODO: MyBatis DAO를 이용하여 즐겨찾기 추가
    }

    @Override
    public void removeFavorite(String username, Long placeId) {
        // TODO: MyBatis DAO를 이용하여 즐겨찾기 삭제
    }
}
