package com.software.hereisdog.domain;

/**
 * 사용자가 즐겨찾는 장소 정보를 저장하는 클래스
 */
public class FavoritePlace {
    private Long id;
    private Long userId;   // 즐겨찾기한 사용자 ID
    private Long placeId;  // 즐겨찾기된 장소 ID

    public FavoritePlace() {}

    public FavoritePlace(Long id, Long userId, Long placeId) {
        this.id = id;
        this.userId = userId;
        this.placeId = placeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }
}
