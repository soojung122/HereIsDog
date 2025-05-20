package com.software.hereisdog.controller;

//import jakarta.validation.constraints.NotEmpty;

/**
 * 장소 등록 및 표시용 DTO (지도 렌더링, REST 응답 포함)
 */
public class PlaceForm {

    private String name;

    private String address;

    private String description;

    // 지도 표시용 필드
    private double lat;    // 위도
    private double lng;    // 경도
    private String type;   // 장소 유형: hospital, cafe, park 등

    // 기본 생성자
    public PlaceForm() {}

    // Getter & Setter
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
}
