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
    /*
    @Override
    public List<PlaceForm> getPlacesByType(String type) {
        // api호출확인 임시데이터
        List<PlaceForm> result = new ArrayList<>();

        PlaceForm p1 = new PlaceForm();
        p1.setName("테스트 공원1");
        p1.setAddress("서울시 강남구");
        p1.setDescription("테스트 설명1");

        PlaceForm p2 = new PlaceForm();
        p2.setName("테스트 공원2");
        p2.setAddress("서울시 마포구");
        p2.setDescription("테스트 설명2");

        result.add(p1);
        result.add(p2);

        return result;
    }*/
    
}
