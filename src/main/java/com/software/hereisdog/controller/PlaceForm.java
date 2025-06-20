package com.software.hereisdog.controller;

//import jakarta.validation.constraints.NotEmpty;

/**
 * 장소 등록 및 표시용 DTO (지도 렌더링, REST 응답 포함)
 */
public class PlaceForm {
	
	private Long placeId;  // 장소 식별용 ID
	
    private String name;

    private String address;

    private String description;
    private String ownerUsername;
    private String openingHours;
    private String phoneNumber;


    // 지도 표시용 필드
    private double lat;    // 위도
    private double lng;    // 경도
    private String type;   // 장소 유형: hospital, cafe, park 등

    // 기본 생성자
    public PlaceForm() {}

    // Getter & Setter
    public Long getId() { return placeId; }
    public void setId(Long placeId) { this.placeId = placeId; }
    
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
