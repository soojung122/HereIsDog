package com.software.hereisdog.service;

import com.software.hereisdog.controller.PlaceForm;
import com.software.hereisdog.domain.Place;

import java.util.List;

/**
 * 장소 등록 및 조회 비즈니스 로직을 처리하는 서비스 인터페이스
 */
public interface PlaceService {
    List<PlaceForm> findAll(); // DTO로 반환(프론트 친화적)
    List<PlaceForm> getPlacesByType(String type);
    public PlaceForm getPlaceByNameAndAddress(String name, String address);
    void registerMyPlace(PlaceForm form, String username);
}
