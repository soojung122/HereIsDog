package com.software.hereisdog.service;

/**
 * 즐겨찾기 추가 및 삭제 비즈니스 로직을 처리하는 서비스 인터페이스
 */
public interface FavoriteService {
    String addFavorite(Long userId, Long placeId, String name, String address);
    void removeFavoriteById(Long id);
}
