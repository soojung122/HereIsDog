package com.software.hereisdog.controller;

import javax.validation.constraints.NotEmpty;

/**
 * 장소 등록 시 입력받는 데이터
 */
public class PlaceForm {

    @NotEmpty(message = "장소 이름을 입력하세요.")
    private String name;

    @NotEmpty(message = "주소를 입력하세요.")
    private String address;

    @NotEmpty(message = "설명을 입력하세요.")
    private String description;

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
}
