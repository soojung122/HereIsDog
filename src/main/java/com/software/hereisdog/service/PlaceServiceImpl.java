package com.software.hereisdog.service;

import com.software.hereisdog.controller.PlaceForm;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Collections;
import com.software.hereisdog.dao.PlaceDao;
import com.software.hereisdog.domain.Place;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * PlaceService 인터페이스 구현 클래스
 */
@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceDao placeDao;
    
    @Override
    public void registerPlace(PlaceForm placeForm) {
        // TODO: MyBatis DAO를 이용하여 장소 등록
    }

    @Override
    public List<Object> findAll() {
        // TODO: MyBatis DAO를 이용하여 장소 전체 조회
        return Collections.emptyList();
    }

    @Override
    public List<Place> filterPlacesByType(String type) {
        return placeDao.findByType(type);
    }

}
