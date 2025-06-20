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

    @Override
    public void registerPlace(PlaceForm placeForm) {
        Place place = new Place();
        place.setName(placeForm.getName());
        place.setAddress(placeForm.getAddress());
        place.setDescription(placeForm.getDescription());
        place.setOwnerUsername(placeForm.getOwnerUsername());
        place.setOpeningHours(placeForm.getOpeningHours());
        place.setPhoneNumber(placeForm.getPhoneNumber());

        Place existing = placeMapper.selectPlaceByOwner(place.getOwnerUsername());
        if (existing == null) {
            placeMapper.insertPlace(place);
        } else {
            placeMapper.updatePlace(place);
        }
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
}
