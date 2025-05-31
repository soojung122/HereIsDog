package com.software.hereisdog.domain;

/**
 * 사용자가 즐겨찾는 장소 정보를 저장하는 클래스
 */
public class FavoritePlace {
    private Long id;
    private Long userId;   // 즐겨찾기한 사용자 ID
    private String name;       // 장소 이름
    private String address;    // 장소 주소
    private String liked;     // 찜 여부

    public FavoritePlace() {}

    public FavoritePlace(Long id, Long userId, String name, String address, String liked) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.address = address;
        this.liked = liked;
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
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLiked(String liked) {
        this.liked = liked;
    }
    
    public String getLiked() {
        return liked;
    }
}
