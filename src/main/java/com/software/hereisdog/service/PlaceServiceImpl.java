package com.software.hereisdog.service;

import com.software.hereisdog.controller.PlaceForm;
import com.software.hereisdog.dao.PlaceDao;
import com.software.hereisdog.domain.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.software.hereisdog.dao.mybatis.mapper.PlaceMapper;


import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * PlaceService 인터페이스 구현 클래스
 */
@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceMapper placeMapper; // PlaceDao → PlaceMapper
    
    public void registerMyPlace(PlaceForm form, String username) {
    	// 1. 이미 이 사용자가 등록한 가게가 있는지 확인
        Place myExistingPlace = placeMapper.selectPlaceByOwner(username);
        if (myExistingPlace != null) {
            throw new IllegalStateException("이미 등록한 가게가 있습니다.");
        }

        // 2. 해당 장소가 이미 다른 오너에 의해 등록된 경우 등록 불가
        Place existing = placeMapper.findByNameAndAddress(form.getName(), form.getAddress());
        if (existing != null) {
            throw new IllegalStateException("이미 다른 사용자가 등록한 가게입니다.");
        }

        // 3. 진짜로 등록 가능한 상태면 insert
        Place newPlace = new Place();
        newPlace.setName(form.getName());
        newPlace.setAddress(form.getAddress());
        newPlace.setDescription(form.getDescription());
        newPlace.setPhoneNumber(form.getPhoneNumber());
        newPlace.setOpeningHours(form.getOpeningHours());
        newPlace.setOwnerUsername(username);

        placeMapper.insert(newPlace);
    }

    @Override
    public List<PlaceForm> findAll() {
        List<Place> places = placeMapper.findAll(); // 이 메서드 mapper에 정의 필요
        List<PlaceForm> result = new ArrayList<>();
        for (Place place : places) {
            PlaceForm form = new PlaceForm();
            form.setName(place.getName());
            form.setAddress(place.getAddress());
            form.setDescription(place.getDescription());
            form.setOwnerUsername(place.getOwnerUsername());
            form.setOpeningHours(place.getOpeningHours());
            form.setPhoneNumber(place.getPhoneNumber());
            result.add(form);
        }
        return result;
    }

    @Override
    public List<PlaceForm> getPlacesByType(String type) {
        List<Place> places = placeMapper.findByType(type); // mapper에 정의 필요
        List<PlaceForm> result = new ArrayList<>();
        for (Place place : places) {
            PlaceForm form = new PlaceForm();
            form.setName(place.getName());
            form.setAddress(place.getAddress());
            form.setDescription(place.getDescription());
            form.setOwnerUsername(place.getOwnerUsername());
            form.setOpeningHours(place.getOpeningHours());
            form.setPhoneNumber(place.getPhoneNumber());
            result.add(form);
        }
        return result;
    }
    
    @Override
    public PlaceForm getPlaceByNameAndAddress(String name, String address) {
        Place place = placeMapper.findByNameAndAddress(name, address);
        if (place == null) return null;

        PlaceForm form = new PlaceForm();
        form.setName(place.getName());
        form.setAddress(place.getAddress());
        form.setDescription(place.getDescription());
        form.setOwnerUsername(place.getOwnerUsername());
        form.setOpeningHours(place.getOpeningHours());
        form.setPhoneNumber(place.getPhoneNumber());
        form.setType(place.getType());
        return form;
    }

}
