package com.software.hereisdog.service;

import com.software.hereisdog.controller.PlaceForm;
import com.software.hereisdog.dao.PlaceDao;
import com.software.hereisdog.domain.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * PlaceService 인터페이스 구현 클래스
 */
@Service
public class PlaceServiceImpl implements PlaceService {

	@Autowired
    private PlaceDao placeDao;

    @Override
    public void registerPlace(PlaceForm placeForm) {
        // PlaceForm → Place 변환 후 DAO로 전달 (직접 변환 예시)
        Place place = new Place(null, placeForm.getName(), placeForm.getAddress(), placeForm.getDescription());
        // placeDao.insertPlace(place); // 실제 구현 시 등록
    }

    @Override
    public List<PlaceForm> findAll() {
        List<Place> places = placeDao.findAll();
        List<PlaceForm> result = new ArrayList<>();
        for (Place place : places) {
            PlaceForm form = new PlaceForm();
            form.setName(place.getName());
            form.setAddress(place.getAddress());
            form.setDescription(place.getDescription());
            result.add(form);
        }
        return result;
    }

    
    @Override
    public List<PlaceForm> getPlacesByType(String type) {
        List<Place> places = placeDao.findByType(type);
        List<PlaceForm> result = new ArrayList<>();
        for (Place place : places) {
            PlaceForm form = new PlaceForm();
            form.setName(place.getName());
            form.setAddress(place.getAddress());
            form.setDescription(place.getDescription());
            result.add(form);
        }
        return result;
    }
    
}
